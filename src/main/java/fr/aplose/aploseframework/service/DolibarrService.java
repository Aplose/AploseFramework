package fr.aplose.aploseframework.service;


import jakarta.annotation.PostConstruct;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.databind.type.TypeFactory;

import org.springframework.core.ParameterizedTypeReference;

import fr.aplose.aploseframework.dto.ProposalLineDTO;
import fr.aplose.aploseframework.exception.DolibarrException;
import fr.aplose.aploseframework.exception.ProposalLineNotUpdatedException;
import fr.aplose.aploseframework.exception.ProposalNotFoundException;
import fr.aplose.aploseframework.exception.ProposalUpdateException;
import fr.aplose.aploseframework.exception.ProposalValidationException;
import fr.aplose.aploseframework.model.UserAccount;
import fr.aplose.aploseframework.model.dictionnary.AbstractDictionnary;
import fr.aplose.aploseframework.model.dictionnary.Civility;
import fr.aplose.aploseframework.model.dictionnary.Company;
import fr.aplose.aploseframework.model.dictionnary.Country;
import fr.aplose.aploseframework.model.dictionnary.Currency;
import fr.aplose.aploseframework.model.dictionnary.EventType;
import fr.aplose.aploseframework.model.dictionnary.LegalForm;
import fr.aplose.aploseframework.model.dictionnary.PaymentType;
import fr.aplose.aploseframework.model.dictionnary.Region;
import fr.aplose.aploseframework.model.dictionnary.ShippingMethod;
import fr.aplose.aploseframework.model.dictionnary.Staff;
import fr.aplose.aploseframework.model.dictionnary.State;
import fr.aplose.aploseframework.model.dictionnary.TicketCategory;
import fr.aplose.aploseframework.model.dictionnary.TicketSeverity;
import fr.aplose.aploseframework.model.dictionnary.TicketType;
import fr.aplose.aploseframework.model.dictionnary.Unit;
import fr.aplose.aploseframework.model.dolibarr.AgendaEvent;
import fr.aplose.aploseframework.model.dolibarr.BankAccount;
import fr.aplose.aploseframework.model.dolibarr.Category;
import fr.aplose.aploseframework.model.dolibarr.Contact;
import fr.aplose.aploseframework.model.dolibarr.Contract;
import fr.aplose.aploseframework.model.dolibarr.Document;
import fr.aplose.aploseframework.model.dolibarr.DocumentFile;
import fr.aplose.aploseframework.model.dolibarr.DolibarrObject;
import fr.aplose.aploseframework.model.dolibarr.ExpenseReport;
import fr.aplose.aploseframework.model.dolibarr.Invoice;
import fr.aplose.aploseframework.model.dolibarr.Order;
import fr.aplose.aploseframework.model.dolibarr.Product;
import fr.aplose.aploseframework.model.dolibarr.Project;
import fr.aplose.aploseframework.model.dolibarr.Proposal;
import fr.aplose.aploseframework.model.dolibarr.ProposalLine;
import fr.aplose.aploseframework.model.dolibarr.StockMovement;
import fr.aplose.aploseframework.model.dolibarr.SupplierInvoice;
import fr.aplose.aploseframework.model.dolibarr.SupplierOrder;
import fr.aplose.aploseframework.model.dolibarr.Task;
import fr.aplose.aploseframework.model.dolibarr.ThirdParty;
import fr.aplose.aploseframework.model.dolibarr.Ticket;
import fr.aplose.aploseframework.model.dolibarr.User;
import fr.aplose.aploseframework.model.dolibarr.Warehouse;
import fr.aplose.aploseframework.model.dolibarr.DolibarrPaginatedResponse;

/**
 *
 * @author oandrade
 */
@Service
public class DolibarrService {

    @Autowired
    private ConfigService configService;
    @Autowired
    private RestClient restClient;
    @Autowired
    private UserAccountService _userAccountService;

    private String dolibarrApiUrl;
    private String dolibarrUserApiKey;
    private final Map<String, Class<? extends AbstractDictionnary[]>> dictionaryTypes = new HashMap<>();
    private final Map<String, Class<? extends DolibarrObject[]>> dolibarrObjectArrayTypes = new HashMap<>();
    private final Map<String, Class<? extends DolibarrObject>> dolibarrObjectTypes = new HashMap<>();
    private final Map<String,String> dolibarrObjectRouteNameByType = new HashMap<>();


