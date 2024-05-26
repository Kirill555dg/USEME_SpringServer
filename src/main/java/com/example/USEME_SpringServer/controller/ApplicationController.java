package com.example.USEME_SpringServer.controller;

import com.example.USEME_SpringServer.model.Group;
import com.example.USEME_SpringServer.model.Student;
import com.example.USEME_SpringServer.model.invite.Application;
import com.example.USEME_SpringServer.model.invite.ApplicationPK;
import com.example.USEME_SpringServer.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/invites")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public List<Application> getAllApplications(){
        return applicationService.getAllApplications();
    }

    @GetMapping("/student")
    public List<Group> getInvites(@RequestParam(name = "student_id") Long studentId) {
        return applicationService.getInvites(studentId);
    }
    @GetMapping("/group")
    public List<Student> getApplications(@RequestParam(name = "group_id") Long groupId) {
        return applicationService.getApplications(groupId);
    }

    @PutMapping
    public Application acceptInvite(@RequestBody ApplicationPK applicationPK) {
        return applicationService.acceptInvite(applicationPK);
    }

    @PostMapping
    public Application sendInvite(@RequestBody ApplicationPK applicationPK){
        return applicationService.sendInvite(applicationPK);
    }

    @DeleteMapping
    public void deleteInvite(@RequestBody ApplicationPK applicationPK) {
        applicationService.deleteInvite(applicationPK);
    }
}
