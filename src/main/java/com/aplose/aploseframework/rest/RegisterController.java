/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.rest;

import com.aplose.aploseframework.ZDEVELOP.developHelper;
import com.aplose.aploseframework.dto.RegisterDto;
import com.aplose.aploseframework.dto.UserAccountDto;
import com.aplose.aploseframework.exception.RegistrationException;
import com.aplose.aploseframework.model.DolibarrUser;
import com.aplose.aploseframework.model.Person;
import com.aplose.aploseframework.model.UserAccount;
import com.aplose.aploseframework.model.dictionnary.AbstractDictionnary;
import com.aplose.aploseframework.model.dictionnary.Civility;
import com.aplose.aploseframework.service.AuthenticationService;
import com.aplose.aploseframework.service.DolibarrService;
import com.aplose.aploseframework.service.PersonService;
import com.aplose.aploseframework.service.UserAccountActivationService;
import com.aplose.aploseframework.service.UserAccountService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private AuthenticationService _authenticationService;
    @Autowired
    private UserAccountActivationService _accountActivationService;

    
    /**
     * 
     * @param userAccountDto
     * @return
     * @throws RegistrationException 
     */
    @PostMapping("/register")
    public ResponseEntity<Person> register(@Valid @RequestBody RegisterDto userAccountDto){
        
        if( ! userAccountDto.getPersonUserAccountPassword().equals(userAccountDto.getPasswordRepeat())){
            throw new RegistrationException("Password and password repeat do not match.");
        }

        return  ResponseEntity.ok(
            this._authenticationService.register(
                this._modelMapper.map(userAccountDto, Person.class),
                userAccountDto.getIsProfessional()
            )
        );
    }
    

    /**
     * @param activationCode
     * @return
     * @throws
     */
    @PatchMapping("/accountActivation/{activationCode}")
    public ResponseEntity<String> activateAccount(@PathVariable("activationCode") String activationCode) {
        UserAccount userAccount = this._userAccountService.getByActivationCode(activationCode);
        if(userAccount == null){
            return ResponseEntity.badRequest().body("The activation code is invalid");
        }
        if(this._accountActivationService.activationCodeIsExpired(userAccount)){
            this._accountActivationService.reSendActivationCode(userAccount);
            return ResponseEntity.status(HttpStatus.GONE).body("The activation code is expired, a new code has been re-sent");
        }
        this._accountActivationService.activateAccount(userAccount);
        return ResponseEntity.ok().body("Activation successfull !");
    }    
}
