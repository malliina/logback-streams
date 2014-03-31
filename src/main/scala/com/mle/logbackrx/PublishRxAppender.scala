package com.mle.logbackrx

import rx.lang.scala.Subject
import com.mle.logbackrx.RxLogback._
import ch.qos.logback.classic.spi.ILoggingEvent

/**
 *
 * @author mle
 */
class PublishRxAppender[E] extends RxAppenderBase[E] {
  override protected val subject: Subject[E] = Subject[E]()
}

class BasicPublishRxAppender
  extends PublishRxAppender[ILoggingEvent]
  with EventMapping