package com.uniovi.sdipractica134.controllers;


import com.uniovi.sdipractica134.entities.Post;
import com.uniovi.sdipractica134.entities.User;
import com.uniovi.sdipractica134.services.PostsService;
import com.uniovi.sdipractica134.services.UsersService;
import com.uniovi.sdipractica134.validators.PostFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Date;

@Controller
public class PostController {
    @Autowired
    private PostsService postsService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private PostFormValidator postFormValidator;

    @RequestMapping("post/list")
    public String listOwnPosts(Model model, Pageable pageable, Principal principal, @RequestParam(value="",required=false)String searchTest){

        return "list";
    }

    @RequestMapping(value="post/add", method= RequestMethod.POST)
    public String publishPost(Principal principal, @Validated Post post, BindingResult result){
        postFormValidator.validate(post, result);
        if(result.hasErrors()){
            return "post/add";
        }

        post.setDateOfCreation(new Date());
        User owner=usersService.getUserByEmail(principal.getName());

        post.setOwner(owner);
        postsService.addNewPost(post);

        return "redirect:/post/list";
    }
    @RequestMapping("post/add")
    public String getPost(){
        return "post/add";
    }
}