    public DolibarrService(ConfigService configService, RestClient restClient){
        this.configService = configService;
        this.restClient = restClient;
    }


    @PostConstruct
    private void init() {
        
        //types and names
        dolibarrObjectRouteNameByType.put("product", "products");
        dolibarrObjectRouteNameByType.put("category", "categories");
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
        dolibarrObjectArrayTypes.put("agendaevents", AgendaEvent[].class);
        dolibarrObjectArrayTypes.put("bankaccounts", BankAccount[].class);
        dolibarrObjectArrayTypes.put("categories", Category[].class);
        dolibarrObjectArrayTypes.put("contacts", Contact[].class);
        dolibarrObjectArrayTypes.put("contracts", Contract[].class);
        dolibarrObjectArrayTypes.put("documents", Document[].class);
        dolibarrObjectArrayTypes.put("expensereports", ExpenseReport[].class);
        dolibarrObjectArrayTypes.put("invoices", Invoice[].class);
        dolibarrObjectArrayTypes.put("orders", Order[].class);
        dolibarrObjectArrayTypes.put("products", Product[].class);
        dolibarrObjectArrayTypes.put("product", Product[].class);
        dolibarrObjectArrayTypes.put("projects", Project[].class);
        dolibarrObjectArrayTypes.put("proposals", Proposal[].class);
        dolibarrObjectArrayTypes.put("stockmovements", StockMovement[].class);
        dolibarrObjectArrayTypes.put("supplierinvoices", SupplierInvoice[].class);
        dolibarrObjectArrayTypes.put("supplierorders", SupplierOrder[].class);
        dolibarrObjectArrayTypes.put("tasks", Task[].class);
        dolibarrObjectArrayTypes.put("thirdparties", ThirdParty[].class);
        dolibarrObjectArrayTypes.put("tickets", Ticket[].class);
        dolibarrObjectArrayTypes.put("users", User[].class);
        dolibarrObjectArrayTypes.put("warehouses", Warehouse[].class);

        dolibarrObjectTypes.put("agendaevents", AgendaEvent.class);
        dolibarrObjectTypes.put("bankaccounts", BankAccount.class);
        dolibarrObjectTypes.put("categories", Category.class);
        dolibarrObjectTypes.put("contacts", Contact.class);
        dolibarrObjectTypes.put("contracts", Contract.class);
        dolibarrObjectTypes.put("documents", Document.class);
        dolibarrObjectTypes.put("expensereports", ExpenseReport.class);
        dolibarrObjectTypes.put("invoices", Invoice.class);
        dolibarrObjectTypes.put("orders", Order.class);
        dolibarrObjectTypes.put("products", Product.class);
        dolibarrObjectTypes.put("product", Product.class);
        dolibarrObjectTypes.put("projects", Project.class);
        dolibarrObjectTypes.put("proposals", Proposal.class);
        dolibarrObjectTypes.put("stockmovements", StockMovement.class);
        dolibarrObjectTypes.put("supplierinvoices", SupplierInvoice.class);
        dolibarrObjectTypes.put("supplierorders", SupplierOrder.class);
        dolibarrObjectTypes.put("tasks", Task.class);
        dolibarrObjectTypes.put("thirdparties", ThirdParty.class);
        dolibarrObjectTypes.put("tickets", Ticket.class);
        dolibarrObjectTypes.put("users", User.class);
        dolibarrObjectTypes.put("warehouses", Warehouse.class);


        dolibarrApiUrl = configService.getStringConfig("dolibarr.api.url");
        dolibarrUserApiKey=configService.getStringConfig("dolibarr.api.userkey");
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
        sb.append(dolibarrApiUrl+"/setup/dictionary/"+name+"?DOLAPIKEY="+dolibarrUserApiKey+"&limit=0");
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


    //CATEGORY
    public Category[] getAllCategories(String type,Map<String,String> params){
        StringBuilder sb = new StringBuilder();
        sb.append(dolibarrApiUrl+"/categories?DOLAPIKEY="+dolibarrUserApiKey).append("&type="+type).append("&limit=0");
        if (params!=null){
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        Category[] result = new Category[0];
        try{
            result = restClient.get()
                .uri(sb.toString())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Category[].class);
        }catch(RestClientException rce){
            rce.printStackTrace();
            System.out.println("No category for type "+type);
        }
        return result;
    }
    public Page<DolibarrObject> getAllObjectsForCategory(String idCat, String type, PageRequest pageRequest) {
        String url = "";
        if(type.equals("product")){
            url = dolibarrApiUrl + "/products?DOLAPIKEY=" + dolibarrUserApiKey 
                + "&type=" + type 
                + "&category=" + idCat
                + "&limit=" + pageRequest.getPageSize() 
                + "&page=" + pageRequest.getPageNumber()
                + "&pagination_data=1";
        } else {
            url = dolibarrApiUrl + "/categories/" + idCat + "/objects?DOLAPIKEY=" + dolibarrUserApiKey 
                + "&type=" + type 
                + "&limit=" + pageRequest.getPageSize() 
                + "&page=" + pageRequest.getPageNumber()
                + "&pagination_data=1";
        }

        try {
            Class<? extends DolibarrObject> objectType = dolibarrObjectTypes.get(type);
            ParameterizedTypeReference<DolibarrPaginatedResponse<? extends DolibarrObject>> responseType = 
                ParameterizedTypeReference.forType(
                    TypeFactory.defaultInstance().constructParametricType(
                        DolibarrPaginatedResponse.class,
                        objectType
                    )
                );

            DolibarrPaginatedResponse<? extends DolibarrObject> response = restClient.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(responseType);

            if (response != null && response.getData() != null) {
                return new PageImpl<>(
                    Arrays.asList(response.getData()),
                    pageRequest,
                    response.getPagination().getTotal()
                );
            }
            
            return new PageImpl<>(Collections.emptyList(), pageRequest, 0);
        } catch (RestClientException rce) {
            rce.printStackTrace();
            System.out.println("No objects of type " + type + " for category " + idCat);
            return new PageImpl<>(Collections.emptyList(), pageRequest, 0);
        }
    }
    //PRODUCTS
    public Category[] getCategoriesForProduct(String id) {
        Category[] result = new Category[0];
        String uri = dolibarrApiUrl+"/products/"+id+"/categories?DOLAPIKEY="+dolibarrUserApiKey;
        try{
            result = restClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Category[].class);
        }catch(RestClientException rce){
            rce.printStackTrace();
            System.out.println("No category for product "+id);
        }
        return result;
    }

    
    //DOLIBARROBJECT
    
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
        DolibarrObject[] result = new DolibarrObject[0];
        StringBuilder sb = new StringBuilder();
        sb.append(dolibarrApiUrl+"/"+name+"?DOLAPIKEY="+dolibarrUserApiKey+"&limit=0");
        if (params!=null){
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        try{
            result = restClient.get()
                    .uri(sb.toString())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(dolibarrObjectArrayTypes.get(name));
        }catch(RestClientException rce){
            rce.printStackTrace();
        }
        return result;
    }


