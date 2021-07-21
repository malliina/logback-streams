package com.malliina.logback

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.spi.{ILoggingEvent, IThrowableProxy, StackTraceElementProxy}
import io.circe._
import io.circe.generic.semiauto._

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

  implicit val levelCodec: Codec[Level] = Codec.from(
    Decoder.decodeString.map(s => Level.toLevel(s)),
    Encoder.encodeString.contramap(l => l.levelStr)
  )
  implicit val throwableProxyEncoder: Encoder[IThrowableProxy] =
    Encoder.encodeString.contramap(itp => itp.toString)
  implicit val stackElementEncoder: Encoder[StackTraceElementProxy] =
    Encoder.encodeString.contramap(step => step.toString)

  implicit val format: Codec[LogEvent] = deriveCodec[LogEvent]
}
