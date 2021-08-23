/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.client.services;

import com.ksm.client.models.AccountRole;
import com.ksm.client.models.Role;
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
public class RoleService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${base.url}/role")
    private String URL;
    
    //Find All 
    public List<Role> getAll(){
        ResponseEntity<List<Role>> response = restTemplate
                .exchange(URL, HttpMethod.GET, null, 
                new ParameterizedTypeReference<List<Role>>(){});
        
        return response.getBody();
    }
    //Find by ID 
    public Role getById(Integer id) {
        ResponseEntity<Role> response = restTemplate
                .exchange(URL + "/" +id, HttpMethod.GET, null,
                        new ParameterizedTypeReference<Role>() {});
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
     public void create(Role role) {
        try {
            restTemplate.postForEntity(URL, role, Role.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }
    //Update with put
    public void update(Integer id, Role role){
        try {
            restTemplate.put(URL + "/" + id, role);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }
}
