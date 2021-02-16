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
package com.bridgelabz.main

import akka.actor.Props
import com.bridgelabz.actors.DownloadActor
import com.bridgelabz.actorsystemfactory.ActorSystemFactory
import com.bridgelabz.services.SaveService
import com.typesafe.scalalogging.LazyLogging
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
import play.api.libs.json._
import scala.concurrent.ExecutionContext

object HttpClientSingleRequest extends LazyLogging{
  val nameOfActor = "MongoClient_AkkahttpApp"
  def main(args: Array[String]): Unit = {
    val system = ActorSystemFactory.system
    implicit val executor: ExecutionContext = system.dispatcher
    val httpClient = HttpClientBuilder.create().build()
    val url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=5min&outputsize=full&apikey=demo"
    val response = httpClient.execute(new
        HttpGet(url))
    // Actor to download data from third party API
    val actorToDownloadData = system.actorOf(Props[DownloadActor],"downloadingThirdPartyData")
    logger.info("Downloading data!")
    actorToDownloadData ! "Downloading"
    val entity = response.getEntity
    val stringToParse = EntityUtils.toString(entity,"UTF-8")
    val jsonParser = Json.parse(stringToParse)
    // Save to Database
    SaveService.saveToDatabase(jsonParser.as[JsObject].fields(1))
    // Save to CSV
    SaveService.saveToCSV(jsonParser.as[JsObject].fields(1))
  }
}
