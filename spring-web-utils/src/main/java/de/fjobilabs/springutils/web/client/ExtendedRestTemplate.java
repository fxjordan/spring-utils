package de.fjobilabs.springutils.web.client;

import java.net.URI;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Extends Spring's {@link RestTemplate} with the operations defined by
 * {@link ExtendedRestOperations}.
 * 
 * @author Felix Jordan
 * @since 14.08.2016 - 13:54:14
 * @version 1.0
 */
public class ExtendedRestTemplate extends RestTemplate implements ExtendedRestOperations {
    
    @Override
    public <T> T putForObject(String url, Object request, Class<T> responseType,
            Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = httpEntityCallback(request, responseType);
        HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor<T>(
                responseType, getMessageConverters());
        return execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }
    
    @Override
    public <T> T putForObject(String url, Object request, Class<T> responseType,
            Map<String, ?> uriVariables) throws RestClientException {
        RequestCallback requestCallback = httpEntityCallback(request, responseType);
        HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor<T>(
                responseType, getMessageConverters());
        return execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }
    
    @Override
    public <T> T putForObject(URI url, Object request, Class<T> responseType)
            throws RestClientException {
        RequestCallback requestCallback = httpEntityCallback(request, responseType);
        HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor<T>(
                responseType, getMessageConverters());
        return execute(url, HttpMethod.PUT, requestCallback, responseExtractor);
    }
    
    @Override
    public <T> T deleteForObject(String url, Class<T> responseType, Object... uriVariables)
            throws RestClientException {
        HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor<T>(
                responseType, getMessageConverters());
        return execute(url, HttpMethod.PUT, null, responseExtractor, uriVariables);
    }
    
    @Override
    public <T> T deleteForObject(String url, Class<T> responseType, Map<String, ?> uriVariables)
            throws RestClientException {
        HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor<T>(
                responseType, getMessageConverters());
        return execute(url, HttpMethod.PUT, null, responseExtractor, uriVariables);
    }
    
    @Override
    public <T> T deleteForObject(URI url, Class<T> responseType) throws RestClientException {
        HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor<T>(
                responseType, getMessageConverters());
        return execute(url, HttpMethod.PUT, null, responseExtractor);
    }
}
