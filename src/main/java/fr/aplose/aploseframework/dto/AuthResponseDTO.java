package fr.aplose.aploseframework.dto;

import fr.aplose.aploseframework.model.UserAccount;
import fr.aplose.aploseframework.model.security.Token;

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
