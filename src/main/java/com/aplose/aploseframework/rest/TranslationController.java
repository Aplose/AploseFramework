package com.aplose.aploseframework.rest;

import com.aplose.aploseframework.dto.TranslationDTO;
import com.aplose.aploseframework.service.TranslationService;
import java.util.List;
import java.util.Locale;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Recherche de traduction en base de données.
 * @author oandrade
 */
@RestController
@CrossOrigin
@RequestMapping("/api/translation")
public class TranslationController {
    @Autowired
    TranslationService translationService;
    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping
    public String getTranslation(Locale locale, @RequestParam("code") String code, @RequestParam(required = false, value = "defaultMessage") String defaultMessage){
        return translationService.getTranslationByLocaleCode(locale.toString(), code, defaultMessage);
    }
    
    @GetMapping("/all")
    public List<TranslationDTO> getAllTranslationForLocale(Locale locale){
        return translationService.getAllTranslationByLocale(locale.toString()).stream().map(translation -> modelMapper.map(translation, TranslationDTO.class)).toList();
    }
    
}
