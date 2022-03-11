package com.uniovi.sdipractica134.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostController {
    @RequestMapping("post/list")
    public String listOwnPosts(){

        return "list";
    }

    @RequestMapping("post/add")
    public String publishPost(){

        return "home";
    }
}
