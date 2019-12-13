name := "jitz.me"

version := "0.1"

lazy val versions = new {
  val scala = "2.12.10"
  val finatra = "19.11.0"
  val slick = "3.3.2"
  val guice = "4.0"
  val typesafeConfig = "1.3.1"
  val logback = "1.2.3"
  val flyway = "6.1.1"
}

scalaVersion := versions.scala

libraryDependencies ++= Seq(
  "com.typesafe.slick" % "slick_2.12" % versions.slick,
  "com.typesafe" % "config" % versions.typesafeConfig,

  "com.twitter" % "finatra-http_2.12" % versions.finatra,
  "com.twitter" % "finatra-httpclient_2.12" % versions.finatra,
  "com.twitter" % "inject-server_2.12" % versions.finatra,

   "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  "ch.qos.logback" % "logback-classic" % versions.logback,

  "org.flywaydb" % "flyway-core" % versions.flyway,

  "com.twitter" %% "finatra-http" % versions.finatra % "test",
  "com.twitter" %% "finatra-jackson" % versions.finatra % "test",
  "com.twitter" %% "inject-server" % versions.finatra % "test",
  "com.twitter" %% "inject-app" % versions.finatra % "test",
  "com.twitter" %% "inject-core" % versions.finatra % "test",
  "com.twitter" %% "inject-modules" % versions.finatra % "test",
  "com.google.inject.extensions" % "guice-testlib" % versions.guice % "test",

  "com.twitter" %% "finatra-http" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "finatra-jackson" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-server" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-app" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-core" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-modules" % versions.finatra % "test" classifier "tests",
)
