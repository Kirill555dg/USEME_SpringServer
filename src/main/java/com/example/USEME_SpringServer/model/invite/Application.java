package com.example.USEME_SpringServer.model.invite;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "invites")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {

    @EmbeddedId
    private ApplicationPK pk;

    @Column(name = "accept")
    private Boolean isAccept;

}
