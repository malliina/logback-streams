import bintray.Plugin.bintraySettings
import com.mle.sbtutils.{SbtProjects, SbtUtils}
import sbt.Keys._
import sbt._
import SbtUtils.{gitUserName, developerName}
/**
 * A scala build file logger.
 */
object LoggerBuild extends Build {

  lazy val project = SbtProjects.testableProject("logback-rx").settings(projectSettings: _*)

  lazy val projectSettings = bintraySettings ++ Seq(
    version := "0.2.2",
    gitUserName := "malliina",
    organization := s"com.github.${gitUserName.value}",
    developerName := "Michael Skogberg",
    scalaVersion := "2.11.6",
    fork in Test := true,
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.7.12",
      "io.reactivex" %% "rxscala" % "0.24.1",
      "com.typesafe.play" %% "play-json" % "2.3.9",
      "ch.qos.logback" % "logback-classic" % "1.1.3",
      "ch.qos.logback" % "logback-core" % "1.1.3",
      "org.scala-lang.modules" %% "scala-xml" % "1.0.2" % "test"
    ),
    licenses +=("MIT", url("http://opensource.org/licenses/MIT"))
  )
}
