package com.aplose.aploseframework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aplose.aploseframework.dto.AuthResponseDTO;
import com.aplose.aploseframework.dto.google.GoogleAuthResultDto;
import com.aplose.aploseframework.model.UserAccount;
import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.auth.oauth2.TokenVerifier;
import com.google.auth.oauth2.TokenVerifier.VerificationException;

import jakarta.persistence.EntityNotFoundException;



@Service
public class GoogleIdentityService {

    @Autowired
    private ConfigService _configService;
    @Autowired
    private UserAccountService _userAccountService;
    @Autowired
    private AuthenticationService _authenticationService;


    
    public AuthResponseDTO googleLogin(String token) throws VerificationException{

        JsonWebSignature.Payload payload = this.getPayload(token);
        UserAccount userAccount;

        try{
            userAccount = this._userAccountService.loadUserByUsername((String) payload.get("email"));
        }
        catch(EntityNotFoundException e){
            System.err.println("Error at AuthenticationService.googleLogin(): " + e.getMessage());
            throw new VerificationException("UserAccount with username" + payload.get("email") + " not exist");
        }

        return this._authenticationService.internalLogin(userAccount.getUsername(), payload.getSubject());
    }
    


    public JsonWebSignature.Payload getPayload(String token) throws VerificationException{
        String CLIENT_ID = this._configService.getStringConfig("google.client.id");
        JsonWebSignature.Payload payload;

            payload = TokenVerifier
            .newBuilder()
            .setAudience(CLIENT_ID)
            .build()
            .verify(token)
            .getPayload();

        return payload;
    }



    public GoogleAuthResultDto getUserClaimsFromGoogleToken(String googleToken) throws Exception{

        JsonWebSignature.Payload payload = this.getPayload(googleToken);

        UserAccount userAccount;
        try{
            userAccount = this._userAccountService.loadUserByUsername((String) payload.get("email"));
        }
        catch(UsernameNotFoundException e){
            System.out.println("\n\n\n" + e.getMessage() + "\n\n");
            userAccount = null;
        }
        
        if(userAccount != null){
            throw new Exception("Account allready exist with this e-mail address.");
        }

        return new GoogleAuthResultDto(googleToken, payload);
    }


}
