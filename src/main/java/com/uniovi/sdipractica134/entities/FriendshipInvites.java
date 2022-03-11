package com.uniovi.sdipractica134.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = { "FROM_ID", "TO_ID" })})
public class FriendshipInvites {


    @Id
    private Long id;

    @ManyToOne()
    @JoinColumn(name="from_id")
    private User from;

    @ManyToOne()
    @JoinColumn(name="to_id")
    private User to;

    private String state;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
