package com.aplose.aploseframework.model.dolibarr;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author oandrade
 */
public abstract class DolibarrObject {
    Integer id;
    Integer entity;    
    @JsonIgnore
    String endPoint;
    String ref;
    String ref_ext;
    Integer status;        // Utiliser les constantes de DolibarrThirdPartyStatus 
    String module;
    String import_key;
    String label;
    String description;

    public DolibarrObject(){}

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

    public String getEndPoint() {
        return endPoint;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRef_ext() {
        return ref_ext;
    }

    public void setRef_ext(String ref_ext) {
        this.ref_ext = ref_ext;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getImport_key() {
        return import_key;
    }

    public void setImport_key(String import_key) {
        this.import_key = import_key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
