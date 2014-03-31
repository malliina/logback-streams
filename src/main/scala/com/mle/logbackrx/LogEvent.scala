package com.mle.logbackrx

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.spi.ILoggingEvent
import play.api.libs.json.{JsResult, Json, JsValue, Format}

/**
 *
 * @author mle
 */
case class LogEvent(timeStamp: Long, timeFormatted: String, message: String, loggerName: String, threadName: String, level: Level)

object LogEvent {
  def fromLogbackEvent(e: ILoggingEvent, timeFormatter: Long => String): LogEvent =
    LogEvent(e.getTimeStamp, timeFormatter(e.getTimeStamp), e.getMessage, e.getLoggerName, e.getThreadName, e.getLevel)

  implicit object LevelFormat extends Format[Level] {
    override def writes(o: Level): JsValue =
      Json.toJson(o.levelStr)

    override def reads(json: JsValue): JsResult[Level] =
      json.validate[String].map(name => Level.toLevel(name))
  }

  implicit val format = Json.format[LogEvent]
}