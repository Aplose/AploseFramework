/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aplose.aploseframework.ZDEVELOP.developHelper;
import com.aplose.aploseframework.dto.AuthResponseDTO;
import com.aplose.aploseframework.enums.TokenCategoryEnum;
import com.aplose.aploseframework.exception.RegistrationException;
import com.aplose.aploseframework.model.Person;
import com.aplose.aploseframework.model.RoleEnum;
import com.aplose.aploseframework.model.UserAccount;
import com.aplose.aploseframework.model.dictionnary.Civility;
import com.aplose.aploseframework.model.security.Token;
import com.aplose.aploseframework.utils.jwt.JwtTokenUtil;

import io.jsonwebtoken.Claims;


/**
 *
 * @author oandrade
 */
@Service
public class AuthenticationService {

    @Autowired
    private DolibarrService _dolibarrService;
    @Autowired
    private UserAccountService _userAccountService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PersonService _personService;
    @Autowired
    private JwtTokenUtil _jwtTokenUtil;
    @Autowired
    private RoleService _roleService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserAccountActivationService _accountActivationService;



        public Person register(Person person, Boolean isProfessionnalAccount){

        person.getUserAccount().setPassword(passwordEncoder.encode(person.getUserAccount().getPassword()));

        if(isProfessionnalAccount){
            person.getUserAccount().setRoles(List.of(this._roleService.getByAuthority(RoleEnum.ROLE_PROFESSIONAL.toString())));
        }

        this._accountActivationService.setAndSendActivationCode(person.getUserAccount());
        

        person.setUserAccount(this._userAccountService.save(person.getUserAccount()));

        Map<String, String> map = new HashMap<String, String>();
        map.put("sqlfilters", "(t.rowid:like:'" + person.getCivility().getRowid() + "')");

        Civility civility = (Civility) this._dolibarrService.getDictionnary("civilities", map)[0];

        if(civility.getRowid() != person.getCivility().getRowid()){
            this._userAccountService.delete(person.getUserAccount());
            throw new RegistrationException("La civilité que vous avez renseigné n'éxiste pas ou n'est pas correcte.");
        }

        person.setCivility( civility );

        person.getUserAccount().setDolibarrUserId( 
            this._dolibarrService.createUser(person) 
        );
        person = this._personService.save(person);

        return person;
    }

    
    public String dolibarrLogin(String userName, String password) {
        //TODO choisir le mode d'authent
        //Dans Dolibarr on ne se préoccupe pas du password
        // retieve du user dolibbar via l'api pour récupérer sa token
        // login et password sont vérifiés par SpringSecurity.
        return this._dolibarrService.login(userName, password);
    }


    public AuthResponseDTO internalLogin(String username, String password){
     
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken( username, password )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserAccount userDetails = userDetailsService.loadUserByUsername(username);
        final String token = this._jwtTokenUtil.generateToken(userDetails);

        return new AuthResponseDTO(
            new Token(
                token, 
                TokenCategoryEnum.INTERNAL, 
                this._jwtTokenUtil.extractClaim(token, Claims::getExpiration)
            ),
            userDetails
        );
    }
}
