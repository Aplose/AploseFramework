package com.aplose.aploseframework.service.stripe;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplose.aploseframework.model.Person;
import com.aplose.aploseframework.model.UserAccount;
import com.aplose.aploseframework.model.dolibarr.ThirdParty;
import com.aplose.aploseframework.service.ConfigService;
import com.aplose.aploseframework.service.DolibarrService;
import com.aplose.aploseframework.service.EmailService;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.AccountLink;
import com.stripe.param.AccountCreateParams;
import com.stripe.param.AccountLinkCreateParams;

@Service
public class StripeAccountService {

//    private String _clientUrl;

    @Autowired
    private DolibarrService _dolibarrService;
    @Autowired
    private EmailService _emailService;
    @Autowired
    private ConfigService _configService;



    public Account createLinkedAccount(Person person){

        Account account;
        ThirdParty thirdParty = (ThirdParty) this._dolibarrService.getById(ThirdParty.NAME, person.getUserAccount().getDolibarrThirdPartyId());
        
        try{
            account = Account.create(
                AccountCreateParams.builder()
                // .setAccountToken(token.getId())
                .setCapabilities(
                    AccountCreateParams.Capabilities.builder()
                    .setTransfers(
                        AccountCreateParams.Capabilities.Transfers.builder()
                            .setRequested(true)
                            .build()
                    )
                    .setCardPayments(
                        AccountCreateParams.Capabilities.CardPayments.builder()
                            .setRequested(true)
                            .build()
                    )
                    .build())
                // .setExternalAccount(new Object())
                .setType(AccountCreateParams.Type.EXPRESS)
                .setMetadata(new HashMap<String, String>(){{ 
                    put("userAccountId", person.getUserAccount().getId().toString()); 
                }})
                .setIndividual(AccountCreateParams.Individual.builder()
                    .setFirstName(person.getFirstName())
                    .setLastName(person.getLastName())
                    .build())
                .setBusinessType(AccountCreateParams.BusinessType.INDIVIDUAL)
                .setEmail(person.getUserAccount().getUsername())
                .build()
            );

            //NOTE si un bug de parsing de la réponse de Dolibarr se produit ici, il peux s'agir des attributs supplémentaire de ThirdParty, on-t-ils étaient 
            // ajoutés dans les réglages du module Dolibarr 'ThirdParty' (ou 'Tiers') ?
            this._dolibarrService.update(ThirdParty.NAME, thirdParty.getId(), thirdParty);
        }
        catch(StripeException e){
            System.err.println( "\n\n\tStripeException - StripeController.createLinkedAccount(): " + e.getMessage());
            account = null;
        }

        thirdParty.setStripeAccountId(account.getId());
        this._dolibarrService.update(ThirdParty.NAME, thirdParty.getId(), thirdParty);

        this.sendAccountLinkByMail(thirdParty.getEmail(), this.getAccountLink(account.getId()).getUrl());
        
        return account;
    }
    


    public Account deleteLinkedAccount(String accountId) throws StripeException{
        return this.getById(accountId).delete();
    }



    public Account getById(String accountId) throws StripeException{
        return Account.retrieve(accountId);
    }



    public AccountLink updateLinkedAccount(String accountId) throws StripeException{
        return this.getAccountLink(accountId);
    }


    // Créer une session de compte (accès onboarding du pro et autre)
    public AccountLink getAccountLink(String accountId){

        try{
            AccountLinkCreateParams params = AccountLinkCreateParams.builder()
                .setAccount(accountId)
                .setRefreshUrl(this._configService.getStringConfig("app.root.url") + "/refresh-account-link")
                .setReturnUrl(this._configService.getStringConfig("app.root.url"))
                .setType(AccountLinkCreateParams.Type.ACCOUNT_ONBOARDING)
                .build();
    
            AccountLink accountLink = AccountLink.create(params);
            return accountLink;
        }
        catch(StripeException e){
            System.err.println( "\n\n\tStripeException - StripeController.getAccountLink(): " + e.getMessage());
            return null;
        }
    }


    public void sendAccountLinkByMail(String email, String accountLink){
        //TODO faire un mail propre 
        this._emailService.sendMessage(
            email, 
            "Professional account validation", 
            """
            Votre compte pro

            Merci de renseigner vos informations bancaires afin que nous puissions vous envoyer vos payements.
            Pour ce faire cliquez sur ce lien: 
            
            """
            +
            accountLink
        );
    }


    public void resendAccountLinkByMail(UserAccount userAccount){
        this.sendAccountLinkByMail(
            userAccount.getUsername(),
            this.getAccountLink(
                ((ThirdParty) this._dolibarrService.getById(ThirdParty.NAME, userAccount.getDolibarrThirdPartyId())).getStripeAccountId()
            ).getUrl()
        );
    }


       // Voir si un compte à besoin d'informations supplementaires
       public void checkAccountInformations(String StripeAccountId) throws StripeException{
        Account account = this.getById(StripeAccountId);

        System.err.println("DUE INFORMATIONS:");
        for (String requirement : account.getRequirements().getCurrentlyDue()) {
            System.out.println("Requirement currently due: " + requirement);
            // Collecter les informations supplémentaires requises par Stripe
        }
        for (String requirement : account.getRequirements().getCurrentlyDue()) {
            System.out.println("Requirement eventualy due: " + requirement);
            // Collecter les informations supplémentaires requises par Stripe
        }
        if(account.getRequirements().getCurrentlyDue().size() == 0){
            System.err.println("NO REQUIREMENT");
        }
    }
}
