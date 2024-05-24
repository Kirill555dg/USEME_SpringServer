package com.example.USEME_SpringServer.model;


import com.example.USEME_SpringServer.model.statistic.Statistic;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "homeworks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"group", "tasks", "statistics"})
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "date_of_issue", nullable = false)
    private LocalDate dateOfIssue;

    @Column(name = "deadline")
    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToMany
    @JoinTable(name = "homeworks_tasks",
    joinColumns = @JoinColumn(name = "homework_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "pk.homework", cascade = CascadeType.ALL)
    private List<Statistic> statistics = new ArrayList<>();

    @Transient
    private int countTasks;

    public int getCountTasks() {
        return tasks.size();
    }
}
