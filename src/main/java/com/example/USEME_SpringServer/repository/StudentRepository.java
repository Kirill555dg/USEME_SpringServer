package com.example.USEME_SpringServer.repository;

import com.example.USEME_SpringServer.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository - не нужно, так как наследуемся от JpaRepository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByEmail(String email);
    void deleteByEmail(String email);
}
