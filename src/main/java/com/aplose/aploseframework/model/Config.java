/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.Instant;

/**
 *
 * @author oandrade
 */
@Entity
public class Config {
    @Id
    private String configKey;
    private String stringValue;
    private Integer integerValue;
    private Long longValue;
    private Float floatValue;
    private Double doubleValue;
    private Instant instantValue;
    private Boolean booleanValue;
    
    public Config(){}

    public Config(String configKey, String stringValue) {
        this.configKey = configKey;
        this.stringValue = stringValue;
    }

    public Config(String configKey, Integer integerValue) {
        this.configKey = configKey;
        this.integerValue = integerValue;
    }

    public Config(String configKey, Long longValue) {
        this.configKey = configKey;
        this.longValue = longValue;
    }

    public Config(String configKey, Float floatValue) {
        this.configKey = configKey;
        this.floatValue = floatValue;
    }

    public Config(String configKey, Instant instantValue) {
        this.configKey = configKey;
        this.instantValue = instantValue;
    }

    public Config(String configKey, Double doubleValue) {
        this.configKey = configKey;
        this.doubleValue = doubleValue;
    }

    public Config(String configKey, Boolean booleanValue) {
        this.configKey = configKey;
        this.booleanValue = booleanValue;
    }
    
    

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Integer getIntegerValue() {
        return integerValue;
    }

    public void setIntegerValue(Integer integerValue) {
        this.integerValue = integerValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public Float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(Float floatValue) {
        this.floatValue = floatValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    
    public Instant getInstantValue() {
        return instantValue;
    }

    public void setInstantValue(Instant instantValue) {
        this.instantValue = instantValue;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    
    
}
