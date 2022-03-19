package com.uniovi.sdipractica134.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    @Transient
    private Set<Post> posts;

    @Transient
    private Set<FriendshipInvites> friendShipsSent;



    public User(){
        this.role = "ROLE_USER";
    }

    public User(String username, String name, String surname) {
        this.username = username;
        this.name = name;
        this.surname = surname;
    }

    private String name;
    private String surname;

    private String password;
    private String passwordConfirm;

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    private String role;

    public Set<FriendshipInvites> getFriendShipsSent() {
        return friendShipsSent;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getUsername(){
        return this.username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public void setFriendShipsSent(Set<FriendshipInvites> friendShipsSent) {
        this.friendShipsSent = friendShipsSent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Post> getPosts() {
        return new HashSet<Post>(this.posts);

    }

    public Long getId() {
        return id;
    }

}
