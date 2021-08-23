/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.client.config;

import com.ksm.client.services.LoginService;
import com.ksm.client.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author loisceka
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginService loginService;
    @Autowired
    RegisterService registerService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /*
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(loginService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    } 


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    } */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/dashboard").authenticated()
                .antMatchers("/employee").hasAnyRole("ADMIN", "USER", "SUPER_ADMIN")
                .antMatchers("/credential-profile").hasAnyRole("ADMIN", "USER", "SUPER_ADMIN")
                .antMatchers("/personal-profile").hasAnyRole("ADMIN", "USER", "SUPER_ADMIN")
                .antMatchers("/account").hasAnyRole("SUPER_ADMIN")
                .antMatchers("/accountrole").hasAnyRole("ADMIN", "SUPER_ADMIN")
                .antMatchers("/role").hasAnyRole("ADMIN", "SUPER_ADMIN")
                .antMatchers("/register").hasAnyRole("ADMIN", "SUPER_ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .failureForwardUrl("/login?erroor")
                .successForwardUrl("/dashboard")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .csrf()
                .disable();

    }
}
