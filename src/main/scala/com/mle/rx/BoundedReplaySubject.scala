package com.mle.rx

import rx.operators.OperationReplay.CustomReplaySubject
import rx.lang.scala.Subject

/**
 * I don't have the competence to claim that this is actually proper, but for now I use it.
 *
 * @author mle
 */
object BoundedReplaySubject {
  def apply[T](n: Int): BoundedReplaySubject[T] =
    new BoundedReplaySubject[T](CustomReplaySubject.create(n))
}

class BoundedReplaySubject[T](val asJavaSubject: rx.operators.OperationReplay.CustomReplaySubject[T, T, T])
  extends Subject[T]
