package com.example.USEME_SpringServer.repository;

import com.example.USEME_SpringServer.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByEmail(String email);

    void deleteByEmail(String email);

    boolean existsByEmail(String email);
}
