/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.service;

import com.aplose.aploseframework.model.Config;
import com.aplose.aploseframework.repository.ConfigRepository;
import jakarta.annotation.PostConstruct;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author oandrade
 */
@Service
public class ConfigService {
    @Autowired
    ConfigRepository configRepository;
    @Value("${dolibarr.api.url}")
    String dolibarrApiUrl;
    @Value("${dolibarr.user.api.key}")
    String dolibarrUserApiKey;
    
    private Map<String,Config> configurations = new HashMap<>();
    
    
    @PostConstruct    
    private void init(){
        List<String> allKeys = configRepository.getAllKeys();
        //on va voir si les config existent sinon on les crée à la volée...
        Optional<Config> configOptional;
        Config config=null;
        //dolibarr.api.url utilisé pour accéder à Dolibarr
        //on utilise le dolibarr serenitydate par défaut
        configOptional = configRepository.findById("dolibarr.api.url");
        if(configOptional.isEmpty()){
            config = new Config("dolibarr.api.url", dolibarrApiUrl);
            configRepository.save(config);
            allKeys.remove(config.getConfigKey());
            configurations.put(config.getConfigKey(), config);
        }else{
            allKeys.remove(configOptional.get().getConfigKey());
        }
        //dolibarr.user.api.key utilisé pour accéder à Dolibarr
        //on utilise le dolibarr serenitydate par défaut
        configOptional = configRepository.findById("dolibarr.user.api.key");
        if(configOptional.isEmpty()){
            config = new Config("dolibarr.user.api.key", dolibarrUserApiKey);
            configRepository.save(config);
            allKeys.remove(config.getConfigKey());
            configurations.put(config.getConfigKey(), config);
        }else{
            allKeys.remove(configOptional.get().getConfigKey());
        }
        //aploseframework.backend.root.url utilisé pour les liens envoyés par email
        configOptional = configRepository.findById("aploseframework.backend.root.url");
        if(configOptional.isEmpty()){
            config = new Config("aploseframework.backend.root.url", "http://localhost:8087");        
            configRepository.save(config);
            allKeys.remove(config.getConfigKey());
            configurations.put(config.getConfigKey(), config);
        }else{
            allKeys.remove(configOptional.get().getConfigKey());
        }
        //aploseframework.mail.fromEmail utilisé pour les envoi d'email
        configOptional = configRepository.findById("aploseframework.mail.fromEmail");
        if(configOptional.isEmpty()){
            config = new Config("aploseframework.mail.fromEmail", "noreply@aplose.fr");
            configRepository.save(config);
            allKeys.remove(config.getConfigKey());
            configurations.put(config.getConfigKey(), config);
        }else{
            allKeys.remove(configOptional.get().getConfigKey());
        }
/*        for(String keyToDelete:allKeys){
            configRepository.deleteById(keyToDelete);
        }*/
    }

    public String getStringConfig(String key){
        return getConfigFromCacheOrDb(key).getStringValue();
    }
    
    public Integer getIntegerConfig(String key){
        return getConfigFromCacheOrDb(key).getIntegerValue();
    }
    public Long getLongConfig(String key){
        return getConfigFromCacheOrDb(key).getLongValue();
    }
    public Float getFloatConfig(String key){
        return getConfigFromCacheOrDb(key).getFloatValue();
    }
    public Instant getInstantConfig(String key){
        return getConfigFromCacheOrDb(key).getInstantValue();
    }
    public Double getDoubleConfig(String key){
        return getConfigFromCacheOrDb(key).getDoubleValue();
    }
    public Boolean getBooleanConfig(String key){
        return getConfigFromCacheOrDb(key).getBooleanValue();
    }
    private Config getConfigFromCacheOrDb(String key){
        Config config=configurations.get(key);
        if(config == null){
            config = configRepository.findById(key).get();
        }
        return config;
    }
    
    public void createOrUpdate(Config config){
        configRepository.save(config);
        configurations.put(config.getConfigKey(), config);
    }
    public Config getConfig(String key){
        return configRepository.findById(key).get();
    }
    public List<Config> getAllConfigs(){
        return configRepository.findAll();
    }
}
