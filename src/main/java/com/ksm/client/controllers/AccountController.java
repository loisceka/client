/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.client.controllers;

import com.ksm.client.models.Account;
import com.ksm.client.models.Employee;
import com.ksm.client.services.AccountService;
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
@RequestMapping("account")
public class AccountController {
    @Autowired
    AccountService accountService;
    
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("accounts", accountService.getAll());
        model.addAttribute("title", "KSM - Account Web Page");
        return "account";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") String id, Model model) {
        model.addAttribute("account", accountService.getById(id));
        return "account-detail";
    }

    //CREATE
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("account", new Account());
        model.addAttribute("title", "KSM - Account Form");
        return "account-form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Account account, Model model) {
        accountService.create(account);
        return "redirect:/account";
    }

    //UPDATE
    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("account", accountService.getById(id));
        model.addAttribute("title", "KSM - Account Update Form");
        return "account-update";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") String id,
            @ModelAttribute Account account, Model model) {
        accountService.update(id, account);
        return "redirect:/account";
    }

    //DELETE
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") String id, Model model) {
        accountService.delete(id);
        return "redirect:/account";
    }
    
}
