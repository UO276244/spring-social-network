package com.uniovi.sdipractica134.repositories;

import com.uniovi.sdipractica134.entities.Post;
import com.uniovi.sdipractica134.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface PostsRepository extends CrudRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.owner = ?1 ORDER BY p.dateOfCreation DESC")
    Page<Post> findAllByUser(Pageable pageable, User user);

    @Transactional
    @Modifying
    @Query("DELETE FROM Post p where p.owner.id in (:ids)")
    void deletePostsOfUsers(List<Long> ids);

}
