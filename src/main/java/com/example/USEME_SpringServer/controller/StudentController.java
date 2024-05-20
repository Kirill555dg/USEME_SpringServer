package com.example.USEME_SpringServer.controller;

import com.example.USEME_SpringServer.model.Student;
import com.example.USEME_SpringServer.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> findStudent() {
        return studentService.findAllStudent();
    }

    @PostMapping("/registration")
    public String saveStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
        return "Student successfully saved";
    }

    @GetMapping("/student")
    public Student findByEmail(@RequestParam String email) {
        return studentService.findByEmail(email);
    }

    @PutMapping("/update_student")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/delete_student")
    public void deleteStudent(@RequestParam String email) {
        studentService.deleteStudent(email);
    }
}
