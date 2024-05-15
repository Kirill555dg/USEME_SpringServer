package com.example.USEME_SpringServer.controller;

import com.example.USEME_SpringServer.model.Student;
import com.example.USEME_SpringServer.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public List<Student> findAllStudent() {

        //return service.findAllStudent();
        return null;
    }

    @PostMapping("save_student")
    public String saveStudent(@RequestBody Student student) {
        /*service.saveStudent(student);
        return "Student successfully saved";*/
        return null;
    }

    @GetMapping("/{email}")
    public Student findByEmail(@PathVariable String email) {
        // return service.findByEmail(email);
        return null;
    }

    @PutMapping("update_student")
    public Student updateStudent(@RequestBody Student student) {
        //return service.updateStudent(student);
        return null;
    }

    @DeleteMapping("delete_student/{email}")
    public void deleteStudent(@PathVariable String email) {
        //service.deleteStudent(email);
    }
}
