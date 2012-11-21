package com.example

class UserService(repository: UserRepository) {
  def user(id: String):String = {
    doSomethingFoo()
    repository.user(id)
  }

  private def doSomethingFoo() ={
    //pretending to be doing something here.
  }
}

trait UserRepository {
  def user(id: String): String
}

class MongoUserRepository extends UserRepository {
  def user(id: String) = id match {
    case "123" => "Xuemin Guan"
    case "456" => "Dan"
    case _ => "John catches all!"
  }
}
