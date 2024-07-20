/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.service;

import com.aplose.aploseframework.model.Config;
import com.aplose.aploseframework.repository.ConfigRepository;
import jakarta.annotation.PostConstruct;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author oandrade
 */
@Service
public class ConfigService {
    @Autowired
    ConfigRepository configRepository;
    
    @PostConstruct    
    private void init(){
        List<String> allKeys = configRepository.getAllKeys();
        //on va voir si les config existent sinon on les crée à la volée...
        Optional<Config> configOptional;
        Config config=null;
        //currency utilisé pour quoter les symbols
/*        configOptional = configRepository.findById("currency");
        if(configOptional.isEmpty()){
            config = new Config("currency", "USDT");
            configRepository.save(config);
            allKeys.remove(config.getConfigKey());
        }else{
            allKeys.remove(configOptional.get().getConfigKey());
        }
        //binanceApiKeyOfMasterBinanceAccount utilisé pour récupérer toutes les données communes
        configOptional = configRepository.findById("binanceApiKeyOfMasterBinanceAccount");
        if(configOptional.isEmpty()){
            config = new Config("binanceApiKeyOfMasterBinanceAccount", this.key);
            configRepository.save(config);
            allKeys.remove(config.getConfigKey());
        }else{
            allKeys.remove(configOptional.get().getConfigKey());
        }
        //binanceApiSecretOfMasterBinanceAccount utilisé pour récupérer toutes les données communes
        configOptional = configRepository.findById("binanceApiSecretOfMasterBinanceAccount");
        if(configOptional.isEmpty()){
            config = new Config("binanceApiSecretOfMasterBinanceAccount", this.secret);
            configRepository.save(config);
            allKeys.remove(config.getConfigKey());
        }else{
            allKeys.remove(configOptional.get().getConfigKey());
        }
        //maxAssetOfPorfolio représente le coeff max d'un asset dans le portefeuille 0.05 max au départ soit 5%
        configOptional = configRepository.findById("maxAssetOfPorfolio");
        if(configOptional.isEmpty()){
            config = new Config("maxAssetOfPorfolio", 0.05);
            configRepository.save(config);
            allKeys.remove(config.getConfigKey());
        }else{
            allKeys.remove(configOptional.get().getConfigKey());
        }
        //targetPriceCoef représente le multiplicateur de prix pour la vente
        configOptional = configRepository.findById("targetPriceCoef");
        if(configOptional.isEmpty()){
            config = new Config("targetPriceCoef", 1.004F);
            configRepository.save(config);
            allKeys.remove(config.getConfigKey());
        }else{
            allKeys.remove(configOptional.get().getConfigKey());
        }
        //fakingBuySell permet d'activer ou non l'achat et la vente réelle du bot TRUE par défaut
        configOptional = configRepository.findById("fakingBuySell");
        if(configOptional.isEmpty()){
            config = new Config("fakingBuySell", Boolean.TRUE);
             configRepository.save(config);
            allKeys.remove(config.getConfigKey());
        }else{
            allKeys.remove(configOptional.get().getConfigKey());
        }
        //traceOn utilisé pour récupérer afficher des traces supplémentaires
        configOptional = configRepository.findById("traceOn");
        if(configOptional.isEmpty()){
            config = new Config("traceOn", Boolean.TRUE);
            configRepository.save(config);
            allKeys.remove(config.getConfigKey());
        }else{
            allKeys.remove(configOptional.get().getConfigKey());
        }
        //nbDaysBeforeCancel représente le nombre de jours avant suppression d'un ordre trop vieux et vente au marché
        configOptional = configRepository.findById("nbDaysBeforeCancel");
        if(configOptional.isEmpty()){
            config = new Config("nbDaysBeforeCancel", 4);
             configRepository.save(config);
            allKeys.remove(config.getConfigKey());
        }else{
            allKeys.remove(configOptional.get().getConfigKey());
        }
        //volumeOfSymbolToFollowInBTC représente le volume de trading quotidien en BTC d'un symbol pour qu'on le prenne en compte.
        configOptional = configRepository.findById("volumeOfSymbolToFollowInBTC");
        if(configOptional.isEmpty()){
            config = new Config("volumeOfSymbolToFollowInBTC", 20);
            configRepository.save(config);
            allKeys.remove(config.getConfigKey());
        }else{
            allKeys.remove(configOptional.get().getConfigKey());
        }
        //minNumberOfDayTrades représente le nombre minimum de trades pour qu'un symbol soit suivi.
        configOptional = configRepository.findById("minNumberOfDayTrades");
        if(configOptional.isEmpty()){
            config = new Config("minNumberOfDayTrades", 1000);
             configRepository.save(config);
            allKeys.remove(config.getConfigKey());
        }else{
            allKeys.remove(configOptional.get().getConfigKey());
        }
        //numberOfAverageDays : nombre de jours pour la moyenne mobile observée
        configOptional = configRepository.findById("numberOfAverageDays");
        if(configOptional.isEmpty()){
            config = new Config("numberOfAverageDays", 4);
            configRepository.save(config);
            allKeys.remove(config.getConfigKey());
        }else{
            allKeys.remove(configOptional.get().getConfigKey());
        }
        //numberOfCurrencySavings : nombre d'unité de currency que l'on garde afin de pouvoir investir en BTC sur Binance EARN par exemple
        // uniquement pour le master account
        configOptional = configRepository.findById("numberOfCurrencySavings");
        if(configOptional.isEmpty()){
            config = new Config("numberOfCurrencySavings", 1);
            configRepository.save(config);
            allKeys.remove(config.getConfigKey());
        }else{
            allKeys.remove(configOptional.get().getConfigKey());
        }
        //Ajout pour fonctionnement Front RDU
        configOptional = configRepository.findById("sponsorshipLink");
        if(configOptional.isEmpty()){
            config = new Config("sponsorshipLink", "https://accounts.binance.com/register?ref=385030280");
            configRepository.save(config);
            allKeys.remove(config.getConfigKey());
        }else{
            allKeys.remove(configOptional.get().getConfigKey());
        }
        configOptional = configRepository.findById("apiTutorial");
        if(configOptional.isEmpty()){
            config = new Config("apiTutorial", "https://www.binance.com/fr/support/faq/how-to-create-api-360002502072");
            configRepository.save(config);
            allKeys.remove(config.getConfigKey());
        }else{
            allKeys.remove(configOptional.get().getConfigKey());
        }
        configOptional = configRepository.findById("cryptoEcolo");
        if(configOptional.isEmpty()){
            config = new Config("cryptoEcolo", "https://www.cryptoecolo.fr/");
            configRepository.save(config);
            allKeys.remove(config.getConfigKey());
        }else{
            allKeys.remove(configOptional.get().getConfigKey());
        }
        configOptional = configRepository.findById("botIp");
        if(configOptional.isEmpty()){
            config = new Config("botIp", "51.75.254.2");
            configRepository.save(config);
            allKeys.remove(config.getConfigKey());
        }else{
            allKeys.remove(configOptional.get().getConfigKey());
        }        
        //SUPPRESSIONS DES RESTANTS : ANCIENNES CONFIG PLUS UTILISEES FAIRE LES NOUVELLES AVANT...
        for(String keyToDelete:allKeys){
            configRepository.deleteById(keyToDelete);
        }*/
    }

    public String getStringConfig(String key){
        Optional<Config> result = configRepository.findById(key);
        return result.get().getStringValue();
    }
    
    public Integer getIntegerConfig(String key){
        Optional<Config> result = configRepository.findById(key);
        return result.get().getIntegerValue();
    }
    public Long getLongConfig(String key){
        Optional<Config> result = configRepository.findById(key);
        return result.get().getLongValue();
    }
    public Float getFloatConfig(String key){
        Optional<Config> result = configRepository.findById(key);
        return result.get().getFloatValue();
    }
    public Instant getInstantConfig(String key){
        Optional<Config> result = configRepository.findById(key);
        return result.get().getInstantValue();
    }
    
    public Double getDoubleConfig(String key){
        Optional<Config> result = configRepository.findById(key);
        return result.get().getDoubleValue();
    }
    
    public Boolean getBooleanConfig(String key){
        Optional<Config> result = configRepository.findById(key);
        return result.get().getBooleanValue();
    }    
    
}
