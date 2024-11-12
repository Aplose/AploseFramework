package fr.aplose.aploseframework.model.dictionnary;

/**
 *
 * @author oandrade
 */
public class State extends AbstractDictionnary {
    private Long id;
    private Integer entity;
    private String code;
    private String label;
    private Integer active;
    private Long rowid;
    private String code_departement;
    private String name;
    private String nom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEntity() {
        return entity;
    }

    public void setEntity(Integer entity) {
        this.entity = entity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    public String getCode_departement() {
        return code_departement;
    }

    public void setCode_departement(String code_departement) {
        this.code_departement = code_departement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
