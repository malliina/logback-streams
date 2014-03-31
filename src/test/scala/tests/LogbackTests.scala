package tests

import org.scalatest.FunSuite
import com.mle.logbackrx.{PublishRxAppender, LogbackUtils}
import ch.qos.logback.classic.spi.ILoggingEvent
import org.slf4j.LoggerFactory

/**
 *
 * @author mle
 */
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
    Thread.sleep(100)
    s.unsubscribe()
    assert(emittedMessage.exists(_ == actualMessage))
  }
}
