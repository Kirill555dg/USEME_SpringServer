package com.example.USEME_SpringServer.service;

import com.example.USEME_SpringServer.model.Task;
import com.example.USEME_SpringServer.repository.TaskRepository;
import com.example.USEME_SpringServer.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
}
