package com.aplose.aploseframework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.ArrayList;

import com.aplose.aploseframework.model.UserAccount;
import com.aplose.aploseframework.enums.AuthenticationTypeEnum;
import com.aplose.aploseframework.exception.RegistrationException;
import com.aplose.aploseframework.model.Person;
import com.aplose.aploseframework.model.Role;
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

    private ConfigService _configService;
    private DolibarrService _dolibarrService;
    private UserAccountService _userAccountService;
    private PasswordEncoder _passwordEncoder;
    private PersonService _personService;
    private RoleService _roleService;
    private EmailService _emailService;
    private StripeAccountService _stripeAccountService;
    private StripeCustomerService _stripeCustomerService;

    RegisterService(
        ConfigService configService,
        DolibarrService dolibarrService,
        UserAccountService userAccountService,
        PasswordEncoder passwordEncoder,
        PersonService personService,
        RoleService roleService,
        EmailService emailService,
        StripeAccountService stripeAccountService,
        StripeCustomerService stripeCustomerService
    ){
        this._configService = configService;
        this._dolibarrService = dolibarrService;
        this._userAccountService = userAccountService;
        this._passwordEncoder = passwordEncoder;
        this._personService = personService;
        this._roleService = roleService;
        this._emailService = emailService;
        this._stripeAccountService = stripeAccountService;
        this._stripeCustomerService = stripeCustomerService;
    }

    
    public Person register(AuthenticationTypeEnum authenticationType, Person person, Boolean isProfessionnalAccount){

        person.getUserAccount().setPassword(_passwordEncoder.encode(person.getUserAccount().getPassword()));

        if(isProfessionnalAccount){
            List<Role> roles = new ArrayList<>();
            roles.add(this._roleService.getByAuthority(RoleEnum.ROLE_PROFESSIONAL.toString()));
            person.getUserAccount().setRoles(roles);
        }

        if(authenticationType == AuthenticationTypeEnum.INTERNAL){
            this.setAndSendActivationCode(person.getUserAccount());
        }

        this.retrieveAndSetCivility(person);
        this.retrieveAndSetCountry(person);

        person.getUserAccount().setLocale(new Locale(person.getAddress().getCountry().getCode()));

        person.setUserAccount(this._userAccountService.save(person.getUserAccount()));

        person = this._personService.save(person);

        if(authenticationType == AuthenticationTypeEnum.GOOGLE){
            this.activateAccount(person.getUserAccount());
        }

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
        return userAccount.getActivationCodeInstant().plusSeconds(this._configService.getIntegerConfig("spring.user-account.second-to-activate-account")).isBefore(Instant.now()) ?
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
