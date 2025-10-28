package com.todoapp.repository

import com.todoapp.model.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
trait TodoRepository extends JpaRepository[Todo, Long] {
  // Vulnerable: SQL injection possible through direct query
  def findByTitleRaw(title: String): java.util.List[Todo] = {
    // This is intentionally vulnerable to SQL injection
    val query = s"SELECT * FROM todo WHERE title = '$title'"
    // In a real app, this would execute the raw SQL query
    null
  }
}
