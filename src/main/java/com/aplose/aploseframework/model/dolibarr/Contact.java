/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.model.dolibarr;

import com.aplose.aploseframework.constantes.dolibarr.DolibarrStatus;
import com.aplose.aploseframework.model.Person;

/**
 *
 * @author oandrade
 */
public class Contact extends DolibarrObject {

    private Long civility_id;
    private String civility_code;
    private String civility;
    private String firstname;
    private String lastname;
    private String email;
    private String phone_pro;
    private String address;
    private String zip;
    private String town;
    private Long country_id;
    private String country_code;
    private Long socid;  // ThirdParty id
    private Integer admin = 0;  // 0 = false; 1 = true


    public Contact(){
        endPoint="/contacts";
    }
    

    public Contact(Person person){
        this();
        this.setCivility_code(person.getCivility().getCode());
        this.setFirstname(person.getFirstName());
        this.setLastname(person.getLastName());
        this.setEmail(person.getUserAccount().getUsername());
        this.setPhone_pro(person.getPhone());
        //TODO voir pour row3, row4 ...
        this.setAddress(person.getAddress() != null ? person.getAddress().getRow2() : "");
        this.setZip(person.getAddress().getTown() != null ? person.getAddress().getTown().getZipCode() : "");
        this.setTown(person.getAddress().getTown() != null ? person.getAddress().getTown().getName() : "");
        this.setCountry_id(person.getAddress().getCountry().getId());
        this.setSocid(person.getUserAccount().getDolibarrThirdPartyId());
        this.setStatus(DolibarrStatus.ACTIF);
        this.setCountry_code(person.getAddress().getCountry().getCode());
    }


    public String getFirstname() {
        return firstname;
    }


    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getLastname() {
        return lastname;
    }


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone_pro() {
        return phone_pro;
    }


    public void setPhone_pro(String phone_pro) {
        this.phone_pro = phone_pro;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getZip() {
        return zip;
    }


    public void setZip(String zip) {
        this.zip = zip;
    }


    public String getTown() {
        return town;
    }


    public void setTown(String town) {
        this.town = town;
    }


    public Long getCountry_id() {
        return country_id;
    }


    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }


    public Long getSocid() {
        return socid;
    }


    public void setSocid(Long socid) {
        this.socid = socid;
    }


    public String getCountry_code() {
        return country_code;
    }


    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }


    public String getCivility_code() {
        return civility_code;
    }


    public void setCivility_code(String civility_code) {
        this.civility_code = civility_code;
    }


    public String getCivility() {
        return civility;
    }


    public void setCivility(String civility) {
        this.civility = civility;
    }


    public Long getCivility_id() {
        return civility_id;
    }


    public void setCivility_id(Long civility_id) {
        this.civility_id = civility_id;
    }


    public Integer getAdmin() {
        return admin;
    }


    public void setAdmin(Integer admin) {
        this.admin = admin;
    }
}