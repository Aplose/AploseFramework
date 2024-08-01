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
    Integer status;

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

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
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
    
}
