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
    private ConfigRepository configRepository;

    @Value("${spring.user-account.second-to-activate-account}")
    private Long secondToActivateAccount;
    @Value("${dolibarr.api.url}")
    private String dolibarrApiUrl;
    @Value("${dolibarr.user.api.key}")
    private String dolibarrUserApiKey;
    @Value("${app.root.url}")
    private String appRootUrl;
    @Value("${stripe.webhook.secret}")
    private String stripeWebHookSecret;
    @Value("${stripe.api.key}")
    private String stripeApiKey;
    @Value("${aplose.framework.security.jwt.secretKey}")
    private String jwtSecretKey;
    @Value("${aplose.framework.superAdmin.defaultPassword}")
    private String superAdminDefaultPassword;
    @Value("${google.client.id}")
    private String googleClientId;
    @Value("vizulive.server.url")
    private String vizuliveServerUrl;
    @Value("vizulive.admin.id")
    private String vizuliveAdminId;
    @Value("vizulive.admin.user")
    private String vizuliveAdminUser;
    @Value("vizulive.admin.password")
    private String vizuliveAdminPassword;
    @Value("spring.mail.username")
    private String springMailUsername;
    @Value("spring.mail.password")
    private String springMailPassword;
    
    private Map<String,Config> configurations = new HashMap<>();
    
    
    @PostConstruct    
    private void init(){
        // List<String> allKeys = configRepository.getAllKeys();
        // //on va voir si les config existent sinon on les crée à la volée...
        // Optional<Config> configOptional;
        // Config config=null;

       



        // // temps de validité, en seconde, du code d'activation de compte lors d'un enregistrement d'un utilisateur 
        // configOptional = configRepository.findById("spring.user-account.second-to-activate-account");
        // if(configOptional.isEmpty()){
        //     config = new Config("spring.user-account.second-to-activate-account", secondToActivateAccount);
        //     configRepository.save(config);
        //     allKeys.remove(config.getConfigKey());
        //     configurations.put(config.getConfigKey(), config);
        // }else{
        //     allKeys.remove(configOptional.get().getConfigKey());
        // }
        // // mot de passe par defaut du super admin
        // configOptional = configRepository.findById("aplose.framework.superAdmin.defaultPassword");
        // if(configOptional.isEmpty()){
        //     config = new Config("aplose.framework.superAdmin.defaultPassword", superAdminDefaultPassword);
        //     configRepository.save(config);
        //     allKeys.remove(config.getConfigKey());
        //     configurations.put(config.getConfigKey(), config);
        // }else{
        //     allKeys.remove(configOptional.get().getConfigKey());
        // }
        // // phrase secrete pour le Json Web Token d'authentification
        // configOptional = configRepository.findById("aplose.framework.security.jwt.secretKey");
        // if(configOptional.isEmpty()){
        //     config = new Config("aplose.framework.security.jwt.secretKey", jwtSecretKey);
        //     configRepository.save(config);
        //     allKeys.remove(config.getConfigKey());
        //     configurations.put(config.getConfigKey(), config);
        // }else{
        //     allKeys.remove(configOptional.get().getConfigKey());
        // }
        // //stripe api key
        // configOptional = configRepository.findById("stripe.api.key");
        // if(configOptional.isEmpty()){
        //     config = new Config("stripe.api.key", stripeApiKey);
        //     configRepository.save(config);
        //     allKeys.remove(config.getConfigKey());
        //     configurations.put(config.getConfigKey(), config);
        // }else{
        //     allKeys.remove(configOptional.get().getConfigKey());
        // }

        // //stripe webhook secret
        // configOptional = configRepository.findById("stripe.webhook.secret");
        // System.err.println("ok");
        // if(configOptional.isEmpty()){
        //     config = new Config("stripe.webhook.secret", stripeWebHookSecret);
        //     configRepository.save(config);
        //     allKeys.remove(config.getConfigKey());
        //     configurations.put(config.getConfigKey(), config);
        // }else{
        //     allKeys.remove(configOptional.get().getConfigKey());
        // }

        // //google.client.id utilisé pour l'authentification grâce à Google
        // configOptional = configRepository.findById("google.client.id");
        // if(configOptional.isEmpty()){
        //     config = new Config("google.client.id", googleClientId);
        //     configRepository.save(config);
        //     allKeys.remove(config.getConfigKey());
        //     configurations.put(config.getConfigKey(), config);
        // }else{
        //     allKeys.remove(configOptional.get().getConfigKey());
        // }
        // //dolibarr.api.url utilisé pour accéder à Dolibarr
        // //on utilise le dolibarr serenitydate par défaut
        // configOptional = configRepository.findById("dolibarr.api.url");
        // if(configOptional.isEmpty()){
        //     config = new Config("dolibarr.api.url", dolibarrApiUrl);
        //     configRepository.save(config);
        //     allKeys.remove(config.getConfigKey());
        //     configurations.put(config.getConfigKey(), config);
        // }else{
        //     allKeys.remove(configOptional.get().getConfigKey());
        // }
        // //dolibarr.user.api.key utilisé pour accéder à Dolibarr
        // //on utilise le dolibarr serenitydate par défaut
        // configOptional = configRepository.findById("dolibarr.user.api.key");
        // if(configOptional.isEmpty()){
        //     config = new Config("dolibarr.user.api.key", dolibarrUserApiKey);
        //     configRepository.save(config);
        //     allKeys.remove(config.getConfigKey());
        //     configurations.put(config.getConfigKey(), config);
        // }else{
        //     allKeys.remove(configOptional.get().getConfigKey());
        // }
        // //appRootUrl utilisé pour les liens stripe
        // configOptional = configRepository.findById("app.root.url");
        // if(configOptional.isEmpty()){
        //     config = new Config("app.root.url", appRootUrl);
        //     configRepository.save(config);
        //     allKeys.remove(config.getConfigKey());
        //     configurations.put(config.getConfigKey(), config);
        // }else{
        //     allKeys.remove(configOptional.get().getConfigKey());
        // }
        // //aploseframework.backend.root.url utilisé pour les liens envoyés par email
        // configOptional = configRepository.findById("aploseframework.backend.root.url");
        // if(configOptional.isEmpty()){
        //     config = new Config("aploseframework.backend.root.url", "http://localhost:8087");        
        //     configRepository.save(config);
        //     allKeys.remove(config.getConfigKey());
        //     configurations.put(config.getConfigKey(), config);
        // }else{
        //     allKeys.remove(configOptional.get().getConfigKey());
        // }
        // //aploseframework.mail.fromEmail utilisé pour les envoi d'email
        // configOptional = configRepository.findById("aploseframework.mail.fromEmail");
        // if(configOptional.isEmpty()){
        //     config = new Config("aploseframework.mail.fromEmail", "noreply@aplose.fr");
        //     configRepository.save(config);
        //     allKeys.remove(config.getConfigKey());
        //     configurations.put(config.getConfigKey(), config);
        // }else{
        //     allKeys.remove(configOptional.get().getConfigKey());
        // }
        // // for(String keyToDelete:allKeys){
        // //     configRepository.deleteById(keyToDelete);
        // // }

         // temps de validité, en seconde, du code d'activation de compte lors d'un enregistrement d'un utilisateur 
         this.setConfig("spring.user-account.second-to-activate-account", secondToActivateAccount);

         // mot de passe par defaut du super admin
         this.setConfig("aplose.framework.superAdmin.defaultPassword", superAdminDefaultPassword);
 
         // phrase secrete pour le Json Web Token d'authentification
         this.setConfig("aplose.framework.security.jwt.secretKey", jwtSecretKey);
 
         //stripe api key
         this.setConfig("stripe.api.key", stripeApiKey);
 
         //stripe webhook secret
         this.setConfig("stripe.webhook.secret", stripeWebHookSecret);
 
         // google.client.id utilisé pour l'authentification grâce à Google
         this.setConfig("google.client.id", googleClientId);
 
         //dolibarr.api.url utilisé pour accéder à Dolibarr
         //on utilise le dolibarr serenitydate par défaut
         this.setConfig("dolibarr.api.url", dolibarrApiUrl);
 
         //dolibarr.user.api.key utilisé pour accéder à Dolibarr
         //on utilise le dolibarr serenitydate par défaut
         this.setConfig("dolibarr.user.api.key", dolibarrUserApiKey);
 
         //appRootUrl utilisé pour les liens stripe
         this.setConfig("app.root.url", appRootUrl);
 
         //aploseframework.backend.root.url utilisé pour les liens envoyés par email
         this.setConfig("aploseframework.backend.root.url", "http://localhost:8087");
         
         //aploseframework.mail.fromEmail utilisé pour les envoi d'email
         this.setConfig("aploseframework.mail.fromEmail", "noreply@aplose.fr");
         
         this.setConfig("vizulive.server.url", vizuliveServerUrl);
         this.setConfig("vizulive.admin.id", vizuliveAdminId);
         this.setConfig("vizulive.admin.user", vizuliveAdminUser);
         this.setConfig("vizulive.admin.password", vizuliveAdminPassword);
 
         this.setConfig("spring.mail.username", springMailUsername);
         this.setConfig("spring.mail.password", springMailPassword);
    }

    private void setConfig(String key, String value){
        Optional<Config> configOptional = configRepository.findById(key);
        if(configOptional.isEmpty()){
            Config config = new Config(key, value);
            configRepository.save(config);
            // allKeys.remove(config.getConfigKey());
            this.configurations.put(config.getConfigKey(), config);
        }else{
            // allKeys.remove(configOptional.get().getConfigKey());
        }
    }

    private void setConfig(String key, Long value){
        Optional<Config> configOptional = configRepository.findById(key);
        if(configOptional.isEmpty()){
            Config config = new Config(key, value);
            configRepository.save(config);
            // allKeys.remove(config.getConfigKey());
            this.configurations.put(config.getConfigKey(), config);
        }else{
            // allKeys.remove(configOptional.get().getConfigKey());
        }
    }

    private void setConfig(String key, Boolean value){
        Optional<Config> configOptional = configRepository.findById(key);
        if(configOptional.isEmpty()){
            Config config = new Config(key, value);
            configRepository.save(config);
            // allKeys.remove(config.getConfigKey());
            this.configurations.put(config.getConfigKey(), config);
        }else{
            // allKeys.remove(configOptional.get().getConfigKey());
        }
    }

    private void setConfig(String key, Integer value){
        Optional<Config> configOptional = configRepository.findById(key);
        if(configOptional.isEmpty()){
            Config config = new Config(key, value);
            configRepository.save(config);
            // allKeys.remove(config.getConfigKey());
            this.configurations.put(config.getConfigKey(), config);
        }else{
            // allKeys.remove(configOptional.get().getConfigKey());
        }
    }

    private void setConfig(String key, Float value){
        Optional<Config> configOptional = configRepository.findById(key);
        if(configOptional.isEmpty()){
            Config config = new Config(key, value);
            configRepository.save(config);
            // allKeys.remove(config.getConfigKey());
            this.configurations.put(config.getConfigKey(), config);
        }else{
            // allKeys.remove(configOptional.get().getConfigKey());
        }
    }

    private void setConfig(String key, Instant value){
        Optional<Config> configOptional = configRepository.findById(key);
        if(configOptional.isEmpty()){
            Config config = new Config(key, value);
            configRepository.save(config);
            // allKeys.remove(config.getConfigKey());
            this.configurations.put(config.getConfigKey(), config);
        }else{
            // allKeys.remove(configOptional.get().getConfigKey());
        }
    }

    private void setConfig(String key, Double value){
        Optional<Config> configOptional = configRepository.findById(key);
        if(configOptional.isEmpty()){
            Config config = new Config(key, value);
            configRepository.save(config);
            // allKeys.remove(config.getConfigKey());
            this.configurations.put(config.getConfigKey(), config);
        }else{
            // allKeys.remove(configOptional.get().getConfigKey());
        }
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
    //    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public void createOrUpdate(Config config){
        configRepository.save(config);
        configurations.put(config.getConfigKey(), config);
    }
    public Optional<Config> getConfig(String key){
        return configRepository.findById(key);
    }
    public List<Config> getAllConfigs(){
        return configRepository.findAll();
    }
}
