package com.example.USEME_SpringServer.repository;

import com.example.USEME_SpringServer.model.invite.Invite;
import com.example.USEME_SpringServer.model.invite.InvitePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InviteRepository extends JpaRepository<Invite, InvitePK> {
}
