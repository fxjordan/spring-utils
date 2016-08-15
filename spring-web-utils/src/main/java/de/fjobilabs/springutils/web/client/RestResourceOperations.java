package de.fjobilabs.springutils.web.client;

import java.net.URI;
import java.util.Map;

import org.springframework.web.client.RestClientException;

import de.fjobilabs.springutils.web.resources.RestResource;

/**
 * @author Felix Jordan
 * @since 15.08.2016 - 23:30:57
 * @version 1.0
 */
public interface RestResourceOperations {
    
    RestResource getForResource(String url, Object... uriVariables)
            throws RestClientException;
            
    RestResource getForResource(String url, Map<String, ?> uriVariables)
            throws RestClientException;
            
    RestResource getForResource(URI url) throws RestClientException;
    
    RestResource postForResource(String url, Object request, Object... uriVariables)
            throws RestClientException;
            
    RestResource postForResource(String url, Object request, Map<String, ?> uriVariables)
            throws RestClientException;
            
    RestResource postForResource(URI url, Object request) throws RestClientException;
    
    RestResource putForResource(String url, Object request, Object... uriVariables)
            throws RestClientException;
            
    RestResource putForResource(String url, Object request, Map<String, ?> uriVariables)
            throws RestClientException;
            
    RestResource putForResource(URI url, Object request) throws RestClientException;
    
    RestResource deleteForResource(String url, Object... uriVariables)
            throws RestClientException;
            
    RestResource deleteForResource(String url, Map<String, ?> uriVariables)
            throws RestClientException;
            
    RestResource deleteForResource(URI url) throws RestClientException;
}
