package com.bridgelabz
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object AsyncMain {
  val theOneWhoCannotBeNamed = "Voldemort"

  /**
   *
   * @param name : name to be checked and printed
   * @return : Future object of type string
   */
  def sayHelloTo(name: String): Future[Option[String]] = {
    Future {
      if (theOneWhoCannotBeNamed.equals(name)) None else Some(getHelloMessage(name))
    }
  }

  /**
   *
   * @param name : name to print inside the message
   * @return : String message
   */
  def getHelloMessage(name: String): String = {
    s"Hello ${name}, welcome to the future world!"
  }
}
