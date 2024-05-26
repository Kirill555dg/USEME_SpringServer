package com.example.USEME_SpringServer.repository;

import com.example.USEME_SpringServer.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, String> {

}
