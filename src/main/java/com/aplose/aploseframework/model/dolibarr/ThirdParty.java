/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.model.dolibarr;

/**
 *
 * @author oandrade
 */
public class ThirdParty extends DolibarrObject{
    public ThirdParty(){
        endPoint = "/thirdparties";
    }
    Integer id;
    Integer entity;
    String name;
    Integer client = 0;
    Integer prospect = 0;
    Integer fournisseur;
    String code_client;
    String code_fournisseur;
    Integer particulier;
    String phone;
    String email;
    String url;
    String idprof1;
    String idprof2;
    String idprof3;
    String idprof4;
    Integer tva_assuj = 1;
    String tva_intra;
    Integer forme_juridique = 0;
    String logo;
    String logo_squarred;
    Integer country_id;
    Integer state_id;
    Integer region_id;
    String address;
    String zip;
    String town;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getProspect() {
        return prospect;
    }

    public void setProspect(Integer prospect) {
        this.prospect = prospect;
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

    public Integer getForme_juridique() {
        return forme_juridique;
    }

    public void setForme_juridique(Integer forme_juridique) {
        this.forme_juridique = forme_juridique;
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

    public Integer getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
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
    
    
}
