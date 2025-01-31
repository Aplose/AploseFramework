package fr.aplose.aploseframework.rest;
import fr.aplose.aploseframework.dto.ProposalLineDTO;
import fr.aplose.aploseframework.exception.DolibarrException;
import fr.aplose.aploseframework.exception.ProposalNotFoundException;
import fr.aplose.aploseframework.exception.ProposalUpdateException;
import fr.aplose.aploseframework.model.UserAccount;
import fr.aplose.aploseframework.model.dolibarr.Category;
import fr.aplose.aploseframework.model.dolibarr.Document;
import fr.aplose.aploseframework.model.dolibarr.DocumentFile;
import fr.aplose.aploseframework.model.dolibarr.DolibarrObject;
import fr.aplose.aploseframework.model.dolibarr.Proposal;
import fr.aplose.aploseframework.model.dolibarr.ProposalLine;
import fr.aplose.aploseframework.service.DolibarrService;
import fr.aplose.aploseframework.tool.TreeBuilder;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author oandrade
 */
@RestController
@CrossOrigin
@RequestMapping("/api/dolibarr")
public class DolibarrController {
    @Autowired
    DolibarrService dolibarrService;
    @GetMapping("/getAll/{name}")
    public DolibarrObject[] getAll(@PathVariable("name") String name,
            @RequestParam(required = false) Map<String, String> params) {
        return dolibarrService.getAll(name, params);
    }
    /**
     * Get file of a module
     */
    @PostMapping(value = "/document/download", produces = MediaType.APPLICATION_JSON_VALUE)
    public DocumentFile getFile(@RequestParam String modulePart, @RequestBody Document document) {
        return dolibarrService.getFile(document.getLevel1name(), document.getRelativename(), modulePart);
    }
    /**
     * Get file of a module by route
     */
    @GetMapping(value = "/document/download/{modulePart}/{level1name}/{relativename}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DocumentFile getFile(@PathVariable String modulePart, @PathVariable String level1name, @PathVariable String relativename) {
        return dolibarrService.getFile(level1name, relativename, modulePart);
    }

    @GetMapping("/category")
    public Category[] getCategoryTreeForType(@RequestParam("type") String type,
            @RequestParam Map<String, String> params) {
        Category[] categories = dolibarrService.getAllCategories(type, params);
        categories = TreeBuilder.getCategoryTree(categories);
        return categories;
    }

    @GetMapping("/category/{idCat}/objects")
    public Page<DolibarrObject> getAllObjectsForCategory(@PathVariable("idCat") String idCat,
            @RequestParam("type") String type, @RequestParam("page") int page, @RequestParam("size") int size) {
        return dolibarrService.getAllObjectsForCategory(idCat, type, PageRequest.of(page, size));
    }

    @GetMapping("/product/{id}/category")
    public Category[] getCategoryForProduct(@PathVariable("id") String id) {
        Category[] categories = dolibarrService.getCategoriesForProduct(id);
        return categories;
    }

    @PutMapping("/proposal")
    public void updateProposal(@RequestBody Proposal proposal) throws ProposalUpdateException{
        dolibarrService.updateProposal(proposal);
    }

    /*
     * Ajout de produit au devis (Proposal) de l'utilisateur connecté
     * Créer le devis en cours si besoin
     */
    @PostMapping("/proposal/lines")
    public Integer addProposalLine(@AuthenticationPrincipal UserAccount userAccount, @RequestBody ProposalLineDTO proposalLineDTO){
        try{
            // ajouter une ligne (ProposalLine) au devis (Proposal) et retourner l'id
            return this.dolibarrService.addProposalLine(
                userAccount,
                this.dolibarrService.createProposalLine(proposalLineDTO)
                );
        }catch(DolibarrException e){
            // Retourne 0 en cas d'échec de la requête
            return 0;
        }
    }
    /*
     * Récupérer le devis (Proposal) en cours
     */
    @GetMapping("/proposal")
    public ResponseEntity<Proposal> getPendingProposal(@AuthenticationPrincipal UserAccount userAccount) throws ProposalNotFoundException{
        if(userAccount.getDolibarrPendingProposalId() == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(dolibarrService.getProposalById(userAccount.getDolibarrPendingProposalId()));
    }
    /*
     * Mettre à jour une ligne du devis en cours
     */
    @PutMapping("/proposal/lines/{lineid}")
    public void updateDolibarrObject(@AuthenticationPrincipal UserAccount userAccount, @PathVariable String lineid, @RequestBody ProposalLine proposalLine){
        this.dolibarrService.updateProposalLine(userAccount.getDolibarrPendingProposalId(), proposalLine);
    }
    /*
     * Supprimer une ligne du devis en cours
     */
    @DeleteMapping("/proposal/lines/{lineid}")
    public void deleteProposalLine(@AuthenticationPrincipal UserAccount userAccount, @PathVariable String lineid){
        this.dolibarrService.deleteProposalLine(userAccount.getDolibarrPendingProposalId(), lineid);
    }
    /*
     * Récupèrer tout les devis (hors-mis le brouillon (devis en cours)) 
     */
    @GetMapping("proposals")
    public Proposal[] getProposals(@AuthenticationPrincipal UserAccount userAccount){
        Map<String, String> params = new HashMap<>();
        params.put("sqlfilters", "(t.fk_soc:like:'"+userAccount.getDolibarrThirdPartyId()+"')and(t.fk_statut:>:'0')");
        
        // Récupérer le résultat comme DolibarrObject[]
        DolibarrObject[] objects = this.dolibarrService.getAll(Proposal.NAME, params);
        
        // Créer un nouveau tableau de Proposal de la même taille
        Proposal[] proposals = new Proposal[objects.length];
        
        // Convertir chaque élément
        for (int i = 0; i < objects.length; i++) {
            proposals[i] = (Proposal) objects[i];
        }
        
        return proposals;
    }
    /*
     * Valider le devis en cours
     */
    @PostMapping("/proposal/validate")
    public Integer validatePendingProposal(@AuthenticationPrincipal UserAccount userAccount){
        return this.dolibarrService.validatePendingProposal(userAccount);
    }
}