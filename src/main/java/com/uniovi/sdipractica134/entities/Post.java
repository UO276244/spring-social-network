package com.uniovi.sdipractica134.entities;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


public class Post {

    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;
}
