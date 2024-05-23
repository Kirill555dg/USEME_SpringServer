package com.example.USEME_SpringServer.service;

import com.example.USEME_SpringServer.exception.GroupNotFoundException;
import com.example.USEME_SpringServer.model.Group;
import com.example.USEME_SpringServer.model.Teacher;
import com.example.USEME_SpringServer.repository.GroupRepository;
import com.example.USEME_SpringServer.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private TeacherRepository teacherRepository;




    public List<Group> findByTeacher(Long id) {
        return groupRepository.findByTeacher_Id(id);
    }

    public Group create(Group group, Long teacher_id) {
        Teacher teacher = teacherRepository.findById(teacher_id).get();
        group.setTeacher(teacher);
        return groupRepository.save(group);
    }

    public Group findGroup(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Группы с id " + id + "не существует"));
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
}
