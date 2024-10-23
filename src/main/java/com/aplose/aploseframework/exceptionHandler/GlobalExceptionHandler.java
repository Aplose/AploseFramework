package com.aplose.aploseframework.exceptionHandler;

import com.aplose.aploseframework.exception.EmailAllreadyExistException;
import com.aplose.aploseframework.exception.RegistrationException;
import com.stripe.exception.StripeException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        System.err.println("\n\nMethodArgumentNotValidException:\n");
        ex.printStackTrace();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<String> handleRegistrationException(RegistrationException ex) {
        System.err.println("\n\nRegistrationException:\n");
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // @ExceptionHandler(VizuLiveException.class)
    // public ResponseEntity<String> handleVizuLiveException(VizuLiveException ex){
    //     System.err.println("\n\nVizuLiveException:\n");
    //     ex.printStackTrace();
    //     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    // }

    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // @ExceptionHandler(AppointmentException.class)
    // public ResponseEntity<String> handleAppointmentException(AppointmentException ex){
    //     System.err.println("\n\nAppointmentException:\n");
    //     ex.printStackTrace();
    //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    // }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(Exception ex) {
        System.err.println("\n\nAuthenticationException:\n" );
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( ex.getMessage());
    }

    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler(StripeException.class)
    public ResponseEntity<Map<String, String>> handleStripeException(StripeException ex) {
        System.err.println("\n\nStripeException:\n");
        ex.printStackTrace();
        
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("statusCode", String.valueOf(HttpStatus.EXPECTATION_FAILED.value()));
        errorDetails.put("errorType", ex.getStripeError().getType());
        errorDetails.put("errorCode", ex.getStripeError().getCode());
        errorDetails.put("errorMessage", ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(errorDetails);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailAllreadyExistException.class)
    public ResponseEntity<String> handleEmailAllreadyExistException(Exception ex) {
        System.err.println("\n\nEmailAllreadyExistException:\n" );
        System.err.println("\n\nmessage:\n"+ex.getMessage() );
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.CONFLICT).body( ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        System.err.println("\n\nException:\n" );
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }

}
