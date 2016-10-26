import sbt.Keys._
import sbt._

object BuildBuild {

  lazy val settings = sbtPlugins ++ Seq(
    resolvers += Resolver.url(
      "bintray-sbt-plugin-releases",
      url("http://dl.bintray.com/content/sbt/sbt-plugin-releases"))(
        Resolver.ivyStylePatterns)
  ) ++ sbtPlugins

  def sbtPlugins = Seq(
    "com.malliina" % "sbt-utils" % "0.4.0",
    "me.lessis" % "bintray-sbt" % "0.2.1"
  ) map addSbtPlugin
}
