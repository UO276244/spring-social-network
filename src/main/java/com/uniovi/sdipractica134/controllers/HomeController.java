package com.uniovi.sdipractica134.controllers;


import com.uniovi.sdipractica134.services.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private LoggerService loggerService;

    @RequestMapping(value = {"/"})
    public String index(){

        loggerService.createPETLog("HomeController --> /",
                "GET",
                new String[] {});
        return "index";
    }



}