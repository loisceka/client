/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.client.services;

import com.ksm.client.models.Account;
import com.ksm.client.models.AccountRole;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author loisceka
 */
@Service
public class AccountRoleService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${base.url}/accountrole")
    private String URL;
    
    //Find All 
    public List<AccountRole> getAll(){
        ResponseEntity<List<AccountRole>> response = restTemplate
                .exchange(URL, HttpMethod.GET, null, 
                new ParameterizedTypeReference<List<AccountRole>>(){});
        
        return response.getBody();
    }
    //Find by ID 
    public AccountRole getById(Integer id) {
        ResponseEntity<AccountRole> response = restTemplate
                .exchange(URL + "/" +id, HttpMethod.GET, null,
                        new ParameterizedTypeReference<AccountRole>() {});
        return response.getBody();
    }
    //Delete by ID O
    public void delete(Integer id){
        try {
            restTemplate.delete(URL + "/" + id);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }
    //Insert Data
     public void create(AccountRole accountRole) {
        try {
            restTemplate.postForEntity(URL, accountRole, AccountRole.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }
    //Update with put
    public void update(Integer id, AccountRole accountRole){
        try {
            restTemplate.put(URL + "/" + id, accountRole);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }
}
