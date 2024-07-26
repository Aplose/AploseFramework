/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.model.dictionnary;

/**
 *
 * @author oandrade
 */
public class PaymentTerm extends AbstractDictionnary{
    private Long id;
    private String code;
    private Integer sortorder;
    private String label;
    private String descr;
    private String type_cdr;
    private Integer nbjour;
    private Integer decalage;
    private String module;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSortorder() {
        return sortorder;
    }

    public void setSortorder(Integer sortorder) {
        this.sortorder = sortorder;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getType_cdr() {
        return type_cdr;
    }

    public void setType_cdr(String type_cdr) {
        this.type_cdr = type_cdr;
    }

    public Integer getNbjour() {
        return nbjour;
    }

    public void setNbjour(Integer nbjour) {
        this.nbjour = nbjour;
    }

    public Integer getDecalage() {
        return decalage;
    }

    public void setDecalage(Integer decalage) {
        this.decalage = decalage;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
    
}
