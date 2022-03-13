package com.uniovi.sdipractica134.services;

import com.uniovi.sdipractica134.entities.Post;
import com.uniovi.sdipractica134.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostsService {
    @Autowired
    PostsRepository postsRepository;
    public List<Post> getPostsByUser(){

        return null;
    }

}
