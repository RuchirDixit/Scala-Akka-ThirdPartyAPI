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
package com.bridgelabz.services

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
import org.scalatest.matchers.should
import org.scalatest.wordspec.AnyWordSpec
import org.mockito.Mockito.when
import org.scalatestplus.mockito.MockitoSugar
import play.api.libs.json.{JsObject, JsResultException, JsValue, Json}

class ThirdPartyServiceTest extends AnyWordSpec with should.Matchers with MockitoSugar {
  // Test case To check if user exists
  val httpClient = HttpClientBuilder.create().build()
  val response = httpClient.execute(new
      HttpGet("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=5min&outputsize=full&apikey=demo"))
  val entity = response.getEntity
  val stringToParse = EntityUtils.toString(entity,"UTF-8")
  val jsonParser = Json.parse(stringToParse)
  // Save to Database

  // Save to CSV
 // SaveService.saveToCSV(jsonParser.as[JsObject].fields(1))
//  "Check if exists" should {
//    "return Added" in {
//      val serbice =mock[(String,JsValue)]
//      when(serbice._2).thenReturn(JsValue)
//      val status = SaveService.saveToDatabase(jsonParser.as[JsObject].fields(1))
//      assert(status == "Added")
//    }
//  }
}
