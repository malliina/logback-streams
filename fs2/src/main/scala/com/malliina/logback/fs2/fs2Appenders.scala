package com.malliina.logback.fs2

import cats.effect.{Concurrent, IO}
import ch.qos.logback.core.AppenderBase
import fs2.Stream
import fs2.concurrent.{SignallingRef, Topic}

class FS2IOAppender[E]()(implicit c: Concurrent[IO]) extends FS2Appender[E] {
  override def append(eventObject: E): Unit =
    topic.publish1(Option(eventObject)).unsafeRunAsyncAndForget()
}

abstract class FS2Appender[E](implicit c: Concurrent[IO]) extends AppenderBase[E] {
  val topic = Topic[IO, Option[E]](None).unsafeRunSync()
  val signal = SignallingRef[IO, Boolean](false).unsafeRunSync()
  val source: Stream[IO, E] = topic
    .subscribe(maxQueued = 10)
    .drop(1)
    .flatMap(opt => opt.map(e => Stream(e)).getOrElse(Stream.empty))
    .interruptWhen(signal)

  def close(): Unit = signal.set(true).unsafeRunSync()
}
