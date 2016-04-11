package tests

import ch.qos.logback.classic.spi.ILoggingEvent
import com.malliina.logbackrx.{LogEvent, LogbackUtils, PublishRxAppender, RxLogback}
import org.scalatest.FunSuite
import org.slf4j.LoggerFactory

class LogbackTests extends FunSuite {
  val log = LoggerFactory.getLogger(getClass)

  test("can log to rx") {
    val actualMessage = "hello, world"
    var emittedMessage: Option[String] = None
    val appender = new PublishRxAppender[ILoggingEvent]
    val s = appender.events.subscribe(e => {
      emittedMessage = Some(e.getMessage)
    })
    LogbackUtils.installAppender(appender)
    log info actualMessage
    s.unsubscribe()
    assert(emittedMessage contains actualMessage)
  }

  test("stacktrace") {
    val testException = new TestException
    val expectedMsg = "Failure!"
    var actualEvent: Option[ILoggingEvent] = None
    val appender = new PublishRxAppender[ILoggingEvent]
    val s = appender.events.subscribe(e => {
      actualEvent = Some(e)
    })
    LogbackUtils.installAppender(appender)
    log.error(expectedMsg, testException)
    s.unsubscribe()
    val stackTraceOpt = actualEvent.flatMap(ile => LogEvent.fromLogbackEvent(ile, RxLogback.defaultFormat).stackTrace)
    assert(stackTraceOpt.exists(_ contains testException.getClass.getName))
  }

  class TestException extends Exception("test")

}
