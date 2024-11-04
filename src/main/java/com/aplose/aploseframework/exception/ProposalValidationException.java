package com.aplose.aploseframework.exception;

public class ProposalValidationException extends RuntimeException{
    
    public ProposalValidationException(String message){
        super(message);
    }

    public ProposalValidationException(String message, Throwable cause){
        super(message, cause);
    }
}
