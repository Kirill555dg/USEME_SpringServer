package com.example.USEME_SpringServer.model;

import com.example.USEME_SpringServer.model.invite.Invite;
import com.example.USEME_SpringServer.model.statistic.Statistic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;


@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "email")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private Boolean isMale;


    @JsonIgnore
    @OneToMany(mappedBy = "pk.student", cascade = CascadeType.ALL)
    private List<Statistic> statistics = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "pk.student", cascade = CascadeType.ALL)
    private List<Invite> invites = new ArrayList<>();

    @Transient
    private int age;

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    @JsonIgnore
    public List<Statistic> getStatistics() {
        return statistics;
    }
    @JsonProperty("statistics")
    public void setStatistics(List<Statistic> statistics) {
        this.statistics = statistics;
    }

    @JsonIgnore
    public List<Invite> getInvites() {
        return invites;
    }

    @JsonProperty("invites")
    public void setInvites(List<Invite> invites) {
        this.invites = invites;
    }
}
