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
}
