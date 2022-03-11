package com.uniovi.sdipractica134.entities;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class FriendshipInviteId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User fromId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User toId;

    public FriendshipInviteId(User fromId, User toId){
        this.fromId = fromId;
        this.toId = toId;
    }


    protected FriendshipInviteId() {}
}
