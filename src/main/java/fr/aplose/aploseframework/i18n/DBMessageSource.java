package fr.aplose.aploseframework.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;

import fr.aplose.aploseframework.model.Translation;
import fr.aplose.aploseframework.repository.TranslationRepository;

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
