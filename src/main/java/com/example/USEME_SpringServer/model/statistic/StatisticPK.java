package com.example.USEME_SpringServer.model.statistic;

import com.example.USEME_SpringServer.model.Homework;
import com.example.USEME_SpringServer.model.Student;
import com.example.USEME_SpringServer.model.Task;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
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
