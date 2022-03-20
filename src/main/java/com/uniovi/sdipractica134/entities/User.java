package com.uniovi.sdipractica134.entities;

import javax.persistence.*;
import java.util.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
    private Set<Post> posts;

    @OneToMany(mappedBy = "from",cascade = CascadeType.ALL)
    private Set<FriendshipInvites> friendShipsSent;

    @OneToMany(mappedBy = "to",cascade = CascadeType.ALL)
    private Set<FriendshipInvites> friendShipsReceived;

    public User(){
        this.role = "ROLE_USER";
    }

    public User(String username, String name, String surname) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.friendShipsSent = new HashSet<>();
        this.friendShipsReceived = new HashSet<>();
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

    /**
     * Método para comprobar si este usuario puede recibir una invitación del usuario especificado
     * @param username
     * @return true si puede recibir invitación y false si no
     */
    public boolean canReceiveFriendshipInvite(String username){
        List<User> friends = getFriends();
        for (User u: friends) {
            if (u.getUsername().equals(username))
                return true;
        }
        for (FriendshipInvites received: friendShipsReceived) {
            if (received.getFrom().getUsername().equals(username))
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Método que devuelve una lista con todos los amigos de un usuario
     * @return lista con todos los amigos de un usuario
     */
    public List<User> getFriends() {
        List<User> friends = new ArrayList<>();
        User friend;
        for (FriendshipInvites sent: friendShipsSent) {
            friend = sent.getTo();
            if (sent.isAccepted() && !friends.contains(friend))
                friends.add(friend);
        }
        for (FriendshipInvites received: friendShipsReceived) {
            friend = received.getFrom();
            if (received.isAccepted() && !friends.contains(friend))
                friends.add(friend);
        }
        return friends;
    }

    /**
     * Método que devuelve la lista de invitaciones pendientes por aceptar por este usuario
     * @return lista de invitaciones pendientes
     */
    public List<FriendshipInvites> getFriendShipsReceivedNPending() {
        List<FriendshipInvites> result = new ArrayList<>();
        for (FriendshipInvites received: friendShipsReceived) {
            if (received.isPending())
                result.add(received);
        }
        return result;
    }

    /**
     * Método que devuelve si este usuario es amigo del especificado
     * @param user
     * @return boolean true si son amigos y false si no
     */
    public boolean isFriendsWith(User user) {
        List<User> friends = getFriends();
        return friends.contains(user);
    }
}
