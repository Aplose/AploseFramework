package fr.aplose.aploseframework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.aplose.aploseframework.enums.AuthenticationTypeEnum;
import fr.aplose.aploseframework.exception.RegistrationException;
import fr.aplose.aploseframework.model.Person;
import fr.aplose.aploseframework.model.Role;
import fr.aplose.aploseframework.model.RoleEnum;
import fr.aplose.aploseframework.model.UserAccount;
import fr.aplose.aploseframework.model.dictionnary.Civility;
import fr.aplose.aploseframework.model.dictionnary.Country;
import fr.aplose.aploseframework.model.dolibarr.Contact;
import fr.aplose.aploseframework.model.dolibarr.ThirdParty;
import fr.aplose.aploseframework.service.stripe.StripeAccountService;
import fr.aplose.aploseframework.service.stripe.StripeCustomerService;
import fr.aplose.aploseframework.tool.dolibarr.DolibarrTool;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;


@Service
public class RegisterService {

    @Autowired
    private ConfigService _configService;
    @Autowired
    private DolibarrService _dolibarrService;
    @Autowired
    private UserAccountService _userAccountService;
    @Autowired
    private PasswordEncoder _passwordEncoder;
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


    
    public Person register(AuthenticationTypeEnum authenticationType, Person person, Boolean isProfessionnalAccount){

        person.getUserAccount().setPassword(_passwordEncoder.encode(person.getUserAccount().getPassword()));

        if(isProfessionnalAccount){
            List<Role> roles = new ArrayList<>();
            roles.add(this._roleService.getByAuthority(RoleEnum.ROLE_PROFESSIONAL.toString()));
            person.getUserAccount().setRoles(roles);
        }

        if(authenticationType == AuthenticationTypeEnum.INTERNAL){
            this.setActivationCode(person.getUserAccount());
        }

        this.retrieveAndSetCivility(person);
        this.retrieveAndSetCountry(person);

        person.getUserAccount().setLocale(new Locale(person.getAddress().getCountry().getCode()));

        person.setUserAccount(this._userAccountService.create(person.getUserAccount()));

        person = this._personService.save(person);

        if(authenticationType == AuthenticationTypeEnum.INTERNAL){
            this._emailService.sendRegistrationSuccessfullMessage(
                person.getUserAccount().getLocale(),
                person.getUserAccount().getActivationCode(),
                person.getUserAccount().getUsername()
            );
        }

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
        this.setActivationCode(userAccount);
        this._emailService.sendRegistrationSuccessfullMessage(
            userAccount.getLocale(),
            userAccount.getActivationCode(),
            userAccount.getUsername());
        this._userAccountService.update(userAccount);
    }
    


    public void setActivationCode(UserAccount userAccount){
//        System.err.println("\n\tSEND MAIL\n\n");
        DecimalFormat format = new DecimalFormat("0000");
        userAccount.setActivationCode(format.format(new SecureRandom().nextInt(9999)));
        userAccount.setActivationCodeInstant(Instant.now());
    }



    public Boolean activationCodeIsExpired(UserAccount userAccount){
        return userAccount.getActivationCodeInstant().plusSeconds(this._configService.getLongConfig("spring.user-account.second-to-activate-account")).isBefore(Instant.now()) ?
            true 
            : 
            false;
    }



    public void activateAccount(UserAccount userAccount) {
        
        userAccount.setActivationCode(null);
        userAccount.setActivationCodeInstant(null);
        userAccount.setEnabled(Boolean.TRUE);
        userAccount.setLocked(Boolean.FALSE);
        userAccount.setUpdateDateTime(LocalDateTime.now());
        
        Person person = this._personService.getByUserAccount(userAccount);
        
        // créé le ThirdParty, le Contact et le User
        this.createAndSetDolibarrAccounts(person);

        this._userAccountService.update(userAccount);
        this._personService.save(person);

        if(this._configService.getStringConfig("stripe.api.key").length() > 1 && person.getUserAccount().isProfessionnalAccount()){
            // créer le compte connecté Stripe
            this._stripeAccountService.createLinkedAccount(person);

            // créer le compte Customer stripe
            this._stripeCustomerService.createCustomer(person).getId();
        }
        
        userAccount.setEnabled(Boolean.TRUE);

        this._userAccountService.update(userAccount);
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
