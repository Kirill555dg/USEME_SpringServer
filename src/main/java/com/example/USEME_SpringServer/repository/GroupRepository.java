package com.example.USEME_SpringServer.repository;

import com.example.USEME_SpringServer.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findByTeacher_Id(Long teacher_id);
}
