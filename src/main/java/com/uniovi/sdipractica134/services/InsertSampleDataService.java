package com.uniovi.sdipractica134.services;

import com.uniovi.sdipractica134.entities.Post;
import com.uniovi.sdipractica134.entities.FriendshipInvites;
import com.uniovi.sdipractica134.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

        List<User> allUsers = new ArrayList<User>();
        User user1 = new User("user01@email.com", "User01Nombre", "User01Apellido");
        user1.setPassword("user01");
        user1.setRole(rolesService.getRoles()[RolesService.USER]);
        allUsers.add(user1);

        User user2 = new User("user02@email.com", "User02Nombre", "User02Apellido");
        user2.setPassword("user02");
        user2.setRole(rolesService.getRoles()[RolesService.USER]);
        allUsers.add(user2);

        User user3 = new User("user03@email.com", "User03Nombre", "User03Apellido");
        user3.setPassword("user03");
        user3.setRole(rolesService.getRoles()[RolesService.USER]);
        allUsers.add(user3);

        User user4 = new User("user04@email.com", "User04Nombre", "User04Apellido");
        user4.setPassword("user04");
        user4.setRole(rolesService.getRoles()[RolesService.USER]);
        allUsers.add(user4);


        User user5 = new User("user05@email.com", "User05Nombre", "User05Apellido");
        user5.setPassword("user05");
        user5.setRole(rolesService.getRoles()[RolesService.USER]);
        allUsers.add(user5);

        User user6 = new User("user06@email.com", "User06Nombre", "User06Apellido");
        user6.setPassword("user06");
        user6.setRole(rolesService.getRoles()[RolesService.USER]);
        allUsers.add(user6);

        User user7 = new User("user07@email.com", "User07Nombre", "User07Apellido");
        user7.setPassword("user07");
        user7.setRole(rolesService.getRoles()[RolesService.USER]);
        allUsers.add(user7);

        User user8 = new User("user08@email.com", "User08Nombre", "User08Apellido");
        user8.setPassword("user08");
        user8.setRole(rolesService.getRoles()[RolesService.USER]);
        allUsers.add(user8);

        User user9 = new User("user09@email.com", "User09Nombre", "User09Apellido");
        user9.setPassword("user09");
        user9.setRole(rolesService.getRoles()[RolesService.USER]);
        allUsers.add(user9);

        User user10 = new User("user10@email.com", "User10Nombre", "User10Apellido");
        user10.setPassword("user10");
        user10.setRole(rolesService.getRoles()[RolesService.USER]);
        allUsers.add(user10);

        User user11 = new User("user11@email.com", "User11Nombre", "User11Apellido");
        user11.setPassword("user11");
        user11.setRole(rolesService.getRoles()[RolesService.USER]);
        allUsers.add(user11);

        User user12 = new User("user12@email.com", "User12Nombre", "User12Apellido");
        user12.setPassword("user12");
        user12.setRole(rolesService.getRoles()[RolesService.USER]);
        allUsers.add(user12);

        User user13 = new User("user13@email.com", "User13Nombre", "User13Apellido");
        user13.setPassword("user13");
        user13.setRole(rolesService.getRoles()[RolesService.USER]);
        allUsers.add(user13);

        User user14 = new User("user14@email.com", "User14Nombre", "User14Apellido");
        user14.setPassword("user14");
        user14.setRole(rolesService.getRoles()[RolesService.USER]);
        allUsers.add(user14);

        User user15 = new User("user15@email.com", "User15Nombre", "User15Apellido");
        user15.setPassword("user15");
        user15.setRole(rolesService.getRoles()[RolesService.USER]);
        allUsers.add(user15);


        User user16 = new User("admin@email.com", "AdminNombre", "AdminApellido");
        user16.setPassword("admin");
        user16.setRole(rolesService.getRoles()[RolesService.ADMIN]);
        allUsers.add(user16);

        User user17 = new User("user17@email.com", "User17Nombre", "User17Apellido");
        user17.setPassword("user17");//USER CON POSTS
        user17.setRole(rolesService.getRoles()[RolesService.USER]);
        allUsers.add(user17);

        //USUARIO EXTRA -> EMPLEADOS PARA TEST EXTRA EN LOS QUE SE COMPRUEBA QUÉ PASA SI UN USUARIO / AMIGO NO TIENE POST.-> ES EL ÚNICO USUARIO SIN POSTS.
        User extra= new User("userExtra@email.com", "UserExtraNombre", "UserExtraApellido");
        extra.setPassword("userExtra");//USER CON POSTS
        extra.setRole(rolesService.getRoles()[RolesService.USER]);



        Set<Post> postsForTest =new HashSet<>();
        //Creación de posts
        LocalDate date= LocalDate.of(2021,2,21);
        postsForTest.add(new Post("Hola a todos!",date,"He ido a dar un paseo y me he roto la pierna!"));

        date= LocalDate.of(2022,11,5);
        postsForTest.add(new Post("Mi verano en la playa",date,"Os voy a contar todo lo que hice en verano"));
        date= LocalDate.of(2020,3,18);
        postsForTest.add(new Post("COVID?",date," 15 días sin  cole qué bien"));
        date= LocalDate.of(2020,10,23);
        postsForTest.add(new Post("COVID:(",date," Pensaba que iban a ser solo 15 días..."));
        date= LocalDate.of(2022,01,15);
        postsForTest.add(new Post("dia de la marmota",date," no puedo más!!!!!!!!!!!!!!!!!!"));
        date= LocalDate.of(2022,01,29);
        postsForTest.add(new Post("Cuándo estaremos tranquilos?",date,"Solo quiero un poco de tranquiliad!"));
        date= LocalDate.of(2022,02,2);
        postsForTest.add(new Post("FELICITADME TODOS!",date,"hoy es mi santo !!!!!!! :)"));
        date= LocalDate.of(2022,03,7);
        postsForTest.add(new Post("Qué agobio",date," Ya no queda nada para los exámenes!"));
        date= LocalDate.of(2020,10,23);
        postsForTest.add(new Post("Aburrimiento",date," Alguien me recomienda una película? Que sea buena eh!"));
        date= LocalDate.of(2022,3,27);
        postsForTest.add(new Post("otro mes se va",date,"Este mes se me ha hecho CORTÍSIMO ..."));
        user17.setPosts(postsForTest);

        //El usuario extra no tiene post.

        Set user1Friends = new HashSet<FriendshipInvites>() {
            {
                add(new FriendshipInvites(user1, user2, "ACCEPTED"));
                add(new FriendshipInvites(user1, user3, "ACCEPTED"));
                add(new FriendshipInvites(user1, user5, "PENDING"));
                add(new FriendshipInvites(user1, user7, "ACCEPTED"));
                add(new FriendshipInvites(user1, user17, "ACCEPTED"));
            }
        };
        user1.setFriendShipsSent(user1Friends);
        Set user2Friends = new HashSet<FriendshipInvites>() {
            {
                add(new FriendshipInvites(user2, user5, "PENDING"));
                add(new FriendshipInvites(user2, user3, "ACCEPTED"));
                add(new FriendshipInvites(user2, extra, "ACCEPTED"));
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




        for(User u : allUsers){

            generateDefaultPosts(u);
            usersService.addUser(u);
        }

        usersService.addUser(extra);

    }

    private void  generateDefaultPosts(User user) {//Por cada usuario los post tienen que ser nuevos, o se producirán errores de integridad en la base de datos debido a repeticiones de iD.
        Set<Post> posts = new HashSet<>();
        LocalDate date;
        for (int i = 0; i <10; i++) {
            date= LocalDate.of(2022,3,i+1);
            posts.add(new Post("Título por defecto",date,"Él unico usuario que tiene post realistas es el @user17Nombre, que es el que se emplea en los test de post."));
        }
        addPostsToUser(user, posts);
        user.setPosts(posts);

    }

    private void addPostsToUser(User user, Set<Post> posts) {
        for (var post: posts) {
            post.setOwner(user);

        }
    }
}