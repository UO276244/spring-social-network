package com.uniovi.sdipractica134.controllers;


import com.uniovi.sdipractica134.entities.Post;
import com.uniovi.sdipractica134.entities.User;
import com.uniovi.sdipractica134.services.PostsService;
import com.uniovi.sdipractica134.services.UsersService;
import com.uniovi.sdipractica134.validators.PostFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import com.uniovi.sdipractica134.services.LoggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.security.Principal;
import java.time.LocalDate;


@Controller
public class PostController {
    @Autowired
    private PostsService postsService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private PostFormValidator postFormValidator;
    @Autowired
    private LoggerService loggerService;

    @RequestMapping("post/list")
    public String listOwnPosts(Model model, Pageable pageable, Principal principal){
        String email=principal.getName();//El usuario incicia sesión empleando mail y contraseña
        User user =usersService.getUserByUsername(email);
        Page<Post> posts=postsService.getPostsByUser(pageable,user);
        model.addAttribute("postList",posts.getContent());
        model.addAttribute("page",posts);
        logger.info(
                loggerService.createPETLog("PostController --> post/list",
                        "GET",
                        new String[] {"User: " + principal.getName()})
        );
        return "/post/list";
    }
    @RequestMapping("post/list/{ownerUsername}")
    public String listFriendsPosts(@PathVariable String ownerUsername, Model model, Pageable pageable, Principal principal){
        String email=principal.getName();//El usuario incicia sesión empleando mail y contraseña
        User authenticatedUser =usersService.getUserByUsername(email);
        User ownerOfPosts =usersService.getUserByUsername(ownerUsername);
        if(!authenticatedUser.isFriendsWith(ownerOfPosts.getUsername())|| ownerOfPosts==null){
             return "error";
        }
        Page<Post> posts=postsService.getPostsByUser(pageable,ownerOfPosts);
        model.addAttribute("postList",posts.getContent());
        model.addAttribute("page",posts);
        logger.info(
                loggerService.createPETLog("PostController --> post/list/"+ownerUsername,
                        "GET",
                        new String[] {"User: " + principal.getName()})
        );
        return "/post/list";
    }

    @RequestMapping(value="post/add", method= RequestMethod.POST)
    public String publishPost(Principal principal, @Validated Post post, BindingResult result){
        postFormValidator.validate(post, result);
        if(result.hasErrors()){
            return "post/add";//Return to the view when the publication could not be performed
        }

        post.setDateOfCreation(LocalDate.now());
        User owner=usersService.getUserByUsername(principal.getName());

        post.setOwner(owner);
        postsService.addNewPost(post);
        logger.info(
                loggerService.createPETLog("PostController --> post/add",
                        "GET",
                        new String[] {})
        );

        return "redirect:/post/list";
    }
    @RequestMapping(value="post/add" , method=RequestMethod.GET)
    public String getPost(Model model){

        model.addAttribute("post",new Post());
        //The controller that answers the form must include an empty entity in the view.
        return "post/add";
    }

    Logger logger = LoggerFactory.getLogger(FriendsController.class);
}
