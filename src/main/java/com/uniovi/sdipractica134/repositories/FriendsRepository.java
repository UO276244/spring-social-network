package com.uniovi.sdipractica134.repositories;


import com.uniovi.sdipractica134.entities.FriendshipInvites;
import com.uniovi.sdipractica134.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.domain.Pageable;

public interface FriendsRepository extends CrudRepository<FriendshipInvites, Long> {
    @Query("SELECT u FROM FriendshipInvites f " +
            "join f.from u " +
            "WHERE f.state = 'ACCEPTED'")
    Page<User> findFriendsByUserFrom(Pageable pageable, User user);

    @Query("SELECT u FROM FriendshipInvites f " +
            "join f.to u " +
            "WHERE f.state = 'ACCEPTED'")
    Page<User> findFriendsByUserTo(Pageable pageable, User user);
}
