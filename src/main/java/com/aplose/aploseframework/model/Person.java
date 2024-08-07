/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.model;


import com.aplose.aploseframework.model.dictionnary.Civility;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

/**
 *
 * @author oandrade
 */
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    Address address;
    @Embedded
    private Civility civility;
    @OneToOne(fetch = FetchType.EAGER)
    private UserAccount userAccount;
    private String firstName;
    private String lastName;
    private String fullName;
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Civility getCivility() {
        return civility;
    }

    public void setCivility(Civility civility) {
        this.civility = civility;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    
    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(this.fullName == null){
            this.fullName = firstName;
        }
        else if(this.fullName == this.firstName + ' ' + this.lastName){
            this.fullName = firstName + ' ' + this.lastName;
        }
        else{
            this.fullName = firstName + ' ' + this.fullName;
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(this.fullName == null){
            this.fullName = lastName;
        }
        else if(this.fullName == this.firstName + ' ' + this.lastName){
            this.fullName = this.firstName + ' ' + lastName ;
        }
        else{
            this.fullName = this.fullName + ' ' + lastName;
        }
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getFullName(){
        return this.fullName;
    }

    public void setFullName(){
        this.fullName = this.firstName + ' ' + this.lastName;
    }
}
