package com.aplose.aploseframework.rest;

import com.aplose.aploseframework.exception.ConfigRestrictedToBackendException;
import com.aplose.aploseframework.model.Config;
import com.aplose.aploseframework.service.ConfigService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataNotFoundException;
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
@RequestMapping("/api/config")
public class ConfigController {
    @Autowired
    ConfigService configService;
    @GetMapping("/{configKey}")
    public Config getConfig(@PathVariable("configKey")String configKey) throws ConfigRestrictedToBackendException{        
        Optional<Config> config = configService.getConfig(configKey);
        if (config.isPresent()&&config.get().getIsFrontAllowed()){
            return config.get();
        }else{
            if(config.isEmpty()){
                throw new ConfigRestrictedToBackendException("Config not found");
            }else{
                throw new ConfigRestrictedToBackendException("Config not allowed in frontend");
            }
        }
    }
}
