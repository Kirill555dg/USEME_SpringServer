package com.example.USEME_SpringServer.controller;

import com.example.USEME_SpringServer.model.Group;
import com.example.USEME_SpringServer.model.invite.Application;
import com.example.USEME_SpringServer.service.GroupService;
import com.example.USEME_SpringServer.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public List<Group> findAllTeacherGroups() {
        return groupService.findAllGroups();
    }

    @GetMapping("/teacher")
    public List<Group> findAllTeacherGroups(@RequestParam("teacher_id") Long id) {
        return groupService.findByTeacher(id);
    }

    @GetMapping("/student")
    public List<Group> findAllStudentGroups(@RequestParam("student_id") Long id) {
        return applicationService.findGroupsByStudent(id);
    }

    @GetMapping("/{id}")
    public Group findGroup(@PathVariable Long id) {
        return groupService.findGroupById(id);
    }

    @PostMapping("/create")
    public Group createGroup(@RequestBody Group group) {
        return groupService.create(group);
    }

    @PutMapping("/{id}")
    public Group updateGroup(@PathVariable Long id, @RequestBody Group group){
        return groupService.update(id, group);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id){
        groupService.delete(id);
    }
}
