package de.fjobilabs.springutils.web.filestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import de.fjobilabs.springutils.web.filestore.exception.FileNotFoundException;
import de.fjobilabs.springutils.web.filestore.exception.FileStoreException;
import de.fjobilabs.springutils.web.filestore.exception.NoContentUploadedException;
import de.fjobilabs.springutils.web.requestidmanager.service.RequestIdService;
import de.fjobilabs.springutils.web.resources.RestResource;

/**
 * @author Felix Jordan
 * @since 26.08.2016 - 23:02:13
 * @version 1.0
 */
@RestControllerAdvice
public class FileControllerAdvice {
    
    @Autowired
    private RequestIdService requestIdService;
    
    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(code=HttpStatus.NOT_FOUND)
    public RestResource fileNotFound(FileNotFoundException exception) {
        RestResource response = new RestResource();
        response.setRequestId(this.requestIdService.getRequestId());
        response.setStatus(RestResource.FAIL);
        response.setData(exception.getMessage());
        response.setCode(exception.getCode());
        return response;
    }
    
    @ExceptionHandler(NoContentUploadedException.class)
    @ResponseStatus(code=HttpStatus.NOT_FOUND)
    public RestResource noContentUploaded(NoContentUploadedException exception) {
        RestResource response = new RestResource();
        response.setRequestId(this.requestIdService.getRequestId());
        response.setStatus(RestResource.FAIL);
        response.setData(exception.getMessage());
        response.setCode(exception.getCode());
        return response;
    }
    
    @ExceptionHandler(FileStoreException.class)
    @ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResource fileStoreException(FileStoreException exception) {
        RestResource response = new RestResource();
        response.setRequestId(this.requestIdService.getRequestId());
        response.setStatus(RestResource.ERROR);
        response.setData(exception.getMessage());
        response.setCode(exception.getCode());
        return response;
    }
}
