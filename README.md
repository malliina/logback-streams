[![Build Status](https://github.com/malliina/logback-streams/workflows/Test/badge.svg)](https://github.com/malliina/logback-streams)
[![Maven Central](https://img.shields.io/maven-central/v/com.malliina/logback-streams_2.13.svg)](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.malliina%22%20AND%20a%3A%22logback-streams_2.13%22)

# logback-streams

This library provides an [Akka Streams](https://doc.akka.io/docs/akka/2.5/stream/) Source for events logged using Logback.
You might find this useful if you wish to stream log events to your user interface with minimal delay.

## Installation

    "com.malliina" %% "logback-streams" % "1.7.0"

## Usage

Configure an [AkkaAppender](src/main/scala/com/malliina/logback/akka/AkkaAppender.scala) in 
your Logback configuration.

Events you log using the SLF4J API with Logback will now be emitted by the Akka Streams Stream:

    log.info("Hello, world")
