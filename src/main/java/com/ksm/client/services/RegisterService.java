/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.client.services;

import com.ksm.client.models.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author loisceka
 */
@Service
public class RegisterService {
    
    @Autowired
    private RestTemplate restTemplate;

    @Value("${base.url}/register")
    private String URL;
    
    //Register
    public boolean register(Register register) {
        try {
            ResponseEntity<Register> reg
                    = restTemplate.postForEntity(URL, register, Register.class);

            return true;
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                return false;
            }
        }
        return false;
    }
}
