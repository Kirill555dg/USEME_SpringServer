package com.example.USEME_SpringServer.controller;


import com.example.USEME_SpringServer.exception.UserAlreadyExistException;
import com.example.USEME_SpringServer.model.Student;
import com.example.USEME_SpringServer.model.Teacher;
import com.example.USEME_SpringServer.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public List<Teacher> findAllTeacher() {
        return teacherService.findAllTeacher();
    }

    @PostMapping("/registration")
    public Teacher saveTeacher(@RequestBody Teacher teacher) {
        return teacherService.registration(teacher);
    }

    @GetMapping("/teacher")
    public Teacher findByEmail(@RequestParam String email) {
        return teacherService.findByEmail(email);
    }

    @PutMapping("/update_teacher")
    public Teacher updateStudent(@RequestBody Teacher teacher) {
        return teacherService.updateTeacher(teacher);
    }

    @DeleteMapping("/delete_teacher")
    public void deleteStudent(@RequestParam String email) {
        teacherService.deleteTeacher(email);
    }

}
