package com.aplose.aploseframework.service;

import com.aplose.aploseframework.dto.TranslationDTO;
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
    public TranslationDTO getTranslationByLocaleCode(String locale, String code, String defaultMessage) {
        Translation translation = translationRepository.findByCodeAndLocale(code, locale);
        if(translation==null){
            //TODO dev a dolibarr webservice to allow asking for translation
            translation = new Translation();
            translation.setCode(code);
            translation.setLocale(locale);
            translation.setMessage(defaultMessage);
            translationRepository.save(translation);
        }
        return new TranslationDTO(translation);
    }
    public List<Translation> getAllTranslationByLocale(String locale){
        return translationRepository.findByLocale(locale);
    }
}
