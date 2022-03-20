package com.uniovi.sdipractica134.controllers;


import com.uniovi.sdipractica134.services.LoggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private LoggerService loggerService;

    Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = {"/","/home"})
    public String index(){
        logger.info(
                loggerService.createPETLog("HomeController --> /",
                "GET",
                new String[] {})
        );

        return "index";
    }



}