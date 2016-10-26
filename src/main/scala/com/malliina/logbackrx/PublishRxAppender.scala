package com.malliina.logbackrx

import ch.qos.logback.classic.spi.ILoggingEvent
import com.malliina.logbackrx.RxLogback.{EventMapping, RxAppenderBase}
import rx.lang.scala.Subject
import rx.lang.scala.subjects.SerializedSubject

class PublishRxAppender[E] extends RxAppenderBase[E] {
  override protected val subject: SerializedSubject[E] = Subject[E]().toSerialized
}

class BasicPublishRxAppender extends PublishRxAppender[ILoggingEvent] with EventMapping
