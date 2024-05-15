package com.example.USEME_SpringServer.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "groups")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "name")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "students_groups",
    inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
    joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    private List<Student> students = new ArrayList<>();


    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Homework> homeworks = new ArrayList<>();

}
