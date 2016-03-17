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
public class KweetProviderException extends Exception {

    public KweetProviderException() {
    }

    public KweetProviderException(String message) {
        super(message);
    }

    public KweetProviderException(String message, Throwable cause) {
        super(message, cause);
    }

    public KweetProviderException(Throwable cause) {
        super(cause);
    }

    public KweetProviderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
