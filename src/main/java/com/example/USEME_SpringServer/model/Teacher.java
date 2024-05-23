package com.example.USEME_SpringServer.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "email")
public class Teacher {

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
    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private Boolean isMale;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @Getter(AccessLevel.NONE)
    private List<Group> groups = new ArrayList<>();

    @Transient
    private int age;

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public List<Long> getGroups(){
        List<Long> groups_id = new ArrayList<>();
        for (Group group : groups) {
            groups_id.add(group.getId());
        }
        return groups_id;
    }
}
