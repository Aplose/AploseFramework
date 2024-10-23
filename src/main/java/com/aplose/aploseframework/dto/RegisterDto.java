package com.aplose.aploseframework.dto;

import com.aplose.aploseframework.enums.AuthenticationTypeEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * Used for registration and profile
 * @author oandrade
 */
public class RegisterDto {

    private Long id;

    @Email(message = "Email is invalid")
    private String userAccountUsername; // email address ti copy in email field

    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!?@#$%^&*+-]).{8,}$",
        message = "Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, and one special character (!?@#$%^&*+-)"
    )
    @NotNull(message = "Password cannot be null")
    private String userAccountPassword; 

    @NotNull(message = "Password repeat cannot be null")
    private String passwordRepeat;

    private Boolean isProfessional = Boolean.FALSE; // if TRUE -> thirdparty type = prospect/customer
    //if false -> thirdparty type = particulier
    private Long personId;
    
    //ajouter un champ pour les pros : CompanyName -> thirdparty->name.
    private String userAccountCompanyName;

    @NotNull(message = "First-name cannot be null")
    private String firstName; // contact and user firstname 

    @NotNull(message = "Last-name cannot be null")
    private String lastName; // contact and user 

    private String phone; //contact and user

    @NotNull(message = "You must provide your civility")
    private Long civilityRowid;
    
    private Long addressId;

    @NotNull(message = "You must provide your country")
    private String addressCountryCode;

    @NotNull(message = "You must provide an AuthenticationTypeEnum")
    private AuthenticationTypeEnum authenticationType;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserAccountUsername() {
        return userAccountUsername;
    }

    public void setUserAccountUsername(String userAccountUsername) {
        this.userAccountUsername = userAccountUsername;
    }

    public String getUserAccountPassword() {
        return userAccountPassword;
    }

    public void setUserAccountPassword(String userAccountPassword) {
        this.userAccountPassword = userAccountPassword;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public Boolean getIsProfessional() {
        return isProfessional;
    }

    public void setIsProfessional(Boolean isProfessional) {
        this.isProfessional = isProfessional;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getUserAccountCompanyName() {
        return userAccountCompanyName;
    }

    public void setUserAccountCompanyName(String userAccountCompanyName) {
        this.userAccountCompanyName = userAccountCompanyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getCivilityRowid() {
        return civilityRowid;
    }

    public void setCivilityRowid(Long civilityRowid) {
        this.civilityRowid = civilityRowid;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddressCountryCode() {
        return addressCountryCode;
    }

    public void setAddressCountryCode(String addressCountryCode) {
        this.addressCountryCode = addressCountryCode;
    }

    public AuthenticationTypeEnum getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(AuthenticationTypeEnum authenticationType) {
        this.authenticationType = authenticationType;
    }

        
}