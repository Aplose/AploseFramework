package fr.aplose.aploseframework.rest;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.aplose.aploseframework.exception.ConfigRestrictedToBackendException;
import fr.aplose.aploseframework.model.Config;
import fr.aplose.aploseframework.service.ConfigService;

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
