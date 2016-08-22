package de.fjobilabs.springutils.web.filestore.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.fjobilabs.springutils.web.filestore.domain.FileInfo;
import de.fjobilabs.springutils.web.filestore.exception.FileStoreException;
import de.fjobilabs.springutils.web.filestore.service.FileService;
import de.fjobilabs.springutils.web.requestidmanager.service.RequestIdService;
import de.fjobilabs.springutils.web.resources.RestResource;

/**
 * @author Felix Jordan
 * @since 10.08.2016 - 14:12:58
 * @version 1.0
 */
@RestController
public class FileController {
    
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    
    private RequestIdService requestIdService;
    private FileService fileService;
    
    @RequestMapping(path = "/files", method = RequestMethod.GET)
    public RestResource getFileInfos() {
        RestResource response = new RestResource();
        response.setRequestId(this.requestIdService.getRequestId());
        
        List<FileInfo> fileInfos = this.fileService.getFileInfos();
        
        response.setData(fileInfos);
        response.setStatus(RestResource.SUCCESS);
        return response;
    }
    
    @RequestMapping(path = "/files/{file-id}", method = RequestMethod.GET)
    public RestResource getFileInfo(@PathVariable("file-id") String fileId) {
        RestResource response = new RestResource();
        response.setRequestId(this.requestIdService.getRequestId());
        
        FileInfo fileInfo = this.fileService.getFileInfo(fileId);
        if (fileInfo == null) {
            response.setStatus(RestResource.FAIL);
            response.setData("File with id " + fileId + " does not exist");
            return response;
        }
        
        response.setData(fileInfo);
        response.setStatus(RestResource.SUCCESS);
        return response;
    }
    
    @RequestMapping(path = "/files/{file-id}/content", method = RequestMethod.GET)
    public void getFileContent(@PathVariable("file-id") String fileId, HttpServletResponse response)
            throws IOException {
        String requestId = this.requestIdService.getRequestId();
        logger.info("(" + requestId + ") downloading file id " + fileId);
        
        InputStream content = new BufferedInputStream(this.fileService.getFileContent(fileId));
        FileCopyUtils.copy(content, response.getOutputStream());
    }
    
    @RequestMapping(path = "/files", method = RequestMethod.POST)
    public RestResource createFile() {
        RestResource response = new RestResource();
        response.setRequestId(this.requestIdService.getRequestId());
        
        FileInfo fileInfo = this.fileService.createFile();
        
        response.setData(fileInfo);
        response.setStatus(RestResource.SUCCESS);
        return response;
    }
    
    @RequestMapping(path = "/files/{file-id}/content", method = RequestMethod.PUT)
    public RestResource uploadFile(@PathVariable("file-id") String fileId,
            HttpServletRequest request) {
        RestResource response = new RestResource();
        response.setRequestId(this.requestIdService.getRequestId());
        
        FileInfo fileInfo;
        try {
            fileInfo = this.fileService.storeFile(fileId, request.getInputStream());
        } catch (IOException e) {
            throw new FileStoreException("Failed to store file", e);
        }
        
        response.setData(fileInfo);
        response.setStatus(RestResource.SUCCESS);
        return response;
    }
    
    public RequestIdService getRequestIdService() {
        return requestIdService;
    }
    
    public void setRequestIdService(RequestIdService requestIdService) {
        this.requestIdService = requestIdService;
    }
    
    public FileService getFileService() {
        return fileService;
    }
    
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
