lazy val p = Project("logback-streams", file("."))
  .enablePlugins(MavenCentralPlugin)

gitUserName := "malliina"
organization := "com.malliina"
developerName := "Michael Skogberg"
scalaVersion := "2.12.8"
libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.25",
  "io.reactivex" %% "rxscala" % "0.26.5",
  "com.typesafe.akka" %% "akka-stream" % "2.5.20",
  "com.typesafe.play" %% "play-json" % "2.7.1",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "ch.qos.logback" % "logback-core" % "1.2.3",
  "org.scala-lang.modules" %% "scala-xml" % "1.1.1" % Test
)
