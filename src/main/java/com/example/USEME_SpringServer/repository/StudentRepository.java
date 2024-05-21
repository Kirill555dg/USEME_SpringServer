package com.example.USEME_SpringServer.repository;

import com.example.USEME_SpringServer.model.Student;
import com.example.USEME_SpringServer.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Repository - не нужно, так как наследуемся от JpaRepository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    void deleteByEmail(String email);

    boolean existsByEmail(String email);
}
