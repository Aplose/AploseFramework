package fr.aplose.aploseframework.model.dictionnary;

/**
 * Represent the Company using the Dolibarr
 *
 * @author oandrade
 */
public class Company {

//  "module": "societe",
  private Long id;
//  private Long entity;
//  private String import_key;
//  "array_options": [],
//  "array_languages": null,
//  "contacts_ids": null,
//  "linked_objects": null,
//  "linkedObjectsIds": null,
//  "oldref": null,
//  "actionmsg": null,
//  "actionmsg2": null,
//  "canvas": null,
//  "fk_project": null,
//  "user": null,
//  "origin": null,
//  "origin_id": null,
//  "origin_object": null,
//  "expedition": null,
//  "livraison": null,
//  "commandeFournisseur": null,
//  "ref": null,
//  "ref_ext": null,
//  "statut": null,
    private Integer status;
    private Long country_id;
    private String country_code;
    private Long state_id;
    private Long region_id;
//  "barcode_type": null,
//  "barcode_type_coder": null,
//  "mode_reglement_id": null,
//  "cond_reglement_id": null,
//  "demand_reason_id": null,
//  "transport_mode_id": null,
//  "shipping_method_id": null,
//  "shipping_method": null,
//  "fk_multicurrency": null,
//  "multicurrency_code": null,
//  "multicurrency_tx": null,
//  "multicurrency_total_ht": null,
//  "multicurrency_total_tva": null,
//  "multicurrency_total_ttc": null,
//  "multicurrency_total_localtax1": null,
//  "multicurrency_total_localtax2": null,
//  "model_pdf": null,
//  "last_main_doc": null,
//  "fk_bank": null,
//  "fk_account": null,
//  "note_public": null,
//  "note_private": "",
    private String name;
//  "date_creation": null,
//  "date_validation": null,
//  "date_modification": null,
//  "date_update": null,
//  "date_cloture": null,
//  "user_author": null,
//  "user_creation": null,
//  "user_creation_id": null,
//  "user_valid": null,
//  "user_validation": null,
//  "user_validation_id": null,
//  "user_closing_id": null,
//  "user_modification": null,
//  "user_modification_id": null,
//  "specimen": 0,
//  "totalpaid": null,
//  "labelStatus": [],
//  "labelStatusShort": [],
//  "tpl": null,
//  "showphoto_on_popup": null,
//  "nb": [],
//  "nbphoto": null,
//  "output": null,
//  "extraparams": [],
//  "product": null,
//  "deposit_percent": null,
//  "retained_warranty_fk_cond_reglement": null,
//  "warehouse_id": null,
//  "SupplierCategories": [],
//  "prefixCustomerIsRequired": null,
//  "name_alias": null,
//  "particulier": null,
    private String phone;
//  "fax": "",
    private String email;
//  "no_email": null,
//  "skype": null,
//  "twitter": null
//  "facebook": null,
//  "linkedin": null,
    private String url;
//  "barcode": null,
//  "idprof1": "",
    private String siren;
    private String idprof2;
    private String siret;
    private String idprof3;
    private String ape;
    private String idprof4;
    private String idprof5;
    private String idprof6;
//  "socialobject": "",
    private String tva_assuj;
//  "tva_intra": "",
//  "vat_reverse_charge": 0,
//  "localtax1_assuj": 0,
//  "localtax1_value": null,
//  "localtax2_assuj": 0,
//  "localtax2_value": null,
    private String managers;
    private String capital;
//  "typent_id": 0,
//  "typent_code": null,
//  "remise_percent": null,
//  "remise_supplier_percent": null,
//  "code_client": null,
//  "code_fournisseur": null,
//  "code_compta_client": null,
//  "code_compta": null,
//  "accountancy_code_customer": null,
//  "code_compta_fournisseur": null,
//  "accountancy_code_supplier": null,
//  "code_compta_product": null,
//  "stcomm_id": null,
//  "stcomm_picto": null,
//  "status_prospect_label": null,
//  "price_level": null,
//  "outstanding_limit": null,
//  "order_min_amount": null,
//  "supplier_order_min_amount": null,
//  "commercial_id": null,
//  "parent": null,
//  "default_lang": "auto",
//  "webservices_url": null,
//  "webservices_key": null,
    private String logo;
    private String logo_small;
    private String logo_mini;
    private String logo_squarred;
    private String logo_squarred_small;
    private String logo_squarred_mini;
//  "accountancy_code_sell": null,
//  "accountancy_code_buy": null,
//  "fk_warehouse": null,
//  "partnerships": [],
//  "bank_account": null,
//  "socialnetworks": [],
    private String address;
    private String zip;
    private String town;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Long getState_id() {
        return state_id;
    }

    public void setState_id(Long state_id) {
        this.state_id = state_id;
    }

    public Long getRegion_id() {
        return region_id;
    }

    public void setRegion_id(Long region_id) {
        this.region_id = region_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public String getIdprof2() {
        return idprof2;
    }

    public void setIdprof2(String idprof2) {
        this.idprof2 = idprof2;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getIdprof3() {
        return idprof3;
    }

    public void setIdprof3(String idprof3) {
        this.idprof3 = idprof3;
    }

    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        this.ape = ape;
    }

    public String getIdprof4() {
        return idprof4;
    }

    public void setIdprof4(String idprof4) {
        this.idprof4 = idprof4;
    }

    public String getIdprof5() {
        return idprof5;
    }

    public void setIdprof5(String idprof5) {
        this.idprof5 = idprof5;
    }

    public String getIdprof6() {
        return idprof6;
    }

    public void setIdprof6(String idprof6) {
        this.idprof6 = idprof6;
    }

    public String getTva_assuj() {
        return tva_assuj;
    }

    public void setTva_assuj(String tva_assuj) {
        this.tva_assuj = tva_assuj;
    }

    public String getManagers() {
        return managers;
    }

    public void setManagers(String managers) {
        this.managers = managers;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogo_small() {
        return logo_small;
    }

    public void setLogo_small(String logo_small) {
        this.logo_small = logo_small;
    }

    public String getLogo_mini() {
        return logo_mini;
    }

    public void setLogo_mini(String logo_mini) {
        this.logo_mini = logo_mini;
    }

    public String getLogo_squarred() {
        return logo_squarred;
    }

    public void setLogo_squarred(String logo_squarred) {
        this.logo_squarred = logo_squarred;
    }

    public String getLogo_squarred_small() {
        return logo_squarred_small;
    }

    public void setLogo_squarred_small(String logo_squarred_small) {
        this.logo_squarred_small = logo_squarred_small;
    }

    public String getLogo_squarred_mini() {
        return logo_squarred_mini;
    }

    public void setLogo_squarred_mini(String logo_squarred_mini) {
        this.logo_squarred_mini = logo_squarred_mini;
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
