package com.bridgelabz

import akka.actor.Actor

class DownloadActor extends Actor{
  val url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=5min&outputsize=full&apikey=demo"
  override def receive: Receive = {
    case message:String => println("Downloading data from " + url)
  }
}
