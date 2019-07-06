lazy val p = Project("logback-streams", file("."))
  .enablePlugins(MavenCentralPlugin)

gitUserName := "malliina"
organization := "com.malliina"
developerName := "Michael Skogberg"
scalaVersion := "2.13.0"
crossScalaVersions := scalaVersion.value :: "2.12.8" :: Nil
libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.26",
  "com.typesafe.akka" %% "akka-stream" % "2.5.23",
  "com.typesafe.play" %% "play-json" % "2.7.4",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "ch.qos.logback" % "logback-core" % "1.2.3",
  "org.scala-lang.modules" %% "scala-xml" % "1.2.0" % Test,
  "org.scalatest" %% "scalatest" % "3.0.8" % Test
)
