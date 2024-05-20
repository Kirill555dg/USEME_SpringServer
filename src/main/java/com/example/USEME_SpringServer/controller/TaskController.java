package com.example.USEME_SpringServer.controller;

import com.example.USEME_SpringServer.exception.TaskNotFoundException;
import com.example.USEME_SpringServer.exception.UserAlreadyExistException;
import com.example.USEME_SpringServer.model.Task;
import com.example.USEME_SpringServer.model.Teacher;
import com.example.USEME_SpringServer.repository.TaskRepository;
import com.example.USEME_SpringServer.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id){
        return taskService.findById(id);
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        taskService.save(task);
        return task;
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task){
        return taskService.update(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.delete(id);
    }
}
