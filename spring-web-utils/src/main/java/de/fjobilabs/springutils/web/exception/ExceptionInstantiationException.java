package de.fjobilabs.springutils.web.exception;

/**
 * @author Felix Jordan
 * @since 26.08.2016 - 21:55:01
 * @version 1.0
 */
public class ExceptionInstantiationException extends RuntimeException {
    
    private static final long serialVersionUID = 5918406461735139941L;
    
    public ExceptionInstantiationException() {
        super();
    }
    
    public ExceptionInstantiationException(String message) {
        super(message);
    }
    
    public ExceptionInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ExceptionInstantiationException(Throwable cause) {
        super(cause);
    }
}