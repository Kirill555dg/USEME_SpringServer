package com.example.USEME_SpringServer.controller;

import com.example.USEME_SpringServer.model.Group;
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
    public List<Group> findAllTeacherGroups(@RequestParam Long id) {
        return groupService.findByTeacher(id);
    }

    @GetMapping("/{id}")
    public Group findGroup(@PathVariable Long id) {
        return groupService.findGroup(id);
    }

    @PostMapping("/create")
    public Group createGroup(@RequestBody Group group, @RequestParam Long teacher_id) {
        return groupService.create(group, teacher_id);
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
