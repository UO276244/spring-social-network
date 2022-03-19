package com.uniovi.sdipractica134.services;

import com.uniovi.sdipractica134.entities.FriendshipInvites;
import com.uniovi.sdipractica134.entities.Post;
import com.uniovi.sdipractica134.entities.FriendshipInvites;
import com.uniovi.sdipractica134.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.HashSet;
import java.util.Set;

@Service  //descomentar si se quiere insertar datos
public class InsertSampleDataService {
    @Autowired
    private UsersService usersService;
    @Autowired
    private RolesService rolesService;
    @Autowired
    private PostsService postsService;
    @PostConstruct
    public void init() {
        User user1 = new User("user01@email.com", "User01Nombre", "User01Apellido");
        user1.setPassword("user01");
        user1.setRole(rolesService.getRoles()[RolesService.USER]);

        User user2 = new User("user02@email.com", "User02Nombre", "User02Apellido");
        user2.setPassword("user02");
        user2.setRole(rolesService.getRoles()[RolesService.USER]);

        User user3 = new User("user03@email.com", "User03Nombre", "User03Apellido");
        user3.setPassword("user03");
        user3.setRole(rolesService.getRoles()[RolesService.USER]);

        User user4 = new User("user04@email.com", "User04Nombre", "User04Apellido");
        user4.setPassword("user04");
        user4.setRole(rolesService.getRoles()[RolesService.USER]);

        User user5 = new User("user05@email.com", "User05Nombre", "User05Apellido");
        user5.setPassword("user05");
        user5.setRole(rolesService.getRoles()[RolesService.USER]);

        User user6 = new User("user06@email.com", "User06Nombre", "User06Apellido");
        user6.setPassword("user06");
        user6.setRole(rolesService.getRoles()[RolesService.USER]);

        User user7 = new User("user07@email.com", "User07Nombre", "User07Apellido");
        user7.setPassword("user07");
        user7.setRole(rolesService.getRoles()[RolesService.USER]);

        User user8 = new User("user08@email.com", "User08Nombre", "User08Apellido");
        user8.setPassword("user08");
        user8.setRole(rolesService.getRoles()[RolesService.USER]);

        User user9 = new User("user09@email.com", "User09Nombre", "User09Apellido");
        user9.setPassword("user09");
        user9.setRole(rolesService.getRoles()[RolesService.USER]);

        User user10 = new User("user10@email.com", "User10Nombre", "User10Apellido");
        user10.setPassword("user10");
        user10.setRole(rolesService.getRoles()[RolesService.USER]);

        User user11 = new User("user11@email.com", "User11Nombre", "User11Apellido");
        user11.setPassword("user11");
        user11.setRole(rolesService.getRoles()[RolesService.USER]);

        User user12 = new User("user12@email.com", "User12Nombre", "User12Apellido");
        user12.setPassword("user12");
        user12.setRole(rolesService.getRoles()[RolesService.USER]);

        User user13 = new User("user13@email.com", "User13Nombre", "User13Apellido");
        user13.setPassword("user13");
        user13.setRole(rolesService.getRoles()[RolesService.USER]);

        User user14 = new User("user14@email.com", "User14Nombre", "User14Apellido");
        user14.setPassword("user14");
        user14.setRole(rolesService.getRoles()[RolesService.USER]);

        User user15 = new User("user15@email.com", "User15Nombre", "User15Apellido");
        user15.setPassword("user15");
        user15.setRole(rolesService.getRoles()[RolesService.USER]);
        User user16 = new User("admin@email.com", "AdminNombre", "AdminApellido");
        user16.setPassword("admin");
        user16.setRole(rolesService.getRoles()[RolesService.ADMIN]);

        Set user1Friends = new HashSet<FriendshipInvites>() {
            {
                add(new FriendshipInvites(user1, user2, "ACCEPTED"));
                add(new FriendshipInvites(user1, user3, "ACCEPTED"));
                add(new FriendshipInvites(user1, user5, "PENDING"));
                add(new FriendshipInvites(user1, user7, "ACCEPTED"));
            }
        };
        user1.setFriendShipsSent(user1Friends);
        Set user2Friends = new HashSet<FriendshipInvites>() {
            {
                add(new FriendshipInvites(user2, user5, "PENDING"));
                add(new FriendshipInvites(user2, user3, "ACCEPTED"));
            }
        };
        user2.setFriendShipsSent(user2Friends);
        Set user3Friends = new HashSet<FriendshipInvites>() {
            {
                add(new FriendshipInvites(user3, user10, "ACCEPTED"));
                add(new FriendshipInvites(user3, user5, "PENDING"));
            }
        };
        user3.setFriendShipsSent(user3Friends);
        usersService.addUser(user1);
        usersService.addUser(user2);
        usersService.addUser(user3);
        usersService.addUser(user4);
        usersService.addUser(user5);
        usersService.addUser(user6);
        usersService.addUser(user7);
        usersService.addUser(user8);
        usersService.addUser(user9);
        usersService.addUser(user10);
        usersService.addUser(user11);
        usersService.addUser(user12);
        usersService.addUser(user13);
        usersService.addUser(user14);
        usersService.addUser(user15);
        usersService.addUser(user16);


        //Creación de posts
        LocalDate date= LocalDate.of(2021,2,21);
        Post post1=new Post("Hola a todos!",date,"Esta es mi primera publicación!");
        date= LocalDate.of(2022,11,5);
        Post post2=new Post("Mi verano en la playa",date,"Os voy a contar todo lo que hice en verano");
        date= LocalDate.of(2020,3,18);
        Post post3=new Post("COVID?",date," 15 días sin  cole qué bien");
        date= LocalDate.of(2020,10,23);
        Post post4=new Post("COVID:(",date," Pensaba que iban a ser solo 15 días...");
        postsService.addNewPost(post1);
        postsService.addNewPost(post2);
        postsService.addNewPost(post3);
        postsService.addNewPost(post4);


}
}