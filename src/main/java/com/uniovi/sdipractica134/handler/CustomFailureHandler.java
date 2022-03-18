package com.uniovi.sdipractica134.handler;

import com.uniovi.sdipractica134.services.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomFailureHandler implements AuthenticationFailureHandler {


    @Autowired
    private LoggerService loggerService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        loggerService.createLOGIN_ERRLog(request.getParameter("username"));
        redirectStrategy.sendRedirect(request,response,"/login");

    }
}
