package com.malliina.play.json

class ToStringWriter[T] extends JsonWriter[T, String](_.toString)
