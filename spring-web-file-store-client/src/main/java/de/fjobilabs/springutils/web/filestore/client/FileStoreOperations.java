package de.fjobilabs.springutils.web.filestore.client;

import java.io.InputStream;
import java.util.List;

import de.fjobilabs.springutils.web.filestore.client.domain.FileInfo;

/**
 * @author Felix Jordan
 * @since 22.08.2016 - 16:00:15
 * @version 1.0
 */
public interface FileStoreOperations {
    
    /**
     * Returns the informations for all file objects in the store.
     * 
     * @param uri The base <code>URI</code> of the file store.           
     * @return A list of all {@link FileInfo}s in the store.
     */
    public List<FileInfo> getFileInfos(String uri);
    
    /**
     * Returns the {@link FileInfo} for the file with the given id.
     * 
     * @param uri The base <code>URI</code> of the file store.
     * @param id The file id.
     * @return The {@code FileInfo} for the specific file.
     */
    public FileInfo getFileInfo(String uri, String id);
    
    /**
     * Returns the content of the file with the given id.
     * 
     * @param uri The base <code>URI</code> of the file store.
     * @param id The file id.
     * @return A stream to read the file content from.
     */
    public InputStream getFileContent(String uri, String id);
    
    /**
     * Creates a new, empty file in the file store.
     * 
     * @param uri The base <code>URI</code> of the file store.
     * @return The {@code FileInfo} for the created file.
     */
    public FileInfo createFile(String uri);
    
    /**
     * Creates a new, file with the given content in the file store.
     * 
     * @param uri The base <code>URI</code> of the file store.
     * @param content The file content to store.
     * @return The {@code FileInfo} for the created file.
     */
    public FileInfo createFile(String uri, InputStream content);
    
    /**
     * Updates the content of the file with the given id.
     * 
     * @param uri The base <code>URI</code> of the file store.
     * @param id The file id.
     * @param content The new file content to store.
     * @return The {@code FileInfo} for the created file.
     */
    public FileInfo updateFileContent(String uri, String id, InputStream content);
}
