/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.model.dolibarr;

import java.time.Instant;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author oandrade
 */
public class Proposal extends DolibarrObject {
    
    public final static String NAME = "proposals";
    private Integer socid;  // id du ThirdParty
    private String datec; // date de création
    private String datep; // proposal date
    private String date_valid; // validation date
    private String date_cloture; // closed date
    private BigDecimal total_ht; // Total without VAT
    private BigDecimal total; // 	Total TTC
    private BigDecimal tva; // 		total VAT
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

    public String getDate_valid() {
        return date_valid;
    }

    public void setDate_valid(String date_valid) {
        this.date_valid = date_valid;
    }

    public String getDate_cloture() {
        return date_cloture;
    }

    public void setDate_cloture(String date_cloture) {
        this.date_cloture = date_cloture;
    }



    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public BigDecimal getTva() {
        return tva;
    }

    public void setTva(BigDecimal tva) {
        this.tva = tva;
    }

    public List<ProposalLine> getLines() {
        return lines;
    }

    public void setLines(List<ProposalLine> lines) {
        this.lines = lines;
    }

    // public List<ProposalLine> getLines() {
    //     return lines;
    // }

    // public void setLines(List<ProposalLine> lines) {
    //     this.lines = lines;
    // }




}