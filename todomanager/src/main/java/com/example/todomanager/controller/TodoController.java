package com.example.todomanager.controller;

import com.example.todomanager.entity.Todo;
import com.example.todomanager.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodoStatus(@PathVariable("id") Long id, @RequestBody Boolean status){
        Todo updatedTodo = todoService.updateTodoStatus(id, status);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTodoById(@PathVariable("id") Long id){
        todoService.deleteTodoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
