organization in ThisBuild := "br.com.ingaia.teamdata"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.13.0"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.3" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.1.1" % Test

lazy val `hermes-data-ingestion` = (project in file("."))
  .aggregate(`hermes-data-ingestion-api`, `hermes-data-ingestion-impl`, `hermes-data-ingestion-stream-api`, `hermes-data-ingestion-stream-impl`)

lazy val `hermes-data-ingestion-api` = (project in file("hermes-data-ingestion-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `hermes-data-ingestion-impl` = (project in file("hermes-data-ingestion-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings)
  .dependsOn(`hermes-data-ingestion-api`)

lazy val `hermes-data-ingestion-stream-api` = (project in file("hermes-data-ingestion-stream-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `hermes-data-ingestion-stream-impl` = (project in file("hermes-data-ingestion-stream-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`hermes-data-ingestion-stream-api`, `hermes-data-ingestion-api`)
