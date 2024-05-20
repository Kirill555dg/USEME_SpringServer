package com.example.USEME_SpringServer.service;

import com.example.USEME_SpringServer.model.Student;
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

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public Student findByEmail(String email) {
        return studentRepository.findStudentByEmail(email);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(String email) {
        studentRepository.deleteByEmail(email);
    }
}
