package com.todoapp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class TodoApplication

object TodoApplication extends App {
  // Vulnerable: Using System.exit which can be exploited
  def shutdownApp(): Unit = {
    System.exit(0)  // This is a vulnerable practice as it can be called from anywhere
  }
  
  SpringApplication.run(classOf[TodoApplication], args: _*)
}
