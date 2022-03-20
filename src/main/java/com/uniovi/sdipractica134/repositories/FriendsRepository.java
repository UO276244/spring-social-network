package com.uniovi.sdipractica134.repositories;


import com.uniovi.sdipractica134.entities.FriendshipInvites;
import com.uniovi.sdipractica134.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

public interface FriendsRepository extends CrudRepository<FriendshipInvites, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM FriendshipInvites f where f.from.id in ?1 or f.to.id in ?1")
    void deleteFriendshipInvitesBy(List<Long> userIds);
}
