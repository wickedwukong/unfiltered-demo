package com.example

import unfiltered.request._
import unfiltered.response.{ContentType, Ok, ResponseString}

class UserResource(userService: UserService = DependencyContainer.userService) extends unfiltered.filter.Plan {
  def intent = {
    case req@Path(Seg("db" :: "user" :: Nil)) => req match {
      case POST(_) => Ok ~> ContentType("application/json;charset=utf-8") ~> ResponseString("Received posted body: " + Body.string(req))
    }

    case req@Path(Seg("db" :: "user" :: userId :: Nil)) => req match {
      case GET(_) => Ok ~> ContentType("application/json;charset=utf-8") ~> ResponseString(userService.user(userId))
    }

  }
}


