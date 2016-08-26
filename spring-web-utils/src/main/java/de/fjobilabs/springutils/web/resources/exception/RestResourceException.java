package de.fjobilabs.springutils.web.resources.exception;

/**
 * @author Felix Jordan
 * @since 26.08.2016 - 20:31:46
 * @version 1.0
 */
public abstract class RestResourceException extends RuntimeException {
    
    private static final long serialVersionUID = -6448102155628607977L;
    
    public RestResourceException() {
        super();
    }
    
    public RestResourceException(String message) {
        super(message);
    }
    
    public RestResourceException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public RestResourceException(Throwable cause) {
        super(cause);
    }
    
    public abstract int getCode();
}
