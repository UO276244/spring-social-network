package com.uniovi.sdipractica134.controllers;

import com.uniovi.sdipractica134.entities.FriendshipInvites;
import com.uniovi.sdipractica134.entities.User;
import com.uniovi.sdipractica134.services.FriendsService;
import com.uniovi.sdipractica134.services.UsersService;
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
import java.util.List;

@Controller
public class FriendsController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UsersService usersService;

    @Autowired
    private FriendsService friendsService;


    @RequestMapping("/friends/list")
    public String getList(Model model, Pageable pageable, Principal principal, @RequestParam(value = "", required = false) String searchText){
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);
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
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);
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
    public String delete(Model model, Pageable pageable, Principal principal, @PathVariable Long id) {
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);
        friendsService.acceptFriendshipInvite(pageable, user, id);
        return "redirect:/friends/invites";
    }
}
