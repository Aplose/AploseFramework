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
    private String username; // email address ti copy in email field

    @NotNull(message = "Password cannot be null")
    private String password; 

    @NotNull(message = "Password repeat cannot be null")
    private String passwordRepeat;

    private Boolean isProfessional = Boolean.FALSE; // if TRUE -> thirdparty type = prospect/customer
    //if false -> thirdparty type = particulier
    private Long personId;
    
    //ajouter un champ pour les pros : CompanyName -> thirdparty->name.

    @NotNull(message = "First name cannot be null")
    private String personFirstName; // contact and user firstname 

    @NotNull(message = "Last name cannot be null")
    private String personLastName; // contact and user 

    private String personPhone; //contact and user

    @NotNull(message = "You must provide your civility")
    private Long personCivilityId;
    
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

    public Long getPersonAddressCountryId(){ 
        return this.personAddressCountryId; 
    }
    public void setPersonAddressCountryId(Long personAddressCountryId){ 
        this.personAddressCountryId = personAddressCountryId; 
    }

    public Long getPersonCivilityId() { 
        return this.personCivilityId; 
    }

    public void setPersonCivilityId( Long personCivilityId ) { 
        this.personCivilityId = personCivilityId; 
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

        
}