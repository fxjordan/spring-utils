package de.fjobilabs.springutils.web.resources.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Felix Jordan
 * @since 26.08.2016 - 22:00:44
 * @version 1.0
 */
public class MapExceptionCodeContext implements ExceptionCodeContext {
    
    private Map<Integer, Class<? extends RestResourceException>> exceptions = new HashMap<>();
    
    @Override
    public Class<? extends RestResourceException> getExceptionClass(int code) {
        return exceptions.get(code);
    }
    
    public Map<Integer, Class<? extends RestResourceException>> getExceptions() {
        return exceptions;
    }
    
    public void setExceptions(Map<Integer, Class<? extends RestResourceException>> exceptions) {
        this.exceptions = exceptions;
    }
}
