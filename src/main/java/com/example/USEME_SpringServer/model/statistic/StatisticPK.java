package com.example.USEME_SpringServer.model.statistic;

import com.example.USEME_SpringServer.model.Homework;
import com.example.USEME_SpringServer.model.Student;
import com.example.USEME_SpringServer.model.Task;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class StatisticPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "homework_id")
    private Homework homework;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
