package com.malliina.logback

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.spi.{ILoggingEvent, IThrowableProxy, StackTraceElementProxy}
import com.malliina.play.json.ToStringWriter
import play.api.libs.json._

case class LogEvent(
  timestamp: Long,
  timeFormatted: String,
  message: String,
  loggerName: String,
  threadName: String,
  level: Level,
  stackTrace: Option[String]
) {

  val hasStackTrace = stackTrace.nonEmpty
}

object LogEvent {
  private def lineSep = "\n\t" // sys.props.get("line.separator") getOrElse "\n"

  def fromLogbackEvent(e: ILoggingEvent, timeFormatter: Long => String): LogEvent = {
    val stackTrace = buildStackTrace(e).map(_ mkString lineSep)
    LogEvent(
      e.getTimeStamp,
      timeFormatter(e.getTimeStamp),
      e.getMessage,
      e.getLoggerName,
      e.getThreadName,
      e.getLevel,
      stackTrace
    )
  }

  def buildStackTrace(e: ILoggingEvent): Option[Seq[String]] = {
    val exOpt = Option(e.getThrowableProxy)
    for {
      ex <- exOpt
      trace <- Option(ex.getStackTraceElementProxyArray) if trace.nonEmpty
    } yield s"${ex.getClassName}: ${ex.getMessage}" :: trace.map(_.toString).toList
  }

  implicit object LevelFormat extends Format[Level] {
    override def writes(o: Level): JsValue =
      Json.toJson(o.levelStr)

    override def reads(json: JsValue): JsResult[Level] =
      json.validate[String].map(name => Level.toLevel(name))
  }

  implicit object ThrowableProxyWriter extends ToStringWriter[IThrowableProxy]

  implicit object StackElementWriter extends ToStringWriter[StackTraceElementProxy]

  implicit val format = Json.format[LogEvent]
}
