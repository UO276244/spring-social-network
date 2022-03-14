package com.uniovi.sdipractica134.controllers;


import com.uniovi.sdipractica134.entities.User;
import com.uniovi.sdipractica134.services.PostsService;
import com.uniovi.sdipractica134.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class PostController {
    @Autowired
    private UsersService userService;
    @Autowired
    private PostsService postsService;
    @RequestMapping("post/list")
    public String listOwnPosts(Model model, Pageable pageable, Principal principal){
         String email=principal.getName();//El usuario incicia sesión empleando mail y contraseña
         User user =userService.getUserByEmail(email);
         model.addAttribute("postsList",postsService.getPostsByUser(pageable,user));
        return "/posts/list";
    }

    @RequestMapping("post/add")
    public String publishPost(){

        return "home";
    }
}
