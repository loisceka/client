/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.client.controllers;

import com.ksm.client.models.LoginRequest;
import com.ksm.client.models.LoginResponse;
import com.ksm.client.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author loisceka
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    LoginService loginService;

    //LOGIN
    @GetMapping()
    public String login(Model model) {
        model.addAttribute("login", new LoginRequest());
        String result = "";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal().equals("anonymousUser")) {
            result = "login";
        } else {
            result = "redirect:/dashboard";
        }
        return result;
    }

    @PostMapping()
    public String loginVerification(@ModelAttribute LoginRequest request, Model model) {

        String result = "";
        if (loginService.login(request)) {
            System.out.println("Login Success !");
            result = "employee";
        } else {
            System.out.println("Login Failed !");
            result = "login?error";
        }

        return "redirect:/" + result;
    }

}
