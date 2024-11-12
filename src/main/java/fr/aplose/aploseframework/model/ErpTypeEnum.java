package fr.aplose.aploseframework.model;

/**
 * Define kind of ERP we use in backend
 * @author oandrade
 */
public enum ErpTypeEnum {
    INTERNAL,//no backend, we use our internal ERP
    DOLIBARR,//we use Dolibarr ERP (i.e. DolibarrService and Dolibarr Rest Api)
    EBP,
    //....
}
