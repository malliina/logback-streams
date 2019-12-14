[![Build Status](https://travis-ci.org/malliina/logback-rx.png?branch=master)](https://travis-ci.org/malliina/logback-rx)
[![Maven Central](https://img.shields.io/maven-central/v/com.malliina/logback-rx_2.12.svg)](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.malliina%22%20AND%20a%3A%22logback-rx_2.12%22)

# logback-streams

This library provides an [Akka Streams](https://doc.akka.io/docs/akka/2.5/stream/) Source for events logged using Logback.
You might find this useful if you wish to stream log events to your user interface with minimal delay.

## Installation

    "com.malliina" %% "logback-streams" % "1.5.0"

## Usage

Configure an [AkkaAppender](src/main/scala/com/malliina/logback/akka/AkkaAppender.scala) in 
your Logback configuration.

Events you log using the SLF4J API with Logback will now be emitted by the Akka Streams Stream:

    log.info("Hello, world")
