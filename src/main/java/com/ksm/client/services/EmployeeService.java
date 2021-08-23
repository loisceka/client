/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.client.services;

import com.ksm.client.models.Employee;
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
public class EmployeeService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${base.url}/employee")
    private String URL;
    
    //Find All 
    public List<Employee> getAll(){
        ResponseEntity<List<Employee>> response = restTemplate
                .exchange(URL, HttpMethod.GET, null, 
                new ParameterizedTypeReference<List<Employee>>(){});
        
        return response.getBody();
    }
    //Find by ID 
    public Employee getById(String id) {
        ResponseEntity<Employee> response = restTemplate
                .exchange(URL + "/" +id, HttpMethod.GET, null,
                        new ParameterizedTypeReference<Employee>() {});
        return response.getBody();
    }
    //Delete by ID O
    public void delete(String id){
        try {
            restTemplate.delete(URL + "/" + id);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }
    //Insert Data
     public void create(Employee employee) {
        try {
            restTemplate.postForEntity(URL, employee, Employee.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }
    //Update with Patch
    public void update(String id, Employee employee){
        try {
            restTemplate.put(URL + "/" + id, employee);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }
}
