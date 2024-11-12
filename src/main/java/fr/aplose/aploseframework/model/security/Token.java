package fr.aplose.aploseframework.model.security;

import java.util.Date;

import fr.aplose.aploseframework.enums.AuthenticationTypeEnum;

public class Token {
    
    private String accessToken;
    private AuthenticationTypeEnum type;
    private Date expireAt;


    public Token(String accessToken, AuthenticationTypeEnum type, Date expireAt){
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


    public AuthenticationTypeEnum getType() {
        return type;
    }


    public void setType(AuthenticationTypeEnum type) {
        this.type = type;
    }


    public Date getExpireAt() {
        return expireAt;
    }


    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
    }
}
