import com.malliina.sbtutils.SbtProjects
import com.malliina.sbtutils.SbtUtils.{developerName, gitUserName}
import sbt.Keys._
import sbt._

object LoggerBuild {
  lazy val project = SbtProjects.mavenPublishProject("logback-rx")
    .settings(projectSettings: _*)

  lazy val projectSettings = Seq(
    gitUserName := "malliina",
    organization := "com.malliina",
    developerName := "Michael Skogberg",
    scalaVersion := "2.11.8",
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.7.22",
      "io.reactivex" %% "rxscala" % "0.26.5",
      "com.typesafe.play" %% "play-json" % "2.5.10",
      "ch.qos.logback" % "logback-classic" % "1.1.8",
      "ch.qos.logback" % "logback-core" % "1.1.8",
      "org.scala-lang.modules" %% "scala-xml" % "1.0.6" % Test
    )
  )
}
