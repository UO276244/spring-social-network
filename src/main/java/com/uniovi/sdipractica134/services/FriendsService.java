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

    /**
     * Devuelve todos los amigos de un usuario
     * @param user usuario
     * @return Page User amigos
     */
    public Page<User> getFriendsForUser(User user) {
        List<User> friendList = user.getFriends();
        return new PageImpl<>(friendList);
    }

    /**
     * Devuelve todos los amigos de un usuario cuyo nombre contenga el texto del parámetro
     * @param searchText texto a buscar
     * @param user usuario
     * @return Page User amigos
     */
    public Page<User> searchFriendsByNameForUser(String searchText, User user) {
        List<User> friendList = user.getFriends();
        List<User> result = new ArrayList<>();
        for (User u : friendList) {
            if (u.getName().contains(searchText))
                result.add(u);
        }
        return new PageImpl<>(result);
    }

    /**
     * Devuelve todas las invitaciones pendientes de aceptar que ha recibido un usuario
     * @param user usuario
     * @return Page FriendshipInvites invitaciones de amistad
     */
    public Page<FriendshipInvites> getFriendInvitesForUser(User user) {
        List<FriendshipInvites> invitesList = user.getFriendShipsReceivedNPending();
        return new PageImpl<>(invitesList);
    }

    /**
     * Devuelve todas las invitaciones pendientes de aceptar que ha recibido un usuario por otro usuario cuyo nombre
     * contiene el texto especificado de parámetro
     * @param searchText texto a buscar
     * @param user usuario
     * @return Page FriendshipInvites invitaciones de amistad
     */
    public Page<FriendshipInvites> searchFriendInvitesByNameForUser(String searchText, User user) {
        List<FriendshipInvites> invitesList = user.getFriendShipsReceivedNPending();
        List<FriendshipInvites> result = new ArrayList<>();
        for (FriendshipInvites invite: invitesList) {
            if (invite.getFrom().getUsername().contains(searchText))
                result.add(invite);
        }
        return new PageImpl<>(result);
    }

    /**
     * Aceptar una invitación de amistad con el id especificado
     * @param id
     */
    public void acceptFriendshipInvite(Long id) {
        Optional<FriendshipInvites> inviteOp = friendsRepository.findById(id);
        FriendshipInvites invite;
        //if invite exists
        if (inviteOp.isPresent()) {
            invite = inviteOp.get();
            invite.accept();
            friendsRepository.save(invite);
        }
    }

    /**
     * Enviar una invitación de amistad del usuario from al usuario to
     * Solo se envía si el usuario to no ha recibido ya una invitación de el usuario from
     * @param from usuario que envía la invitación
     * @param to usuario que recibe la invitación
     */
    public void sendInvite(User from, User to) {
        Page<FriendshipInvites> invitesToUser = getFriendInvitesForUser(to);
        for (FriendshipInvites i: invitesToUser) {
            if (i.getFrom().equals(from)) {
                return;
            }
        }
        //only if users exist
        if (from != null && to != null) {
            FriendshipInvites invite = new FriendshipInvites(from, to, "PENDING");
            friendsRepository.save(invite);
        }
    }
}
