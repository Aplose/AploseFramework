package com.aplose.aploseframework.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

/**
 * Used for registration and profile
 * @author oandrade
 */
public class RegisterDto {

    private Long id;

    @Email(message = "Email is invalid")
    private String personUserAccountUsername; // email address ti copy in email field

    @NotNull(message = "Password cannot be null")
    private String personUserAccountPassword; 

    @NotNull(message = "Password repeat cannot be null")
    private String passwordRepeat;

    private Boolean isProfessional = Boolean.FALSE; // if TRUE -> thirdparty type = prospect/customer
    //if false -> thirdparty type = particulier
    private Long personId;
    
    //ajouter un champ pour les pros : CompanyName -> thirdparty->name.
    private String companyName;

    @NotNull(message = "First-name cannot be null")
    private String personFirstName; // contact and user firstname 

    @NotNull(message = "Last-name cannot be null")
    private String personLastName; // contact and user 

    private String personPhone; //contact and user

    @NotNull(message = "You must provide your civility")
    private Long personCivilityRowid;
    
    private Long personAddressId;

    @NotNull(message = "You must provide your country")
    private Long personAddressCountryId;


    public Long getId(){ 
        return this.id;
    }

    public void setId(Long id){ 
        this.id = id; 
    }

    public Long getPersonId(){ 
        return this.personId; 
    }

    public void setPersonId(Long personId){ 
        this.personId = personId; 
    }

    public Long getPersonAddressId(){ 
        return this.personAddressId; 
    }

    public void setPersonAddressId(Long personAddressId){ 
        this.personAddressId = personAddressId; 
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getPersonAddressCountryId(){ 
        return this.personAddressCountryId; 
    }
    public void setPersonAddressCountryId(Long personAddressCountryId){ 
        this.personAddressCountryId = personAddressCountryId; 
    }

    public Long getPersonCivilityRowid() { 
        return this.personCivilityRowid; 
    }

    public void setPersonCivilityRowid( Long personCivilityRowid ) { 
        this.personCivilityRowid = personCivilityRowid; 
    }



    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
    
    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public Boolean getIsProfessional() {
        return isProfessional;
    }

    public void setIsProfessional(Boolean isProfessional) {
        this.isProfessional = isProfessional;
    }

    public String getPersonUserAccountUsername() {
        return personUserAccountUsername;
    }

    public void setPersonUserAccountUsername(String personUserAccountUsername) {
        this.personUserAccountUsername = personUserAccountUsername;
    }

    public String getPersonUserAccountPassword() {
        return personUserAccountPassword;
    }

    public void setPersonUserAccountPassword(String personUserAccountPassword) {
        this.personUserAccountPassword = personUserAccountPassword;
    }

        
}