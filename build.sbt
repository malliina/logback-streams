val p = Project("logback-streams", file("."))
  .enablePlugins(MavenCentralPlugin)
  .settings(
    gitUserName := "malliina",
    organization := "com.malliina",
    developerName := "Michael Skogberg",
    scalaVersion := "2.13.2",
    crossScalaVersions := scalaVersion.value :: "2.12.11" :: Nil,
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.7.30",
      "com.typesafe.akka" %% "akka-stream" % "2.6.5",
      "com.typesafe.play" %% "play-json" % "2.9.0",
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "ch.qos.logback" % "logback-core" % "1.2.3",
      "org.scala-lang.modules" %% "scala-xml" % "1.3.0" % Test,
      "org.scalameta" %% "munit" % "0.7.7" % Test
    ),
    testFrameworks += new TestFramework("munit.Framework")
  )

Global / onChangedBuildSource := ReloadOnSourceChanges
