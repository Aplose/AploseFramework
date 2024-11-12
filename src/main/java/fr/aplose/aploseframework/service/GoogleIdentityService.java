package fr.aplose.aploseframework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.auth.oauth2.TokenVerifier;
import com.google.auth.oauth2.TokenVerifier.VerificationException;

import fr.aplose.aploseframework.dto.google.GoogleAuthResultDto;
import fr.aplose.aploseframework.model.UserAccount;




@Service
public class GoogleIdentityService {

    @Autowired
    private ConfigService _configService;
    @Autowired
    private UserAccountService _userAccountService;

    

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
