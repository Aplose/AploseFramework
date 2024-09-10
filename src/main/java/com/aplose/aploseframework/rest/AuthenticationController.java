/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.rest;

import com.aplose.aploseframework.dto.AuthRequestDTO;
import com.aplose.aploseframework.dto.AuthResponseDTO;
import com.aplose.aploseframework.enums.AuthenticationTypeEnum;
import com.aplose.aploseframework.model.UserAccount;
import com.aplose.aploseframework.model.security.Token;
import com.aplose.aploseframework.service.AuthenticationService;
import com.google.auth.oauth2.TokenVerifier.VerificationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private AuthenticationService _authenticationService;


    
    @PostMapping("/dolibarr-login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO authRequestDTO){
        return new AuthResponseDTO(
            new Token(
                _authenticationService.dolibarrLogin(authRequestDTO.getUsername(),authRequestDTO.getPassword()), 
                AuthenticationTypeEnum.DOLIBARR, 
                null
            ),
            new UserAccount()
        );
    }
    


    @PostMapping("/internal-login")
    public ResponseEntity<AuthResponseDTO> internalLogin(@RequestBody AuthRequestDTO loginRequest) {
        return ResponseEntity.ok(
            this._authenticationService.internalLogin(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );
    }


    @PostMapping("/google-login")
    public ResponseEntity<AuthResponseDTO> googleLogin(@RequestBody String googleToken) throws VerificationException{
        return ResponseEntity.ok(this._authenticationService.googleLogin(googleToken));
    }
}
