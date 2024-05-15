package com.example.USEME_SpringServer.service;


import com.example.USEME_SpringServer.exception.UserAlreadyExistException;
import com.example.USEME_SpringServer.model.Teacher;
import com.example.USEME_SpringServer.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher registration(Teacher teacher) throws UserAlreadyExistException {
        if (teacherRepository.findTeacherByEmail(teacher.getEmail()) != null) {
            throw new UserAlreadyExistException("Пользователь с такой почтой уже существует");
        }
        return teacherRepository.save(teacher);
    }

    public Teacher getTeacher(Long id) {
        return teacherRepository
                .findById(id)
                .orElse(null);
    }
}
