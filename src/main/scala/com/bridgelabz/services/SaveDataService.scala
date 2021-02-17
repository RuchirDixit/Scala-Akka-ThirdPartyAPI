// Copyright (C) 2011-2012 the original author or authors.
// See the LICENCE.txt file distributed with this work for additional
// information regarding copyright ownership.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.bridgelabz.services

import java.io.{BufferedWriter, FileWriter}

import au.com.bytecode.opencsv.CSVWriter
import com.bridgelabz.database.{DatabaseConfig}
import com.typesafe.scalalogging.LazyLogging
import org.mongodb.scala.Document
import play.api.libs.json.{JsNull, JsObject, JsValue}

import scala.collection.mutable.ListBuffer
import scala.util.{Failure, Success}
import scala.collection.JavaConverters._

class SaveDataService extends LazyLogging with DatabaseConfig{
  /**
   * Save data fetched from API into database
   * @param str : values that we receive from our api for Time Series (5min) field
   *  Used to parse Json data, extract values in form of string and save data into MongoDB database
   */
  def saveToDatabase(str:(String,JsValue)) : String = {
    logger.info("inside save to database")
    val dataToBeSaved = str._2
    dataToBeSaved match {
      case JsObject(fields) =>
        fields.values.foreach(value => {
          val jsObject = value.as[JsObject]
          val open = jsObject("1. open").toString()
          val high = jsObject("2. high").toString()
          val low = jsObject("3. low").toString()
          val close = jsObject("4. close").toString()
          val volume = jsObject("5. volume").toString()
          val documentToBeInserted : Document = Document("Open" -> open, "High" -> high, "Low" -> low, "Close" -> close , "Volume" -> volume)
          val bindFuture = collection.insertOne(documentToBeInserted).toFuture()
          bindFuture.onComplete{
            case Success(_) => logger.info("Added Successfully!")
            case Failure(exception) => logger.error(exception.toString)
          }
        })
        "Added"
      case JsNull => logger.error("Null value found in data")
        "Null"
    }
  }

  /**
   * Save data fetched from API into csv file using csvWriter
   * @param str : values that we receive from our api for Time Series (5min) field
   *  Used to parse Json data, extract values in form of string and save into Result.csv file using fields mentioned
   */
  def saveToCSV(str:(String,JsValue)) : String = {
    logger.info("inside save to csv")
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
          listOfRecords += Array(jsObject("1. open").toString,
            jsObject("2. high").toString, jsObject("3. low").toString,
            jsObject("4. close").toString, jsObject("5. volume").toString)
        })
        csvWriter.writeAll(listOfRecords.toList.asJava)
        logger.info("Written!")
        outputFile.close()
        "Saved to csv"
      }
      case JsNull => logger.error("Null")
        "Null"
    }
}
}
