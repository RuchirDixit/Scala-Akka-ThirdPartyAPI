package com.bridgelabz

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
import akka.http.scaladsl.model.{HttpEntity, HttpRequest}
import akka.http.scaladsl.unmarshalling.Unmarshal
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.{HttpGet, HttpUriRequest}
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
import scala.concurrent.ExecutionContext
import spray.json._
object HttpClientSingleRequest {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("SingleRequest")
    implicit val executor: ExecutionContext = system.dispatcher
    val httpClient = HttpClientBuilder.create().build()
    val response = httpClient.execute(new HttpGet("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=5min&outputsize=full&apikey=demo"))
    val downloadActor = system.actorOf(Props[DownloadActor],"actorDownloading")
    downloadActor ! "Downloading"
    val entity = response.getEntity
    val str = EntityUtils.toString(entity,"UTF-8")
    //println("String: "+str)
    val jsonParser = str.parseJson
    println(jsonParser.asJsObject.fields)

    //SaveToDatabase.saveToDatabase(str)
  }
}
