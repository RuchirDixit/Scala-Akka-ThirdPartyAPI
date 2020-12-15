package com.bridgelabz
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object AsynchMain {
  val theOneWhoCannotBeNamed = "Voldemort"

  def sayHelloTo(name: String): Future[Option[String]] = {
    Future {
      if (theOneWhoCannotBeNamed.equals(name)) None else Some(getHelloMessage(name))
    }
  }

  def getHelloMessage(name: String): String = {
    s"Hello ${name}, welcome to the future world!"
  }
}
