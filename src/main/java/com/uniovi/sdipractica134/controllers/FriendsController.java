package com.uniovi.sdipractica134.controllers;

import com.uniovi.sdipractica134.entities.FriendshipInvites;
import com.uniovi.sdipractica134.entities.User;
import com.uniovi.sdipractica134.services.FriendsService;
import com.uniovi.sdipractica134.services.LoggerService;
import com.uniovi.sdipractica134.services.RolesService;
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

import java.security.Principal;


@Controller
public class FriendsController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private LoggerService loggerService;

    @Autowired
    private RolesService rolesService;

    Logger logger = LoggerFactory.getLogger(FriendsController.class);

    @RequestMapping("/friends/list")
    public String getList(Model model, Principal principal, @RequestParam(value = "", required = false) String searchText){
        String username = principal.getName();
        User user = usersService.getUserByUsername(username);
        Page<User> friends = null;
        if (searchText != null && !searchText.isEmpty()){
            logger.info(
                    loggerService.createPETLog("FriendsController --> /friends/list",
                    "GET",
                    new String[] {"searchText="+searchText})
            );
            friends = friendsService.searchFriendsByNameForUser(searchText, user);
        } else{
            logger.info(
                    loggerService.createPETLog("FriendsController --> /friends/list",
                    "GET",
                    new String[] {})
            );
            friends = friendsService.getFriendsForUser(user);
        }
        model.addAttribute("friendList", friends.getContent());
        model.addAttribute("page", friends);

        return "friends/list";
    }

    @RequestMapping("/friends/invites")
    public String getInvites(Model model, Principal principal, @RequestParam(value = "", required = false) String searchText){
        String username = principal.getName();
        User user = usersService.getUserByUsername(username);
        Page<FriendshipInvites> invites = null;
        if (searchText != null && !searchText.isEmpty()){
            logger.info(
                    loggerService.createPETLog("FriendsController --> /friends/invites",
                    "GET",
                    new String[] {"searchText="+searchText})
            );
            invites = friendsService.searchFriendInvitesByNameForUser(searchText, user);
        } else {
            logger.info(
                    loggerService.createPETLog("FriendsController --> /friends/invites",
                    "GET",
                    new String[] {})
            );
            invites = friendsService.getFriendInvitesForUser(user);
        }
        model.addAttribute("inviteList", invites.getContent());
        model.addAttribute("page", invites);

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
    public String sendFriendshipInvite(Principal principal, @PathVariable Long id){
        logger.info(
                loggerService.createPETLog("FriendsController --> /invite/send/" + id,
                "GET",
                new String[] {})
        );

        String username = principal.getName();
        User from = usersService.getUserByUsername(username);
        User to = usersService.getUser(id);
        //we check that the admin cannot receive an invite
        if (!to.getRole().equals(rolesService.getRoles()[RolesService.ADMIN]))
            friendsService.sendInvite(from, to);
        return "redirect:/user/list";
    }
}
