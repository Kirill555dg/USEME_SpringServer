package com.example.USEME_SpringServer.service;

import com.example.USEME_SpringServer.exception.GlobalExceptionHandler;
import com.example.USEME_SpringServer.exception.TaskNotFoundException;
import com.example.USEME_SpringServer.model.Task;
import com.example.USEME_SpringServer.repository.TaskRepository;
import com.example.USEME_SpringServer.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }
    public Task findById(Long id) {
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public void save(Task task) {
        taskRepository.saveAndFlush(task);
    }

    public Task update(Long id, Task task) {
        return taskRepository
                .findById(id)
                .map(oldTask -> {
                    oldTask.setTopicPK(task.getTopicPK());
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

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
