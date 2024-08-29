package com.aplose.aploseframework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.time.Instant;


import com.aplose.aploseframework.model.UserAccount;
import com.aplose.aploseframework.exception.RegistrationException;
import com.aplose.aploseframework.model.Person;
import com.aplose.aploseframework.model.RoleEnum;
import com.aplose.aploseframework.model.dictionnary.Civility;
import com.aplose.aploseframework.model.dictionnary.Country;
import com.aplose.aploseframework.model.dolibarr.Contact;
import com.aplose.aploseframework.model.dolibarr.ThirdParty;
import com.aplose.aploseframework.service.stripe.StripeAccountService;
import com.aplose.aploseframework.service.stripe.StripeCustomerService;
import com.aplose.aploseframework.tool.dolibarr.DolibarrTool;


@Service
public class RegisterService {

    @Value("${spring.user-account.second-to-activate-account}")
    private Long secondToActivateAccount;

    @Autowired
    private DolibarrService _dolibarrService;
    @Autowired
    private UserAccountService _userAccountService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PersonService _personService;
    @Autowired
    private RoleService _roleService;
    @Autowired
    private EmailService _emailService;
    @Autowired
    private StripeAccountService _stripeAccountService;
    @Autowired
    private StripeCustomerService _stripeCustomerService;


    
    public Person register(Person person, Boolean isProfessionnalAccount){

        person.getUserAccount().setPassword(passwordEncoder.encode(person.getUserAccount().getPassword()));

        if(isProfessionnalAccount){
            person.getUserAccount().setRoles(List.of(this._roleService.getByAuthority(RoleEnum.ROLE_PROFESSIONAL.toString())));
        }

        this.setAndSendActivationCode(person.getUserAccount());

        this.retrieveAndSetCivility(person);
        this.retrieveAndSetCountry(person);

        person.getUserAccount().setLocale(java.util.Locale.getDefault());  //TODO mettre une Locale dynamiquement
        
        person.setUserAccount(this._userAccountService.save(person.getUserAccount()));

        person = this._personService.save(person);

        return person;
    }



    public void retrieveAndSetCivility(Person person){
        Map<String, String> civilityFilter = new HashMap<String, String>();
        civilityFilter.put("sqlfilters", "(t.rowid:like:'" + person.getCivility().getRowid() + "')");

        Civility civility = (Civility) this._dolibarrService.getDictionnary("civilities", civilityFilter)[0];

        if(civility == null || civility.getRowid() != person.getCivility().getRowid()){
            this._userAccountService.delete(person.getUserAccount());
            throw new RegistrationException("This Civility does not exist.");
        }

        person.setCivility( civility );
    }



    public Person retrieveAndSetCountry(Person person){

        Map<String, String> countryFilter = new HashMap<String, String>();
        countryFilter.put("sqlfilters", "(t.code:like:'" + person.getAddress().getCountry().getCode() + "')");

        Country country = (Country) this._dolibarrService.getDictionnary("countries", countryFilter)[0];

        if(country == null ||  ! country.getCode().equals(person.getAddress().getCountry().getCode())){
            this._userAccountService.delete(person.getUserAccount());
            throw new RegistrationException("This Country does not exist.");
        }

        person.getAddress().setCountry(country);

        return person;
    }



    public void reSendActivationCode(UserAccount userAccount){
        this.setAndSendActivationCode(userAccount);
        this._userAccountService.save(userAccount);
    }
    


    public void setAndSendActivationCode(UserAccount userAccount){
        System.err.println("\n\tSEND MAIL\n\n");
        DecimalFormat format = new DecimalFormat("0000");
        userAccount.setActivationCode(format.format(new SecureRandom().nextInt(9999)));
        userAccount.setActivationCodeInstant(Instant.now());
        this._emailService.sendRegistrationSuccessfullMessage(
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
        
        userAccount.setActivationCode(null);
        userAccount.setActivationCodeInstant(null);
        
        Person person = this._personService.getByUserAccount(userAccount);
        
        // créé le ThirdParty, le Contact et le User
        this.createAndSetDolibarrAccounts(person);

        this._userAccountService.save(userAccount);
        this._personService.save(person);

        if(person.getUserAccount().isProfessionnalAccount()){
            // créer le compte connecté Stripe
            this._stripeAccountService.createLinkedAccount(person);

            // créer le compte Customer stripe
            this._stripeCustomerService.createCustomer(person).getId();
        }
        
        userAccount.setEnabled(Boolean.TRUE);

        this._userAccountService.save(userAccount);
        this._personService.save(person);
    }



    public void createAndSetDolibarrAccounts(Person person){   

        person.getUserAccount().setDolibarrThirdPartyId(
            this._dolibarrService.createDolibarrObject(new ThirdParty(person))
        );

        person.getUserAccount().setDolibarrContactId(
            this._dolibarrService.createDolibarrObject(new Contact(person))
        );

        person.getUserAccount().setDolibarrUserId(
            this._dolibarrService.createExternalUser(
                person.getUserAccount().getDolibarrContactId(),
                DolibarrTool.createDolibarrLogin(person),
                person.getUserAccount().getPassword()
            )
        );
    }


}
