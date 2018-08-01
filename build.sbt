name := "http4s-doobie-circle-example"

version := "0.1"

scalaVersion := "2.12.6"

val http4sVersion = "0.18.15"

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)

scalacOptions ++= Seq("-Ypartial-unification")