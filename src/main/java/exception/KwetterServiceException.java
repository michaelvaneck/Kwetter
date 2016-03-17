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
public class KwetterServiceException extends Exception{

    public KwetterServiceException() {
    }

    public KwetterServiceException(String message) {
        super(message);
    }

    public KwetterServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public KwetterServiceException(Throwable cause) {
        super(cause);
    }

    public KwetterServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
