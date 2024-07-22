/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ConfigService configService;
    private String dolibarrApiUrl;
    private String dolibarrUserApiKey;
    
    @PostConstruct
    private void init(){
        dolibarrApiUrl = configService.getStringConfig("dolibarr.api.url");
        dolibarrUserApiKey=configService.getStringConfig("dolibarr.user.api.key");
    }
                 
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
