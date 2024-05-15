package com.example.USEME_SpringServer.repository;

import com.example.USEME_SpringServer.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // public Task findTaskByID(Long id);
}
