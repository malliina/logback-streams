import com.mle.sbtutils.{SbtProjects, SbtUtils}
import sbt.Keys._
import sbt._

/**
 * A scala build file logger.
 */
object LoggerBuild extends Build {

  lazy val project = SbtProjects.mavenPublishProject("logback-rx").settings(projectSettings: _*)

  lazy val projectSettings = Seq(
    version := "0.1.2",
    SbtUtils.gitUserName := "malliina",
    SbtUtils.developerName := "Michael Skogberg",
    scalaVersion := "2.11.4",
    //    crossScalaVersions := Seq("2.11.0", "2.10.4"),
    fork in Test := true,
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.7.7",
      "io.reactivex" %% "rxscala" % "0.22.0",
      "com.typesafe.play" %% "play-json" % "2.3.2",
      "ch.qos.logback" % "logback-classic" % "1.1.2",
      "ch.qos.logback" % "logback-core" % "1.1.2",
      "org.scala-lang.modules" %% "scala-xml" % "1.0.2" % "test"
    )
  )
}