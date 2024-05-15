package com.example.USEME_SpringServer.controller;


import com.example.USEME_SpringServer.exception.UserAlreadyExistException;
import com.example.USEME_SpringServer.model.Teacher;
import com.example.USEME_SpringServer.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody Teacher teacher) {
        try {
            teacherService.registration(teacher);
            return ResponseEntity.ok("Пользователь успешно сохранен");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getTeacher(@PathVariable Long id){
        return ResponseEntity.notFound().build();
    }

}
