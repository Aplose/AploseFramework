/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.controller;

import com.aplose.aploseframework.exception.AAException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author oandrade
 */
@ControllerAdvice
public class ControllerAdviceHandler {
    
    @ExceptionHandler({ AAException.class})
    protected ResponseEntity<String> traiterErreurs(AAException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
