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
  val postgres = "9.3-1100-jdbc4"
  val circe = "0.12.3"
}

scalaVersion := versions.scala

libraryDependencies ++= Seq(
  // Finatra stuff
  "com.twitter" % "finatra-http_2.12" % versions.finatra,
  "com.twitter" % "finatra-httpclient_2.12" % versions.finatra,
  "com.twitter" % "inject-server_2.12" % versions.finatra,
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

  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  "ch.qos.logback" % "logback-classic" % versions.logback,

  // Adding circe because Jackson doesn't always play nice with case classes

  "io.circe" %% "circe-core" % versions.circe,
  "io.circe" %% "circe-generic" % versions.circe,
  "io.circe" %% "circe-parser" % versions.circe,

  // DB stuff
  "com.typesafe.slick" % "slick_2.12" % versions.slick,
  "com.typesafe" % "config" % versions.typesafeConfig,
  "org.flywaydb" % "flyway-core" % versions.flyway,
  "org.postgresql" % "postgresql" % versions.postgres,
)

enablePlugins(FlywayPlugin)

flywayUrl := "jdbc:postgresql://localhost/jitz?user=postgres&password=postgres"
flywayUser := "postgres"
flywayPassword := "postgres"
flywayLocations += "filesystem:migrations/sql"
