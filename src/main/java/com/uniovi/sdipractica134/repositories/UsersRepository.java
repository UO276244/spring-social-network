package com.uniovi.sdipractica134.repositories;

import com.uniovi.sdipractica134.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface UsersRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    @Transactional
    @Modifying
    @Query("DELETE FROM User r WHERE r.username = ?1 ")
    void deleteByUsername(String username);

    @Query("SELECT count(r) FROM User r")
    int countUsers();

    /**
     * Un administrador puede ver a todos los usuarios
     * @return List User usuarios
     */
    @Query("Select u FROM User u WHERE u.role='ROLE_USER'")
    Page<User> getUsersAdminView(Pageable pageable);

    /**
     * Un usuario normal puede ver a todos los usuarios menos a sí mismo y a los administradores
     * @param id del usuario que lanza la request, para excluirlo de la lista
     * @return List User usuarios
     */
    @Query("Select u FROM User u WHERE u.id <> ?1 and upper(u.role)='ROLE_USER'")
    Page<User> getUsersNormalUserView(Pageable pageable, Long id);

    @Modifying
    @Transactional
    @Query("delete from User u where u.id in(:ids)")
    void deleteByIds(List<Long> ids);

    @Query("SELECT u FROM User u WHERE (LOWER(u.username) LIKE LOWER(?2) OR LOWER(u.name) LIKE LOWER(?2) OR " +
            "LOWER(u.surname) LIKE LOWER(?2)) AND u.id<>?1 and upper(u.role)='ROLE_USER'")
    Page<User> getUsersNormalUserViewSearch(Pageable pageable, Long id, String searchText);

    @Query("SELECT u FROM User u WHERE upper(u.role)='ROLE_ADMIN'")
    List<User> finAdminUsers();

}