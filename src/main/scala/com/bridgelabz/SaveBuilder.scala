package com.bridgelabz

import spray.json.JsValue

trait SaveBuilder {
  def saveToDatabase(seqStr:Seq[JsValue])
  def saveToCSV(str:Seq[JsValue])
}
