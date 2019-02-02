[![Build Status](https://travis-ci.org/malliina/logback-rx.png?branch=master)](https://travis-ci.org/malliina/logback-rx)
[![Maven Central](https://img.shields.io/maven-central/v/com.malliina/logback-rx_2.11.svg)](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.malliina%22%20AND%20a%3A%22logback-rx_2.11%22)

# logback-streams

This library provides an [Akka Streams](https://doc.akka.io/docs/akka/2.5/stream/) Source for events logged using Logback.
You might find this useful if you wish to stream log events to your user interface with minimal delay.

## Installation

    "com.malliina" %% "logback-streams" % "1.5.0"

## Usage

### Akka Streams

Configure an AkkaAppender in your Logback configuration.

### Rx

Add a custom Logback appender to your Logback configuration, then obtain it in code to access the Observable of log 
events.

Here's an example logback.xml:

    <configuration>
        <appender name="RX" class="com.mle.logbackrx.BasicPublishRxAppender"/>
        <appender name="BOUNDED" class="com.mle.logbackrx.BasicBoundedReplayRxAppender">
            <bufferSize>100</bufferSize>
            <timeFormat>yyyy:MM:dd HH:mm:ss</timeFormat>
        </appender>
        <root level="INFO">
            <appender-ref ref="RX"/>
            <appender-ref ref="BOUNDED"/>
        </root>
    </configuration>

To customize time formatting, use the timeFormat property of the appenders, which works according to the 
SimpleDateFormat time syntax.

In order to access the Observable of log events, you need to get the appender first:

    // gets appender based on name from logback.xml
    val appender = com.mle.logbackrx.LogbackUtils.getAppender[BasicPublishRxAppender]("RX")
    // subscribes to observable of log events
    val subscription = appender.logEvents.subscribe(event => println(event))

You could also have installed the appender programmatically:

    val appender = new BasicBoundedReplayRxAppender
    com.mle.logbackrx.LogbackUtils.installAppender(appender)
    appender.logEvents.subscribe(e => println(e))

Events you log using the SLF4J API with Logback will now be emitted by the Observable:

    log.info("Hello, world")
