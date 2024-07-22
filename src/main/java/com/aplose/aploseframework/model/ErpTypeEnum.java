/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.aplose.aploseframework.model;

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
