package com.uniovi.sdipractica134.entities;

import javax.persistence.*;
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

    private String name;
    private String surname;

    private String password;

    private String role;


}
