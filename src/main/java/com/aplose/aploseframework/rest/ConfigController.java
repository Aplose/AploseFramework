package com.aplose.aploseframework.rest;

import com.aplose.aploseframework.model.Config;
import com.aplose.aploseframework.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author oandrade
 */
@RestController
@RequestMapping("/api/config")
public class ConfigController {
    @Autowired
    ConfigService configService;
    @GetMapping("/{configKey}")
    public Config getConfig(@PathVariable("configKey")String configKey){        
        return configService.getConfig(configKey).get();
    }
}
