package com.example.USEME_SpringServer.service;

import com.example.USEME_SpringServer.exception.AlreadyExistException;
import com.example.USEME_SpringServer.exception.NotFoundException;
import com.example.USEME_SpringServer.model.Group;
import com.example.USEME_SpringServer.model.Student;
import com.example.USEME_SpringServer.model.Teacher;
import com.example.USEME_SpringServer.model.invite.Invite;
import com.example.USEME_SpringServer.model.invite.InvitePK;
import com.example.USEME_SpringServer.repository.GroupRepository;
import com.example.USEME_SpringServer.repository.InviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private InviteRepository inviteRepository;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    public List<Group> findByTeacher(Long id) {
        return groupRepository.findByTeacher_Id(id);
    }

    public Group create(Group group, Long teacher_id) {
        Teacher teacher = teacherService.findTeacherById(teacher_id);
        group.setTeacher(teacher);
        return groupRepository.save(group);
    }

    public Group findGroupById(Long id) {
        return groupRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Группы с id " + id + " не существует"));
    }

    public Group update(Long id, Group group) {
        return groupRepository
                .findById(id)
                .map(oldTask -> {
                    oldTask.setName(group.getName());
                    oldTask.setDescription(group.getDescription());
                    oldTask.setTargetSubject(group.getTargetSubject());
                    return groupRepository.save(oldTask);
                })
                .orElseGet(() -> {
                    group.setId(id);
                    return groupRepository.save(group);
                });
    }

    public void delete(Long id) {
        groupRepository.deleteById(id);
    }

    public Invite sendInvite(Long groupId, Long studentId) {

        Group group = findGroupById(groupId);
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
}
