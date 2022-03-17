package com.uniovi.sdipractica134.controllers;


import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class PostController {
    @RequestMapping("post/list")
    public String listOwnPosts(Model model, Pageable pageable, Principal principal, @RequestParam(value="",required=false)String searchTest){

        return "list";
    }

    @RequestMapping("post/add")
    public String publishPost(){

        return "home";
    }
}
