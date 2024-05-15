package com.example.USEME_SpringServer.controller;

import com.example.USEME_SpringServer.exception.TaskNotFoundException;
import com.example.USEME_SpringServer.model.Task;
import com.example.USEME_SpringServer.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id){
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    /*
    @PostMapping
    public Task createTask(@RequestParam String subject,
                           @RequestParam String topic,
                           @RequestParam String category,
                           @RequestParam String condition,
                           @RequestParam String answer) {
        Task task = new Task(subject, topic, category, condition, answer);
        taskRepository.save(task);
        return task;
    }*/

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        taskRepository.saveAndFlush(task);
        return task;
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task){
        return taskRepository.findById(id)
                .map(oldTask -> {
                    oldTask.setSubject(task.getSubject());
                    oldTask.setTopic(task.getTopic());
                    oldTask.setCategory(task.getCategory());
                    oldTask.setCondition(task.getCondition());
                    oldTask.setAnswer(task.getAnswer());
                    return taskRepository.save(oldTask);
                })
                .orElseGet(() -> {
                    task.setId(id);
                    return taskRepository.save(task);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskRepository.deleteById(id);
    }
}
