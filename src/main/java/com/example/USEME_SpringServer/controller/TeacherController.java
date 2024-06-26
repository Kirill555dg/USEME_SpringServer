package com.example.USEME_SpringServer.controller;


import com.example.USEME_SpringServer.model.Teacher;
import com.example.USEME_SpringServer.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Teacher registerTeacher(@RequestBody Teacher teacher) {
        return teacherService.registration(teacher);
    }
    @PostMapping("/authorization")
    public Teacher authorizeTeacher(@RequestBody Teacher teacher) {
        return teacherService.authorization(teacher);
    }

    @GetMapping("/teacher")
    public Teacher findByEmail(@RequestParam String email) {
        return teacherService.findByEmail(email);
    }

    @PatchMapping("/change_info")
    public Teacher changeInfo(@RequestBody Teacher teacher){
        return teacherService.changeInfo(teacher);
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
