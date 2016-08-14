package de.fjobilabs.springutils.web.client;

import java.net.URI;
import java.util.Map;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

/**
 * Extends the basic set of Restful operations by PUT and DELETE requests, which
 * can return Objects.
 * 
 * @author Felix Jordan
 * @since 14.08.2016 - 13:51:29
 * @version 1.0
 */
public interface ExtendedRestOperations extends RestOperations {
    
    <T> T putForObject(String url, Object request, Class<T> responseType, Object... uriVariables)
            throws RestClientException;
            
    <T> T putForObject(String url, Object request, Class<T> responseType,
            Map<String, ?> uriVariables) throws RestClientException;
            
    <T> T putForObject(URI url, Object request, Class<T> responseType) throws RestClientException;
    
    <T> T deleteForObject(String url, Class<T> responseType, Object... uriVariables)
            throws RestClientException;
            
    <T> T deleteForObject(String url, Class<T> responseType,
            Map<String, ?> uriVariables) throws RestClientException;
            
    <T> T deleteForObject(URI url, Class<T> responseType) throws RestClientException;
}
