val logbackModules = Seq("classic", "core")
val circeModules = Seq("generic", "parser")

inThisBuild(
  Seq(
    gitUserName := "malliina",
    organization := "com.malliina",
    developerName := "Michael Skogberg",
    scalaVersion := "3.0.0",
    crossScalaVersions := scalaVersion.value :: "2.13.6" :: Nil,
    releaseCrossBuild := true,
    libraryDependencies ++=
      logbackModules.map(m => "ch.qos.logback" % s"logback-$m" % "1.2.4") ++
        circeModules.map(m => "io.circe" %% s"circe-$m" % "0.14.1") ++
        Seq(
          "org.slf4j" % "slf4j-api" % "1.7.30",
          "org.scalameta" %% "munit" % "0.7.27" % Test
        ),
    testFrameworks += new TestFramework("munit.Framework")
  )
)

val common = logbackProject("common")

val fs2 = logbackProject("fs2")
  .dependsOn(common)
  .settings(
    libraryDependencies ++= Seq(
      "co.fs2" %% "fs2-core" % "2.5.9"
    )
  )

//val streams = logbackProject("akka-streams")
//  .dependsOn(common)
//  .settings(
//    libraryDependencies ++= Seq(
//      "com.typesafe.akka" %% "akka-stream" % "2.6.5",
//      "org.scala-lang.modules" %% "scala-xml" % "1.3.0" % Test
//    ),
//    releaseProcess := tagReleaseProcess.value
//  )

val all = project
  .in(file("."))
  .aggregate(common, fs2)
  .settings(
    publishTo := Some(Resolver.file("Unused transient repository", file("target/unusedrepo"))),
    publish / skip := true,
    publishArtifact := false,
    packagedArtifacts := Map.empty,
    publish := {},
    publishLocal := {},
    releaseProcess := (common / tagReleaseProcess).value
  )

def logbackProject(name: String) = Project(name, file(name))
  .enablePlugins(MavenCentralPlugin)
  .settings(moduleName := s"logback-$name", releaseProcess := tagReleaseProcess.value)

Global / onChangedBuildSource := ReloadOnSourceChanges
