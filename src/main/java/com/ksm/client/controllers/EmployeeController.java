/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.client.controllers;

import com.ksm.client.models.Employee;
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
@RequestMapping("employee")
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("title", "KSM - Employee Web Page");
        return "employee";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") String id, Model model) {
        model.addAttribute("employee", employeeService.getById(id));
        return "employee-detail";
    }

    //CREATE
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("title", "KSM - Employee Form");
        return "employee-create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Employee employee, Model model) {
        employeeService.create(employee);
        return "redirect:/employee";
    }

    //UPDATE
    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("employee", employeeService.getById(id));
        model.addAttribute("title", "KSM - Employee Update Form");
        return "employee-update";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") String id,
            @ModelAttribute Employee employee, Model model) {
        employeeService.update(id, employee);
        return "redirect:/employee";
    }

    //DELETE
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") String id, Model model) {
        employeeService.delete(id);
        return "redirect:/employee";
    }
}
