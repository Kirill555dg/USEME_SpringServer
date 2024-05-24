package com.example.USEME_SpringServer.controller;

import com.example.USEME_SpringServer.model.Group;
import com.example.USEME_SpringServer.model.invite.Invite;
import com.example.USEME_SpringServer.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public List<Group> findAllTeacherGroups(@RequestParam("teacher_id") Long id) {
        return groupService.findByTeacher(id);
    }

    @GetMapping("/{id}")
    public Group findGroup(@PathVariable Long id) {
        return groupService.findGroupById(id);
    }

    @PostMapping("/create")
    public Group createGroup(@RequestBody Group group, @RequestParam(name = "teacher_id") Long teacherId) {
        return groupService.create(group, teacherId);
    }

    @PostMapping("/send_invite")
    public Invite sendInvite(@RequestParam(name = "group_id") Long groupId, @RequestParam(name = "student_id") Long studentId){
        return groupService.sendInvite(groupId, studentId);
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
