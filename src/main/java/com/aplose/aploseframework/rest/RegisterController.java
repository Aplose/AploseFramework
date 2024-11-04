/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.rest;

import com.aplose.aploseframework.dto.RegisterDto;
import com.aplose.aploseframework.dto.google.GoogleAuthResultDto;
import com.aplose.aploseframework.exception.RegistrationException;
import com.aplose.aploseframework.model.Person;
import com.aplose.aploseframework.model.UserAccount;
import com.aplose.aploseframework.service.GoogleIdentityService;
import com.aplose.aploseframework.service.RegisterService;
import com.aplose.aploseframework.service.UserAccountService;
import jakarta.validation.Valid;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author oandrade
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class RegisterController {  
    
    @Autowired
    private ModelMapper _modelMapper;
    @Autowired
    private UserAccountService _userAccountService;
    @Autowired
    private RegisterService _registerService;
    @Autowired
    private GoogleIdentityService _googleService;

    
    /**
     * 
     * @param registerDto
     * @return ResponseEntity<Person>
     * @throws RegistrationException 
     */
    @PostMapping("/register")
    public ResponseEntity<Person> register(@Valid @RequestBody RegisterDto registerDto){
        
        if( ! registerDto.getUserAccountPassword().equals(registerDto.getPasswordRepeat())){
            throw new RegistrationException("Password and password repeat do not match.");
        }

        return  ResponseEntity.ok(
            this._registerService.register(
                registerDto.getAuthenticationType(),
                this._modelMapper.map(registerDto, Person.class),
                registerDto.getIsProfessional()
            )
        );
    }
    

    /**
     * @param activationCode
     * @return
     * @throws
     */
    @PatchMapping("/account-activation/{activationCode}")
    public ResponseEntity<String> activateAccount(@PathVariable("activationCode") String activationCode) {
        UserAccount userAccount = this._userAccountService.getByActivationCode(activationCode);
        if(userAccount == null){
            return ResponseEntity.badRequest().body("The activation code is invalid");
        }
        if(this._registerService.activationCodeIsExpired(userAccount)){
            this._registerService.reSendActivationCode(userAccount);
            return ResponseEntity.status(HttpStatus.GONE).body("The activation code is expired, a new code has been re-sent");
        }
        this._registerService.activateAccount(userAccount);
        return ResponseEntity.ok("Activation successfull !");
    }    


    @PostMapping("/google-extract-claims")
    public ResponseEntity<GoogleAuthResultDto> googleExtractClaims(@RequestBody String googleToken){
        GoogleAuthResultDto claims;
        try{
            claims = this._googleService.getUserClaimsFromGoogleToken(googleToken);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(claims);
    }
}
