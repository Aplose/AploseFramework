/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.aplose.aploseframework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.auth.oauth2.TokenVerifier.VerificationException;

import fr.aplose.aploseframework.dto.AuthResponseDTO;
import fr.aplose.aploseframework.enums.AuthenticationTypeEnum;
import fr.aplose.aploseframework.model.UserAccount;
import fr.aplose.aploseframework.model.security.Token;
import fr.aplose.aploseframework.tool.jwt.JwtTokenTool;
import io.jsonwebtoken.Claims;
import jakarta.persistence.EntityNotFoundException;


/**
 *
 * @author oandrade
 */
@Service
public class AuthenticationService {

    @Autowired
    private DolibarrService _dolibarrService;
    @Autowired
    private UserAccountService _userAccountService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenTool _jwtTokenUtil;
    @Autowired
    private GoogleIdentityService _googleService;
    @Autowired
    private PasswordEncoder _passwordEncoder;



    
    public String dolibarrLogin(String userName, String password) {
        //TODO choisir le mode d'authent
        //Dans Dolibarr on ne se préoccupe pas du password
        // retieve du user dolibbar via l'api pour récupérer sa token
        // login et password sont vérifiés par SpringSecurity.
        return this._dolibarrService.login(userName, password);
    }

    

    public AuthResponseDTO internalLogin(String username, String password){

        final UserAccount userDetails;

        try{
            userDetails = this._userAccountService.loadUserByUsername(username);
        }catch(UsernameNotFoundException e){
            return null;
        }

        if(this._passwordEncoder.matches(password, userDetails.getPassword())){

            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken( username, password )
            );
    
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            String token = this._jwtTokenUtil.generateToken(userDetails);
    
            return new AuthResponseDTO(
                new Token(
                    token, 
                    AuthenticationTypeEnum.INTERNAL, 
                    this._jwtTokenUtil.extractClaim(token, Claims::getExpiration)
                ),
                userDetails
            );
        }
        return null;
    }



    public AuthResponseDTO googleLogin(String googleToken) throws VerificationException{

        JsonWebSignature.Payload payload = this._googleService.getPayload(googleToken);
        UserAccount userAccount;

        try{
            userAccount = this._userAccountService.loadUserByUsername((String) payload.get("email"));
        }
        catch(EntityNotFoundException e){
            System.err.println("Error at AuthenticationService.googleLogin(): " + e.getMessage());
            throw new VerificationException("UserAccount with username" + payload.get("email") + " not exist");
        }

        if(userAccount != null){

            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken( userAccount.getUsername(), payload.getSubject() )
            );
    
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            String token = this._jwtTokenUtil.generateToken(userAccount);
    
            return new AuthResponseDTO(
                new Token(
                    token, 
                    AuthenticationTypeEnum.GOOGLE, 
                    this._jwtTokenUtil.extractClaim(token, Claims::getExpiration)
                ),
                userAccount
            );
        }
        return null;
    }
}
