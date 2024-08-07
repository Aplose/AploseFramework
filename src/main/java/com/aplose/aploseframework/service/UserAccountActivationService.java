package com.aplose.aploseframework.service;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aplose.aploseframework.model.UserAccount;


@Service
public class UserAccountActivationService {

    @Value("${spring.user-account.second-to-activate-account}")
    private Long secondToActivateAccount;

    @Autowired
    private UserAccountService _userAccountService;
    @Autowired
    private EmailService _emailService;


    public void reSendActivationCode(UserAccount userAccount){
        this.setAndSendActivationCode(userAccount);
        this._userAccountService.save(userAccount);
    }
    


    public void setAndSendActivationCode(UserAccount userAccount){
        DecimalFormat format = new DecimalFormat("0000");
        userAccount.setActivationCode(format.format(new SecureRandom().nextInt(9999)));
        userAccount.setActivationCodeInstant(Instant.now());
        _emailService.sendRegistrationSuccessfullMessage(
            userAccount.getLocale(),
            userAccount.getActivationCode(),
            userAccount.getUsername()
        );
    }



    public Boolean activationCodeIsExpired(UserAccount userAccount){
        return userAccount.getActivationCodeInstant().plusSeconds(secondToActivateAccount).isBefore(Instant.now()) ?
            true 
            : 
            false;
    }



    public void activateAccount(UserAccount userAccount) {
        userAccount.setEnabled(Boolean.TRUE);
        userAccount.setActivationCode(null);
        userAccount.setActivationCodeInstant(null);
        _userAccountService.save(userAccount);
    }
}
