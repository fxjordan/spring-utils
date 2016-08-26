package de.fjobilabs.springutils.web.filestore.exception;

import de.fjobilabs.springutils.web.resources.exception.RestResourceException;

/**
 * @author Felix Jordan
 * @since 10.08.2016 - 17:24:35
 * @version 1.0
 */
public class FileNotFoundException extends RestResourceException {
    
    public static final int CODE = 1101;
    
    private static final long serialVersionUID = 5918406461735139941L;
    
    public FileNotFoundException() {
        super();
    }
    
    public FileNotFoundException(String message) {
        super(message);
    }
    
    public FileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public FileNotFoundException(Throwable cause) {
        super(cause);
    }
    
    @Override
    public int getCode() {
        return CODE;
    }
}
