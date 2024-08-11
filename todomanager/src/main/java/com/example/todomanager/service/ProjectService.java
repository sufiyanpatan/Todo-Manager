package com.example.todomanager.service;

import com.example.todomanager.entity.Project;
import com.example.todomanager.entity.Todo;
import com.example.todomanager.repository.ProjectRepository;
import com.example.todomanager.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project){
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long projectId){
        return projectRepository.findById(projectId).orElseThrow(()
                -> new RuntimeException("project not found"));
    }

    public Project updateProject(Long id, Project projectDetails){
        Project project = getProjectById(id);
        project.setTitle(projectDetails.getTitle());
        return projectRepository.save(project);
    }

    public void deleteProject(Long projectId){
        projectRepository.deleteById(projectId);
    }

}
