package com.example.USEME_SpringServer.service;


import com.example.USEME_SpringServer.exception.UserAlreadyExistException;
import com.example.USEME_SpringServer.exception.UserNotFoundException;
import com.example.USEME_SpringServer.exception.WrongPasswordException;
import com.example.USEME_SpringServer.model.Teacher;
import com.example.USEME_SpringServer.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return teacherRepository.save(teacher);
    }
    public Teacher authorization(Teacher teacher) {
        String email = teacher.getEmail();
        String password = teacher.getPassword();
        Teacher real = teacherRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        if (!real.getPassword().equals(password)) {
            throw new WrongPasswordException();
        }
        return real;
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

    public Teacher changeInfo(Teacher teacher) {
        Teacher realTeacher = findByEmail(teacher.getEmail());
        realTeacher.setFirstName(teacher.getFirstName());
        realTeacher.setLastName(teacher.getLastName());
        realTeacher.setMiddleName(teacher.getMiddleName());
        realTeacher.setIsMale(teacher.getIsMale());
        realTeacher.setDateOfBirth(teacher.getDateOfBirth());
        return realTeacher;
    }
}
