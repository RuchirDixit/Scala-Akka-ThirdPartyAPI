package com.bridgelabz
import java.io.{BufferedWriter, FileWriter}
import au.com.bytecode.opencsv.CSVWriter
import org.mongodb.scala.Document
import play.api.libs.json.{JsNull, JsObject, JsValue}
import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.jdk.CollectionConverters._
object SaveService {

  /**
   *
   * @param str : values that we receive from our api for Time Series (5min) field
   *  Used to parse Json data, extract values in form of string and save data into MongoDB database
   */
  def saveToDatabase(str:(String,JsValue)) = {
    val data = str._2
    data match {
      case JsObject(fields) =>
        fields.values.foreach(value => {
          val jsObject = value.as[JsObject]
          val open = jsObject("1. open").toString()
          val high = jsObject("2. high").toString()
          val low = jsObject("3. low").toString()
          val close = jsObject("4. close").toString()
          val volume = jsObject("5. volume").toString()
          val documentToBeInserted : Document = Document("Open" -> open, "High" -> high, "Low" -> low, "Close" -> close , "Volume" -> volume)
          val bindFuture = MongoDBService.collection.insertOne(documentToBeInserted).toFuture()
          bindFuture.onComplete{
            case Success(_) => println("Added Successfully!")
            case Failure(exception) => println(exception)
          }
        })
      case JsNull => println("Null value found in data")
    }
  }

  /**
   *
   * @param str : values that we receive from our api for Time Series (5min) field
   *  Used to parse Json data, extract values in form of string and save into Result.csv file using fields mentioned
   */
  def saveToCSV(str:(String,JsValue)) = {
    val outputFile = new BufferedWriter(new FileWriter("Result.csv"))
    val csvWriter = new CSVWriter(outputFile)
    val data = str._2
    data match {
      case JsObject(fields) => {
        var listOfRecords = new ListBuffer[Array[String]]()
        val csvFields = Array("Open","High","Low","Close","Volume")
        listOfRecords += csvFields
        fields.values.foreach(value => {
          val jsObject = value.as[JsObject]
          listOfRecords += Array(jsObject("1. open").toString, jsObject("2. high").toString, jsObject("3. low").toString, jsObject("4. close").toString, jsObject("5. volume").toString)
        })
        csvWriter.writeAll(listOfRecords.toList.asJava)
        println("Written!")
        outputFile.close()
      }
      case JsNull => println("Null")
    }
}
}
