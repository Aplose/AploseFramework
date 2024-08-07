package com.aplose.aploseframework.model.security;

import java.util.Date;

import com.aplose.aploseframework.enums.TokenCategoryEnum;

public class Token {
    
    private String accessToken;
    private TokenCategoryEnum type;
    private Date expireAt;


    public Token(String accessToken, TokenCategoryEnum type, Date expireAt){
        this.accessToken = accessToken;
        this.type = type;
        this.expireAt = expireAt;
    }


    public String getAccessToken() {
        return accessToken;
    }


    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


    public TokenCategoryEnum getType() {
        return type;
    }


    public void setType(TokenCategoryEnum type) {
        this.type = type;
    }


    public Date getExpireAt() {
        return expireAt;
    }


    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
    }
}
