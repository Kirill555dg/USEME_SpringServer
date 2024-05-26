package com.example.USEME_SpringServer.repository;

import com.example.USEME_SpringServer.model.Group;
import com.example.USEME_SpringServer.model.Student;
import com.example.USEME_SpringServer.model.invite.Application;
import com.example.USEME_SpringServer.model.invite.ApplicationPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, ApplicationPK> {

    List<Application> findAllByIsAcceptTrueAndPk_Group(Group pk_group);
    List<Application> findAllByIsAcceptTrueAndPk_Student(Student pk_student);

    List<Application> findAllByIsAcceptFalseAndPk_Student(Student pk_student);
    List<Application> findAllByIsAcceptFalseAndPk_Group(Group pk_group);
}
