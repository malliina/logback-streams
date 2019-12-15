package com.malliina.logback.akka

import akka.NotUsed
import akka.stream.scaladsl.Source
import ch.qos.logback.classic.spi.ILoggingEvent
import com.malliina.logback.LogEvent
import com.malliina.logback.LogbackFormatting.TimeFormatting

class DefaultAkkaAppender
    extends AkkaAppender[ILoggingEvent]("AkkaAppender")
    with AkkaEventMapping

// TODO Evaluate whether this API makes sense
trait AkkaEventMapping
    extends AkkaAppender[ILoggingEvent]
    with TimeFormatting[ILoggingEvent] {
  val logEvents: Source[LogEvent, NotUsed] =
    source.map(e => LogEvent.fromLogbackEvent(e, format))
}
