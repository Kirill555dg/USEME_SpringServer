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

    @Column(name = "accept")
    private Boolean isAccept;

}
