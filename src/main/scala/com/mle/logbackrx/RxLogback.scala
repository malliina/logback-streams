package com.mle.logbackrx

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.{AppenderBase, CoreConstants}
import rx.lang.scala.{Observable, Observer, Subject}

/**
 *
 * @author mle
 */
object RxLogback {
  val defaultFormatter = new TimeFormatter(CoreConstants.ISO8601_PATTERN)

  def defaultFormat(time: Long): String = {
    defaultFormatter format time
  }

  /**
   * A Logback appender that provides an [[Observable]] of log events.
   *
   * @author mle
   */
  trait RxAppenderBase[E] extends AppenderBase[E] {
    protected def subject: Subject[E]

    protected def observer: Observer[E] = subject

    def events: Observable[E] = subject

    override def append(event: E): Unit = observer onNext event
  }

  trait TimeFormatting[E] extends AppenderBase[E] {
    private var formatter = new TimeFormatter(CoreConstants.ISO8601_PATTERN)

    def getTimeFormat: String = formatter.timeFormat

    def setTimeFormat(format: String): Unit = {
      formatter = new TimeFormatter(format)
      addInfo(s"Time format set to ${formatter.timeFormat}")
    }

    def format(timeStamp: Long) = formatter.format(timeStamp)
  }

  trait EventMapping extends RxAppenderBase[ILoggingEvent] with TimeFormatting[ILoggingEvent] {
    lazy val logEvents = events.map(e => LogEvent.fromLogbackEvent(e, format))
  }


}