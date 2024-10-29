/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.service;

import com.aplose.aploseframework.model.Config;
import com.aplose.aploseframework.repository.ConfigRepository;
import jakarta.annotation.PostConstruct;
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
    @Value("${dolibarr.api.userkey}")
    private String dolibarrApiUserkey;
    @Value("${app.root.url}")
    private String appRootUrl;
    @Value("${stripe.webhook.account.secretkey}")
    private String stripeWebHookAccountSecretKey;
    @Value("${stripe.webhook.customer.secretkey}")
    private String stripeWebHookCustomerSecretKey;
    @Value("${stripe.webhook.identity.secretkey}")
    private String stripeWebHookIdentitySecretKey;
    @Value("${stripe.webhook.capability.secretkey}")
    private String stripeWebHookCapabilitySecretKey;


    @Value("${stripe.api.key}")
    private String stripeApiKey;
    @Value("${aplose.framework.security.jwt.secretKey}")
    private String jwtSecretKey;
    @Value("${aplose.framework.superAdmin.defaultPassword}")
    private String superAdminDefaultPassword;
    @Value("${google.client.id}")
    private String googleClientId;
    @Value("${vizulive.server.url}")
    private String vizuliveServerUrl;
    @Value("${vizulive.admin.id}")
    private String vizuliveAdminId;
    @Value("${vizulive.admin.user}")
    private String vizuliveAdminUser;
    @Value("${vizulive.admin.password}")
    private String vizuliveAdminPassword;
    @Value("${spring.mail.username}")
    private String springMailUsername;
    @Value("${spring.mail.password}")
    private String springMailPassword;
    
    private Map<String,Config> configurations = new HashMap<>();
    
    
    @PostConstruct    
    private void init(){
         // temps de validité, en seconde, du code d'activation de compte lors d'un enregistrement d'un utilisateur 
         this.setConfig("spring.user-account.second-to-activate-account", secondToActivateAccount,Boolean.FALSE);
         // mot de passe par defaut du super admin
         this.setConfig("aplose.framework.superAdmin.defaultPassword", superAdminDefaultPassword,Boolean.FALSE); 
         // phrase secrete pour le Json Web Token d'authentification
         this.setConfig("aplose.framework.security.jwt.secretKey", jwtSecretKey,Boolean.FALSE); 
         //stripe api key
         this.setConfig("stripe.api.key", stripeApiKey,Boolean.FALSE); 
         //stripe account webhook secret key
         this.setConfig("stripe.webhook.account.secretkey", stripeWebHookAccountSecretKey,Boolean.FALSE);
        //stripe customer webhook secret key
        this.setConfig("stripe.webhook.customer.secretkey", stripeWebHookCustomerSecretKey,Boolean.FALSE);
        //stripe identity webhook secret key
         this.setConfig("stripe.webhook.identity.secretkey", stripeWebHookIdentitySecretKey,Boolean.FALSE);
        //stripe capability webhook secret key
        this.setConfig("stripe.webhook.capability.secretkey", stripeWebHookCapabilitySecretKey,Boolean.FALSE);
         // google.client.id utilisé pour l'authentification grâce à Google
         this.setConfig("google.client.id", googleClientId,Boolean.FALSE);
         //dolibarr.api.url utilisé pour accéder à Dolibarr
         this.setConfig("dolibarr.api.url", dolibarrApiUrl,Boolean.FALSE); 
         //dolibarr.api.userkey utilisé pour accéder à Dolibarr
         this.setConfig("dolibarr.api.userkey", dolibarrApiUserkey,Boolean.FALSE); 
         //appRootUrl utilisé pour les liens stripe
         this.setConfig("app.root.url", appRootUrl,Boolean.TRUE); 
         //aploseframework.backend.root.url utilisé pour les liens envoyés par email
         this.setConfig("aploseframework.backend.root.url", "http://localhost:8087",Boolean.FALSE);         
         //vizulive config
         this.setConfig("vizulive.server.url", vizuliveServerUrl,Boolean.FALSE);
         this.setConfig("vizulive.admin.id", vizuliveAdminId,Boolean.FALSE);
         this.setConfig("vizulive.admin.user", vizuliveAdminUser,Boolean.FALSE);
         this.setConfig("vizulive.admin.password", vizuliveAdminPassword,Boolean.FALSE);
         //mail
         //aploseframework.mail.fromEmail utilisé pour les envoi d'email
         this.setConfig("aploseframework.mail.fromEmail", "noreply@aplose.fr",Boolean.FALSE); 
         this.setConfig("spring.mail.username", springMailUsername,Boolean.FALSE);
         this.setConfig("spring.mail.password", springMailPassword,Boolean.FALSE);
    }

    public void setConfig(String key, String value, Boolean isFrontAllowed){
        Optional<Config> configOptional = configRepository.findById(key);
        if(configOptional.isEmpty()){
            Config config = new Config(key, value);
            configRepository.save(config);
            this.configurations.put(config.getConfigKey(), config);
        }else{
            Config config = configOptional.get();
            //initialisation des nouveaux champs
            if (config.getIsFrontAllowed()==null){
                config.setIsFrontAllowed(isFrontAllowed);
                configRepository.save(config);
            }
        }
    }

    public void setConfig(String key, Long value, Boolean isFrontAllowed){
        Optional<Config> configOptional = configRepository.findById(key);
        if(configOptional.isEmpty()){
            Config config = new Config(key, value);
            configRepository.save(config);
            this.configurations.put(config.getConfigKey(), config);
        }else{
            Config config = configOptional.get();
            //initialisation des nouveaux champs
            if (config.getIsFrontAllowed()==null){
                config.setIsFrontAllowed(isFrontAllowed);
                configRepository.save(config);
            }
        }
    }

    public void setConfig(String key, Boolean value, Boolean isFrontAllowed){
        Optional<Config> configOptional = configRepository.findById(key);
        if(configOptional.isEmpty()){
            Config config = new Config(key, value);
            configRepository.save(config);
            this.configurations.put(config.getConfigKey(), config);
        }else{
            Config config = configOptional.get();
            //initialisation des nouveaux champs
            if (config.getIsFrontAllowed()==null){
                config.setIsFrontAllowed(isFrontAllowed);
                configRepository.save(config);
            }
        }
    }

    public void setConfig(String key, Integer value, Boolean isFrontAllowed){
        Optional<Config> configOptional = configRepository.findById(key);
        if(configOptional.isEmpty()){
            Config config = new Config(key, value);
            configRepository.save(config);
            this.configurations.put(config.getConfigKey(), config);
        }else{
            Config config = configOptional.get();
            //initialisation des nouveaux champs
            if (config.getIsFrontAllowed()==null){
                config.setIsFrontAllowed(isFrontAllowed);
                configRepository.save(config);
            }
        }
    }

    public void setConfig(String key, Float value, Boolean isFrontAllowed){
        Optional<Config> configOptional = configRepository.findById(key);
        if(configOptional.isEmpty()){
            Config config = new Config(key, value);
            configRepository.save(config);
            this.configurations.put(config.getConfigKey(), config);
        }else{
            Config config = configOptional.get();
            //initialisation des nouveaux champs
            if (config.getIsFrontAllowed()==null){
                config.setIsFrontAllowed(isFrontAllowed);
                configRepository.save(config);
            }
        }
    }

    public void setConfig(String key, Double value, Boolean isFrontAllowed){
        Optional<Config> configOptional = configRepository.findById(key);
        if(configOptional.isEmpty()){
            Config config = new Config(key, value);
            configRepository.save(config);
            this.configurations.put(config.getConfigKey(), config);
        }else{
            Config config = configOptional.get();
            //initialisation des nouveaux champs
            if (config.getIsFrontAllowed()==null){
                config.setIsFrontAllowed(isFrontAllowed);
                configRepository.save(config);
            }
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
