package com.aplose.aploseframework.rest;

import com.aplose.aploseframework.model.dolibarr.Category;
import com.aplose.aploseframework.service.DolibarrService;
import com.aplose.aploseframework.tool.TreeBuilder;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author oandrade
 */
@RestController
@RequestMapping("/api/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private DolibarrService dolibarrService;
    @GetMapping
    public Category[] getCategoryTreeForType(@RequestParam("type") String type,@RequestParam Map<String,String> params){
        Category[] categories = dolibarrService.getAllCategories(type, params);
        categories = TreeBuilder.getCategoryTree(categories);
        return categories;
    }
    
}
