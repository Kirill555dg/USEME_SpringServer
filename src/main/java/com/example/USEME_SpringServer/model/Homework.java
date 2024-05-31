package com.example.USEME_SpringServer.model;


import com.example.USEME_SpringServer.model.statistic.Statistic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "homeworks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
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

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    //@JsonIgnore
    @ManyToMany
    @JoinTable(name = "homeworks_tasks",
    joinColumns = @JoinColumn(name = "homework_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
    private List<Task> tasks = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "pk.homework", cascade = CascadeType.ALL)
    private List<Statistic> statistics = new ArrayList<>();

    @Transient
    private int countTasks;

    @Transient
    private HashSet<Long> completed;

    public int getCountTasks() {
        return tasks.size();
    }

    public HashSet<Long> getCompleted(){
        HashSet<Long> completed = new HashSet<>();
        for (Statistic statistic : statistics) {
            completed.add(statistic.getPk().getStudent().getId());
        }
        return completed;
    }

    /*@JsonProperty("group")
    public void setGroup(Group group) {
        this.group = group;
    }

    @JsonIgnore
    public Group getGroup() {
        return group;
    }*/

    /*@JsonIgnore
    public List<Task> getTasks() {
        return tasks;
    }

    @JsonProperty("tasks")
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }*/

    @JsonIgnore
    public List<Statistic> getStatistics() {
        return statistics;
    }

    @JsonProperty("statistics")
    public void setStatistics(List<Statistic> statistics) {
        this.statistics = statistics;
    }
}
