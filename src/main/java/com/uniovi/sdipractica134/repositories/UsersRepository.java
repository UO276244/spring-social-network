package com.uniovi.sdipractica134.repositories;

import com.uniovi.sdipractica134.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface UsersRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    @Transactional
    @Modifying
    @Query("DELETE FROM User r WHERE r.username = ?1 ")
    void deleteByUsername(String username);

    @Query("SELECT count(r) FROM User r")
    int countUsers();







}