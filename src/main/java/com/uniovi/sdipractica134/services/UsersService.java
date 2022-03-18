package com.uniovi.sdipractica134.services;

import com.uniovi.sdipractica134.entities.User;
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

    @PostConstruct
    public void init(){

    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        usersRepository.findAll().forEach(users::add);
        return users;
    }
    public List<User> getUsersAdminView(String email){
        List<User> users = new ArrayList<>();
        return usersRepository.getUsersAdminView(email);
    }
    public List<User> getUsersNormalUserView(String email){
        List<User> users = new ArrayList<>();
        return usersRepository.getUsersNormalUserView(email);
    }

    public User getUser(Long id) {
        return usersRepository.findById(id).get();
    }

    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    public User getUserByEmail(String email) { return usersRepository.findByEmail(email); }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    public void deleteById(List<Long> ids){
        usersRepository.deleteByIds(ids);
    }

    public List<User> getUsersNormalUserViewSearch(Long id, String searchText) {
        searchText = "%" + searchText + "%";
        return usersRepository.getUsersNormalUserViewSearch(id,searchText);
    }
}
