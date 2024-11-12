package fr.aplose.aploseframework.model.dictionnary;

/**
 *
 * @author oandrade
 */
public class TicketSeverity extends AbstractDictionnary {
    private Long rowid;
    private String code;
    private Integer pos;
    private String label;
    private Integer use_default;
    private String color;
    private String description;

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

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getUse_default() {
        return use_default;
    }

    public void setUse_default(Integer use_default) {
        this.use_default = use_default;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
