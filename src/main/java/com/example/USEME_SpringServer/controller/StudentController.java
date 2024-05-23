package com.example.USEME_SpringServer.controller;

import com.example.USEME_SpringServer.model.Student;
import com.example.USEME_SpringServer.model.Teacher;
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
    public List<Student> findAllStudent() {
        return studentService.findAllStudent();
    }

    @PostMapping("/registration")
    public Student registerTeacher(@RequestBody Student student) {
        return studentService.registration(student);
    }
    @PostMapping("/authorization")
    public Student authorizeTeacher(@RequestBody Student student) {
        return studentService.authorization(student);
    }


    @GetMapping("/student")
    public Student findByEmail(@RequestParam String email) {
        return studentService.findByEmail(email);
    }

    @PatchMapping("/change_info")
    public Student changeInfo(@RequestBody Student student){
        return studentService.changeInfo(student);
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
