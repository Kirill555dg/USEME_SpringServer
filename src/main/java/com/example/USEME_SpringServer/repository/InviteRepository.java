package com.example.USEME_SpringServer.repository;

import com.example.USEME_SpringServer.model.Group;
import com.example.USEME_SpringServer.model.Student;
import com.example.USEME_SpringServer.model.invite.Invite;
import com.example.USEME_SpringServer.model.invite.InvitePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InviteRepository extends JpaRepository<Invite, InvitePK> {

    List<Invite> findAllByIsAcceptTrueAndPk_Group(Group pk_group);
    List<Invite> findAllByIsAcceptTrueAndPk_Student(Student pk_student);

    List<Invite> findAllByIsAcceptFalseAndPk_Student(Student pk_student);
}
