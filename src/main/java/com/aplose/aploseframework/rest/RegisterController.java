/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.rest;

import com.aplose.aploseframework.dto.RegisterDto;
import com.aplose.aploseframework.dto.UserAccountDto;
import com.aplose.aploseframework.exception.RegistrationException;
import com.aplose.aploseframework.model.UserAccount;
import com.aplose.aploseframework.service.UserAccountService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author oandrade
 */
@RestController
@CrossOrigin
public class RegisterController {  
    
    @Autowired
    UserAccountService _userAccountService;

    
    /**
     * 
     * @param userAccountDto
     * @return
     * @throws RegistrationException 
     */
    @PostMapping("/api/register")
    public ResponseEntity<UserAccountDto> register(@Valid @RequestBody RegisterDto userAccountDto){
        return ResponseEntity.ok().body(this._userAccountService.registerUserAccount(userAccountDto));
    }
    

    /**
     * @param activationCode
     * @return
     * @throws
     */
    @PatchMapping("/api/accountActivation/{activationCode}")
    public ResponseEntity<String> activateAccount(@PathVariable String activationCode) {
        UserAccount userAccount = this._userAccountService.getByActivationCode(activationCode);
        if(userAccount == null){
            return ResponseEntity.badRequest().body("The activation code is invalid");
        }
        if(this._userAccountService.activationCodeIsExpired(userAccount)){
            this._userAccountService.reSendActivationCode(userAccount);
            return ResponseEntity.status(HttpStatus.GONE).body("The activation code is expired, a new code has been re-sent");
        }
        this._userAccountService.activateAccount(userAccount);
        return ResponseEntity.ok().build();
    }    
}
