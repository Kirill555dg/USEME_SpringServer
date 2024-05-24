package com.example.USEME_SpringServer.model;


import com.example.USEME_SpringServer.model.invite.Invite;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
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

    @Transient
    private int countMembers;

    @Transient
    private int countHomeworks;

    @JsonIgnore
    @OneToMany(mappedBy = "pk.group")
    private List<Invite> invites = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Homework> homeworks = new ArrayList<>();

    public int getCountMembers() {
        countMembers = 0;
        for (Invite invite : invites) {
            if (invite.getIsAccept()) {
                countMembers++;
            }
        }
        return countMembers;
    }

    public int getCountHomeworks(){
        return homeworks.size();
    }

    @JsonIgnore
    public List<Invite> getInvites() {
        return invites;
    }
    @JsonProperty("invites")
    public void setInvites(List<Invite> invites) {
        this.invites = invites;
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


