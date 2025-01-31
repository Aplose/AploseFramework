package fr.aplose.aploseframework.model.dolibarr;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author oandrade
 */
public class Proposal extends DolibarrObject {
    
    public final static String NAME = "proposals";
    private String ref;
    private String ref_client; // ref du client
    private Integer socid;  // id du ThirdParty
    private String datec; // date de création
    private String datep; // proposal date
    private String date_validation; // validation date
    private String date_cloture; // closed date
    private BigDecimal total_ht; // Total without VAT
    private BigDecimal total_ttc; // Total TTC
    private BigDecimal total_tva; // Total TVA
    private String note_private; // commentaire
    private String note_public; // commentaire public
    private List<ProposalLine> lines = new ArrayList<ProposalLine>();

    public Proposal(){
        endPoint="/proposals";
    }

    public Integer getSocid() {
        return socid;
    }

    public void setSocid(Integer socid) {
        this.socid = socid;
    }

    public String getRef_client() {
        return ref_client;
    }

    public void setRef_client(String ref_client) {
        this.ref_client = ref_client;
    }

    public String getDatec() {
        return datec;
    }

    public void setDatec(String datec) {
        this.datec = datec;
    }

    public String getDatep() {
        return datep;
    }

    public void setDatep(String datep) {
        this.datep = datep;
    }

    public String getDate_cloture() {
        return date_cloture;
    }

    public void setDate_cloture(String date_cloture) {
        this.date_cloture = date_cloture;
    }




    public String getNote_private() {
        return note_private;
    }

    public void setNote_private(String note_private) {
        this.note_private = note_private;
    }

    public String getNote_public() {
        return note_public;
    }

    public void setNote_public(String note_public) {
        this.note_public = note_public;
    }

    public BigDecimal getTotal_ht() {
        return total_ht;
    }

    public void setTotal_ht(BigDecimal total_ht) {
        this.total_ht = total_ht;
    }

    public List<ProposalLine> getLines() {
        return lines;
    }

    public void setLines(List<ProposalLine> lines) {
        this.lines = lines;
    }

    public String getDate_validation() {
        return date_validation;
    }

    public void setDate_validation(String date_validation) {
        this.date_validation = date_validation;
    }

    public BigDecimal getTotal_ttc() {
        return total_ttc;
    }

    public void setTotal_ttc(BigDecimal total_ttc) {
        this.total_ttc = total_ttc;
    }

    public BigDecimal getTotal_tva() {
        return total_tva;
    }

    public void setTotal_tva(BigDecimal total_tva) {
        this.total_tva = total_tva;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
