package com.example.todomanager.service;

import com.example.todomanager.entity.Project;
import com.example.todomanager.entity.Todo;
import com.example.todomanager.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Todo createTodoForProject(Long projectId,
                                     Todo todo,
                                     ProjectService projectService){
        Project project = projectService.getProjectById(projectId);
        todo.setProject(project);
        return todoRepository.save(todo);
    }

    public List<Todo> getTodosByProjectId(Long id){
        return todoRepository.findByProjectId(id);
    }

    public Todo updateTodoStatus(Long id, Boolean status) {
        Todo todo = todoRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setStatus(status);
        todo.setUpdatedDate(LocalDateTime.now());
        return todoRepository.save(todo);
    }

    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }

}
