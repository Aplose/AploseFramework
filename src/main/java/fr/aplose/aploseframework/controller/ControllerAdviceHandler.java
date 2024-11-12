package fr.aplose.aploseframework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fr.aplose.aploseframework.exception.AAException;

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
