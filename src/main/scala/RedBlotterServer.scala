package com.example

import org.clapper.avsl.Logger

object RedBlotterServer {
  val logger = Logger(RedBlotterServer.getClass)

  def main(args: Array[String]) {
    val http = unfiltered.jetty.Http.anylocal // this will not be necessary in 0.4.0
    http.context("/assets") {
      _.resources(new java.net.URL(getClass().getResource("/www/css"), "."))
    }
      .filter(new App)
      .filter(new UserResource)
      .run({
      svr =>
        unfiltered.util.Browser.open(http.url)
    }, {
      svr =>
        logger.info("shutting down server")
    })
  }
}
