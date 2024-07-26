package com.aplose.aploseframework.rest;

import com.aplose.aploseframework.model.dictionnary.AbstractDictionnary;
import com.aplose.aploseframework.service.DolibarrService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Récupération des dictionnaire du Dolibarr central
 * @author oandrade
 */
@RestController
@RequestMapping("/api/dictionnary")
public class DictionnaryController {
    @Autowired
    DolibarrService dolibarrService;
    
    @GetMapping("/{name}")
    public AbstractDictionnary[] getDictionnary(@PathVariable("name") String name,
            @RequestParam Map<String,String> params){
        return dolibarrService.getDictionnary(name, params);
    }
}
