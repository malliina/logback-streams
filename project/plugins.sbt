scalaVersion := "2.12.12"

Seq(
  "com.malliina" % "sbt-utils-maven" % "1.0.0",
  "ch.epfl.scala" % "sbt-bloop" % "1.4.6",
  "org.scalameta" % "sbt-scalafmt" % "2.4.0"
) map addSbtPlugin
