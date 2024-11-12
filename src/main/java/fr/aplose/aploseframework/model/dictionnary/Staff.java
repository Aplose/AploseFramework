package fr.aplose.aploseframework.model.dictionnary;

/**
 *
 * @author oandrade
 */
public class Staff extends AbstractDictionnary {
    private Long id;
    private String code;
    private String libelle;
    private Integer active;
    private String module;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }


    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
    
}
