package com.bridgelabz

import org.scalatest.flatspec.AsyncFlatSpec

import scala.concurrent.Future

class AsyncTest extends AsyncFlatSpec {
  behavior of "sayHelloTo"
  it should "return a valid message on a valid name" in {
    val futureHarry : Future[Option[String]] = AsynchMain.sayHelloTo("Harry")
    futureHarry map { res => assert(res == Some("Hello Harry, welcome to the future world!")) }
  }
  it should "return a valid message on an invalid name" in {
    val futureVoldemort : Future[Option[String]] = AsynchMain.sayHelloTo("Voldemort")
    futureVoldemort map { res => assert(res == None) }
  }
}
