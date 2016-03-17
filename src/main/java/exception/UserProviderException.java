/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Michael van Eck
 */
public class UserProviderException extends Exception{

    public UserProviderException() {
    }

    public UserProviderException(String message) {
        super(message);
    }

    public UserProviderException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserProviderException(Throwable cause) {
        super(cause);
    }

    public UserProviderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
