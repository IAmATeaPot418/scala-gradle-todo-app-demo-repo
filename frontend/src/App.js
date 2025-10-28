import React, { useState, useEffect } from 'react';
import axios from 'axios';

function App() {
  const [todos, setTodos] = useState([]);
  const [newTodo, setNewTodo] = useState('');
  const [userToken, setUserToken] = useState(''); // Vulnerable: storing sensitive data in state

  useEffect(() => {
    // Vulnerable: No error handling
    axios.get('http://localhost:8080/api/todos/')
      .then(response => setTodos(response.data));
  }, []);

  const addTodo = () => {
    // Vulnerable: No input validation
    const todo = {
      title: newTodo,
      completed: false,
      userToken: userToken // Vulnerable: Sending sensitive data in plain text
    };

    // Vulnerable: No error handling
    axios.post('http://localhost:8080/api/todos/', todo)
      .then(response => {
        setTodos([...todos, response.data]);
        setNewTodo('');
      });
  };

  // Vulnerable: Command injection possibility
  const executeCommand = () => {
    // Vulnerable: Direct command execution
    axios.get(`http://localhost:8080/api/todos/execute?command=${newTodo}`)
      .then(response => console.log(response.data));
  };

  return (
    <div>
      <h1>Todo List</h1>
      <div>
        <input
          type="text"
          value={newTodo}
          onChange={(e) => setNewTodo(e.target.value)}
          placeholder="Enter new todo"
        />
        <button onClick={addTodo}>Add Todo</button>
        <button onClick={executeCommand}>Execute Command</button> {/* Vulnerable: Exposing dangerous functionality */}
      </div>
      <ul>
        {todos.map(todo => (
          <li key={todo.id}>
            {todo.title} - {todo.completed ? 'Completed' : 'Pending'}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
