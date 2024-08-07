/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.dto;

import java.util.Date;

import com.aplose.aploseframework.model.UserAccount;
import com.aplose.aploseframework.model.security.Token;

/**
 *
 * @author oandrade
 */
public class AuthResponseDTO {

    private Token token;
    private UserAccount userAccount;


    public AuthResponseDTO(){}

    public AuthResponseDTO(Token token, UserAccount userAccount){
        this.token = token;
        this.userAccount = userAccount;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }




}
