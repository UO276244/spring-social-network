package com.uniovi.sdipractica134.repositories;

import com.uniovi.sdipractica134.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}