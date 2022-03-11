package com.uniovi.sdipractica134.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Date dateOfCreation;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User owner;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }



    private void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
