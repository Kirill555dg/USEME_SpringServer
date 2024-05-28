package com.example.USEME_SpringServer.service;

import com.example.USEME_SpringServer.exception.NotFoundException;
import com.example.USEME_SpringServer.model.Group;
import com.example.USEME_SpringServer.model.Teacher;
import com.example.USEME_SpringServer.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private TeacherService teacherService;

    public List<Group> findByTeacher(Long id) {
        return groupRepository.findByTeacher_Id(id);
    }


    public List<Group> findAllGroups() {
        return groupRepository.findAll();
    }


    public List<Group> findByIds(List<Long> ids) {
        return groupRepository.findAllById(ids);
    }

    public Group create(Group group) {
        Teacher teacher = teacherService.findTeacherById(group.getTeacher().getId());
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
                .map(oldGroup -> {
                    oldGroup.setName(group.getName());
                    oldGroup.setDescription(group.getDescription());
                    oldGroup.setTargetSubject(group.getTargetSubject());
                    oldGroup.setPassword(group.getPassword());
                    return groupRepository.save(oldGroup);
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
