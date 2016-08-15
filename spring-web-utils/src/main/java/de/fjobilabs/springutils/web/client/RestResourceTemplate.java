package de.fjobilabs.springutils.web.client;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.fjobilabs.springutils.web.resources.RestResource;

/**
 * @author Felix Jordan
 * @since 15.08.2016 - 23:40:12
 * @version 1.0
 */
public class RestResourceTemplate extends ExtendedRestTemplate implements RestResourceOperations {
    
    private ObjectMapper objectMapper;
    
    @Override
    public RestResource getForResource(String url, Object... uriVariables)
            throws RestClientException {
        try {
            return getForObject(url, RestResource.class, uriVariables);
        } catch (HttpStatusCodeException e) {
            return readErrorResponse(e);
        }
    }
    
    @Override
    public RestResource getForResource(String url, Map<String, ?> uriVariables)
            throws RestClientException {
        try {
            return getForObject(url, RestResource.class, uriVariables);
        } catch (HttpStatusCodeException e) {
            return readErrorResponse(e);
        }
    }
    
    @Override
    public RestResource getForResource(URI url) throws RestClientException {
        try {
            return getForObject(url, RestResource.class);
        } catch (HttpStatusCodeException e) {
            return readErrorResponse(e);
        }
    }
    
    @Override
    public RestResource postForResource(String url, Object request, Object... uriVariables)
            throws RestClientException {
        try {
            return postForObject(url, request, RestResource.class, uriVariables);
        } catch (HttpStatusCodeException e) {
            return readErrorResponse(e);
        }
    }
    
    @Override
    public RestResource postForResource(String url, Object request, Map<String, ?> uriVariables)
            throws RestClientException {
        try {
            return postForObject(url, request, RestResource.class, uriVariables);
        } catch (HttpStatusCodeException e) {
            return readErrorResponse(e);
        }
    }
    
    @Override
    public RestResource postForResource(URI url, Object request) throws RestClientException {
        try {
            return postForObject(url, request, RestResource.class);
        } catch (HttpStatusCodeException e) {
            return readErrorResponse(e);
        }
    }
    
    @Override
    public RestResource putForResource(String url, Object request, Object... uriVariables)
            throws RestClientException {
        try {
            return putForObject(url, request, RestResource.class, uriVariables);
        } catch (HttpStatusCodeException e) {
            return readErrorResponse(e);
        }
    }
    
    @Override
    public RestResource putForResource(String url, Object request, Map<String, ?> uriVariables)
            throws RestClientException {
        try {
            return putForObject(url, request, RestResource.class, uriVariables);
        } catch (HttpStatusCodeException e) {
            return readErrorResponse(e);
        }
    }
    
    @Override
    public RestResource putForResource(URI url, Object request) throws RestClientException {
        try {
            return putForObject(url, request, RestResource.class);
        } catch (HttpStatusCodeException e) {
            return readErrorResponse(e);
        }
    }
    
    @Override
    public RestResource deleteForResource(String url, Object... uriVariables)
            throws RestClientException {
        try {
            return deleteForObject(url, RestResource.class, uriVariables);
        } catch (HttpStatusCodeException e) {
            return readErrorResponse(e);
        }
    }
    
    @Override
    public RestResource deleteForResource(String url, Map<String, ?> uriVariables)
            throws RestClientException {
        try {
            return deleteForObject(url, RestResource.class, uriVariables);
        } catch (HttpStatusCodeException e) {
            return readErrorResponse(e);
        }
    }
    
    @Override
    public RestResource deleteForResource(URI url) throws RestClientException {
        try {
            return deleteForObject(url, RestResource.class);
        } catch (HttpStatusCodeException e) {
            return readErrorResponse(e);
        }
    }
    
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
    
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    
    private RestResource readErrorResponse(HttpStatusCodeException exception) {
        String body = exception.getResponseBodyAsString();
        if (body == null) {
            throw new RestClientException("Missing error response body", exception);
        }
        try {
            return this.objectMapper.readValue(body, RestResource.class);
        } catch (JsonParseException e) {
            throw new RestClientException("Failed to parse error response", e);
        } catch (JsonMappingException e) {
            throw new RestClientException("Failed to parse error response", e);
        } catch (IOException e) {
            throw new RestClientException("Failed to parse error response", e);
        }
    }
}
