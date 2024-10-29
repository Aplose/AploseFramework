/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.model.dolibarr;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;

/**
 *
 * @author oandrade
 */
public class Product extends DolibarrObject {
    
    public Product(){
        endPoint="/products";
    }

    public final static String NAME = "products";
    HashMap<String, Object> array_options=new HashMap<>();
    @JsonIgnore
    Multiprices multiprices;
    @JsonIgnore
    Multiprices multiprices_ttc;
    @JsonIgnore
    Multiprices multiprices_base_type;
    @JsonIgnore
    Multiprices multiprices_min;
    @JsonIgnore
    Multiprices multiprices_min_ttc;
    @JsonIgnore
    Multiprices multiprices_tva_tx;
    Float tva_tx;
    Float remise_percent;

    public HashMap<String, Object> getArray_options() {
        return array_options;
    }

    public void setArray_options(HashMap<String, Object> array_options) {
        this.array_options = array_options;
    }

    
    public Multiprices getMultiprices() {
        return multiprices;
    }

    public void setMultiprices(Multiprices multiprices) {
        this.multiprices = multiprices;
    }

    public Multiprices getMultiprices_ttc() {
        return multiprices_ttc;
    }

    public void setMultiprices_ttc(Multiprices multiprices_ttc) {
        this.multiprices_ttc = multiprices_ttc;
    }

    public Multiprices getMultiprices_base_type() {
        return multiprices_base_type;
    }

    public void setMultiprices_base_type(Multiprices multiprices_base_type) {
        this.multiprices_base_type = multiprices_base_type;
    }

    public Multiprices getMultiprices_min() {
        return multiprices_min;
    }

    public void setMultiprices_min(Multiprices multiprices_min) {
        this.multiprices_min = multiprices_min;
    }

    public Multiprices getMultiprices_min_ttc() {
        return multiprices_min_ttc;
    }

    public void setMultiprices_min_ttc(Multiprices multiprices_min_ttc) {
        this.multiprices_min_ttc = multiprices_min_ttc;
    }

    public Multiprices getMultiprices_tva_tx() {
        return multiprices_tva_tx;
    }

    public void setMultiprices_tva_tx(Multiprices multiprices_tva_tx) {
        this.multiprices_tva_tx = multiprices_tva_tx;
    }

    public Float getTva_tx() {
        return tva_tx;
    }

    public void setTva_tx(Float tva_tx) {
        this.tva_tx = tva_tx;
    }

    public Float getRemise_percent() {
        return remise_percent;
    }

    public void setRemise_percent(Float remise_percent) {
        this.remise_percent = remise_percent;
    }
    
}
