package com.aplose.aploseframework.model.dolibarr;

import com.aplose.aploseframework.constantes.dolibarr.DolibarrStatus;
import com.aplose.aploseframework.model.Person;
import com.aplose.aploseframework.tool.dolibarr.DolibarrTool;

/**
 *
 * @author oandrade
 */
public class User extends DolibarrObject {

    public final static String NAME = "users";
    
    private String civility_code;
    private String gender;
    private String name;
    private String phone;
    private String mobile;
    private String fullname;
    private String firstname;
    private String lastname;
    private String email;
    private Integer country_id;
    private String country_code;
    private String address;
    private String town;
    private String zip;
    private String login;
    private String password;
    private Integer socid; // ThirdParty id
    private Integer contact_id; // Contact id
    private Integer admin = 0;  // 0 = false; 1 = true


    public User(){
        endPoint = "/users";
    }


    public User(Person person){
        this();
        this.setName(person.getFullName());
        this.setPhone(person.getPhone());
        this.setMobile(person.getPhone());
        this.setAddress(person.getAddress() != null ? person.getAddress().getRow2() + " " + person.getAddress().getRow3() + " " + person.getAddress().getRow4() + " " + person.getAddress().getRow5() : "");
        this.setCivility_code(person.getCivility().getCode());
        this.setCountry_code(person.getAddress().getCountry().getCode());
        this.setCountry_id(person.getAddress().getCountry().getId());
        this.setEmail(person.getUserAccount().getUsername());
        this.setFirstname(person.getFirstName());
        this.setFullname(person.getFullName());
        this.setGender("m");
        this.setLastname(person.getLastName());
        this.setLogin(DolibarrTool.createDolibarrLogin(person));
        this.setPassword(person.getUserAccount().getPassword());
        this.setStatus(DolibarrStatus.ACTIF);
        this.setSocid(person.getUserAccount().getDolibarrThirdPartyId());
        this.setContact_id(person.getUserAccount().getDolibarrContactId());
    }

    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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


    public Integer getCountry_id() {
        return country_id;
    }


    public void setCountry_id(Integer country_id) {
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


    public Integer getSocid() {
        return socid;
    }


    public void setSocid(Integer socid) {
        this.socid = socid;
    }

    public Integer getContact_id() {
        return contact_id;
    }


    public void setContact_id(Integer contact_id) {
        this.contact_id = contact_id;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getAdmin() {
        return admin;
    }


    public void setAdmin(Integer admin) {
        this.admin = admin;
    }
}
