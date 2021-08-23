/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.client.models;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author loisceka
 */
@Data
public class Employee {
    
    private String id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String gender;
    private String address;
    private String phone;
    private boolean isDeleted;

    public Employee() {
    }
    
    
    
}
