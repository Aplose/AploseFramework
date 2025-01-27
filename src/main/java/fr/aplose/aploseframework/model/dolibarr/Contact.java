package fr.aplose.aploseframework.model.dolibarr;

import fr.aplose.aploseframework.constantes.dolibarr.DolibarrStatus;
import fr.aplose.aploseframework.model.Person;

/**
 *
 * @author oandrade
 */
public class Contact extends DolibarrObject {

    private Integer civility_id;
    private String civility_code;
    private String civility;
    private String firstname;
    private String lastname;
    private String email;
    private String phone_pro;
    private String address;
    private String zip;
    private String town;
    private Integer country_id;
    private String country_code;
    private Integer socid;  // ThirdParty id
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
        this.setPhone_pro(person.getPhonePrefix() + person.getPhoneNumber());
        //TODO voir pour row3, row4 ...
        this.setAddress(person.getAddress() != null ? person.getAddress().getRow2() + "\n" + person.getAddress().getRow3() + "\n" + person.getAddress().getRow4() + "\n" + person.getAddress().getRow5() : "");
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


    public Integer getCountry_id() {
        return country_id;
    }


    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
    }


    public Integer getSocid() {
        return socid;
    }


    public void setSocid(Integer socid) {
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


    public Integer getCivility_id() {
        return civility_id;
    }


    public void setCivility_id(Integer civility_id) {
        this.civility_id = civility_id;
    }


    public Integer getAdmin() {
        return admin;
    }


    public void setAdmin(Integer admin) {
        this.admin = admin;
    }
}
