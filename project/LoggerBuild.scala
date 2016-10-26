import bintray.Plugin.bintraySettings
import bintray.Keys.{bintray, bintrayOrganization}
import com.malliina.sbtutils.SbtProjects
import com.malliina.sbtutils.SbtUtils.{developerName, gitUserName}
import sbt.Keys._
import sbt._

/** A scala build file logger.
  */
object LoggerBuild {

  lazy val project = SbtProjects.testableProject("logback-rx")
    .settings(projectSettings: _*)

  lazy val projectSettings = bintraySettings ++ Seq(
    version := "1.0.2",
    gitUserName := "malliina",
    organization := s"com.${gitUserName.value}",
    developerName := "Michael Skogberg",
    scalaVersion := "2.11.8",
    fork in Test := true,
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.7.21",
      "io.reactivex" %% "rxscala" % "0.26.2",
      "com.typesafe.play" %% "play-json" % "2.5.9",
      "ch.qos.logback" % "logback-classic" % "1.1.7",
      "ch.qos.logback" % "logback-core" % "1.1.7",
      "org.scala-lang.modules" %% "scala-xml" % "1.0.6" % Test
    ),
    licenses +=("MIT", url("http://opensource.org/licenses/MIT")),
    bintrayOrganization in bintray := None
  )
}
