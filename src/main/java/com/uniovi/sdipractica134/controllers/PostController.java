package com.uniovi.sdipractica134.controllers;


import com.uniovi.sdipractica134.services.LoggerService;
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
    private LoggerService loggerService;

    @RequestMapping("post/list")
    public String listOwnPosts(Model model,
                               Pageable pageable,
                               Principal principal,
                               @RequestParam(value="",required=false)String searchTest){

        loggerService.createPETLog("PostController --> post/list",
                "GET",
                new String[] {"searchText="+searchTest});

        return "list";
    }

    @RequestMapping("post/add")
    public String publishPost(){

        loggerService.createPETLog("PostController --> post/add",
                "GET",
                new String[] {});

        return "home";
    }
}
