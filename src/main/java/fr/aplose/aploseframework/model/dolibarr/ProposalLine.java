package fr.aplose.aploseframework.model.dolibarr;
import java.math.BigDecimal;

public class ProposalLine {

    private String rowid;
    private Integer fk_propal; // ID de la proposition
    private String product_label;  // Optionnel, si tu as un label spécifique
    private Integer fk_product;     // ID du produit, ou null si ce n'est pas lié à un produit
    private Integer qty;        // Quantité de l'article ou du service  
    private BigDecimal subprice;       // Prix unitaire hors taxe   
    private BigDecimal total_ht;      // Total hors taxes (calculé : subprice * qty - remise)   
    private BigDecimal total_tva;    // Total de la TVA (calculé : total_ht * tva_tx / 100)
    private BigDecimal total_ttc;    // Total TTC (calculé : total_ht + total_tva)
    private Integer rang;     // Rang dans la liste des lignes (ordre des lignes)
    private String multicurrency_code;    // Code de la devise
    private BigDecimal multicurrency_subprice;     // Prix unitaire dans la devise choisie
    private BigDecimal multicurrency_total_ht;    // Total HT en devise
    private BigDecimal multicurrency_total_tva;   // Total TVA en devise
    private BigDecimal multicurrency_total_ttc;    // Total TTC en devise
    private Integer product_type;   // type: 0=service; 1=product
    private String libelle; // reference du produit ou service 
    private Float tva_tx;

    // les propriété en dessous ne font pas partie de Dolibarr
    private String productImageSrc;
    private String productLabel;




    
    public Integer getFk_propal() {
        return fk_propal;
    }
    public void setFk_propal(Integer fk_propal) {
        this.fk_propal = fk_propal;
    }
    public String getProduct_label() {
        return product_label;
    }
    public void setProduct_label(String product_label) {
        this.product_label = product_label;
    }
    public Integer getFk_product() {
        return fk_product;
    }
    public void setFk_product(Integer fk_product) {
        this.fk_product = fk_product;
    }
    public Integer getQty() {
        return qty;
    }
    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public BigDecimal getSubprice() {
        return subprice;
    }
    public void setSubprice(BigDecimal subprice) {
        this.subprice = subprice;
    }
    public BigDecimal getTotal_ht() {
        return total_ht;
    }
    public void setTotal_ht(BigDecimal total_ht) {
        this.total_ht = total_ht;
    }
    public BigDecimal getTotal_tva() {
        return total_tva;
    }
    public void setTotal_tva(BigDecimal total_tva) {
        this.total_tva = total_tva;
    }
    public BigDecimal getTotal_ttc() {
        return total_ttc;
    }
    public void setTotal_ttc(BigDecimal total_ttc) {
        this.total_ttc = total_ttc;
    }
    public Integer getRang() {
        return rang;
    }
    public void setRang(Integer rang) {
        this.rang = rang;
    }
    public String getMulticurrency_code() {
        return multicurrency_code;
    }
    public void setMulticurrency_code(String multicurrency_code) {
        this.multicurrency_code = multicurrency_code;
    }
    public BigDecimal getMulticurrency_subprice() {
        return multicurrency_subprice;
    }
    public void setMulticurrency_subprice(BigDecimal multicurrency_subprice) {
        this.multicurrency_subprice = multicurrency_subprice;
    }
    public BigDecimal getMulticurrency_total_ht() {
        return multicurrency_total_ht;
    }
    public void setMulticurrency_total_ht(BigDecimal multicurrency_total_ht) {
        this.multicurrency_total_ht = multicurrency_total_ht;
    }
    public BigDecimal getMulticurrency_total_tva() {
        return multicurrency_total_tva;
    }
    public void setMulticurrency_total_tva(BigDecimal multicurrency_total_tva) {
        this.multicurrency_total_tva = multicurrency_total_tva;
    }
    public BigDecimal getMulticurrency_total_ttc() {
        return multicurrency_total_ttc;
    }
    public void setMulticurrency_total_ttc(BigDecimal multicurrency_total_ttc) {
        this.multicurrency_total_ttc = multicurrency_total_ttc;
    }

    public Integer getProduct_type() {
        return product_type;
    }
    public void setProduct_type(Integer product_type) {
        this.product_type = product_type;
    }
    public String getRowid() {
        return rowid;
    }
    public void setRowid(String rowid) {
        this.rowid = rowid;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public String getProductImageSrc() {
        return productImageSrc;
    }
    public void setProductImageSrc(String productImageSrc) {
        this.productImageSrc = productImageSrc;
    }
    public String getProductLabel() {
        return productLabel;
    }
    public void setProductLabel(String productLabel) {
        this.productLabel = productLabel;
    }
    public Float getTva_tx() {
        return tva_tx;
    }
    public void setTva_tx(Float tva_tx) {
        this.tva_tx = tva_tx;
    }


    

}

/*
    "fk_propal": 25,               // ID de la proposition
    "fk_parent_line": null,         // Si cette ligne fait partie d'un ensemble de lignes parent, sinon null
    "label": null,                  // Optionnel, si tu as un label spécifique
    "description": "Développement d'une application web",
    "fk_product": null,             // ID du produit, ou null si ce n'est pas lié à un produit
    "product_type": 0,              // 0 pour un service, 1 pour un produit
    "qty": 10,                      // Quantité de l'article ou du service
    "tva_tx": 20.0,                 // Taux de TVA
    "subprice": 100.00,             // Prix unitaire hors taxe
    "remise_percent": 5.0,          // Remise en pourcentage
    "total_ht": 950.00,             // Total hors taxes (calculé : subprice * qty - remise)
    "total_tva": 190.00,            // Total de la TVA (calculé : total_ht * tva_tx / 100)
    "total_ttc": 1140.00,           // Total TTC (calculé : total_ht + total_tva)
    "fk_product_fournisseur_price": null, // Pas nécessaire ici, à moins que tu aies un prix fournisseur
    "buy_price_ht": null,           // Prix d'achat HT si applicable
    "special_code": 0,              // Code spécial (par défaut à 0)
    "rang": 1,                      // Rang dans la liste des lignes (ordre des lignes)
    "fk_unit": 0,                   // Unité de mesure si applicable (ou 0)
    "date_start": null,             // Date de début, si applicable
    "date_end": null,               // Date de fin, si applicable
    "multicurrency_code": "EUR",    // Code de la devise
    "multicurrency_subprice": 100.00,  // Prix unitaire dans la devise choisie
    "multicurrency_total_ht": 950.00,  // Total HT en devise
    "multicurrency_total_tva": 190.00, // Total TVA en devise
    "multicurrency_total_ttc": 1140.00 // Total TTC en devise

 */