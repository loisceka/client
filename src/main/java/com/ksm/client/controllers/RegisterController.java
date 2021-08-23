/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.client.controllers;

import com.ksm.client.models.Register;
import com.ksm.client.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author loisceka
 */
@Controller
@RequestMapping("register")
public class RegisterController {

    @Autowired
    RegisterService registerService;
    
    
    //REGISTER
    @GetMapping()
    public String createForm(Model model) {
        model.addAttribute("register", new Register());
        model.addAttribute("title", "KSM - Account Form");
        return "registration";
    }

    @PostMapping()
    public String create(@ModelAttribute Register register) {
        Boolean reg = registerService.register(register);
        if(reg = true ){
            return "redirect:/login";
        }
        return "registration";
    }
}
