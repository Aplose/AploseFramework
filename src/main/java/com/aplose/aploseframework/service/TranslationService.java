package com.aplose.aploseframework.service;

import com.aplose.aploseframework.model.Translation;
import com.aplose.aploseframework.repository.TranslationRepository;
import java.util.List;
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
    
    public String getTranslationByLocaleCode(String locale, String code, String defaultMessage) {
        Translation translation = translationRepository.findByCodeAndLocale(code, locale);
        if(translation==null){
            translation = new Translation();
            translation.setCode(code);
            translation.setLocale(locale);
            translation.setMessage(defaultMessage);
            translationRepository.save(translation);
        }
        return translation.getMessage();
    }
    public List<Translation> getAllTranslationByLocale(String locale){
        return translationRepository.findByLocale(locale);
    }
}
