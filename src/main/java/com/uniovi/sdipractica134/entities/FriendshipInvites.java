package com.uniovi.sdipractica134.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class FriendshipInvites {

    @EmbeddedId
    private FriendshipInviteId id;

    @OneToMany()
    private Set<User> from;

    @OneToMany()
    private Set<User> to;

    private String state;
}
