package com.uniovi.sdipractica134.controllers;

import com.uniovi.sdipractica134.entities.User;
import com.uniovi.sdipractica134.services.LoggerService;
import com.uniovi.sdipractica134.services.RolesService;
import com.uniovi.sdipractica134.services.SecurityService;
import com.uniovi.sdipractica134.services.UsersService;
import com.uniovi.sdipractica134.validators.SignUpFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SignUpFormValidator signUpFormValidator;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private LoggerService loggerService;

    Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Validated User user, BindingResult result) {


        String[] params = new String[] {"username="+user.getUsername(),
                "passwd="+user.getPassword(),
                "name="+user.getName(),
                "surname="+user.getSurname()};


        logger.info(
                loggerService.createPETLog("UserController --> /signup","POST",params)
        );

        signUpFormValidator.validate(user,result);
        if(result.hasErrors()){
            return "signup";
        }

        user.setRole(rolesService.getRoles()[0]);
        usersService.addUser(user);


        logger.info(
                loggerService.createALTALog("UserController","POST",params )
        );

        securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());



        return "redirect:home";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {


        logger.info(
                loggerService.createPETLog("UserController --> /signup","GET", new String[] {})
        );

        model.addAttribute("user", new User());
        return "signup";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {

        logger.info(
                loggerService.createPETLog("UserController --> /login","GET", new String[] {})
        );


        model.addAttribute("user", new User());
        return "login";
    }



    @RequestMapping(value = "/prevlogout", method = RequestMethod.GET)
    public String prevlogout() {

        String loggedIn = securityService.findLoggedInUsername();

        logger.info(
                loggerService.createLOGOUTLog(loggedIn)
        );



        return "redirect:logout";
    }
}
