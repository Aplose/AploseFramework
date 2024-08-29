package com.aplose.aploseframework.model.dolibarr;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oandrade
 */
public class Category extends DolibarrObject {
    @JsonProperty("fk_parent")
    private Integer parentId;
    private List<Category> childs = new ArrayList<>();
    @JsonIgnore
    private Category parent;
    private String label;//Ref of categ
    private String description;
    private String photo;


    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Category> getChilds() {
        return childs;
    }

    public void setChilds(List<Category> childs) {
        this.childs = childs;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isRootCategory() {
        return parentId==null||parentId==0;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public String toString(int level){
        level++;
        StringBuilder sb = new StringBuilder(this.label);
        sb.append("\n");
        for (Category child : childs) {
            for (int i = 0; i < level; i++) {
                sb.append("\t");                
            }
            sb.append(child.toString(level)).append("\n");
        }
        return sb.toString();
    }
}
