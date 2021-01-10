inThisBuild(
  Seq(
    gitUserName := "malliina",
    organization := "com.malliina",
    developerName := "Michael Skogberg",
    scalaVersion := "2.13.3",
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.7.30",
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "ch.qos.logback" % "logback-core" % "1.2.3",
      "com.typesafe.play" %% "play-json" % "2.9.2",
      "org.scalameta" %% "munit" % "0.7.20" % Test
    ),
    testFrameworks += new TestFramework("munit.Framework")
  )
)

val common = logbackProject("common")

val fs2 = logbackProject("fs2")
  .dependsOn(common)
  .settings(
    libraryDependencies ++= Seq(
      "co.fs2" %% "fs2-core" % "2.5.0"
    )
  )

val streams = logbackProject("akka-streams")
  .dependsOn(common)
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-stream" % "2.6.5",
      "org.scala-lang.modules" %% "scala-xml" % "1.3.0" % Test
    )
  )

val all = project
  .in(file("."))
  .aggregate(common, streams, fs2)
  .settings(
    skip in publish := true,
    publishArtifact := false,
    packagedArtifacts := Map.empty,
    publish := {},
    publishLocal := {}
  )

def logbackProject(name: String) = Project(name, file(name))
  .enablePlugins(MavenCentralPlugin)
  .settings(moduleName := s"logback-$name")

Global / onChangedBuildSource := ReloadOnSourceChanges
