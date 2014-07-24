# logback-rx #

This library provides Rx Observables for events logged using Logback.
You might find this useful if you wish to stream log events to your user interface with minimal delay.

## Installation ##

```"com.github.malliina" %% "logback-rx" % "0.1.0"```

## Features ##

Log events are pushed to Observables using custom Logback appenders. The PublishRxAppender provides an Observable
that emits any events subsequent to a subscription. The BoundedReplayRxAppender emits a history of log events and any
following events. The bufferSize property controls the maximum number of log events to replay.

## Usage ##

Add a custom Logback appender to your Logback configuration, then obtain it in
code to access the Observable of log events.

Here's an example logback.xml:

```
<configuration>
    <appender name="RX" class="com.mle.logbackrx.BasicPublishRxAppender"/>
    <appender name="BOUNDED" class="com.mle.logbackrx.BasicBoundedReplayRxAppender">
        <bufferSize>100</bufferSize>
    </appender>
    <root level="INFO">
        <appender-ref ref="RX"/>
        <appender-ref ref="BOUNDED"/>
    </root>
</configuration>
```

In order to access the Observable of log events, you need to get the appender first:

```
// gets appender based on name from logback.xml
val appender = com.mle.logbackrx.LogbackUtils.getAppender[BasicPublishRxAppender]("RX")
// subscribes to observable of log events
val subscription = appender.logEvents.subscribe(event => println(event))
```

You could also have installed the appender programmatically:

```
val appender = new BasicBoundedReplayRxAppender
com.mle.logbackrx.LogbackUtils.installAppender(appender)
appender.logEvents.subscribe(e => println(e))
```

Events you log using the SLF4J API with Logback will now be emitted by the Observable:

```
log.info("Hello, world")
```



