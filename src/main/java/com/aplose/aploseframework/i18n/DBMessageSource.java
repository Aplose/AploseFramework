/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.i18n;

import com.aplose.aploseframework.model.Translation;
import com.aplose.aploseframework.repository.TranslationRepository;
import java.text.MessageFormat;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author oandrade
 */
//@Component("messageSource")
public class DBMessageSource extends AbstractMessageSource{
    @Autowired
    TranslationRepository translationRepository;
    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        Translation translation = translationRepository.findByCodeAndLocale(code, locale.toString());
        String value = "";
        if(translation==null){
            translation = translationRepository.findByCodeAndLocale(code, locale.getLanguage());
        }
        if(translation!=null){
            value = translation.getMessage();
        }
        return new MessageFormat(value,locale);
    }    
}
