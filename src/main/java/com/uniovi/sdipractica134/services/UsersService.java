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
import java.util.Optional;

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

    /**
     * Devuelve la lista de todos los usuarios
     * @return List User usuarios
     */
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        usersRepository.findAll().forEach(users::add);
        return users;
    }

    /**
     * Devuelve la lista de usuarios que puede ver un administrador
     * @param pageable
     * @return Page User usuarios
     */
    public Page<User> getUsersAdminView(Pageable pageable){
        List<User> users = new ArrayList<>();
        return usersRepository.getUsersAdminView(pageable);
    }

    /**
     * Devuelve el usuario cuyo id está especificado
     * @param id
     * @return User usuario o null si no existe
     */
    public User getUser(Long id) {
        Optional<User> userOp = usersRepository.findById(id);
        if (userOp.isPresent())
            return userOp.get();
        return null;
    }

    /**
     * Añade un usuario a la base
     * @param user usuario
     */
    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    /**
     * Devuelve el usuario cuyo nombre de usuario es el especificado
     * @param username nombre de usuario
     * @return User usuario
     */
    public User getUserByUsername(String username) { return usersRepository.findByUsername(username); }

    /**
     * Elimina el usuario con id especificado
     * @param id
     */
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    /**
     * Elimina todos los usuarios cuyo id aparece en la lista
     * @param ids lista de ids
     */
    public void deleteByIds(List<Long> ids){
        //we remove every friendship invite involving them
        friendsRepository.deleteFriendshipInvitesBy(ids);
        //lastly, we remove the users.
        usersRepository.deleteByIds(ids);
    }

    /**
     * Devuelve la lista de usuarios que un usuario normal puede ver en la lista de usuarios conteniendo el texto
     * especificado
     * @param pageable
     * @param authenticated usuario autenticado
     * @param searchText texto a buscar
     * @return Page User lista de usuarios
     */
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
