package com.uniovi.sdipractica134.controllers;

import com.uniovi.sdipractica134.entities.User;
import com.uniovi.sdipractica134.services.RolesService;
import com.uniovi.sdipractica134.services.SecurityService;
import com.uniovi.sdipractica134.services.UsersService;
import com.uniovi.sdipractica134.validators.SignUpFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Locale;

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

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Validated User user, BindingResult result) {

        signUpFormValidator.validate(user,result);
        if(result.hasErrors()){
            return "signup";
        }

        user.setRole(rolesService.getRoles()[0]);
        usersService.addUser(user);
        securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
        return "redirect:home";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping("/user/list")
    public String getList(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authenticated = usersService.getUserByEmail(auth.getName());
        if(authenticated.getRole().toUpperCase().equals(rolesService.getRoles()[RolesService.ADMIN])){
            model.addAttribute("usersList", usersService.getUsersAdminView(authenticated.getEmail()));
        }else{
            model.addAttribute("usersList", usersService.getUsersNormalUserView(authenticated.getEmail()));
        }

        return "user/list";
    }

    @RequestMapping(value="/user/list/delete/{userIds}")
    public String deleteUsers(Model model, @PathVariable List<Long> userIds) {
        usersService.deleteById(userIds);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authenticated = usersService.getUserByEmail(auth.getName());
        model.addAttribute("usersList", usersService.getUsersAdminView(authenticated.getEmail()));
        return "user/list :: tableUsers";
    }
}
