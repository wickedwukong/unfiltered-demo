organization := "com.example"

name := "db-unfiltered-demo"

scalaVersion := "2.9.1"


version := "0.1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "net.databinder" %% "unfiltered-filter" % "0.6.4",
  "net.databinder" %% "unfiltered-jetty" % "0.6.4",
  "org.clapper" %% "avsl" % "0.3.6",
  "net.databinder" %% "unfiltered-spec" % "0.6.4" % "test",
  "org.specs2" %% "specs2" % "1.12.3" % "test"
)

resolvers ++= Seq(
  "java m2" at "http://download.java.net/maven/2"
)
