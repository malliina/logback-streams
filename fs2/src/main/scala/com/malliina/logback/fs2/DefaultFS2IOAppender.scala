package com.malliina.logback.fs2

import cats.effect.{ContextShift, IO}
import ch.qos.logback.classic.spi.ILoggingEvent
import com.malliina.logback.fs2.DefaultFS2IOAppender.contextShift
import com.malliina.logback.{LogEvent, TimeFormatting}
import fs2.Stream

import scala.concurrent.ExecutionContext

object DefaultFS2IOAppender {
  implicit val contextShift: ContextShift[IO] = IO.contextShift(ExecutionContext.global)

  def apply(): DefaultFS2IOAppender = new DefaultFS2IOAppender()
}

class DefaultFS2IOAppender extends FS2IOAppender[ILoggingEvent] with TimeFormatting[ILoggingEvent] {
  val logEvents: Stream[IO, LogEvent] =
    source.map(e => LogEvent.fromLogbackEvent(e, format))
}
