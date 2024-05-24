package com.example.USEME_SpringServer.service;

import com.example.USEME_SpringServer.exception.AlreadyExistException;
import com.example.USEME_SpringServer.exception.NotFoundException;
import com.example.USEME_SpringServer.model.Group;
import com.example.USEME_SpringServer.model.Student;
import com.example.USEME_SpringServer.model.invite.Invite;
import com.example.USEME_SpringServer.model.invite.InvitePK;
import com.example.USEME_SpringServer.repository.InviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InviteService {

    @Autowired
    private InviteRepository inviteRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private GroupService groupService;



    public Invite sendInvite(Long groupId, Long studentId) {

        Group group = groupService.findGroupById(groupId);
        Student student = studentService.findStudentById(studentId);
        InvitePK pk = new InvitePK(student, group);
        if (inviteRepository.existsById(pk)) {
            throw new AlreadyExistException("Приглашение на вступление уже сущетсвует");
        }
        Invite invite = new Invite();
        invite.setPk(pk);
        invite.setIsAccept(false);
        return inviteRepository.save(invite);
    }


    public Invite accpetInvite(Long studentId, Long groupId) {
        Group group = groupService.findGroupById(groupId);
        Student student = studentService.findStudentById(studentId);
        InvitePK pk = new InvitePK(student, group);
        Invite invite = inviteRepository
                .findById(pk)
                .orElseThrow(() -> new NotFoundException("Учащемуся с id " + studentId + " приглашение в группу с id " + groupId + " не поступало"));
        if (invite.getIsAccept()) {
            throw new AlreadyExistException("Приглашение уже принято");
        }
        invite.setIsAccept(true);
        return inviteRepository.save(invite);
    }

    public void deleteInvite(Long groupId, Long studentId) {
        Group group = groupService.findGroupById(groupId);
        Student student = studentService.findStudentById(studentId);
        InvitePK pk = new InvitePK(student, group);
        Invite invite = inviteRepository
                .findById(pk)
                .orElseThrow(() -> new NotFoundException("Учащемуся с id " + studentId + " приглашение в группу с id " + groupId + " не поступало"));
        inviteRepository.delete(invite);
    }

    public List<Student> findStudentsByGroup(Long groupId) {
        Group group = groupService.findGroupById(groupId);
        List<Invite> invites = inviteRepository.findAllByIsAcceptTrueAndPk_Group(group);
        List<Long> studentIds = new ArrayList<>();
        for (Invite invite : invites) {
            studentIds.add(invite.getPk().getStudent());
        }
        return studentService.findByIds(studentIds);
    }

    public List<Group> findGroupsByStudent(Long studentId) {
        Student student = studentService.findStudentById(studentId);
        List<Invite> invites = inviteRepository.findAllByIsAcceptTrueAndPk_Student(student);
        List<Long> groupIds = new ArrayList<>();
        for (Invite invite : invites) {
            groupIds.add(invite.getPk().getGroup());
        }
        return groupService.findByIds(groupIds);
    }

    public List<Group> getInvites(Long studentId) {
        Student student = studentService.findStudentById(studentId);
        List<Invite> invites = inviteRepository.findAllByIsAcceptFalseAndPk_Student(student);
        List<Long> groupIds = new ArrayList<>();
        for (Invite invite : invites) {
            groupIds.add(invite.getPk().getGroup());
        }
        return groupService.findByIds(groupIds);
    }
}
