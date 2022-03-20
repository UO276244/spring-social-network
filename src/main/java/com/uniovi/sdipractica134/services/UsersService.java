package com.uniovi.sdipractica134.services;

import com.uniovi.sdipractica134.entities.User;
import com.uniovi.sdipractica134.repositories.FriendsRepository;
import com.uniovi.sdipractica134.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RolesService rolesService;
    @Autowired
    private FriendsRepository friendsRepository;
    @PostConstruct
    public void init(){

    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        usersRepository.findAll().forEach(users::add);
        return users;
    }
    public Page<User> getUsersAdminView(Pageable pageable){
        List<User> users = new ArrayList<>();
        return usersRepository.getUsersAdminView(pageable);
    }


    public User getUser(Long id) {
        return usersRepository.findById(id).get();
    }

    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    public User getUserByUsername(String username) { return usersRepository.findByUsername(username); }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    public void deleteByIds(List<Long> ids){
        Iterable<User> users = usersRepository.findAllById(ids);
        users.iterator().forEachRemaining( (user) -> user.removeFriends());
        friendsRepository.deleteFriendshipInvitesBy(ids);
        usersRepository.deleteByIds(ids);
    }



    public Page<User> getUsersView(Pageable pageable, User authenticated, String searchText) {
        if(authenticated.getRole().toUpperCase().equals(rolesService.getRoles()[RolesService.ADMIN])){
            return usersRepository.getUsersAdminView(Pageable.unpaged());
        }else{
            if(searchText != null && !searchText.isEmpty()){
                searchText = "%" + searchText + "%";
               return usersRepository.getUsersNormalUserViewSearch(pageable, authenticated.getId(), searchText);
            }else{
                return usersRepository.getUsersNormalUserView(pageable, authenticated.getId());
            }
        }
    }
}
