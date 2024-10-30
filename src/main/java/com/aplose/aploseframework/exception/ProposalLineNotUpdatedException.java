package com.aplose.aploseframework.exception;




public class ProposalLineNotUpdatedException extends RuntimeException{
    
    public ProposalLineNotUpdatedException(String message){
        super(message);
    }

    public ProposalLineNotUpdatedException(String message, Throwable cause){
        super(message, cause);
    }
}
