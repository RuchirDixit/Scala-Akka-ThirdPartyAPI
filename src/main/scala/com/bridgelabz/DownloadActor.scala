package com.bridgelabz

import akka.actor.Actor
import akka.event.Logging
// Actors to receive string message and print result
class DownloadActor extends Actor{
  val log = Logging(context.system, this)
  val url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=5min&outputsize=full&apikey=demo"
  override def receive: Receive = {
    case _:String => log.info("Downloading data from " + url)

  }
}
