package com.uniovi.sdipractica134.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = { "FROM_ID", "TO_ID" })},
        name = "FriendshipInvites")
public class FriendshipInvites {


    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="from_id")
    private User from;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="to_id")
    private User to;

    private String state;

    private final String ACCEPTED = "ACCEPTED";
    private final String PENDING = "PENDING";

    public FriendshipInvites(User from, User to, String state){
        super();
        this.from = from;
        this.to = to;
        this.state = state;
        if (state.equals(ACCEPTED)){
            from.addFriend(to);
            to.addFriend(from);
        }
    }

    public FriendshipInvites() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public User getFrom() {
        return from;
    }

    public void accept() {
        this.state = ACCEPTED;
        from.addFriend(to);
        to.addFriend(from);
    }
}
