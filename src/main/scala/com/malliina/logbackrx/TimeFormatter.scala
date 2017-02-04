package com.malliina.logbackrx

import ch.qos.logback.core.CoreConstants
import ch.qos.logback.core.util.CachingDateFormatter

/**
  * @param simpleDateFormat time format
  * @see ch.qos.logback.classic.pattern.DateConverter.java
  */
class TimeFormatter(simpleDateFormat: String) {
  val (timeFormat, formatter) = {
    val specifiedFormat =
      if (simpleDateFormat == CoreConstants.ISO8601_STR) CoreConstants.ISO8601_PATTERN
      else simpleDateFormat
    try {
      (specifiedFormat, new CachingDateFormatter(specifiedFormat))
    } catch {
      case e: IllegalArgumentException =>
        (CoreConstants.ISO8601_PATTERN, new CachingDateFormatter(CoreConstants.ISO8601_PATTERN))
    }
  }

  def format(timeStamp: Long): String = formatter format timeStamp
}
