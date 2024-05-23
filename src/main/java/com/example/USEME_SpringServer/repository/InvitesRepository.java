package com.example.USEME_SpringServer.repository;

import com.example.USEME_SpringServer.model.invite.Invite;
import com.example.USEME_SpringServer.model.invite.InvitePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitesRepository extends JpaRepository<Invite, InvitePK> {
}
