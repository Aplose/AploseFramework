package fr.aplose.aploseframework.exception;


/**
 *
 * @author oandrade
 */
public class RegistrationException extends RuntimeException {
    
    public RegistrationException(String message){
        super(message);
    }


    public RegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
