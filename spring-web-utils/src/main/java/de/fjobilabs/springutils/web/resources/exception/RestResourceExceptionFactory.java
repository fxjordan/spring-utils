package de.fjobilabs.springutils.web.resources.exception;

/**
 * @author Felix Jordan
 * @since 26.08.2016 - 21:45:11
 * @version 1.0
 */
public interface RestResourceExceptionFactory {
    
    public RestResourceException createException(int code);
    
    public RestResourceException createException(int code, String message);
}
