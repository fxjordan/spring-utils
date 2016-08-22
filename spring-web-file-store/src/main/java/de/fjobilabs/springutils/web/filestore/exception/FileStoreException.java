package de.fjobilabs.springutils.web.filestore.exception;

/**
 * @author Felix Jordan
 * @since 10.08.2016 - 17:24:35
 * @version 1.0
 */
public class FileStoreException extends RuntimeException {
    
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
}
