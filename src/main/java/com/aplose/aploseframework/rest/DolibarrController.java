package com.aplose.aploseframework.rest;

import com.aplose.aploseframework.model.dolibarr.DolibarrObject;
import com.aplose.aploseframework.service.DolibarrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author oandrade
 */
@RestController
@CrossOrigin
@RequestMapping("/api/dolibarr")
public class DolibarrController {
    @Autowired
    DolibarrService dolibarrService;
    @GetMapping("/{name}")
    public DolibarrObject[] getAll(@PathVariable("name") String name){
        return dolibarrService.getAll(name, null);
    }
}
