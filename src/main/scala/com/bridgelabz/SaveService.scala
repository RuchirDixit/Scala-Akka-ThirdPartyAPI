package com.bridgelabz

import java.io.{BufferedWriter, FileWriter}

import akka.Done
import akka.actor.ActorSystem
import au.com.bytecode.opencsv.CSVWriter
import org.mongodb.scala.bson.BsonDocument
import org.mongodb.scala.bson.conversions.Bson
import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}
import spray.json.{JsArray, JsBoolean, JsNull, JsNumber, JsObject, JsString, JsValue}

import scala.collection.mutable.ListBuffer
import scala.util.parsing.json._
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object SaveService {
  implicit val system = ActorSystem("HelloWorld")
  implicit val executor: ExecutionContext = system.dispatcher
  val mongoClient: MongoClient = MongoClient()
  // Getting mongodb database
  val databaseName = sys.env("database_name")
  val database: MongoDatabase = mongoClient.getDatabase(databaseName)
  // Getting mongodb collection
  val collectionName = sys.env("collection_name")
  val collection: MongoCollection[Document] = database.getCollection(collectionName)
  collection.drop()

  def saveToDatabase(str:Seq[JsValue]) = {
    val data = str.head
    println(data match {
      case JsObject(fields) =>
        fields.values.foreach(value => {
          val open = value.asJsObject().fields("1. open")
          val high = value.asJsObject().fields("2. high")
          val low = value.asJsObject().fields("3. low")
          val close = value.asJsObject().fields("4. close")
          val vol = value.asJsObject().fields("5. volume")
          val doc : Document = Document("Open" -> open.toString, "High" -> high.toString, "Low" -> low.toString, "Close" -> close.toString , "Volume" -> vol.toString)
          val bindFuture = collection.insertOne(doc).toFuture()
          bindFuture.onComplete{
            case Success(_) => println("")
            case Failure(_) => println("")
          }
        })
      case JsNull => println("Null")
    })
  }
  def saveToCSV(str:Seq[JsValue]) = {
  val outputFile = new BufferedWriter(new FileWriter("Result.csv"))
  val csvWriter = new CSVWriter(outputFile)
    val data = str.head
    println(data match {
      case JsObject(fields) =>
        //var listOfRecords = new ListBuffer[Array[String]]()
        //val csvFields = Array("Open","High","Low","Close","Volume")
        fields.values.foreach(value => println(value.asJsObject().fields("1. open")))
      case JsNull => println("Null")
    })
}
}
