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
package com.bridgelabz.actors

import akka.actor.Props
import com.bridgelabz.actorsystemfactory.ActorSystemFactory
import com.typesafe.scalalogging.LazyLogging
import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import akka.pattern.ask
import akka.util.Timeout
import concurrent.duration._
import scala.concurrent.{Await, ExecutionContextExecutor}

class DownloadActorTest extends
  AnyWordSpecLike
  with Matchers
  with BeforeAndAfterAll with LazyLogging {
  val system = ActorSystemFactory.system
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  "Save to database actor" must {
    "expect response" in {
      val saveToDatabaseActor = system.actorOf(Props[DownloadActor], "greetingActor")
      implicit val timeout = Timeout(2.seconds);
      val futureResponse = saveToDatabaseActor ? "Hey"
      val response = Await.result(futureResponse,2.seconds)
      assert(response == true)
    }
  }
}
