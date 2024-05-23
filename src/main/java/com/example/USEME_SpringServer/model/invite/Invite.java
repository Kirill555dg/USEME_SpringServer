package com.example.USEME_SpringServer.model.invite;

import com.example.USEME_SpringServer.model.Group;
import com.example.USEME_SpringServer.model.Student;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "invites")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invite {

    @EmbeddedId
    private InvitePK pk;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    @Getter(AccessLevel.NONE)
    private Student student;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    @Getter(AccessLevel.NONE)
    private Group group;

    @Column(name = "accept")
    private Boolean isAccept;

    public Long getStudent() {
        return student.getId();
    }

    public Long getGroup() {
        return group.getId();
    }
}
