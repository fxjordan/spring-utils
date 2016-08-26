package de.fjobilabs.springutils.web.filestore.client.util;

import java.util.Map;

import de.fjobilabs.springutils.web.filestore.client.exception.FileNotFoundException;
import de.fjobilabs.springutils.web.filestore.client.exception.FileStoreException;
import de.fjobilabs.springutils.web.filestore.client.exception.NoContentUploadedException;

import de.fjobilabs.springutils.web.resources.exception.DefaultRestResourceExceptionFactory;
import de.fjobilabs.springutils.web.resources.exception.MapExceptionCodeContext;
import de.fjobilabs.springutils.web.resources.exception.RestResourceException;

/**
 * @author Felix Jordan
 * @since 26.08.2016 - 23:15:06
 * @version 1.0
 */
public class FileStoreExceptionFactory extends DefaultRestResourceExceptionFactory {
    
    private static FileStoreExceptionFactory instance;
    
    private FileStoreExceptionFactory() {
    }
    
    private void registerExceptions() {
        MapExceptionCodeContext context = new MapExceptionCodeContext();
        Map<Integer, Class<? extends RestResourceException>> classes = context.getExceptions();
        classes.put(FileNotFoundException.CODE, FileNotFoundException.class);
        classes.put(FileStoreException.CODE, FileStoreException.class);
        classes.put(NoContentUploadedException.CODE, NoContentUploadedException.class);
        setContext(context);
    }
    
    public static FileStoreExceptionFactory getInstance() {
        if (instance == null) {
            instance = new FileStoreExceptionFactory();
            instance.registerExceptions();
        }
        return instance;
    }
}
