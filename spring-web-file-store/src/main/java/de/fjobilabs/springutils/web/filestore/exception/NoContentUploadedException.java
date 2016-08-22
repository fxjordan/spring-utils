package de.fjobilabs.springutils.web.filestore.exception;

/**
 * @author Felix Jordan
 * @since 10.08.2016 - 21:11:42
 * @version 1.0
 */
public class NoContentUploadedException extends RuntimeException {
    
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
}
