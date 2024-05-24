package com.example.USEME_SpringServer.controller;

import com.example.USEME_SpringServer.model.Group;
import com.example.USEME_SpringServer.model.Student;
import com.example.USEME_SpringServer.model.Teacher;
import com.example.USEME_SpringServer.model.invite.Invite;
import com.example.USEME_SpringServer.service.InviteService;
import com.example.USEME_SpringServer.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private InviteService inviteService;

    @GetMapping
    public List<Student> findAllStudent() {
        return studentService.findAllStudent();
    }


    @GetMapping("/student")
    public Student findByEmail(@RequestParam String email) {
        return studentService.findByEmail(email);
    }

    @GetMapping("/group")
    public List<Student> findStudentByGroup(@RequestParam(name = "group_id") Long groupId) {
        return inviteService.findStudentsByGroup(groupId);
    }

    @PostMapping("/registration")
    public Student registerTeacher(@RequestBody Student student) {
        return studentService.registration(student);
    }

    @PostMapping("/authorization")
    public Student authorizeTeacher(@RequestBody Student student) {
        return studentService.authorization(student);
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


    @GetMapping("/invites")
    public List<Group> getInvites(@RequestParam(name = "student_id") Long studentId) {
        return inviteService.getInvites(studentId);
    }

    @PutMapping("/invites")
    public Invite acceptInvite(@RequestParam(name = "student_id") Long studentId,
                               @RequestParam(name = "group_id") Long groupId) {
        return inviteService.accpetInvite(studentId, groupId);
    }

    @DeleteMapping("/invites")
    public void deleteInvite(@RequestParam(name = "student_id") Long studentId,
                               @RequestParam(name = "group_id") Long groupId) {
        inviteService.deleteInvite(studentId, groupId);
    }
}
