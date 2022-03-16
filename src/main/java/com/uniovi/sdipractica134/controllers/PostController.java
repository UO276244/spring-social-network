package com.uniovi.sdipractica134.controllers;


import com.uniovi.sdipractica134.entities.Post;
import com.uniovi.sdipractica134.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class PostController {
    @Autowired
    private PostsService postsService;
    @RequestMapping("post/list")
    public String listOwnPosts(Model model, Pageable pageable, Principal principal, @RequestParam(value="",required=false)String searchTest){

        return "list";
    }

    @RequestMapping(value="post/add", method= RequestMethod.POST)
    public String publishPost(@ModelAttribute Post post){
        postsService.addNewPost(post);

        return "home";
    }
}
