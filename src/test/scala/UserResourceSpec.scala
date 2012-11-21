package com.example

import dispatch.Http
import org.specs._

object RequestBuilder {
  def GET(path: String)(implicit host : dispatch.Request):Tuple2[Int, String] = {
    val http = new Http
    http x  ((host / path) as_str) {
      case (code, _, _, getResponseBody) => (code, getResponseBody())
    }

  }

  def POST(path: String, body: String)(implicit host : dispatch.Request):Tuple2[Int, String] = {
    val http = new Http
    http x  ((host / path).POST << body as_str) {
      case (code, _, _, getResponseBody) => (code, getResponseBody())
    }

  }
}


object UserResourceSpec extends Specification with Served1 {
  import dispatch._

  def setup = { _.filter(new UserResource) }

  "The user resource" should {
    "report user name for user id 123" in {

      import RequestBuilder._

      GET("db/user/123") must_== (200, "Xuemin Guan")
      GET("db/user/456") must_== (200, "Dan")

    }
  }

  "The user resource" should {
    "accecpt posted body" in {

      import RequestBuilder._

      POST("db/user", "Body") must_== (200, "Received posted body: Body")
    }
  }
}
