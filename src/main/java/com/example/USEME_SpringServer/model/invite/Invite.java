package com.example.USEME_SpringServer.model.invite;

import com.example.USEME_SpringServer.model.Group;
import com.example.USEME_SpringServer.model.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Student student;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "accept")
    private Boolean isAccept;
}
