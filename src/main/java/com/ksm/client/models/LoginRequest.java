/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.client.models;

import lombok.Data;

/**
 *
 * @author loisceka
 */
@Data
public class LoginRequest {
    
    private String email;
    private String password;

    public LoginRequest() {
    }
}
