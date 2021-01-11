package com.malliina.logback.fs2

import cats.effect.IO
import com.malliina.logback.LogbackUtils
import munit.FunSuite
import org.slf4j.LoggerFactory

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import fs2.Stream

class FS2IOAppenderTests extends FunSuite {
  val log = LoggerFactory.getLogger(getClass)

  test("Stream.toList") {
    val value = Stream.emit(1).covary[IO].compile.toList.unsafeRunSync().head
    assertEquals(value, 1)
  }

  test("hi") {
    val appender = DefaultFS2IOAppender()
    LogbackUtils.installAppender(appender)

    val f = appender.logEvents
      .take(2)
      .compile
      .toVector
      .unsafeToFuture()
    val firstMessage = "What"
    log.info(firstMessage)
    log.info("Yes!")
    val events = await(f)
    assertEquals(events.size, 2)
    assertEquals(events.head.message, firstMessage)
  }

  def await[T](f: Future[T]): T = Await.result(f, 3.seconds)
}
