package fr.aplose.aploseframework.service.stripe;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.AccountLink;
import com.stripe.param.AccountCreateParams;
import com.stripe.param.AccountLinkCreateParams;

import fr.aplose.aploseframework.model.Person;
import fr.aplose.aploseframework.model.UserAccount;
import fr.aplose.aploseframework.model.dolibarr.ThirdParty;
import fr.aplose.aploseframework.service.ConfigService;
import fr.aplose.aploseframework.service.DolibarrService;
import fr.aplose.aploseframework.service.EmailService;

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
        this._emailService.sendMessage(
            email, 
            "Validation de votre compte professionnel", 
            """
            Bonjour,

            Nous vous remercions d'avoir créé votre compte professionnel sur notre plateforme.

            Afin de finaliser votre inscription et permettre le traitement de vos paiements, nous vous invitons à compléter 
            vos informations bancaires en cliquant sur le lien ci-dessous :

            %s

            Ce lien est valable pour une durée limitée. Si vous rencontrez des difficultés lors de la validation, 
            n'hésitez pas à nous contacter.

            Cordialement,
            L'équipe Aplose
            """.formatted(accountLink)
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
        for (String requirement : account.getRequirements().getEventuallyDue()) {
            System.out.println("Requirement eventualy due: " + requirement);
            // Collecter les informations supplémentaires requises par Stripe
        }
        if(account.getRequirements().getCurrentlyDue().size() == 0){
            System.err.println("NO REQUIREMENT");
        }
    }
}
