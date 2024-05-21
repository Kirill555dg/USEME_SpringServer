package com.example.USEME_SpringServer.service;

import com.example.USEME_SpringServer.exception.UserAlreadyExistException;
import com.example.USEME_SpringServer.exception.UserNotFoundException;
import com.example.USEME_SpringServer.exception.WrongPasswordException;
import com.example.USEME_SpringServer.model.Student;
import com.example.USEME_SpringServer.model.Teacher;
import com.example.USEME_SpringServer.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }

    public Student registration(Student student) {
        String email = student.getEmail();
        if (studentRepository.existsByEmail(email)) {
            throw new UserAlreadyExistException(email);
        }
        return studentRepository.saveAndFlush(student);
    }
    public Student authorization(Student student) {
        String email = student.getEmail();
        String password = student.getPassword();
        Student real = studentRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        if (!real.getPassword().equals(password)) {
            throw new WrongPasswordException();
        }
        return real;
    }

    public Student findByEmail(String email) {
        return studentRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(String email) {
        studentRepository.deleteByEmail(email);
    }
}
