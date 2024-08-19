package com.aplose.aploseframework.rest;

import com.aplose.aploseframework.model.dolibarr.Category;
import com.aplose.aploseframework.model.dolibarr.Document;
import com.aplose.aploseframework.model.dolibarr.DocumentFile;
import com.aplose.aploseframework.model.dolibarr.DolibarrObject;
import com.aplose.aploseframework.service.DolibarrService;
import com.aplose.aploseframework.tool.TreeBuilder;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public DolibarrObject[] getAll(@PathVariable("name") String name,@RequestParam(required = false) Map<String,String> params){        
        return dolibarrService.getAll(name, params);
    }

    /**
     * Get file of a module
     */
    @PostMapping(value="/document/download",produces = MediaType.APPLICATION_JSON_VALUE)
    public DocumentFile getFile(@RequestParam String modulePart, @RequestBody Document document){
        return dolibarrService.getFile(document.getLevel1name(),document.getRelativename(), modulePart);
    }
    
    
    @GetMapping("/category")
    public Category[] getCategoryTreeForType(@RequestParam("type") String type,@RequestParam Map<String,String> params){
        Category[] categories = dolibarrService.getAllCategories(type, params);
        categories = TreeBuilder.getCategoryTree(categories);
        return categories;
    }
    @GetMapping("/category/{idCat}/objects")
    public DolibarrObject[] getAllObjectsForCategory(@PathVariable("idCat") String idCat,@RequestParam("type") String type){
       return dolibarrService.getAllObjectsForCategory(idCat, type);
    }
    @GetMapping("/product/{id}/category")
    public Category[] getCategoryForProduct(@PathVariable("id") String id){
        Category[] categories = dolibarrService.getCategoriesForProduct(id);
        return categories;
    }
    
    
}
