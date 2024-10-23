package com.aplose.aploseframework.exception;




public class EmailAllreadyExistException extends RuntimeException {

    public EmailAllreadyExistException(String message){
        super(message);
    }

    public EmailAllreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
