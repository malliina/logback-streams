import com.mle.sbtutils.SbtUtils
import sbt._
import sbt.Keys._

/**
 * A scala build file logger.
 */
object LoggerBuild extends Build {

  lazy val project = Project("logback-rx", file(".")).settings(projectSettings: _*)

  lazy val projectSettings = SbtUtils.publishSettings ++ Seq(
    version := "0.0.4",
    SbtUtils.gitUserName := "malliina",
    SbtUtils.developerName := "Michael Skogberg",
    scalaVersion := "2.10.4",
    fork in Test := true,
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "2.0" % "test",
      "org.slf4j" % "slf4j-api" % "1.7.6",
      "com.netflix.rxjava" % "rxjava-scala" % "0.17.2",
      "com.typesafe.play" %% "play-json" % "2.2.0",
      "ch.qos.logback" % "logback-classic" % "1.1.0",
      "ch.qos.logback" % "logback-core" % "1.1.1"
    )
  )
}