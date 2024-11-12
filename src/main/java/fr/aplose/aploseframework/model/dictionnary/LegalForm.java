package fr.aplose.aploseframework.model.dictionnary;

/**
 *
 * @author oandrade
 */
public class LegalForm extends AbstractDictionnary{
    private Long rowid;
    private String code;
    private Long fk_pays;
    private String libelle;
    private Integer isvatexempted;
    private Integer active;
    private String module;
    private Integer position;

    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getFk_pays() {
        return fk_pays;
    }

    public void setFk_pays(Long fk_pays) {
        this.fk_pays = fk_pays;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getIsvatexempted() {
        return isvatexempted;
    }

    public void setIsvatexempted(Integer isvatexempted) {
        this.isvatexempted = isvatexempted;
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
    
}
