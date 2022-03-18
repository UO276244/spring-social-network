package com.uniovi.sdipractica134.entities;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private LocalDate dateOfCreation;

    public Post(String title, LocalDate dateOfCreation,String content) {
        this.title = title;
        this.content = content;
        this.dateOfCreation = dateOfCreation;


    }

    private String pictureURL;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User owner;

    public Post() {

    }

    public String getDescription() {
        return content;
    }

    public void setDescription(String content) {
        this.content = content;
    }


    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }



    private void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setOwner(User owner) {
        this.owner=owner;
    }
}
