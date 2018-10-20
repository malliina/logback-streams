package com.malliina.logback.akka

import akka.actor.ActorSystem
import ch.qos.logback.classic.spi.ILoggingEvent
import com.malliina.logbackrx.LogbackUtils
import org.scalatest.FunSuite
import org.slf4j.LoggerFactory

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future, Promise}

class AkkaAppenderTests extends FunSuite {
  val log = LoggerFactory.getLogger(getClass)

  test("log to an akka streams Source") {
    val as = ActorSystem("test")
    val appender = AkkaAppender[ILoggingEvent](as)
    implicit val mat = appender.mat
    LogbackUtils.installAppender(appender)
    log.info("test0")
    val run1 = appender.source.runFold[List[String]](Nil)((acc, e) => e.getMessage :: acc)
    log.info("test1")
    sleep()
    log.info("test2")
    sleep()
    val run2 = appender.source.runFold[List[String]](Nil)((acc, e) => e.getMessage :: acc)
    sleep()
    log.info("test3")
    sleep()
    val never = Promise[String]()
    appender.source.runForeach { e =>
      never.success(e.getMessage)
    }
    sleep()
    appender.close()
    assert(await(run1) === List("test3", "test2", "test1", "test0"))
    assert(await(run2) === List("test3"))
    assert(!never.isCompleted)
  }

  def sleep(): Unit = Thread.sleep(100)

  def await[T](f: Future[T]): T = Await.result(f, 2.seconds)
}
