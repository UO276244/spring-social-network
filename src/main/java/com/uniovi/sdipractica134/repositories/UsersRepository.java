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
    User findByEmail(String email);

    @Query("SELECT count(r) FROM User r")
    int countUsers();

    /**
     * An Admin can list all the users but her/him, as he cannot delete himself, it's better to not
     * include him/her.
     * @param id
     * @return List<User> users
     */
    @Query("Select u FROM User u WHERE u.email<>?1")
    List<User> getUsersAdminView(String id);
    /**
     * A normal user can list the rest of users but him and administrators.
     * @param id
     * @return List<User> users
     */
    @Query("Select u FROM User u WHERE u.email <> ?1 and upper(u.role)='ROLE_USER'")
    List<User> getUsersNormalUserView(String id);

    @Modifying
    @Transactional
    @Query("delete from User u where u.id in(:ids)")
    void deleteByIds(List<Long> ids);

}