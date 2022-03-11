package com.uniovi.sdipractica134.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Post> posts;

    @OneToMany(mappedBy = "from")
    private Set<FriendshipInvites> friendShipsSent;



    public User(){

    }

    private String name;
    private String surname;

    private String password;

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

    public String getEmail(){
        return this.email;
    }

    public Set<Post> getPosts() {
        return new HashSet<Post>(this.posts);

    }

    public Long getId() {
        return id;
    }
}
