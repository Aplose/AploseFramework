/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author oandrade
 */
@Entity
public class Role implements GrantedAuthority{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    private String authority;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<UserAccount> accounts;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Permission> permissions = new ArrayList<>();
    
    public Role(){}

    public Role(String role) {
        authority = "ROLE_"+role;
    }
    

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<UserAccount> accounts) {
        this.accounts = accounts;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
    
    
}
