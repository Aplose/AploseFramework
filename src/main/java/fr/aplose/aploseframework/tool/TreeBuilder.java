package fr.aplose.aploseframework.tool;

import java.util.ArrayList;
import java.util.List;

import fr.aplose.aploseframework.model.dolibarr.Category;

/**
 *
 * @author oandrade
 */
public class TreeBuilder {
    
    public static Category[] getCategoryTree(Category[] flatCategories){
        List<Category> categories = new ArrayList<>();
        for (Category category : flatCategories) {
            if(category.isRootCategory()){
                categories.add(category);
            }else{
                findAndSetParent(flatCategories,category);
            }
        }
        return categories.toArray(new Category[0]);
    }
    private static void findAndSetParent(Category[] flatCategories, Category category){
        for (Category potentialParent : flatCategories) {
            if(potentialParent.getId().equals(category.getParentId())){
                category.setParent(potentialParent);
                potentialParent.getChilds().add(category);
            }
        }
    }
    public static String categoryTreeToString(Category[] categories){
        StringBuilder sb = new StringBuilder();
        for (Category category : categories) {
            sb.append(category.toString(0));
        }
        return sb.toString();
    }
}
