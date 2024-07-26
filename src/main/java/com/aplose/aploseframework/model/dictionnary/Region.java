/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.model.dictionnary;

/**
 *
 * @author oandrade
 */
public class Region extends AbstractDictionnary {
    private Long id;
    private Integer entity;
    private String code;
    private String label;
    private Integer active;
    private String code_region;
    private Long fk_pays;
    private String name;
    private Long cheflieu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEntity() {
        return entity;
    }

    public void setEntity(Integer entity) {
        this.entity = entity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }


    public String getCode_region() {
        return code_region;
    }

    public void setCode_region(String code_region) {
        this.code_region = code_region;
    }

    public Long getFk_pays() {
        return fk_pays;
    }

    public void setFk_pays(Long fk_pays) {
        this.fk_pays = fk_pays;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCheflieu() {
        return cheflieu;
    }

    public void setCheflieu(Long cheflieu) {
        this.cheflieu = cheflieu;
    }
    
}
