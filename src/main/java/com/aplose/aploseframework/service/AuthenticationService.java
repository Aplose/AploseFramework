/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aplose.aploseframework.ZDEVELOP.developHelper;
import com.aplose.aploseframework.dto.AuthResponseDTO;
import com.aplose.aploseframework.model.UserAccount;
import com.aplose.aploseframework.utils.jwt.JwtTokenUtil;


/**
 *
 * @author oandrade
 */
@Service
public class AuthenticationService {

    @Autowired
    private DolibarrService dolibarrService;
    @Autowired
    private UserAccountService _userAccountService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    
    public String dolibarrLogin(String userName, String password) {
        //TODO choisir le mode d'authent
        //Dans Dolibarr on ne se préoccupe pas du password
        // retieve du user dolibbar via l'api pour récupérer sa token
        // login et password sont vérifiés par SpringSecurity.
        return dolibarrService.login(userName, password);
    }


    public AuthResponseDTO internalLogin(String username, String password){
     
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken( username, password )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserAccount userDetails = userDetailsService.loadUserByUsername(username);

        return new AuthResponseDTO(jwtTokenUtil.generateToken(userDetails), userDetails);
    }
}
