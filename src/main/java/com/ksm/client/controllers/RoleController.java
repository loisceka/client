/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.client.controllers;

import com.ksm.client.models.Employee;
import com.ksm.client.models.Role;
import com.ksm.client.services.RoleService;
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
@RequestMapping("role")
public class RoleController {
    @Autowired
    RoleService roleService;
    
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("roles", roleService.getAll());
        model.addAttribute("title", "KSM - Role Web Page");
        return "role";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("role", roleService.getById(id));
        return "role-detail";
    }

    //CREATE
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("role", new Role());
        model.addAttribute("title", "KSM - Role Form");
        return "role-create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Role role, Model model) {
        roleService.create(role);
        return "redirect:/role";
    }

    //UPDATE
    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("role", roleService.getById(id));
        model.addAttribute("title", "KSM - Role Update Form");
        return "role-update";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id,
            @ModelAttribute Role role, Model model) {
        roleService.update(id, role);
        return "redirect:/role";
    }

    //DELETE
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id, Model model) {
        roleService.delete(id);
        return "redirect:/role";
    }
    
}
