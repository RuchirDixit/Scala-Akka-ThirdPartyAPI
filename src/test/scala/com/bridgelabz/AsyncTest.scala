package com.bridgelabz

import akka.actor.{ActorSystem, Props}
import akka.testkit.{TestActorRef, TestProbe}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.flatspec.{AnyFlatSpec, AsyncFlatSpec}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AsyncTest extends AnyFlatSpec {
  behavior of "sayHelloTo"
  it should "return a valid message on a valid name" in {
    val futureHarry : Future[Option[String]] = AsyncMain.sayHelloTo("Harry")
    futureHarry map { res => assert(res == Some("Hello Harry, welcome to the future world!")) }
  }
  it should "return a valid message on an invalid name" in {
    val futureVoldemort : Future[Option[String]] = AsyncMain.sayHelloTo("Voldemort")
    futureVoldemort map { res => assert(res == None) }
  }

  it should "return true if string is passed" in {
    implicit val system = ActorSystem("TestActor")
    val probe = TestProbe()
    val testActor = TestActorRef[DownloadActor]
    probe.send(testActor,"DownloadData")
    probe.expectMsg(true)
  }

}
