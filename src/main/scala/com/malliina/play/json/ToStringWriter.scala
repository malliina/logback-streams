package com.malliina.play.json

/**
 * @author Michael
 */
class ToStringWriter[T] extends JsonWriter[T, String](_.toString)
