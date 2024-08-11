package com.example.todomanager.repository;

import com.example.todomanager.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository
        extends JpaRepository<Todo,Long> {
    List<Todo> findByProjectId(Long projectId);
}


