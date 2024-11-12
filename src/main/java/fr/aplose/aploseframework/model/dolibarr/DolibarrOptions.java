package fr.aplose.aploseframework.model.dolibarr;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author oandrade
 */
public class DolibarrOptions {
    Map<String, Object> options = new HashMap<>();

    public Map<String, Object> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Object> options) {
        this.options = options;
    }
    
}