    /**
     * Get one Dolibarr object by id
     */
    public DolibarrObject getById(String name, Integer id){
        try{
            return this.restClient.get()
                .uri(dolibarrApiUrl + "/" + name + "/" + String.valueOf(id) + "?DOLAPIKEY=" + dolibarrUserApiKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(dolibarrObjectTypes.get(name));
        }
        catch(RestClientException e){
            System.err.println("\n\n\tRestClientException DolibarrService.getById(): ");
            e.printStackTrace();
            return null;
        }
    }


    public void update(String name, Integer id, DolibarrObject entity){
        this.restClient.put()
            .uri(dolibarrApiUrl + "/" + name + "/" + id + "?DOLAPIKEY=" + dolibarrUserApiKey)
            .contentType(MediaType.APPLICATION_JSON)
            .body(entity)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .body(dolibarrObjectTypes.get(name))
            ;
    }
    

    public Integer createExternalUser(Integer contactId, String login, String password){
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("login", login);
        map.put("password", password);

        return this.restClient.post()
            .uri(dolibarrApiUrl + "/contacts/" + contactId + "/createUser?DOLAPIKEY=" + dolibarrUserApiKey)
            .contentType(MediaType.APPLICATION_JSON)
            .body(map)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .body(Integer.class);
    }
    
    //DOCUMENTS
    public DocumentFile getImage(String modulePart, String id){
        DocumentFile documentFile=new DocumentFile();
        //d'abord on va cherche le document de type image...
        Map<String,String> params = new HashMap<>();
        params.put("modulepart", modulePart);
        params.put("id", id);
        DolibarrObject[] dolibarrObjects = getAll("documents", params);
        Document firstImage=null;
        for (DolibarrObject dolibarrObject : dolibarrObjects) {
            Document document = (Document) dolibarrObject;
            if(document.getType().equals("file")&&(document.getName().endsWith(".png")||document.getName().endsWith(".jpg")||document.getName().endsWith(".jpeg"))){
                firstImage = document;
                break;
            }
        }
        if(firstImage!=null){            
            documentFile = getFile(firstImage, modulePart);
        }else{
            documentFile.setContentType(MediaType.TEXT_PLAIN_VALUE);
            documentFile.setContent("No file for "+modulePart+" id : "+id);
        }
        return documentFile;
    }

    public DocumentFile getFile(Document document, String modulePart){
        return getFile(document.getLevel1name(), document.getRelativename(), modulePart);
    }
    
    public DocumentFile getFile(String level1Name,String relativeName, String modulePart){
        String file = URLEncoder.encode(level1Name, StandardCharsets.UTF_8)+"/"+URLEncoder.encode(relativeName, StandardCharsets.UTF_8);        
        String url = dolibarrApiUrl+"/documents/download?DOLAPIKEY="+dolibarrUserApiKey+"&modulepart="+modulePart+"&original_file="+file;
        DocumentFile documentFile = new DocumentFile();        
        try{
            documentFile = restClient.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(DocumentFile.class);
        }catch(RestClientException rce){     
            rce.printStackTrace();
        }
        return documentFile;
    }


 /*
     * Créer un objet ProposalLine correctement
     */
    public ProposalLine createProposalLine(ProposalLineDTO proposalLineDTO){
        Product product = (Product)this.getById(Product.NAME, proposalLineDTO.getProductId());
        // création d'un ProposalLine
        ProposalLine proposalLine = new ProposalLine();
        // assigner la quantité
        proposalLine.setQty(proposalLineDTO.getQuantity());
        // assigner l'id du produit
        proposalLine.setFk_product(proposalLineDTO.getProductId());
        // assigner le type de produit (0=service, 1=produit)
        proposalLine.setProduct_type(proposalLineDTO.getProduct_type());
        proposalLine.setSubprice(product.getPrice());
        proposalLine.setTva_tx(product.getTva_tx());
        return proposalLine;
    }
    /*
    * Ajoute une ligne à un devis (Proposal) ou crée un nouveau devis si nécessaire.
    */
    public Integer addProposalLine(UserAccount userAccount, ProposalLine proposalLine){
        Integer proposalId;
        try{
            // Récupération de l'ID du devis en cours de l'utilisateur
            proposalId = userAccount.getDolibarrPendingProposalId();
        }
        catch(NullPointerException e){
            proposalId = null;
        }
        //on vérifie si la propale existe toujorus sur dolibarr car elle peut avoir été supprimée par un autre utilisateur
        try{
            this.getProposalById(proposalId);
        }catch(ProposalNotFoundException e){
            userAccount.setDolibarrPendingProposalId(null);
            this._userAccountService.update(userAccount);
            proposalId = null;
        }

        // Si l'utilisateur n'a pas encore de devis associé
        if(proposalId == null){
            // Création d'un nouveau devis (Proposal)
            Proposal proposal = new Proposal();
            // Association du tiers (ThirdParty) au devis
            proposal.setSocid(userAccount.getDolibarrThirdPartyId());
            // Assignation de la date de création au devis
            proposal.setDatep(Instant.now().toString());
            proposal.setStatus(0);
            // Enregistrement du nouveau devis dans Dolibarr et récupération de son ID
            proposalId = this.createDolibarrObject(proposal);
            // Association de l'ID du devis à l'utilisateur
            userAccount.setDolibarrPendingProposalId(proposalId);
            // Sauvegarde de la mise à jour de l'utilisateur
            this._userAccountService.update(userAccount);
        }

        try {
            // Envoi de la requête pour ajouter une ligne au devis et retour de l'ID de la nouvelle ligne
            return this.restClient.post()
                .uri(dolibarrApiUrl + "/proposals/" + proposalId + "/line?DOLAPIKEY=" + dolibarrUserApiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(proposalLine)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Integer.class);
        } catch (RestClientException e) {
            throw new DolibarrException("error when adding a line to the Proposal");
        }
    }
    /*
     * Modifier une ligne du devis en cours
     */
    public void updateProposalLine(Integer proposalId, ProposalLine lineToUpdate){
        System.err.println("\n\n\n\tdto:\n"+"/proposals/"+proposalId+"/lines/"+lineToUpdate.getRowid()+"\nquantity: "+ lineToUpdate.getQty() +"\n\n\n");
        Proposal proposal =  this.restClient.put()
            .uri(dolibarrApiUrl+"/proposals/"+proposalId+"/lines/"+lineToUpdate.getRowid()+"?DOLAPIKEY="+dolibarrUserApiKey)
            .contentType(MediaType.APPLICATION_JSON)
            .body(lineToUpdate)
            .retrieve()
            .body(Proposal.class);
        if(proposal.getId() < 1){
            throw new ProposalLineNotUpdatedException("Error during the proposal line update, proposal line not updated.");
        }
    }


    /*
     * Supprimer une ligne du devis en cours 
     */
    public void deleteProposalLine(Integer proposalId, String proposalLineId){
        Integer proposalIdResult = this.restClient.delete()
        .uri(dolibarrApiUrl+"/proposals/"+proposalId+"/lines/"+proposalLineId+"?DOLAPIKEY="+dolibarrUserApiKey)
        .retrieve()
        .body(Proposal.class)
        .getId();
        if(proposalIdResult < 1){
            throw new ProposalLineNotUpdatedException("Error during the proposal line deletation, proposal line not deleted.");
        }
    }

    /**
     * Obtenir le devis en cours et récupérer des information des produits contenus dans le devis
     */
    public Proposal getProposalById(Integer proposalId) throws ProposalNotFoundException{
        Proposal proposal = (Proposal)this.getById(Proposal.NAME, proposalId);
        if(proposal == null){
            throw new ProposalNotFoundException("Proposal not found");
        }
        proposal.getLines().forEach((ProposalLine line) -> {
            Product product = (Product)this.getById(Product.NAME, line.getFk_product());
            Category[] category = this.getCategoriesForProduct(product.getId().toString());
            line.setProductLabel(product.getLabel());
            line.setProductImageSrc(category[0].getPhoto());
        });
        return proposal;
    }

    /*
     * Valider le devis (brouillon) en cours
     */
    public Integer validatePendingProposal(UserAccount userAccount){
        Proposal proposal = this.restClient.post()
        .uri(dolibarrApiUrl+"/proposals/"+userAccount.getDolibarrPendingProposalId()+"/validate?DOLAPIKEY="+dolibarrUserApiKey)
        .contentType(MediaType.APPLICATION_JSON)
        .body(Map.of("notrigger", 0))
        .retrieve()
        .body(Proposal.class)
        ;        
        if(proposal == null || proposal.getId() < 1){
            throw new ProposalValidationException("Error during the Dolibarr request of proposal validation");
        }
        //on doit générer le pdf du devis validé
        this.generateProposalPdf(proposal);
        userAccount.setDolibarrPendingProposalId(null);
        this._userAccountService.update(userAccount);
        this.sendPropalByMail(proposal.getId());
        return proposal.getId();
    }

    /**
     * Générer le pdf du devis
     */
    public void generateProposalPdf(Proposal proposal){
        //on doit générer le pdf du devis validé pour cela on utilise le endpoint buildoc
        this.restClient.put()
        .uri(dolibarrApiUrl+"/documents/builddoc?DOLAPIKEY="+dolibarrUserApiKey)
        .contentType(MediaType.APPLICATION_JSON)
        .body(Map.of("modulepart", "proposals", "original_file", proposal.getRef()+"/"+proposal.getRef()+".pdf"))
        .retrieve()
        .body(String.class)
        ;
    }

    /**
     * Mets à jour le devis
     */
    public void updateProposal(Proposal proposal) throws ProposalUpdateException{
        Proposal outputProposal= this.restClient.put()
        .uri(dolibarrApiUrl+"/proposals/"+proposal.getId()+"?DOLAPIKEY="+dolibarrUserApiKey)
        .contentType(MediaType.APPLICATION_JSON)
        .body(proposal)
        .retrieve()
        .body(Proposal.class)
        ;
        if(outputProposal == null){
            throw new ProposalUpdateException("Error during the Dolibarr request of proposal update");
        }
    }


    /*
     * Envoyer un devis par mail
     */
    public void sendPropalByMail(Integer proposalId){
        this.restClient.get()
        .uri(dolibarrApiUrl+"/cadkwote/propal?propal_id="+proposalId+"&DOLAPIKEY="+dolibarrUserApiKey)
        .retrieve()
        .body(String.class)
        ;
        
    }
}
