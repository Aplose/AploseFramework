package com.aplose.aploseframework.service;

import com.aplose.aploseframework.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author oandrade
 */
@Service
public class TranslationService {
    @Autowired
    TranslationRepository translationRepository;
    
/*    public String getTranslationByLocaleCodeDefault(String locale, String code, String default){
        Translation translation = translationRepository.findByCodeAndLocale(code, locale);
        return translation.getMessage();
    }*/
}
