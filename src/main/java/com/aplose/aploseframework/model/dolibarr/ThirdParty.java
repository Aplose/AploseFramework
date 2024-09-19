/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.model.dolibarr;
import java.util.HashMap;
import java.util.Map;
import com.aplose.aploseframework.constantes.dolibarr.DolibarrThirdPartyClientTypes;
import com.aplose.aploseframework.constantes.dolibarr.DolibarrEntityTypes;
import com.aplose.aploseframework.constantes.dolibarr.DolibarrStatus;
import com.aplose.aploseframework.model.Person;

/**
 *
 * @author oandrade
 */
public class ThirdParty extends DolibarrObject{

    public final static String NAME = "thirdparties";
    private Integer entity;
    private String  name;
    private String  phone;
    private String  email;
    private String  address;
    private String  town;
    private String  zip;
    private Integer region_id;
    private Integer state_id;
    private Integer    country_id;     // ! 'sqlfilters' (param dans requête sur l'api dolibarr) ne fonctionne que avec country_code, pas country_id
    private String  country_code;   
    private Integer client = 0;     // Utiliser les constantes de DolibarrThirdPartyClientTypes
    private Integer fournisseur;
    private String  code_client;
    private String  code_fournisseur;
    private Integer particulier;
    private String  url;
    private String  idprof1;    // (SIREN)
    private String  idprof2;    // (SIRET)
    private String  idprof3;    // (NAF-APE)
    private String  idprof4;    // (RCS/RM)
    private String  idprof5;    // (numéro EORI)
    private Integer tva_assuj = 1;
    private String  tva_intra;
    private String  forme_juridique;
    private String  logo;
    private String  logo_squarred;
    private String  typent_code;    
    // type d'entité (entreprise, personne physique, asso...)
    private Integer typent_id;    // utiliser les constantes de DolibarrEntityTypes
    private Map<String, String> array_options = new HashMap<String, String>();



    public ThirdParty(){
        endPoint = "/thirdparties";
        this.array_options.put("options_stripe_customer_id", null);
        this.array_options.put("options_stripe_account_id", null);
    }

    public ThirdParty(Person person){
        this();
        this.setName(person.getUserAccount().isProfessionnalAccount() ? person.getUserAccount().getCompanyName() : person.getFullName());
        this.setPhone(person.getPhone());
        this.setEmail(person.getUserAccount().getUsername());
        this.setAddress(person.getAddress().getRow2() != null ? person.getAddress().getRow2()+", "+person.getAddress().getRow3()+", "+person.getAddress().getRow4()+", "+person.getAddress().getRow5() : null);
        this.setTown(person.getAddress().getTown() != null ? person.getAddress().getTown().getName() : null);
        this.setZip(person.getAddress().getTown() != null ? person.getAddress().getTown().getZipCode() : null);
        this.setCountry_id(person.getAddress().getCountry().getId());
        this.setCountry_code(person.getAddress().getCountry().getCode());
        this.setClient(person.getUserAccount().isProfessionnalAccount() ? DolibarrThirdPartyClientTypes.PROSPECT : DolibarrThirdPartyClientTypes.NOT_CLIENT_NOT_PROSPECT);
        this.setTypent_id(person.getUserAccount().isProfessionnalAccount() ? DolibarrEntityTypes.SMALL : DolibarrEntityTypes.PRIVATE);
        this.setStatus(DolibarrStatus.ACTIF);
    }


    public Integer getEntity() {
        return entity;
    }

    public void setEntity(Integer entity) {
        this.entity = entity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public Integer getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Integer fournisseur) {
        this.fournisseur = fournisseur;
    }

    public String getCode_client() {
        return code_client;
    }

    public void setCode_client(String code_client) {
        this.code_client = code_client;
    }

    public String getCode_fournisseur() {
        return code_fournisseur;
    }

    public void setCode_fournisseur(String code_fournisseur) {
        this.code_fournisseur = code_fournisseur;
    }

    public Integer getParticulier() {
        return particulier;
    }

    public void setParticulier(Integer particulier) {
        this.particulier = particulier;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIdprof1() {
        return idprof1;
    }

    public void setIdprof1(String idprof1) {
        this.idprof1 = idprof1;
    }

    public String getIdprof2() {
        return idprof2;
    }

    public void setIdprof2(String idprof2) {
        this.idprof2 = idprof2;
    }

    public String getIdprof3() {
        return idprof3;
    }

    public void setIdprof3(String idprof3) {
        this.idprof3 = idprof3;
    }

    public String getIdprof4() {
        return idprof4;
    }

    public void setIdprof4(String idprof4) {
        this.idprof4 = idprof4;
    }

    public Integer getTva_assuj() {
        return tva_assuj;
    }

    public void setTva_assuj(Integer tva_assuj) {
        this.tva_assuj = tva_assuj;
    }

    public String getTva_intra() {
        return tva_intra;
    }

    public void setTva_intra(String tva_intra) {
        this.tva_intra = tva_intra;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogo_squarred() {
        return logo_squarred;
    }

    public void setLogo_squarred(String logo_squarred) {
        this.logo_squarred = logo_squarred;
    }

    public Integer getState_id() {
        return state_id;
    }

    public void setState_id(Integer state_id) {
        this.state_id = state_id;
    }

    public Integer getRegion_id() {
        return region_id;
    }

    public void setRegion_id(Integer region_id) {
        this.region_id = region_id;
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

    // public Integer getStatus() {
    //     return status;
    // }

    // public void setStatus(Integer status) {
    //     this.status = status;
    // }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }


    public String getIdprof5() {
        return idprof5;
    }


    public void setIdprof5(String idprof5) {
        this.idprof5 = idprof5;
    }


    public String getTypent_code() {
        return typent_code;
    }


    public void setTypent_code(String typent_code) {
        this.typent_code = typent_code;
    }


    public String getForme_juridique() {
        return forme_juridique;
    }


    public void setForme_juridique(String forme_juridique) {
        this.forme_juridique = forme_juridique;
    }


    public Integer getTypent_id() {
        return typent_id;
    }


    public Map<String, String> getArray_options() {
        return array_options;
    }

    public void setTypent_id(Integer typent_id) {
        this.typent_id = typent_id;
    }

    public String getStripeCustomerId(){
        return this.array_options.get("options_stripe_customer_id");
    }

    public void setStripeCustomerId(String stripeCustomerId){
        this.array_options.put("options_stripe_customer_id", stripeCustomerId);
    }

    public String getStripeAccountId(){
        return this.array_options.get("options_stripe_account_id");
    }

    public void setStripeAccountId(String stripeAccountId){
        this.array_options.put("options_stripe_account_id", stripeAccountId);
    }
}
