package com.malliina.rx

import rx.lang.scala.Subject

/**
 * I don't have the competence to claim that this is actually proper, but for now I use it.
 *
 * @author mle
 */
object BoundedReplaySubject {
  def apply[T](n: Int): BoundedReplaySubject[T] =
    new BoundedReplaySubject[T](rx.subjects.ReplaySubject.createWithSize(n))
}

class BoundedReplaySubject[T](val asJavaSubject: rx.subjects.ReplaySubject[T]) extends Subject[T]
