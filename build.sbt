val p = Project("logback-streams", file("."))
  .enablePlugins(MavenCentralPlugin)
  .settings(
    gitUserName := "malliina",
    organization := "com.malliina",
    developerName := "Michael Skogberg",
    scalaVersion := "2.13.1",
    crossScalaVersions := scalaVersion.value :: "2.12.10" :: Nil,
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.7.29",
      "com.typesafe.akka" %% "akka-stream" % "2.6.1",
      "com.typesafe.play" %% "play-json" % "2.8.1",
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "ch.qos.logback" % "logback-core" % "1.2.3",
      "org.scala-lang.modules" %% "scala-xml" % "1.2.0" % Test,
      "org.scalatest" %% "scalatest" % "3.1.0" % Test
    )
  )
