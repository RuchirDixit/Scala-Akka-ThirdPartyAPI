name := "AkkaHtp-3rdparty"

version := "0.1"

scalaVersion := "2.13.1"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.6.8",
  "com.typesafe.akka" %% "akka-http" % "10.2.1",
  "com.typesafe.akka" %% "akka-stream" % "2.6.8",
  "ch.rasc" % "bsoncodec" % "1.0.1",
  "org.mongodb.scala" %% "mongo-scala-driver" % "2.7.0",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.2.1",
  "com.typesafe.akka" %% "akka-http-xml" % "10.2.1",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.4.0",
  "org.apache.httpcomponents" % "httpclient" % "4.5.2",
  "au.com.bytecode" % "opencsv" % "2.4",
  "io.spray" %% "spray-json" % "1.3.6",
  "org.scalatest" %% "scalatest-flatspec" % "3.2.2" % "test",
  "com.typesafe.play" %% "play-json" % "2.8.0",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "org.scalatestplus" %% "mockito-3-4" % "3.2.3.0" % "test",
  "org.scalatest" %% "scalatest" % "3.2.2" % Test,
)