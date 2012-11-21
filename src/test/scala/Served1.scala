package com.example

import org.specs.Specification
import dispatch.{NoLogging, Http, Handler, :/}

trait Hosted1 extends Specification {
  val port = unfiltered.util.Port.any
  implicit val host = :/("localhost", port)

  def logHttpRequests = false

  /** Silent, resource-managed http request executor */
  def http[T](handler: Handler[T]): T = {
    val h = if (logHttpRequests) new Http else new Http with NoLogging
    try {
      h(handler)
    }
    finally {
      h.shutdown()
    }
  }

  /** Silent, resource-managed http request executor which accepts
    * non-ok status */
  def xhttp[T](handler: dispatch.Handler[T]): T = {
    val h = if (logHttpRequests) new Http else new Http with NoLogging
    try {
      h.x(handler)
    }
    finally {
      h.shutdown()
    }
  }
}


trait Served1 extends Hosted1 {
  shareVariables()

  import unfiltered.jetty._

  def setup: (Server => Server)

  lazy val server = setup(unfiltered.jetty.Http(port))

  doBeforeSpec {
    server.start()
  }
  doAfterSpec {
    server.stop();
    server.destroy()
  }
}
