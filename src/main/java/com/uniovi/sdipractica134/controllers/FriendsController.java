package com.uniovi.sdipractica134.controllers;

import com.uniovi.sdipractica134.entities.FriendshipInvites;
import com.uniovi.sdipractica134.entities.User;
import com.uniovi.sdipractica134.services.FriendsService;
import com.uniovi.sdipractica134.services.LoggerService;
import com.uniovi.sdipractica134.services.UsersService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;


@Controller
public class FriendsController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UsersService usersService;

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private LoggerService loggerService;

    Logger logger = LoggerFactory.getLogger(FriendsController.class);


    @RequestMapping("/friends/list")
    public String getList(Model model,
                          Pageable pageable,
                          Principal principal,
                          @RequestParam(value = "", required = false) String searchText){


        logger.info(
                loggerService.createPETLog("FriendsController --> /friends/list",
                        "GET",
                        new String[] {"searchText="+searchText})
        );

        String username = principal.getName();
        User user = usersService.getUserByUsername(username);
        Page<User> friends = null;
        if (searchText != null && !searchText.isEmpty()){
            friends = friendsService.searchFriendsByNameForUser(pageable, searchText, user);
        } else{
            friends = friendsService.getFriendsForUser(pageable, user);
        }
        model.addAttribute("friendList", friends.getContent());
        model.addAttribute("page", friends);

        return "friends/list";
    }

    @RequestMapping("/friends/invites")
    public String getInvites(Model model, Pageable pageable, Principal principal, @RequestParam(value = "", required = false) String searchText){
        logger.info(
                loggerService.createPETLog("FriendsController --> /friends/invites",
                        "GET",
                        new String[] {"searchText="+searchText})
        );
        String username = principal.getName();
        User user = usersService.getUserByUsername(username);
        Page<FriendshipInvites> invites = null;
        if (searchText != null && !searchText.isEmpty()){
            invites = friendsService.searchFriendInvitesByNameForUser(pageable, searchText, user);
        } else {
            invites = friendsService.getFriendInvitesForUser(pageable, user);
        }
        model.addAttribute("inviteList", invites.getContent());

        return "friends/invites";
    }

    @RequestMapping("/invite/accept/{id}")
    public String delete(@PathVariable Long id) {
        logger.info(
                loggerService.createPETLog("FriendsController --> /invite/accept/" + id,
                        "GET",
                        new String[] {})
        );
        friendsService.acceptFriendshipInvite(id);
        return "redirect:/friends/invites";
    }

    @RequestMapping("/invite/send/{id}")
    public String sendFriendshipInvite(Pageable pageable, Model model, Principal principal, @PathVariable Long id){
        logger.info(
                loggerService.createPETLog("FriendsController --> /invite/send/" + id,
                        "GET",
                        new String[] {})
        );
        String username = principal.getName();
        User from = usersService.getUserByUsername(username);
        User to = usersService.getUser(id);
        //we try to send a friendship invite to a user but we had already sent one before
        if (!friendsService.sendInvite(pageable, from, to))
            model.addAttribute("alreadySent", true);
        else
            model.addAttribute("alreadySent", false);
        return "redirect:/user/list";
    }
}
