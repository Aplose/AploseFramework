package com.aplose.aploseframework.rest;

import com.aplose.aploseframework.model.dolibarr.Category;
import com.aplose.aploseframework.model.dolibarr.DocumentFile;
import com.aplose.aploseframework.model.dolibarr.DolibarrObject;
import com.aplose.aploseframework.service.DolibarrService;
import com.aplose.aploseframework.tool.TreeBuilder;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public DolibarrObject[] getAll(@PathVariable("name") String name){
        return dolibarrService.getAll(name, null);
    }
    
    /**
     * Get image of a product or other module
     */
    @GetMapping(value="/{modulePart}/{id}/image")
    public ResponseEntity<DocumentFile> getImage(@PathVariable("modulePart") String modulePart, @PathVariable("id") String id){
        DocumentFile documentFile = dolibarrService.getImage(modulePart, id);
        if (documentFile.getContentType()==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(documentFile,HttpStatus.OK);
        } 
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
    
}
