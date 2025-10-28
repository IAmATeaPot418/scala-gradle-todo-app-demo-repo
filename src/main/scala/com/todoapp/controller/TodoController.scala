package com.todoapp.controller

import com.todoapp.model.Todo
import com.todoapp.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._
import scala.collection.JavaConverters._
import java.io.File

@RestController
@RequestMapping(Array("/api/todos"))
@CrossOrigin(Array("http://localhost:3000"))  // Vulnerable: Allowing all methods and headers
class TodoController @Autowired()(private val todoRepository: TodoRepository) {

  @GetMapping(Array("/"))
  def getAllTodos: java.util.List[Todo] = {
    todoRepository.findAll()
  }

  @PostMapping(Array("/"))
  def createTodo(@RequestBody todo: Todo): Todo = {
    todoRepository.save(todo)
  }

  // Vulnerable: Directory traversal
  @GetMapping(Array("/file/{filename}"))
  def getFile(@PathVariable filename: String): String = {
    // Vulnerable: Direct file access without validation
    val file = new File(filename)
    scala.io.Source.fromFile(file).mkString
  }

  // Vulnerable: Command injection
  @GetMapping(Array("/execute"))
  def executeCommand(@RequestParam command: String): String = {
    // Vulnerable: Direct command execution
    import sys.process._
    command.!!
  }

  @DeleteMapping(Array("/{id}"))
  def deleteTodo(@PathVariable id: Long): Unit = {
    todoRepository.deleteById(id)
  }
}
