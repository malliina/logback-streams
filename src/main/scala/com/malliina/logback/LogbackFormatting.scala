package com.malliina.logback

import ch.qos.logback.core.{AppenderBase, CoreConstants}

object LogbackFormatting extends LogbackFormatting

trait LogbackFormatting {
  val defaultFormatter = new TimeFormatter(CoreConstants.ISO8601_PATTERN)

  def defaultFormat(time: Long): String = {
    defaultFormatter format time
  }

  trait TimeFormatting[E] extends AppenderBase[E] {
    private var formatter: TimeFormatter = LogbackFormatting.defaultFormatter

    def getTimeFormat: String = formatter.timeFormat

    def setTimeFormat(format: String): Unit = {
      formatter = new TimeFormatter(format)
      addInfo(s"Time format set to ${formatter.timeFormat}")
    }

    def format(timeStamp: Long) = formatter.format(timeStamp)
  }
}
