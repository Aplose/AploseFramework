/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 *
 * @author oandrade
 */
@Service
public class DolibarrService {
    @Value("${dolibarr.api.url}")
    private String dolibarrApiUrl;
    @Value("${dolibarr.user.api.key}")
    private String dolibarrUserApiKey;
                 
    RestClient restClient = RestClient.create();
    
    public String login(String login, String password){
        String token = restClient.post()
                .uri(dolibarrApiUrl+"/login?login="+login+"&password="+password) // possible de mettre &reset=1
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(String.class);
        return token;
    }
    
    
    
}
