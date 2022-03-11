package com.uniovi.sdipractica134.repositories;

import com.uniovi.sdipractica134.entities.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostsRepository extends CrudRepository<Post, Long> {
}
