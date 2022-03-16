package com.uniovi.sdipractica134.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = {"/","/home"})
    public String index(){
        return "index";
    }



}