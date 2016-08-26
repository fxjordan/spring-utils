package de.fjobilabs.springutils.web.filestore.client.exception;

import de.fjobilabs.springutils.web.resources.exception.RestResourceException;

/**
 * @author Felix Jordan
 * @since 26.08.2016 - 23:12:23
 * @version 1.0
 */
public class FileStoreException extends RestResourceException {
    
    public static final int CODE = 1102;
    
    private static final long serialVersionUID = 5918406461735139941L;
    
    public FileStoreException() {
        super();
    }
    
    public FileStoreException(String message) {
        super(message);
    }
    
    public FileStoreException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public FileStoreException(Throwable cause) {
        super(cause);
    }
    
    @Override
    public int getCode() {
        return CODE;
    }
}
