package fr.aplose.aploseframework.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

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
    private Boolean booleanValue;
    private Boolean isFrontAllowed=Boolean.FALSE;
    
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

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public Boolean getIsFrontAllowed() {
        return isFrontAllowed;
    }

    public void setIsFrontAllowed(Boolean isFrontAllowed) {
        this.isFrontAllowed = isFrontAllowed;
    }

    
    
}
