package com.uniovi.sdipractica134.services;

import com.uniovi.sdipractica134.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

//@Service  //descomentar si se quiere insertar datos
public class InsertSampleDataService {
    @Autowired
    private UsersService usersService;
    @Autowired
    private RolesService rolesService;
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

        User user15 = new User("user14@email.com", "User14Nombre", "User14Apellido");
        user15.setPassword("user14");
        user15.setRole(rolesService.getRoles()[RolesService.USER]);
        User user16 = new User("admin@email.com", "AdminNombre", "AdminApellido");
        user16.setPassword("admin");
        user16.setRole(rolesService.getRoles()[RolesService.ADMIN]);
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
}
}