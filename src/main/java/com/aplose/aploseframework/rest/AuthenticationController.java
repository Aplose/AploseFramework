/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.rest;

import com.aplose.aploseframework.dto.AuthRequestDTO;
import com.aplose.aploseframework.dto.AuthResponseDTO;
import com.aplose.aploseframework.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author oandrade
 */
@RestController
@CrossOrigin
@RequestMapping("/api/authentication")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;
    
    @PostMapping("/login")
    public AuthResponseDTO login(AuthRequestDTO authRequestDTO){
        return authenticationService.login(authRequestDTO);
    }
    
}
