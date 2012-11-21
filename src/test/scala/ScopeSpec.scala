package com.example


import collection.mutable.ListBuffer
import org.specs2.mutable.{BeforeAfter, Specification}
import org.specs2.specification.Scope


class ScopeResourceSpec extends Specification {


  "The user resource" should {
    "report user name for user id 123" in new mysetup[Int] {

      def mydata = 100

      db(0) must_== 100
    }
  }

  "The user resource" should {
    "accecpt posted body" in new mysetup[String] {

      def mydata: String = "string data"


      db(0) must_== "string data"
    }
  }
}

trait mysetup[T] extends Scope with BeforeAfter {
  def mydata: T

  val db: scala.collection.mutable.Buffer[T] = ListBuffer[T]()

  def before = {
    db += mydata
    println("setting up " + mydata)
  }

  def after = {
    println("tearing down " + mydata)
  }

}

