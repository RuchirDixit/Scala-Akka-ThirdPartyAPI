package com.bridgelabz

import java.io.{BufferedWriter, FileWriter}

import akka.Done
import akka.actor.ActorSystem
import au.com.bytecode.opencsv.CSVWriter
import org.mongodb.scala.bson.BsonDocument
import org.mongodb.scala.bson.conversions.Bson
import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}

import scala.collection.mutable.ListBuffer
import scala.util.parsing.json._
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object SaveToDatabase {
  implicit val system = ActorSystem("HelloWorld")
  implicit val executor: ExecutionContext = system.dispatcher
  val mongoClient: MongoClient = MongoClient()
  // Getting mongodb database
  val database: MongoDatabase = mongoClient.getDatabase("downloadDB")
  // Getting mongodb collection
 // val collection: MongoCollection[Document] = database.getCollection("download")
  //collection.drop()

  import org.bson.Document

  def convertJson(query: String): Document = Document.parse(query)
//  def saveToDatabase(str:String) = {
//    val document:Document = Document.parse(str)
//    val future = collection.insertOne(document.toBsonDocument())
//    future.onComplete {
//      case Success(_) => println("Successfully Added!")
//      case Failure(exception) => println(exception)
//    }
  //}
  def saveToCSV(str:String) = {
  val outputFile = new BufferedWriter(new FileWriter("Resulet.csv"))
  val csvWriter = new CSVWriter(outputFile)
}
}
