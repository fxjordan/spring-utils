package de.fjobilabs.springutils.web.filestore.exception;

import de.fjobilabs.springutils.web.resources.exception.RestResourceException;

/**
 * @author Felix Jordan
 * @since 10.08.2016 - 21:11:42
 * @version 1.0
 */
public class NoContentUploadedException extends RestResourceException {
    
    public static final int CODE = 1103;
    
    private static final long serialVersionUID = 5918406461735139941L;
    
    public NoContentUploadedException() {
        super();
    }
    
    public NoContentUploadedException(String message) {
        super(message);
    }
    
    public NoContentUploadedException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public NoContentUploadedException(Throwable cause) {
        super(cause);
    }
    
    @Override
    public int getCode() {
        return CODE;
    }
}
