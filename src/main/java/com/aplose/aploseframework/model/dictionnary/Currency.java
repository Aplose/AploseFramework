/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.model.dictionnary;

/**
 *
 * @author oandrade
 */
public class Currency extends AbstractDictionnary{
    private String code_iso;
    private String label;
    private String unicode;

    public String getCode_iso() {
        return code_iso;
    }

    public void setCode_iso(String code_iso) {
        this.code_iso = code_iso;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUnicode() {
        return unicode;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }
    
}
