package com.malliina.logback.akka

import akka.NotUsed
import akka.actor.{ActorSystem, PoisonPill}
import akka.actor.Status.Success
import akka.stream.{ActorMaterializer, Materializer, OverflowStrategy}
import akka.stream.scaladsl.{BroadcastHub, Keep, Source}
import ch.qos.logback.core.AppenderBase

object AkkaAppender {
  def apply[E](as: ActorSystem) = new AkkaAppender[E](as)
}

/** A Logback appender that makes log events available in `source` which is an Akka Streams Source.
  *
  * Up to 1024 events are buffered if there is no downstream demand. If this buffer is exceeded, the oldest events
  * are dropped first.
  *
  * @tparam E type of log event, typically ILoggingEvent
  */
class AkkaAppender[E](val as: ActorSystem) extends AppenderBase[E] {
  def this(actorSystemName: String) = this(ActorSystem(actorSystemName))

  implicit val mat: Materializer = ActorMaterializer()(as)

  private val producer =
    Source.actorRef[E](bufferSize = 1024, OverflowStrategy.dropHead)
  private val runnable =
    producer.toMat(BroadcastHub.sink(bufferSize = 1024))(Keep.both)
  private val (subject, src) = runnable.run()
  val source: Source[E, NotUsed] = src

  override def append(eventObject: E): Unit = subject ! eventObject

  def close(): Unit = subject ! Success(())

  def closeImmediately(): Unit = subject ! PoisonPill
}
