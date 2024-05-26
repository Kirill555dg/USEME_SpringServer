package com.example.USEME_SpringServer.repository;

import com.example.USEME_SpringServer.model.Homework;
import com.example.USEME_SpringServer.model.Student;
import com.example.USEME_SpringServer.model.Task;
import com.example.USEME_SpringServer.model.statistic.Statistic;
import com.example.USEME_SpringServer.model.statistic.StatisticPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatisticRepository extends JpaRepository<Statistic, StatisticPK> {
    /*List<Statistic> findByPk_Student(Student pk_student);

    List<Statistic> findByPk_Homework(Homework pk_homework);

    List<Statistic> findByPk_StudentAndPk_Homework(Student pk_student, Homework pk_homework);*/

    @Query("select s from Statistic s where "
            +"(:student_id = -1 or s.pk.student.id = :student_id) and "
            +"(:homework_id = -1 or s.pk.homework.id = :homework_id) and "
            +"(:task_id = -1 or s.pk.task.id = :task_id)")
    List<Statistic> getAllStatistics(@Param("student_id") Long studentId,
                                 @Param("homework_id") Long homeworkId,
                                 @Param("task_id") Long taskId);

    Boolean existsByPk_StudentAndPk_Homework(Student pk_student, Homework pk_homework);
}
