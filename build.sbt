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
  "com.typesafe.play" %% "play-json" % "2.8.0"
)
//scalaVersion := "2.10.2"
//libraryDependencies ++= Seq(
//  "net.databinder.dispatch" %% "dispatch-core" % "0.11.1",
//  "org.json4s" %% "json4s-native" % "3.2.9",
//  "org.json4s" %% "json4s-jackson" % "3.2.9",
//  "com.typesafe.akka" %% "akka-actor" % "2.2.3"
//)