lazy val projectSettings = Seq(
  organization := "se.clan",
  organizationName := "ClanSE",
  scalaVersion := "2.12.4",
  version := "0.0.1",

  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding", "utf8",
    "-feature",
    "-target:jvm-1.8",
    "-unchecked",
    "-Xfatal-warnings",
    "-Xfuture",
    "-Xlint",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Ywarn-unused-import"
  )
)

lazy val dependencies = Seq(
  "org.scala-lang.modules" % "scala-xml_2.12" % "1.0.6",
  "org.scodec" % "scodec-bits_2.12" % "1.1.5",
  "org.scodec" % "scodec-stream_2.12" % "1.1.0-M8",
)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.4"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"

lazy val cl30 = (project in file("."))
  .settings(
    projectSettings,
    name := "calc30",
    libraryDependencies ++= dependencies
  )
