package fr.aplose.aploseframework.rest;

import com.google.auth.oauth2.TokenVerifier.VerificationException;

import fr.aplose.aploseframework.dto.AuthRequestDTO;
import fr.aplose.aploseframework.dto.AuthResponseDTO;
import fr.aplose.aploseframework.enums.AuthenticationTypeEnum;
import fr.aplose.aploseframework.model.UserAccount;
import fr.aplose.aploseframework.model.security.Token;
import fr.aplose.aploseframework.service.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author oandrade
 */
@RestController
@CrossOrigin
@RequestMapping("/api/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService _authenticationService;


    
    @PostMapping("/dolibarr-login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO authRequestDTO){
        return new AuthResponseDTO(
            new Token(
                _authenticationService.dolibarrLogin(authRequestDTO.getUsername(),authRequestDTO.getPassword()), 
                AuthenticationTypeEnum.DOLIBARR, 
                null
            ),
            new UserAccount()
        );
    }
    


    @PostMapping("/internal-login")
    public ResponseEntity<AuthResponseDTO> internalLogin(@RequestBody AuthRequestDTO loginRequest) {
        AuthResponseDTO authResponseDTO = this._authenticationService.internalLogin(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
        if(authResponseDTO == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(authResponseDTO);
    }


    @PostMapping("/google-login")
    public ResponseEntity<AuthResponseDTO> googleLogin(@RequestBody String googleToken) throws VerificationException{
        return ResponseEntity.ok(this._authenticationService.googleLogin(googleToken));
    }
}
