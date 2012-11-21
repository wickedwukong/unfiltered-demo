package com.example

import javax.servlet.Filter
import org.specs._
import unfiltered.jetty.Server

abstract class HttpSpec extends Specification {

  def filter: Filter

  val port = 8080

  lazy val server: Server = unfiltered.jetty.Http.local(port).filter(filter).start()

  lazy val url = "http://hostName:" + port + "/"

  def startJetty = {
    server.start()
    Unit
  }

  def stopJetty = {
    server.stop()
    server.destroy()
    Unit
  }

  def before = {
    startJetty
  }

  def after = {
    stopJetty
  }
}