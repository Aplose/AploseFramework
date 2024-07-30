/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.service;

import com.aplose.aploseframework.dto.AuthRequestDTO;
import com.aplose.aploseframework.dto.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author oandrade
 */
@Service
public class AuthenticationService {
    @Autowired
    DolibarrService dolibarrService;
    public String login(String userName, String password) {
        //TODO choisir le mode d'authent
        //Dans Dolibarr on ne se préoccupe pas du password
// retieve du user dolibbar via l'api pour récupérer sa token
// login et password sont vérifiés par SpringSecurity.
        return dolibarrService.login(userName, password);
    }
}
