package com.malliina.logbackrx

import ch.qos.logback.core.{AppenderBase, CoreConstants}

object RxLogback {
  val defaultFormatter = new TimeFormatter(CoreConstants.ISO8601_PATTERN)

  def defaultFormat(time: Long): String = {
    defaultFormatter format time
  }

  trait TimeFormatting[E] extends AppenderBase[E] {
    private var formatter: TimeFormatter = RxLogback.defaultFormatter

    def getTimeFormat: String = formatter.timeFormat

    def setTimeFormat(format: String): Unit = {
      formatter = new TimeFormatter(format)
      addInfo(s"Time format set to ${formatter.timeFormat}")
    }

    def format(timeStamp: Long) = formatter.format(timeStamp)
  }
}
