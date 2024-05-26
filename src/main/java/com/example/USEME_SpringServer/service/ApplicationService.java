package com.example.USEME_SpringServer.service;

import com.example.USEME_SpringServer.exception.AlreadyExistException;
import com.example.USEME_SpringServer.exception.NotFoundException;
import com.example.USEME_SpringServer.exception.WrongPasswordException;
import com.example.USEME_SpringServer.model.Group;
import com.example.USEME_SpringServer.model.Student;
import com.example.USEME_SpringServer.model.invite.Application;
import com.example.USEME_SpringServer.model.invite.ApplicationPK;
import com.example.USEME_SpringServer.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository inviteRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private GroupService groupService;

    public List<Application> getAllApplications(){
        return inviteRepository.findAll();
    }

    public Application findApplication(ApplicationPK pk) {
        return inviteRepository
                .findById(pk)
                .orElseThrow(() -> new NotFoundException(
                        "Учащийся с id " + pk.getStudent() +
                                " не посылал заявку на вступление в группу с id " + pk.getGroup()));
    }

    public Application sendInvite(ApplicationPK pk) {
        Long groupId = pk.getGroup().getId();
        Long studentId = pk.getStudent().getId();
        Group group = groupService.findGroupById(groupId);
        if (!group.getPassword().equals(pk.getGroup().getPassword())) {
            throw new WrongPasswordException("Введен неверный пароль для вступления");
        }
        Student student = studentService.findStudentById(studentId);
        if (inviteRepository.existsById(pk)) {
            throw new AlreadyExistException("Заявка на вступление уже сущетсвует");
        }
        Application application = new Application(pk);
        application.setIsAccept(false);
        return inviteRepository.save(application);
    }


    public Application acceptInvite(ApplicationPK pk) {
        Long groupId = pk.getGroup().getId();
        Long studentId = pk.getStudent().getId();
        Group group = groupService.findGroupById(groupId);
        Student student = studentService.findStudentById(studentId);
        Application application = findApplication(pk);
        if (application.getIsAccept()) {
            throw new AlreadyExistException("Заявка уже принята");
        }
        application.setIsAccept(true);
        return inviteRepository.save(application);
    }

    public void deleteInvite(ApplicationPK pk) {
        Long groupId = pk.getGroup().getId();
        Long studentId = pk.getStudent().getId();
        Group group = groupService.findGroupById(groupId);
        Student student = studentService.findStudentById(studentId);
        Application application = findApplication(pk);
        inviteRepository.delete(application);
    }

    public List<Student> findStudentsByGroup(Long groupId) {
        Group group = groupService.findGroupById(groupId);
        List<Application> applications = inviteRepository.findAllByIsAcceptTrueAndPk_Group(group);
        List<Long> studentIds = new ArrayList<>();
        for (Application application : applications) {
            studentIds.add(application.getPk().getStudent().getId());
        }
        return studentService.findByIds(studentIds);
    }

    public List<Group> findGroupsByStudent(Long studentId) {
        Student student = studentService.findStudentById(studentId);
        List<Application> applications = inviteRepository.findAllByIsAcceptTrueAndPk_Student(student);
        List<Long> groupIds = new ArrayList<>();
        for (Application application : applications) {
            groupIds.add(application.getPk().getGroup().getId());
        }
        return groupService.findByIds(groupIds);
    }

    public List<Group> getInvites(Long studentId) {
        Student student = studentService.findStudentById(studentId);
        List<Application> applications = inviteRepository.findAllByIsAcceptFalseAndPk_Student(student);
        List<Long> groupIds = new ArrayList<>();
        for (Application application : applications) {
            groupIds.add(application.getPk().getGroup().getId());
        }
        return groupService.findByIds(groupIds);
    }

    public List<Student> getApplications(Long groupId) {
        Group group = groupService.findGroupById(groupId);
        List<Application> applications = inviteRepository.findAllByIsAcceptFalseAndPk_Group(group);
        List<Long> studentIds = new ArrayList<>();
        for (Application application : applications) {
            studentIds.add(application.getPk().getStudent().getId());
        }
        return studentService.findByIds(studentIds);
    }
}
