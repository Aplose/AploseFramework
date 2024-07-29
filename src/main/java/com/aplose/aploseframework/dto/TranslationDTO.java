package com.aplose.aploseframework.dto;

/**
 *
 * @author oandrade
 */
public class TranslationDTO {
    private String locale;
    private String code;
    private String message;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
