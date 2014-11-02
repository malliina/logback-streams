package com.mle.play.json

/**
 * @author Michael
 */
class ToStringWriter[T] extends JsonWriter[T, String](_.toString)
