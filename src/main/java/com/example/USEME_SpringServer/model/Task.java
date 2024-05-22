package com.example.USEME_SpringServer.model;

import com.example.USEME_SpringServer.model.statistic.Statistic;
import com.example.USEME_SpringServer.staticData.Topic;
import com.example.USEME_SpringServer.staticData.TopicPK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private TopicPK topicPK;

    @Column(name = "category")
    private String category;

    @Column(name = "condition", columnDefinition = "text")
    private String condition;

    @Column(name = "answer")
    private String answer;

    @ManyToMany(mappedBy = "tasks")
    private List<Homework> homeworks = new ArrayList<>();

    @OneToMany(mappedBy = "pk.task")
    private List<Statistic> statistics = new ArrayList<>();

}
