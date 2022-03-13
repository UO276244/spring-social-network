package com.uniovi.sdipractica134.services;

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
        //friends = friendsRepository.findFriendsByUser(pageable, user);
        return friends;
    }
}
