/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.client.controllers;

import com.ksm.client.models.AccountRole;
import com.ksm.client.models.Role;
import com.ksm.client.services.AccountRoleService;
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
@RequestMapping("accountrole")
public class AccountRoleController {
    @Autowired
    AccountRoleService accountRoleService;
    
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("accountroles", accountRoleService.getAll());
        model.addAttribute("title", "KSM - Account Role Web Page");
        return "accountrole";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("accountrole", accountRoleService.getById(id));
        return "accountrole-detail";
    }

    //CREATE
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("accountrole", new AccountRole());
        model.addAttribute("title", "KSM - Role Form");
        return "accountrole-form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute AccountRole accountRole, Model model) {
        accountRoleService.create(accountRole);
        return "redirect:/accountrole";
    }

    //UPDATE
    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("role", accountRoleService.getById(id));
        model.addAttribute("title", "KSM - Account Role Update Form");
        return "accountrole-update";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id,
            @ModelAttribute AccountRole accountRole, Model model) {
        accountRoleService.update(id, accountRole);
        return "redirect:/role";
    }

    //DELETE
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id, Model model) {
        accountRoleService.delete(id);
        return "redirect:/role";
    }
    
}
