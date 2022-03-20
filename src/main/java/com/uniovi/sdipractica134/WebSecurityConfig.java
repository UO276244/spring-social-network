package com.uniovi.sdipractica134;

import com.uniovi.sdipractica134.handler.CustomFailureHandler;
import com.uniovi.sdipractica134.handler.CustomSuccessHandler;
import com.uniovi.sdipractica134.handler.CustomSuccessLogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CustomSuccessHandler successHandler() {
        return new CustomSuccessHandler();
    }
    @Bean
    public CustomFailureHandler failureHandler() {
        return new CustomFailureHandler();
    }
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){return  new CustomSuccessLogoutHandler();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean public SpringSecurityDialect securityDialect() { return new SpringSecurityDialect(); }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**", "/images/**", "/script/**", "/", "/signup","/login", "/login/**", "/","/home").permitAll()
                .antMatchers("/friends/**").hasAuthority("ROLE_USER")
                .antMatchers("/invite/**").hasAuthority("ROLE_USER")
                .antMatchers("/user/list/delete/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/user/list").authenticated()
                .antMatchers("/post/**").authenticated()
                .antMatchers("/logout").authenticated()
                .antMatchers("/logs/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/","home").hasAnyAuthority()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(successHandler())
                .failureHandler(failureHandler())
                .permitAll()

                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler())
                .permitAll();
    }
}
