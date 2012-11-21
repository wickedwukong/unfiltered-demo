package com.example


object DependencyContainer {
  val userRepository = new MongoUserRepository()
  val userService: UserService = new UserService(userRepository)

}
