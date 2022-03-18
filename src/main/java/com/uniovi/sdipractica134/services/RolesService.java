package com.uniovi.sdipractica134.services;

import org.springframework.stereotype.Service;

@Service
public class  RolesService {
    String[] roles = {"ROLE_USER", "ROLE_ADMIN"};
    public static final int USER = 0;
    public static final int ADMIN = 1;

    public String[] getRoles() {
        return roles;
    }
}