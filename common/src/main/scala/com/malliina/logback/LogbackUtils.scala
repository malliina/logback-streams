package com.malliina.logback

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.classic.{Logger, LoggerContext}
import ch.qos.logback.core.Appender
import org.slf4j.LoggerFactory

object LogbackUtils {
  def appender[T](
    appenderName: String,
    loggerName: String = org.slf4j.Logger.ROOT_LOGGER_NAME
  ): Option[T] =
    Option(
      LoggerFactory
        .getLogger(loggerName)
        .asInstanceOf[Logger]
        .getAppender(appenderName)
        .asInstanceOf[T]
    )

  def getAppender[T](appenderName: String, loggerName: String = "ROOT"): T =
    appender[T](appenderName, loggerName)
      .getOrElse(
        throw new NoSuchElementException(
          s"Unable to find appender with name: $appenderName"
        )
      )

  def installAppender(
    appender: Appender[ILoggingEvent],
    loggerName: String = org.slf4j.Logger.ROOT_LOGGER_NAME
  ): Unit = {
    if (appender.getContext == null) {
      appender setContext LoggerFactory.getILoggerFactory
        .asInstanceOf[LoggerContext]
    }
    if (!appender.isStarted) {
      appender.start()
    }
    val logger = LoggerFactory.getLogger(loggerName).asInstanceOf[Logger]
    logger addAppender appender
  }
}
