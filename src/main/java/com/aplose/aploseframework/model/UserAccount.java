/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author oandrade
 */
@Entity
public class UserAccount implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    private LocalDate creationDate=LocalDate.now();
    private LocalDateTime updateDateTime=LocalDateTime.now();
    private LocalDate expirationDate;
    private Boolean enabled=Boolean.FALSE;
    private Boolean locked=Boolean.FALSE;    
    private Locale locale;
    private String companyName;
    private String activationCode;
    private Instant activationCodeInstant;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Permission> permissions = new ArrayList<>();
    
    @JsonIgnore
    private Long dolibarrThirdPartyId;
    @JsonIgnore
    private Long dolibarrContactId;
    @JsonIgnore
    private Long dolibarrUserId;


    @JsonIgnore
    private Boolean stripeApplicationIsAuthorized; // L'application (SerenityDate) est autorisée par le propriétaire du compte connecté
    @JsonIgnore
    private Boolean stripeExternalAccountIsCreated; // Un compte bancaire à été renseigner à Stripe
    @JsonIgnore
    private Boolean stripCardPaymentIsEnabled; // Les payments par carte son possibles (nécéssaire pour pouvoir faire des transfers)
    @JsonIgnore
    private Boolean stripeTransferIsEnabled; // Les viremments sont possibles 
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.concat(roles.stream(),permissions.stream()).toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Boolean isProfessionnalAccount(){
        return this.roles.stream().anyMatch(role -> role.getAuthority().equals(RoleEnum.ROLE_PROFESSIONAL.toString()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Instant getActivationCodeInstant() {
        return activationCodeInstant;
    }

    public void setActivationCodeInstant(Instant activationCodeInstant) {
        this.activationCodeInstant = activationCodeInstant;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Long getDolibarrThirdPartyId() {
        return dolibarrThirdPartyId;
    }

    public void setDolibarrThirdPartyId(Long dolibarrThirdPartyId) {
        this.dolibarrThirdPartyId = dolibarrThirdPartyId;
    }

    public Long getDolibarrContactId() {
        return dolibarrContactId;
    }

    public void setDolibarrContactId(Long dolibarrContactId) {
        this.dolibarrContactId = dolibarrContactId;
    }

    public Long getDolibarrUserId() {
        return dolibarrUserId;
    }

    public void setDolibarrUserId(Long dolibarrUserId) {
        this.dolibarrUserId = dolibarrUserId;
    }

    public Boolean getStripeApplicationIsAuthorized() {
        return stripeApplicationIsAuthorized;
    }

    public void setStripeApplicationIsAuthorized(Boolean stripeApplicationIsAuthorized) {
        this.stripeApplicationIsAuthorized = stripeApplicationIsAuthorized;
    }

    public Boolean getStripeExternalAccountIsCreated() {
        return stripeExternalAccountIsCreated;
    }

    public void setStripeExternalAccountIsCreated(Boolean stripeExternalAccountIsCreated) {
        this.stripeExternalAccountIsCreated = stripeExternalAccountIsCreated;
    }

    public Boolean getStripCardPaymentIsEnabled() {
        return stripCardPaymentIsEnabled;
    }

    public void setStripCardPaymentIsEnabled(Boolean stripCardPaymentIsEnabled) {
        this.stripCardPaymentIsEnabled = stripCardPaymentIsEnabled;
    }

    public Boolean getStripeTransferIsEnabled() {
        return stripeTransferIsEnabled;
    }

    public void setStripeTransferIsEnabled(Boolean stripeTransferIsEnabled) {
        this.stripeTransferIsEnabled = stripeTransferIsEnabled;
    }

    
}
