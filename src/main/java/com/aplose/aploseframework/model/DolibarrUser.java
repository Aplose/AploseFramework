package com.aplose.aploseframework.model;

import com.aplose.aploseframework.utils.dolibarr.DolibarrUtil;

public class DolibarrUser {

    String id;
    String civility_code;
    String gender;
    String fullname;
    String firstname;
    String lastname;
    String email;
    Long country_id;
    String country_code;
    String address;
    String town;
    String zip;
    String login;
    String pass;



    public DolibarrUser(Person person){
        this.setAddress(person.getAddress().getRow2() + " " + person.getAddress().getRow3() + " " + person.getAddress().getRow4() + " " + person.getAddress().getRow5());
        this.setCivility_code(person.getCivility().getCode());
        this.setCountry_code(person.getAddress().getCountry().getCode());
        this.setCountry_id(person.getAddress().getCountry().getId());
        this.setEmail(person.getUserAccount().getUsername());
        this.setFirstname(person.getFirstName());
        this.setFullname(person.getFullName());
        this.setGender("m");
        this.setLastname(person.getLastName());
        this.setLogin(DolibarrUtil.createDolibarrLogin(person));
        this.setPass(person.getUserAccount().getPassword());
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCivility_code() {
        return civility_code;
    }
    public void setCivility_code(String civility_code) {
        this.civility_code = civility_code;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
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
    public Long getCountry_id() {
        return country_id;
    }
    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }
    public String getCountry_code() {
        return country_code;
    }
    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }
    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }


    public String getPass() {
        return pass;
    }


    public void setPass(String pass) {
        this.pass = pass;
    }

}
