package de.fjobilabs.springutils.web.filestore.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.IdGenerator;
import org.springframework.util.StreamUtils;

import de.fjobilabs.springutils.web.filestore.domain.FileInfo;
import de.fjobilabs.springutils.web.filestore.exception.FileNotFoundException;
import de.fjobilabs.springutils.web.filestore.exception.FileStoreException;
import de.fjobilabs.springutils.web.filestore.exception.NoContentUploadedException;
import de.fjobilabs.springutils.web.filestore.repository.FileInfoRepository;

/**
 * @author Felix Jordan
 * @since 09.08.2016 - 22:44:21
 * @version 1.0
 */
public class FileService {
    
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    
    private IdGenerator idGenerator;
    private String storeDirectory;
    private FileInfoRepository fileInfoRepository;
    
    @PostConstruct
    public void init() throws IOException {
        logger.info("Using store directory '" + storeDirectory + "'");
        logger.info("Loaded file index: " + getFileInfos());
    }
    
    public List<FileInfo> getFileInfos() {
        return this.fileInfoRepository.findAll();
    }
    
    public FileInfo getFileInfo(String id) {
        return this.fileInfoRepository.findOne(id);
    }
    
    public InputStream getFileContent(String id) {
        FileInfo fileInfo = this.fileInfoRepository.findOne(id);
        if (fileInfo == null) {
            throw new FileNotFoundException("File with id " + id + " does not exist");
        }
        String status = fileInfo.getStatus();
        if (status.equals(FileInfo.EMPTY)) {
            throw new NoContentUploadedException("No file uploaded for id " + id);
        }
        if (status.equals(FileInfo.DELETED)) {
            throw new FileNotFoundException("File with id " + id + " was deleted");
        }
        File file = new File(this.storeDirectory, id);
        if (!file.exists() || file.isDirectory()) {
            throw new NoContentUploadedException("No file uploaded for id " + id);
        }
        try {
            return new FileInputStream(file);
        } catch (java.io.FileNotFoundException e) {
            throw new NoContentUploadedException("No file uploaded for id " + id);
        }
    }
    
    /**
     * Creates a new placeholder for a file, which can be uploaded in the
     * future.
     * 
     * @return The representation of the file.
     * @throws IOException
     */
    public FileInfo createFile() {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(this.idGenerator.generateId().toString());
        fileInfo.setStatus(FileInfo.EMPTY);
        return this.fileInfoRepository.insert(fileInfo);
    }
    
    public FileInfo storeFile(String id, InputStream inputStream) {
        FileInfo fileInfo = this.fileInfoRepository.findOne(id);
        if (fileInfo == null) {
            throw new FileNotFoundException("File with id " + id + " dies not exist");
        }
        try {
            writeFile(id, inputStream);
        } catch (IOException e) {
            throw new FileStoreException("Faield to store file " + id, e);
        }
        
        fileInfo.setUploadTime(new Date());
        fileInfo.setStatus(FileInfo.UPLOADED);
        return this.fileInfoRepository.save(fileInfo);
    }
    
    private void writeFile(String id, InputStream inputStream) throws IOException {
        File file = new File(this.storeDirectory, id);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            if (!file.createNewFile()) {
                throw new IOException("Failed to create file");
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            StreamUtils.copy(inputStream, fileOutputStream);
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }
    
    public IdGenerator getIdGenerator() {
        return idGenerator;
    }
    
    public void setIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }
    
    public String getStoreDirectory() {
        return storeDirectory;
    }
    
    public void setStoreDirectory(String storeDirectory) {
        this.storeDirectory = storeDirectory;
    }
    
    public FileInfoRepository getFileInfoRepository() {
        return fileInfoRepository;
    }
    
    public void setFileInfoRepository(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }
}
