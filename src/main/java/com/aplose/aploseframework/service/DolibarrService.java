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
import com.aplose.aploseframework.model.dolibarr.AgendaEvent;
import com.aplose.aploseframework.model.dolibarr.BankAccount;
import com.aplose.aploseframework.model.dolibarr.Category;
import com.aplose.aploseframework.model.dolibarr.Contact;
import com.aplose.aploseframework.model.dolibarr.Contract;
import com.aplose.aploseframework.model.dolibarr.Document;
import com.aplose.aploseframework.model.dolibarr.ThirdParty;
import com.aplose.aploseframework.model.dolibarr.DolibarrObject;
import com.aplose.aploseframework.model.dolibarr.ExpenseReport;
import com.aplose.aploseframework.model.dolibarr.Invoice;
import com.aplose.aploseframework.model.dolibarr.Order;
import com.aplose.aploseframework.model.dolibarr.Product;
import com.aplose.aploseframework.model.dolibarr.Project;
import com.aplose.aploseframework.model.dolibarr.Proposal;
import com.aplose.aploseframework.model.dolibarr.StockMovement;
import com.aplose.aploseframework.model.dolibarr.SupplierInvoice;
import com.aplose.aploseframework.model.dolibarr.SupplierOrder;
import com.aplose.aploseframework.model.dolibarr.Task;
import com.aplose.aploseframework.model.dolibarr.Ticket;
import com.aplose.aploseframework.model.dolibarr.User;
import com.aplose.aploseframework.model.dolibarr.Warehouse;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    private final Map<String, Class<? extends DolibarrObject[]>> dolibarrObjectTypes = new HashMap<>();
    private RestClient restClient = RestClient.create();

    @PostConstruct
    private void init() {
        //dictionnary types
        dictionaryTypes.put("civilities", Civility[].class);
        dictionaryTypes.put("countries", Country[].class);
        dictionaryTypes.put("currencies", Currency[].class);
        dictionaryTypes.put("event_types", EventType[].class);
        dictionaryTypes.put("legal_form", LegalForm[].class);
        dictionaryTypes.put("payment_types", PaymentType[].class);
        dictionaryTypes.put("regions", Region[].class);
        dictionaryTypes.put("shipping_methods", ShippingMethod[].class);
        dictionaryTypes.put("staff", Staff[].class);
        dictionaryTypes.put("states", State[].class);
        dictionaryTypes.put("ticket_categories", TicketCategory[].class);
        dictionaryTypes.put("ticket_severities", TicketSeverity[].class);
        dictionaryTypes.put("ticket_types", TicketType[].class);
        dictionaryTypes.put("units", Unit[].class);
        //DolibarrObject types
        dolibarrObjectTypes.put("agendaevents", AgendaEvent[].class);
        dolibarrObjectTypes.put("bankaccounts", BankAccount[].class);
        dolibarrObjectTypes.put("categories", Category[].class);
        dolibarrObjectTypes.put("contacts", Contact[].class);
        dolibarrObjectTypes.put("contracts", Contract[].class);
        dolibarrObjectTypes.put("documents", Document[].class);
        dolibarrObjectTypes.put("expensereports", ExpenseReport[].class);
        dolibarrObjectTypes.put("invoices", Invoice[].class);
        dolibarrObjectTypes.put("orders", Order[].class);
        dolibarrObjectTypes.put("products", Product[].class);
        dolibarrObjectTypes.put("projects", Project[].class);
        dolibarrObjectTypes.put("proposals", Proposal[].class);
        dolibarrObjectTypes.put("stockmovements", StockMovement[].class);
        dolibarrObjectTypes.put("supplierinvoices", SupplierInvoice[].class);
        dolibarrObjectTypes.put("supplierorders", SupplierOrder[].class);
        dolibarrObjectTypes.put("tasks", Task[].class);
        dolibarrObjectTypes.put("thirdparties", ThirdParty[].class);
        dolibarrObjectTypes.put("tickets", Ticket[].class);
        dolibarrObjectTypes.put("users", User[].class);
        dolibarrObjectTypes.put("warehouses", Warehouse[].class);


        dolibarrApiUrl = configService.getStringConfig("dolibarr.api.url");
        dolibarrUserApiKey=configService.getStringConfig("dolibarr.user.api.key");
/*        ThirdParty t= new ThirdParty();
        t.setEntity(1);
        t.setName("Test post from AploseFramework");
        t.setIdprof1("123456789");
        t.setClient(3);
        t.setProspect(1);
        t.setFournisseur(1);
        t.setCode_client("-1");
        t.setCode_fournisseur("-1");
        t.setEmail("oandrade@free.fr");
        System.out.println("ThirdParty created id : "+this.createDolibarrObject(t));
        User u = new User();
        u.setEmail("oandrade@aplose.fr");
        u.setEntity(1);
        u.setFirstname("Olivier");
        u.setLastname("ANDRADE SANCHEZ");
        u.setLogin("oandrade-"+UUID.randomUUID());
        u.setMobile("0623678421");
        u.setPassword("Oandrade01");
        System.out.println("User created id : "+this.createDolibarrObject(u));*/

    }
                 
    
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

    //CATEGORY
    public Category[] getAllCategories(String type,Map<String,String> params){
        StringBuilder sb = new StringBuilder();
        sb.append(dolibarrApiUrl+"/categories?DOLAPIKEY="+dolibarrUserApiKey).append("&type="+type).append("&limit=0");
        if (params!=null){
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        Category[] result = restClient.get()
                .uri(sb.toString())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Category[].class);
        return result;
    }
    
    /**
     * Create any DolibarrObject welformed (Thirdparty, contact, user...)
     * @return Integer The id of created Object
    */   
    public Integer createDolibarrObject(DolibarrObject dolibarrObject){
        ResponseEntity<Integer> response = restClient.post()
                .uri(dolibarrApiUrl+dolibarrObject.getEndPoint()+"?DOLAPIKEY="+dolibarrUserApiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(dolibarrObject)
                .retrieve().toEntity(Integer.class);
        return response.getBody();
    }
    /**
     * Get any Dolibarr object
     */
    public DolibarrObject[] getAll(String name, Map<String,String> params){
        StringBuilder sb = new StringBuilder();
        sb.append(dolibarrApiUrl+"/"+name+"?DOLAPIKEY="+dolibarrUserApiKey);
        if (params!=null){
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        DolibarrObject[] result = restClient.get()
                .uri(sb.toString())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(dolibarrObjectTypes.get(name));
        return result;
    }

}
