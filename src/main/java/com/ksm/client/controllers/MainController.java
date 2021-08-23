/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.client.controllers;

import com.ksm.client.models.Account;
import com.ksm.client.models.Employee;
import com.ksm.client.models.LoginResponse;
import com.ksm.client.services.AccountService;
import com.ksm.client.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author loisceka
 */
@Controller
@RequestMapping()
public class MainController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    AccountService accountService;
    
    //UPDATE PERSONAL-PROFILE
    @GetMapping("personal/{id}/update")
    public String updatePersonalForm(String id, Model model) {
        model.addAttribute("employee", employeeService.getById(id));
        model.addAttribute("title", "KSM - Employee Update Form");
        return "personal-profile";
    }

    @PostMapping("personal/{id}/update")
    public String update(@PathVariable("id") String id,
            @ModelAttribute Employee employee, Model model) {
        employeeService.update(id, employee);
        return "redirect:/employee";
    }
    
    //UPDATE CREDENTIAL-PROFILE
    @GetMapping("credential/{id}/update")
    public String updateCredentialForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("account", accountService.getById(id));
        model.addAttribute("title", "KSM - Account Update Form");
        return "credential-profile";
    }

    @PostMapping("credential/{id}/update")
    public String update(@PathVariable("id") String id,
            @ModelAttribute Account account, Model model) {
        accountService.update(id, account);
        return "redirect:/employee";
    }
}
