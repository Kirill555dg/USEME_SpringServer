package com.example.USEME_SpringServer.repository;

import com.example.USEME_SpringServer.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


    @Query("select t from Task t where "
            +"(:id = -1 or t.id = :id) and "
            +"(:subjectName = '' or t.topicPK.subject like :subjectName) and "
            +"(:topicNum = -1 or t.topicPK.topicNumber = :topicNum) and "
            +"(:categoryName = '' or t.category like :categoryName)")
    List<Task> advancedSearch(@Param("id") Long id,
                              @Param("subjectName") String subjectName,
                              @Param("topicNum") Short topicNum,
                              @Param("categoryName") String categoryName);
}
