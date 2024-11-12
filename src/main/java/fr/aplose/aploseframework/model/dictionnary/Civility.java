package fr.aplose.aploseframework.model.dictionnary;

/**
 *
 * @author oandrade
 */
public class Civility extends AbstractDictionnary{
    private Long rowid;
    private String code;
    private String label;

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
}
