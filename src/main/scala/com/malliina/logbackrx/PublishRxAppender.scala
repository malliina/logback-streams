package com.malliina.logbackrx

import ch.qos.logback.classic.spi.ILoggingEvent
import com.malliina.logbackrx.RxLogback.{EventMapping, RxAppenderBase}
import rx.lang.scala.Subject

class PublishRxAppender[E] extends RxAppenderBase[E] {
  override protected val subject: Subject[E] = Subject[E]()
}

class BasicPublishRxAppender extends PublishRxAppender[ILoggingEvent] with EventMapping
