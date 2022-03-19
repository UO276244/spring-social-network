package com.uniovi.sdipractica134.services;

import com.uniovi.sdipractica134.entities.FriendshipInvites;
import com.uniovi.sdipractica134.entities.User;
import com.uniovi.sdipractica134.repositories.FriendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FriendsService {

    @Autowired
    private FriendsRepository friendsRepository;


    public Page<User> getFriendsForUser(Pageable pageable, User user) {
        Page<User> friends = new PageImpl<>(new LinkedList<User>());
        List<User> friendList = new ArrayList<>();
        friends = friendsRepository.findFriendsByUserFrom(pageable, user);
        friendList.addAll(friends.getContent());
        friends = friendsRepository.findFriendsByUserTo(pageable, user);
        friendList.addAll(friends.getContent());
        friends = new PageImpl<>(friendList);
        return friends;
    }

    public Page<User> searchFriendsByNameForUser(Pageable pageable, String searchText, User user) {
        Page<User> friends = new PageImpl<>(new LinkedList<User>());
        List<User> friendList = new ArrayList<>();
        searchText = "%"+searchText+"%";
        friends = friendsRepository.searchFriendsSentByNameAndUser(pageable, searchText, user);
        friendList.addAll(friends.getContent());
        friends = friendsRepository.searchFriendsSentByNameAndUser(pageable, searchText, user);
        friendList.addAll(friends.getContent());
        friends = new PageImpl<>(friendList);
        return friends;
    }

    public Page<FriendshipInvites> getFriendInvitesForUser(Pageable pageable, User user) {
        Page<FriendshipInvites> invites = friendsRepository.findInvitesForUser(pageable, user);
        return invites;
    }

    public Page<FriendshipInvites> searchFriendInvitesByNameForUser(Pageable pageable, String searchText, User user) {
        searchText = "%"+searchText+"%";
        Page<FriendshipInvites> invites = friendsRepository.findInvitesByNameForUser(pageable, searchText, user);
        return invites;
    }

    public void acceptFriendshipInvite(Long id) {
        Optional<FriendshipInvites> inviteOp = friendsRepository.findById(id);
        FriendshipInvites invite;
        if (inviteOp.isPresent()) {
            invite = inviteOp.get();
            invite.accept();
            friendsRepository.save(invite);
        }
    }

    public void sendInvite(Pageable pageable, User from, User to) {
        Page<FriendshipInvites> invitesToUser = getFriendInvitesForUser(pageable, to);
        for (FriendshipInvites i: invitesToUser) {
            if (i.getFrom().equals(from))
                return;
        }
        FriendshipInvites invite = new FriendshipInvites(from, to, "PENDING");
        friendsRepository.save(invite);
    }
}
