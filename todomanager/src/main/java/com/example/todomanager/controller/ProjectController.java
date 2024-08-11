package com.example.todomanager.controller;

import com.example.todomanager.entity.Project;
import com.example.todomanager.entity.Todo;
import com.example.todomanager.service.ProjectService;
import com.example.todomanager.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project){
        Project newProject = projectService.createProject(project);
        return new ResponseEntity<>(newProject, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") Long projectId){
        Project project = projectService.getProjectById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") Long projectId, @RequestBody Project projectDetails){
        Project updatedProject = projectService.updateProject(projectId, projectDetails);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable("id") Long projectId){
        projectService.deleteProject(projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{projectId}/todos")
    public ResponseEntity<Todo> createTodoForProject (@PathVariable("projectId") Long projectId, @RequestBody Todo todo){
        Todo newTodo = todoService.createTodoForProject(projectId, todo, projectService);
        return  new ResponseEntity<>(newTodo, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}/todos")
    public List<Todo> getTodosByProjectId(@PathVariable("projectId") Long projectId){
        return todoService.getTodosByProjectId(projectId);
    }

}
