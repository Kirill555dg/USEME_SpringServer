package com.example.USEME_SpringServer.repository;

import com.example.USEME_SpringServer.model.Student;
import com.example.USEME_SpringServer.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findTeacherByEmail(String email);
}
