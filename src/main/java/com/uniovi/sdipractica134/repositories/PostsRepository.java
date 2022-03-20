package com.uniovi.sdipractica134.repositories;

import com.uniovi.sdipractica134.entities.Post;
import com.uniovi.sdipractica134.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostsRepository extends CrudRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.owner = ?1 ORDER BY p.dateOfCreation DESC")
    Page<Post> findAllByUser(Pageable pageable, User user);

}
