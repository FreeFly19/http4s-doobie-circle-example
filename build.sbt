name := "http4s-doobie-circle-example"

version := "0.1"

scalaVersion := "2.12.6"

val http4sVersion = "0.18.15"
val circeVersion = "0.9.3"

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-literal" % circeVersion,
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)

scalacOptions ++= Seq("-Ypartial-unification")