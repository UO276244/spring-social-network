package com.uniovi.sdipractica134.repositories;


import com.uniovi.sdipractica134.entities.FriendshipInvites;
import com.uniovi.sdipractica134.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.domain.Pageable;

public interface FriendsRepository extends CrudRepository<FriendshipInvites, Long> {
    @Query("SELECT t FROM FriendshipInvites f " +
            "join f.from u " +
            "join f.to t " +
            "WHERE f.state = 'ACCEPTED' " +
            "and u = ?1")
    Page<User> findFriendsByUserFrom(Pageable pageable, User user);

    @Query("SELECT u FROM FriendshipInvites f " +
            "join f.from u " +
            "join f.to t " +
            "WHERE f.state = 'ACCEPTED' " +
            "and t = ?1")
    Page<User> findFriendsByUserTo(Pageable pageable, User user);

    @Query("SELECT f FROM FriendshipInvites f " +
            "join f.to t " +
            "WHERE f.state = 'PENDING' " +
            "and t = ?1")
    Page<FriendshipInvites> findInvitesForUser(Pageable pageable, User user);
}
