package com.example.USEME_SpringServer.repository;

import com.example.USEME_SpringServer.model.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, Long> {

    List<Homework> findByGroup_Id(Long group_id);

}
