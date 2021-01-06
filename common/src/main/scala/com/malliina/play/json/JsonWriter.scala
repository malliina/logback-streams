package com.malliina.play.json

import play.api.libs.json.{JsValue, Json, Writes}

class JsonWriter[T, U](write: T => U)(implicit tjs: Writes[U]) extends Writes[T] {
  override def writes(o: T): JsValue = Json.toJson(write(o))
}
