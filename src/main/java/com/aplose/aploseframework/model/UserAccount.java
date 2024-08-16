/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import java.util.Date;
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
    private String username;
    @JsonIgnore
    private String password;
    private LocalDate creationDate=LocalDate.now();
    private LocalDateTime updateDateTime=LocalDateTime.now();
    private LocalDate expirationDate;
    private Boolean enabled=Boolean.FALSE;
    private Boolean locked=Boolean.FALSE;    
    private Locale locale;
    private String activationCode;
    private Instant activationCodeInstant;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Permission> permissions = new ArrayList<>();
    @JsonIgnore
    private String dolibarrUserId;
    @JsonIgnore
    private String stripeLinkedAccountId;
    @JsonIgnore
    private Date stripeLinkedAccountCreatedAt;
    @JsonIgnore
    private Boolean stripeLinkedAccountIsVerified = false;
    @JsonIgnore
    private Boolean stripeLinkedAccountVerificationRequireInput = true;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
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

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Instant getActivationCodeInstant(){
        return this.activationCodeInstant;
    }

    public void setActivationCodeInstant(Instant activationCodeInstant){
        this.activationCodeInstant = activationCodeInstant;
    }

    public String getDolibarrUserId() {
        return dolibarrUserId;
    }

    public void setDolibarrUserId(String dolibarrUserId) {
        this.dolibarrUserId = dolibarrUserId;
    }

    public String getStripeLinkedAccountId() {
        return stripeLinkedAccountId;
    }

    public void setStripeLinkedAccountId(String stripeLinkedAccountId) {
        this.stripeLinkedAccountId = stripeLinkedAccountId;
    }

    public Date getStripeLinkedAccountCreatedAt() {
        return stripeLinkedAccountCreatedAt;
    }

    public void setStripeLinkedAccountCreatedAt(Date stripeLinkedAccountCreatedAt) {
        this.stripeLinkedAccountCreatedAt = stripeLinkedAccountCreatedAt;
    }

    public Boolean getStripeLinkedAccountIsVerified() {
        return stripeLinkedAccountIsVerified;
    }

    public void setStripeLinkedAccountIsVerified(Boolean stripeLinkedAccountIsVerified) {
        this.stripeLinkedAccountIsVerified = stripeLinkedAccountIsVerified;
    }

    public Boolean getStripeLinkedAccountVerificationRequireInput() {
        return stripeLinkedAccountVerificationRequireInput;
    }

    public void setStripeLinkedAccountVerificationRequireInput(Boolean stripeLinkedAccountVerificationRequireInput) {
        this.stripeLinkedAccountVerificationRequireInput = stripeLinkedAccountVerificationRequireInput;
    }
}
