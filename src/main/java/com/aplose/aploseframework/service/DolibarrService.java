/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.service;


import com.aplose.aploseframework.model.DolibarrUser;
import com.aplose.aploseframework.model.Person;
import com.aplose.aploseframework.model.dictionnary.AbstractDictionnary;
import com.aplose.aploseframework.model.dictionnary.Civility;
import com.aplose.aploseframework.model.dictionnary.Company;
import com.aplose.aploseframework.model.dictionnary.Country;
import com.aplose.aploseframework.model.dictionnary.Currency;
import com.aplose.aploseframework.model.dictionnary.EventType;
import com.aplose.aploseframework.model.dictionnary.LegalForm;
import com.aplose.aploseframework.model.dictionnary.PaymentTerm;
import com.aplose.aploseframework.model.dictionnary.PaymentType;
import com.aplose.aploseframework.model.dictionnary.Region;
import com.aplose.aploseframework.model.dictionnary.ShippingMethod;
import com.aplose.aploseframework.model.dictionnary.Staff;
import com.aplose.aploseframework.model.dictionnary.State;
import com.aplose.aploseframework.model.dictionnary.TicketCategory;
import com.aplose.aploseframework.model.dictionnary.TicketSeverity;
import com.aplose.aploseframework.model.dictionnary.TicketType;
import com.aplose.aploseframework.model.dictionnary.Unit;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 *
 * @author oandrade
 */
@Service
public class DolibarrService {
    @Autowired
    private ConfigService configService;
    private String dolibarrApiUrl;
    private String dolibarrUserApiKey;
    private final Map<String, Class<? extends AbstractDictionnary[]>> dictionaryTypes = new HashMap<>();
    @PostConstruct
    private void init() {
        dictionaryTypes.put("civilities", Civility[].class);
        dictionaryTypes.put("countries", Country[].class);
        dictionaryTypes.put("currencies", Currency[].class);
        dictionaryTypes.put("event_types", EventType[].class);
        dictionaryTypes.put("legal_form", LegalForm[].class);
        dictionaryTypes.put("payment_terms", PaymentTerm[].class);
        dictionaryTypes.put("payment_types", PaymentType[].class);
        dictionaryTypes.put("regions", Region[].class);
        dictionaryTypes.put("shipping_methods", ShippingMethod[].class);
        dictionaryTypes.put("staff", Staff[].class);
        dictionaryTypes.put("states", State[].class);
        dictionaryTypes.put("ticket_categories", TicketCategory[].class);
        dictionaryTypes.put("ticket_severities", TicketSeverity[].class);
        dictionaryTypes.put("ticket_types", TicketType[].class);
        dictionaryTypes.put("units", Unit[].class);

        dolibarrApiUrl = configService.getStringConfig("dolibarr.api.url");
        dolibarrUserApiKey=configService.getStringConfig("dolibarr.user.api.key");
    }
                 
    RestClient restClient = RestClient.create();

    public String login(String login, String password){
        return restClient.get()
                .uri(dolibarrApiUrl+"/login?login="+login+"&password="+password) // possible de mettre &reset=1
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(String.class);
    }
    //COMPANY
    public Company getCompanyInfo(){
        Company company = restClient.get()
                .uri(dolibarrApiUrl+"/setup/company"+"?DOLAPIKEY="+dolibarrUserApiKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Company.class);
        return company;
    }
    //DICTIONNARY
    public AbstractDictionnary[] getDictionnary(String name, Map<String,String> params){
        StringBuilder sb = new StringBuilder();
        sb.append(dolibarrApiUrl+"/setup/dictionary/"+name+"?DOLAPIKEY="+dolibarrUserApiKey);
        if (params!=null){
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        AbstractDictionnary[] result = restClient.get()
                .uri(sb.toString())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(dictionaryTypes.get(name));
        return result;
    }


    public String createUser(Person person){

        return restClient.post()
        .uri(dolibarrApiUrl + "/users?DOLAPIKEY=" + dolibarrUserApiKey)
        .body(
            new DolibarrUser(person)
        )
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .body(String.class)
        ;
    }
    
}
