package com.bridgelabz
import org.mongodb.scala.Document
import spray.json.{JsNull, JsObject, JsValue}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object SaveService extends SaveBuilder {

  /**
   *
   * @param seqStr : values that we receive from our api for Time Series (5min) field
   */
  def saveToDatabase(seqStr:Seq[JsValue]) = {
    val data = seqStr.head
    data match {
      case JsObject(fields) =>
        fields.values.foreach(value => {
          val open = value.asJsObject().fields("1. open")
          val high = value.asJsObject().fields("2. high")
          val low = value.asJsObject().fields("3. low")
          val close = value.asJsObject().fields("4. close")
          val volume = value.asJsObject().fields("5. volume")
          val documentToBeInserted : Document = Document("Open" -> open.toString, "High" -> high.toString, "Low" -> low.toString, "Close" -> close.toString , "Volume" -> volume.toString)
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
   */
  def saveToCSV(str:Seq[JsValue]) = {
//  val outputFile = new BufferedWriter(new FileWriter("Result.csv"))
//  val csvWriter = new CSVWriter(outputFile)
//    val data = str.head
//    println(data match {
//      case JsObject(fields) => {
//        var listOfRecords = new ListBuffer[Array[String]]()
//        val csvFields = Array("Open","High","Low","Close","Volume")
//        listOfRecords += csvFields
//        fields.values.foreach(value => {
//          listOfRecords += Array(value.asJsObject().fields("1. open"),value.asJsObject().fields("2. high"),value.asJsObject().fields("3. low"),value.asJsObject().fields("4. close"),value.asJsObject().fields("5. volume"))
//          csvWriter.writeAll(listOfRecords.toList)
//        })
//      }
//      case JsNull => println("Null")
//    })
}
}
