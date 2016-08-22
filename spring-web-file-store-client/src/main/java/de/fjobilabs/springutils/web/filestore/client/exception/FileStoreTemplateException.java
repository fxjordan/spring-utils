package de.fjobilabs.springutils.web.filestore.client.exception;

/**
 * @author Felix Jordan
 * @since 22.08.2016 - 16:02:27
 * @version 1.0
 */
public class FileStoreTemplateException extends RuntimeException {
    
    private static final long serialVersionUID = 6328676570464617491L;
    
    public FileStoreTemplateException() {
        super();
    }
    
    public FileStoreTemplateException(String message) {
        super(message);
    }
    
    public FileStoreTemplateException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public FileStoreTemplateException(Throwable cause) {
        super(cause);
    }
}
