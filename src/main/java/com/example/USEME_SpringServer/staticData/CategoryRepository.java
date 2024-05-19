package com.example.USEME_SpringServer.staticData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, TopicPK> {

    @Query(nativeQuery = true, value = "SELECT * FROM categories WHERE subject_name = ?1 and topic_num = ?2")
    List<Category> findAllByPk(String subjectName, Short topicNum);
}
