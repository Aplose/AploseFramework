package com.aplose.aploseframework.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplose.aploseframework.model.UserAccount;
import com.aplose.aploseframework.service.UserAccountService;

@RestController
@CrossOrigin
@RequestMapping("/api/user-account")
public class UserAccountController {

    @Autowired
    private UserAccountService _userAccountService;

    
    /**
     * Supprimer le compte d'un utilisateur
     */
    @DeleteMapping()
    public void deleteUserAccount(@AuthenticationPrincipal UserAccount userAccount){
        this._userAccountService.delete(userAccount);
    }
}
