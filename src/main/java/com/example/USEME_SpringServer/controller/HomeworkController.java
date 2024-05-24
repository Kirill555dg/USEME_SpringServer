package com.example.USEME_SpringServer.controller;

import com.example.USEME_SpringServer.model.Group;
import com.example.USEME_SpringServer.model.Homework;
import com.example.USEME_SpringServer.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/homeworks")
public class HomeworkController {

    @Autowired
    private HomeworkService homeworkService;

    @GetMapping
    public List<Homework> findAllHomeworks() {
        return homeworkService.findAllHomeworks();
    }

    @GetMapping("/{id}")
    public Homework findHomework(@PathVariable Long id) {
        return homeworkService.findHomeworkById(id);
    }

    @GetMapping("/group")
    public List<Homework> findAllTeacherGroups(@RequestParam("group_id") Long id) {
        return homeworkService.findByGroup(id);
    }

    @PostMapping("/create")
    public Homework createHomework(@RequestBody Homework homework) {
        return homeworkService.createHomework(homework);
    }

    @PutMapping("/{id}")
    public Homework updateHomework(@PathVariable Long id, @RequestBody Homework homework){
        return homeworkService.updateHomework(id, homework);
    }

    @DeleteMapping("/{id}")
    public void deleteHomework(@PathVariable Long id){
        homeworkService.deleteHomework(id);
    }

}
