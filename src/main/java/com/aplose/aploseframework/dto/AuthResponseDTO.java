/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.dto;

import com.aplose.aploseframework.model.UserAccount;

/**
 *
 * @author oandrade
 */
public class AuthResponseDTO {

    private String accessToken;
    private UserAccount userAccount;


    public AuthResponseDTO(){}

    public AuthResponseDTO(String accessToken, UserAccount userAccount){
        this.accessToken = accessToken;
        this.userAccount = userAccount;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
