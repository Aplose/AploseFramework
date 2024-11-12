package fr.aplose.aploseframework.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.aplose.aploseframework.dto.TranslationDTO;
import fr.aplose.aploseframework.model.Translation;
import fr.aplose.aploseframework.repository.TranslationRepository;

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
            translation = new Translation();
            translation.setCode(code);
            translation.setLocale(locale);
            translation.setMessage(defaultMessage);
            try {
                translationRepository.save(translation);
            } catch (Exception e) {
                System.out.println("Error saving translation: " + e.getMessage());
            }
        }
        return new TranslationDTO(translation);
    }
    public List<Translation> getAllTranslationByLocale(String locale){
        return translationRepository.findByLocale(locale);
    }
}
