/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.client.services;

import com.ksm.client.models.Account;
import com.ksm.client.models.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author loisceka
 */
@Service
public class AccountService {

    @Autowired
    private RestTemplate restTemplate;


    @Value("${base.url}/account")
    private String URL;

    //Find All 
    public List<Account> getAll() {
        ResponseEntity<List<Account>> response = restTemplate
                .exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Account>>() {
                });

        return response.getBody();
    }

    //Find by ID 
    public Account getById(String id) {
        ResponseEntity<Account> response = restTemplate
                .exchange(URL + "/" + id, HttpMethod.GET, null,
                        new ParameterizedTypeReference<Account>() {
                });
        return response.getBody();
    }

    //Delete by ID O
    public void delete(String id) {
        try {
            restTemplate.delete(URL + "/" + id);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }

    //Insert Data
    public void create(Account account) {
        try {
            restTemplate.postForEntity(URL, account, Account.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }

    //Update with put
    public void update(String id, Account account) {
        try {
            restTemplate.put(URL + "/" + id, account);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }

}
