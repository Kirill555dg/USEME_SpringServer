package com.example.USEME_SpringServer.service;

import com.example.USEME_SpringServer.exception.AlreadyExistException;
import com.example.USEME_SpringServer.exception.NotFoundException;
import com.example.USEME_SpringServer.exception.WrongPasswordException;
import com.example.USEME_SpringServer.model.Group;
import com.example.USEME_SpringServer.model.Student;
import com.example.USEME_SpringServer.model.invite.Invite;
import com.example.USEME_SpringServer.model.invite.InvitePK;
import com.example.USEME_SpringServer.repository.InviteRepository;
import com.example.USEME_SpringServer.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }


    public List<Student> findByIds(List<Long> ids) {
        return studentRepository.findAllById(ids);
    }


    public Student registration(Student student) {
        String email = student.getEmail();
        if (studentRepository.existsByEmail(email)) {
            throw new AlreadyExistException("Учащийся с почтой " + email +" уже существует");
        }
        return studentRepository.save(student);
    }
    public Student authorization(Student student) {
        String email = student.getEmail();
        String password = student.getPassword();
        Student real = findByEmail(email);

        if (!real.getPassword().equals(password)) {
            throw new WrongPasswordException();
        }
        return real;
    }

    public Student findByEmail(String email) {
        return studentRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Ученика с почтой " + email + " не существует"));
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(String email) {
        studentRepository.deleteByEmail(email);
    }

    public Student changeInfo(Student student) {

        Student realStudent = findByEmail(student.getEmail());
        realStudent.setFirstName(student.getFirstName());
        realStudent.setLastName(student.getLastName());
        realStudent.setIsMale(student.getIsMale());
        realStudent.setDateOfBirth(student.getDateOfBirth());
        return realStudent;
    }

    public Student findStudentById(Long id) {
        return studentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Ученика с id " + id + " не существует"));
    }

}
