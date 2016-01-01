import com.malliina.sbtutils.SbtUtils.{developerName, gitUserName}
import com.malliina.sbtutils.{SbtProjects, SbtUtils}
import sbt.Keys._
import sbt._

/**
 * A scala build file logger.
 */
object LoggerBuild extends Build {

  lazy val project = SbtProjects.testableProject("logback-rx")
    .enablePlugins(bintray.BintrayPlugin)
    .settings(projectSettings: _*)

  lazy val projectSettings = Seq(
    version := "0.6.0",
    gitUserName := "malliina",
    organization := s"com.${gitUserName.value}",
    developerName := "Michael Skogberg",
    scalaVersion := "2.11.7",
    fork in Test := true,
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.7.13",
      "io.reactivex" %% "rxscala" % "0.25.1",
      "com.typesafe.play" %% "play-json" % "2.4.6",
      "ch.qos.logback" % "logback-classic" % "1.1.3",
      "ch.qos.logback" % "logback-core" % "1.1.3",
      "org.scala-lang.modules" %% "scala-xml" % "1.0.5" % "test"
    ),
    licenses +=("MIT", url("http://opensource.org/licenses/MIT"))
  )
}
