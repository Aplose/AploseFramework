package fr.aplose.aploseframework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import fr.aplose.aploseframework.service.DolibarrService;

/**
 * Allow lot of different authentication (Dolibarr, Internal,...)
 * @author oandrade
 */
@Component
public class DolibarrAuthenticationProvier implements AuthenticationProvider {
    @Autowired
    DolibarrService dolibarrService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        String dolibarrToken;
        try{
            dolibarrToken = dolibarrService.login(username, password);
        }
        catch(Exception e){
            authentication.setAuthenticated(false);
            return authentication;
        }
        if ( dolibarrToken!=null && dolibarrToken.trim().length()>0){
            authentication.setAuthenticated(true);
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
    
}
