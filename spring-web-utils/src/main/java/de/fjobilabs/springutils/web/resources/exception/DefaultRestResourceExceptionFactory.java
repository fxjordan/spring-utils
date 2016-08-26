package de.fjobilabs.springutils.web.resources.exception;

import java.lang.reflect.InvocationTargetException;

import de.fjobilabs.springutils.web.exception.ExceptionInstantiationException;

/**
 * @author Felix Jordan
 * @since 26.08.2016 - 21:47:15
 * @version 1.0
 */
public class DefaultRestResourceExceptionFactory implements RestResourceExceptionFactory {
    
    private ExceptionCodeContext context = new MapExceptionCodeContext();
    
    @Override
    public RestResourceException createException(int code) {
        return createInstance(getExceptionClass(code));
    }
    
    @Override
    public RestResourceException createException(int code, String message) {
        return createInstance(getExceptionClass(code), message);
    }
    
    public ExceptionCodeContext getContext() {
        return context;
    }
    
    public void setContext(ExceptionCodeContext context) {
        this.context = context;
    }
    
    protected Class<? extends RestResourceException> getExceptionClass(int code) {
        Class<? extends RestResourceException> clazz = this.context.getExceptionClass(code);
        if (clazz == null) {
            throw new IllegalArgumentException("Unknown exception code " + code);
        }
        return clazz;
    }
    
    private RestResourceException createInstance(
            Class<? extends RestResourceException> exceptionClass) {
        try {
            return exceptionClass.newInstance();
        } catch (InstantiationException e) {
            throw new ExceptionInstantiationException("Failed to create exception instance", e);
        } catch (IllegalAccessException e) {
            throw new ExceptionInstantiationException("Failed to create exception instance", e);
        }
    }
    
    private RestResourceException createInstance(
            Class<? extends RestResourceException> exceptionClass, String message) {
        try {
            return exceptionClass.getConstructor(String.class).newInstance(message);
        } catch (InstantiationException e) {
            throw new ExceptionInstantiationException("Failed to create exception instance", e);
        } catch (IllegalAccessException e) {
            throw new ExceptionInstantiationException("Failed to create exception instance", e);
        } catch (IllegalArgumentException e) {
            throw new ExceptionInstantiationException("Failed to create exception instance", e);
        } catch (InvocationTargetException e) {
            throw new ExceptionInstantiationException("Failed to create exception instance", e);
        } catch (NoSuchMethodException e) {
            throw new ExceptionInstantiationException("Failed to create exception instance", e);
        } catch (SecurityException e) {
            throw new ExceptionInstantiationException("Failed to create exception instance", e);
        }
    }
}
