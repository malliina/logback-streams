import com.mle.sbtutils.SbtUtils
import sbt._
import sbt.Keys._

/**
 * A scala build file logger.
 */
object LoggerBuild extends Build {

  lazy val project = SbtUtils.testableProject("logback-rx").settings(projectSettings: _*)

  lazy val projectSettings = SbtUtils.publishSettings ++ Seq(
    version := "0.0.5",
    SbtUtils.gitUserName := "malliina",
    SbtUtils.developerName := "Michael Skogberg",
    // cannot do 2.11 until play-json exists for 2.11
    scalaVersion := "2.10.4",
    //    crossScalaVersions := Seq("2.11.0", "2.10.4"),
    fork in Test := true,
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.7.7",
      "com.netflix.rxjava" % "rxjava-scala" % "0.18.1",
      "com.typesafe.play" %% "play-json" % "2.2.2",
      "ch.qos.logback" % "logback-classic" % "1.1.2",
      "ch.qos.logback" % "logback-core" % "1.1.2"
    )
  )
}