/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.client.models;

import java.util.List;
import lombok.Data;

/**
 *
 * @author loisceka
 */
@Data
public class Account {
    
    private String id;
    private String email;
    private String password;
    private String token;
    private Boolean isDeleted;
    private Employee employee;
    private List<AccountRole> accountRoles;
    
    
    public Account() {
    }
    
    
    
}
