/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.model;

import com.aplose.aploseframework.model.dictionnary.Country;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author oandrade
 */
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String row2;
    private String row3;
    private String row4;
    private String row5;
    @ManyToOne(cascade = CascadeType.ALL)
    private Town town;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "country_id")),
        @AttributeOverride(name = "entity", column = @Column(name = "country_entity")),
        @AttributeOverride(name = "code", column = @Column(name = "country_code")),
        @AttributeOverride(name = "codeIso", column = @Column(name = "country_codeIso")),
        @AttributeOverride(name = "label", column = @Column(name = "country_label")),
        @AttributeOverride(name = "active", column = @Column(name = "country_active"))
    })
    private Country country;    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRow2() {
        return row2;
    }

    public void setRow2(String row2) {
        this.row2 = row2;
    }

    public String getRow3() {
        return row3;
    }

    public void setRow3(String row3) {
        this.row3 = row3;
    }

    public String getRow4() {
        return row4;
    }

    public void setRow4(String row4) {
        this.row4 = row4;
    }

    public String getRow5() {
        return row5;
    }

    public void setRow5(String row5) {
        this.row5 = row5;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    } 
    
}
