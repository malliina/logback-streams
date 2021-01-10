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

  test("streams") {
    val hm = Stream.emit(1).covary[IO].compile.toList.unsafeRunSync().head
    println(hm)
  }

  test("hi") {
    val appender = DefaultFS2IOAppender()
    LogbackUtils.installAppender(appender)

    val f = appender.logEvents
      .map { e =>
        println(s"$e")
      }
      .take(1)
      .compile
      .toVector
      .unsafeToFuture()
    log.info("What")
    await(f)
  }

  def await[T](f: Future[T]): T = Await.result(f, 3.seconds)
}
