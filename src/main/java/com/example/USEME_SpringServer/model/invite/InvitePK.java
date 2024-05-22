package com.example.USEME_SpringServer.model.invite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvitePK implements Serializable {

    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "group_id")
    private Long groupId;
}
