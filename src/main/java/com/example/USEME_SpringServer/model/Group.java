package com.example.USEME_SpringServer.model;


import com.example.USEME_SpringServer.model.invite.Application;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "target_subject")
    private String targetSubject;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "password")
    private String password;

    @Transient
    private int countMembers;

    @Transient
    private int countHomeworks;

    @JsonIgnore
    @OneToMany(mappedBy = "pk.group")
    private List<Application> applications = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Homework> homeworks = new ArrayList<>();

    public int getCountMembers() {
        countMembers = 0;
        for (Application application : applications) {
            if (application.getIsAccept()) {
                countMembers++;
            }
        }
        return countMembers;
    }

    public int getCountHomeworks(){
        return homeworks.size();
    }

    @JsonIgnore
    public List<Application> getApplications() {
        return applications;
    }
    @JsonProperty("invites")
    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    @JsonIgnore
    public List<Homework> getHomeworks() {
        return homeworks;
    }

    @JsonProperty("homeworks")
    public void setHomeworks(List<Homework> homeworks) {
        this.homeworks = homeworks;
    }
}


