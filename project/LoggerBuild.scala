import com.malliina.sbtutils.SbtProjects
import com.malliina.sbtutils.SbtUtils.{developerName, gitUserName}
import sbt.Keys._
import sbt._

/** A scala build file logger.
  */
object LoggerBuild extends Build {

  lazy val project = SbtProjects.testableProject("logback-rx")
    .enablePlugins(bintray.BintrayPlugin)
    .settings(projectSettings: _*)

  lazy val projectSettings = Seq(
    version := "1.0.0",
    gitUserName := "malliina",
    organization := s"com.${gitUserName.value}",
    developerName := "Michael Skogberg",
    scalaVersion := "2.11.7",
    fork in Test := true,
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.7.21",
      "io.reactivex" %% "rxscala" % "0.26.0",
      "com.typesafe.play" %% "play-json" % "2.5.1",
      "ch.qos.logback" % "logback-classic" % "1.1.7",
      "ch.qos.logback" % "logback-core" % "1.1.7",
      "org.scala-lang.modules" %% "scala-xml" % "1.0.5" % "test"
    ),
    licenses +=("MIT", url("http://opensource.org/licenses/MIT"))
  )
}
