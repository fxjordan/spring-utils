package de.fjobilabs.springutils.web.resources.exception;

/**
 * @author Felix Jordan
 * @since 26.08.2016 - 21:50:52
 * @version 1.0
 */
public interface ExceptionCodeContext {
    
    public Class<? extends RestResourceException> getExceptionClass(int code);
}
