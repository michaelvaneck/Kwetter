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
public class KweetInterceptorException extends Exception{
    public KweetInterceptorException() {
    }

    public KweetInterceptorException(String message) {
        super(message);
    }

    public KweetInterceptorException(String message, Throwable cause) {
        super(message, cause);
    }

    public KweetInterceptorException(Throwable cause) {
        super(cause);
    }

    public KweetInterceptorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
