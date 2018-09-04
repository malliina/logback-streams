import com.malliina.sbtutils.SbtProjects
import com.malliina.sbtutils.SbtUtils.{developerName, gitUserName}

lazy val p = SbtProjects.mavenPublishProject("logback-rx")

gitUserName := "malliina"
organization := "com.malliina"
developerName := "Michael Skogberg"
scalaVersion := "2.12.6"
libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.25",
  "io.reactivex" %% "rxscala" % "0.26.5",
  "com.typesafe.akka" %% "akka-stream" % "2.5.16",
  "com.typesafe.play" %% "play-json" % "2.6.10",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "ch.qos.logback" % "logback-core" % "1.2.3",
  "org.scala-lang.modules" %% "scala-xml" % "1.1.0" % Test
)
