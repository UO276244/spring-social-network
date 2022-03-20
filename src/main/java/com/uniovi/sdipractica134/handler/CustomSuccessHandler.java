package com.uniovi.sdipractica134.handler;



import com.uniovi.sdipractica134.services.LoggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CustomSuccessHandler implements AuthenticationSuccessHandler {



    @Autowired
    private LoggerService loggerService;


    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    Logger logger = LoggerFactory.getLogger(CustomSuccessHandler.class);

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {

        logger.info(
                loggerService.createLOGIN_EXLog(authentication.getName())
        );

        redirectStrategy.sendRedirect(request,response,"/user/list");

    }

}




