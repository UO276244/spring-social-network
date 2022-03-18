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
    public String listOwnPosts(Model model, Pageable pageable, Principal principal){
        String email=principal.getName();//El usuario incicia sesión empleando mail y contraseña
        User user =usersService.getUserByEmail(email);
        model.addAttribute("postsList",postsService.getPostsByUser(pageable,user));
        return "/posts/list";
    }

    @RequestMapping(value="post/add", method= RequestMethod.POST)
    public String publishPost(Principal principal, @Validated Post post, BindingResult result){
        postFormValidator.validate(post, result);
        if(result.hasErrors()){
            return "add";//Return to the view when the publication could not be performed
        }

        post.setDateOfCreation(new Date());
        User owner=usersService.getUserByEmail(principal.getName());

        post.setOwner(owner);
        postsService.addNewPost(post);

        return "redirect:/post/list";
    }
    @RequestMapping(value="/add" , method=RequestMethod.GET)
    public String getPost(Model model){

        model.addAttribute("post",new Post());
        //The controller that answers the form must include an empty entity in the view.
        return "add";
    }
}
