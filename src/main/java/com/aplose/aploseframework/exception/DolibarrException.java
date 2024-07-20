/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;

/**
 *
 * @author oandrade
 */
public class DolibarrException extends RuntimeException {

    public DolibarrException(String message) {
        super(message);
    }
    
}
