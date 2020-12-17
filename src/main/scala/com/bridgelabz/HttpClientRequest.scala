package com.bridgelabz

import akka.actor.{ActorSystem, Props}
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
import scala.concurrent.ExecutionContext
import play.api.libs.json._
object HttpClientSingleRequest {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("SingleRequest")
    implicit val executor: ExecutionContext = system.dispatcher
    val httpClient = HttpClientBuilder.create().build()
    val response = httpClient.execute(new HttpGet("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=5min&outputsize=full&apikey=demo"))
    val actorToDownloadData = system.actorOf(Props[DownloadActor],"downloadingThirdPartyData")
    actorToDownloadData ! "Downloading"
    val entity = response.getEntity
    val str = EntityUtils.toString(entity,"UTF-8")
    val jsonParser = Json.parse(str)
    SaveService.saveToDatabase(jsonParser.as[JsObject].fields(1))
    SaveService.saveToCSV(jsonParser.as[JsObject].fields(1))
  }
}
