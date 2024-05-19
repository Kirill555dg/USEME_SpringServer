package com.example.USEME_SpringServer.staticData;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, TopicPK> {
    List<Topic> findAllByPkSubject(String pk_subject);
}
