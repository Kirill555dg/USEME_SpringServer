package com.example.USEME_SpringServer.repository;

import com.example.USEME_SpringServer.model.topic.Topic;
import com.example.USEME_SpringServer.model.topic.TopicPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, TopicPK> {
    List<Topic> findAllByPkSubject(String pk_subject);
}
