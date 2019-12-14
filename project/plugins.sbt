scalaVersion := "2.12.10"

resolvers ++= Seq(
  ivyResolver(
    "bintray-sbt-plugin-releases",
    "https://dl.bintray.com/content/sbt/sbt-plugin-releases"
  ),
  ivyResolver(
    "malliina bintray sbt",
    "https://dl.bintray.com/malliina/sbt-plugins/"
  )
)

def ivyResolver(name: String, urlStr: String) =
  Resolver.url(name, url(urlStr))(Resolver.ivyStylePatterns)

Seq(
  "com.malliina" % "sbt-utils-maven" % "0.15.0",
  "ch.epfl.scala" % "sbt-bloop" % "1.3.4",
  "org.scalameta" % "sbt-scalafmt" % "2.3.0"
) map addSbtPlugin
