package com.example.USEME_SpringServer.model;


import com.example.USEME_SpringServer.model.invite.Invite;
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
    @Getter(AccessLevel.NONE)
    private Teacher teacher;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "target_subject")
    private String targetSubject;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Transient
    private int countMembers;

    @OneToMany(mappedBy = "pk.group")
    private List<Invite> invites = new ArrayList<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    @Getter(AccessLevel.NONE)
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

    public List<Long> getHomeworks(){
        List<Long> homeworks_id = new ArrayList<>();
        for (Homework homework : homeworks) {
            homeworks_id.add(homework.getId());
        }
        return homeworks_id;
    }
}


