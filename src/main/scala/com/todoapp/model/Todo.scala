package com.todoapp.model

import javax.persistence.{Entity, GeneratedValue, GenerationType, Id}
import scala.beans.BeanProperty

@Entity
class Todo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @BeanProperty
  var id: Long = _

  @BeanProperty
  var title: String = _

  @BeanProperty
  var completed: Boolean = false

  // Vulnerable: Storing sensitive information in plain text
  @BeanProperty
  var userToken: String = _  // This should be encrypted but isn't

  // Vulnerable: No input validation
  def setTitleUnsafe(input: String): Unit = {
    title = input  // Direct assignment without sanitization
  }
}
