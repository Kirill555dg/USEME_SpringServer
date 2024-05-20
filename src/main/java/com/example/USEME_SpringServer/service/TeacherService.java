package com.example.USEME_SpringServer.service;


import com.example.USEME_SpringServer.exception.UserAlreadyExistException;
import com.example.USEME_SpringServer.exception.UserNotFoundException;
import com.example.USEME_SpringServer.model.Teacher;
import com.example.USEME_SpringServer.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher registration(Teacher teacher) {
        String email = teacher.getEmail();
        if (teacherRepository.existsByEmail(email)) {
            throw new UserAlreadyExistException(email);
        }
        return teacherRepository.saveAndFlush(teacher);
    }

    public List<Teacher> findAllTeacher() {
        return teacherRepository.findAll();
    }

    public Teacher findByEmail(String email) {
        return teacherRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepository.saveAndFlush(teacher);
    }

    public void deleteTeacher(String email) {
        teacherRepository.deleteByEmail(email);
    }

}
