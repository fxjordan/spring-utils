package de.fjobilabs.springutils.web.filestore.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.fjobilabs.springutils.web.client.RestResourceTemplate;
import de.fjobilabs.springutils.web.filestore.client.domain.FileInfo;
import de.fjobilabs.springutils.web.filestore.client.exception.FileStoreTemplateException;
import de.fjobilabs.springutils.web.resources.RestResource;

/**
 * @author Felix Jordan
 * @since 22.08.2016 - 16:01:08
 * @version 1.0
 */
public class FileStoreTemplate implements FileStoreOperations {
    
    private RestResourceTemplate restResourceTemplate = new RestResourceTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public List<FileInfo> getFileInfos(String uri) {
        RestResource response = this.restResourceTemplate
                .getForResource(uri);
        if (!response.getStatus().equals(RestResource.SUCCESS)) {
            throw new FileStoreTemplateException("Failed to get fiel infos");
        }
        return this.objectMapper.convertValue(response.getData(), this.objectMapper.getTypeFactory()
                .constructCollectionType(List.class, FileInfo.class));
    }
    
    @Override
    public FileInfo getFileInfo(String uri, String id) {
        RestResource response = this.restResourceTemplate
                .getForResource(uri + "/{id}", id);
        if (!response.getStatus().equals(RestResource.SUCCESS)) {
            throw new FileStoreTemplateException("Failed to get file info for file " + id);
        }
        return this.objectMapper.convertValue(response.getData(), FileInfo.class);
    }
    
    @Override
    public InputStream getFileContent(String uri, String id) {
        try {
            return getForInputStream(uri + "/" + id + "/content");
        } catch (IOException e) {
            throw new FileStoreTemplateException("Failed to get file content", e);
        }
    }
    
    @Override
    public FileInfo createFile(String uri) {
        RestResource response = this.restResourceTemplate
                .postForResource(uri, null);
        if (!response.getStatus().equals(RestResource.SUCCESS)) {
            throw new FileStoreTemplateException("Failed to create new file");
        }
        return this.objectMapper.convertValue(response, FileInfo.class);
    }
    
    @Override
    public FileInfo createFile(String uri, InputStream content) {
        return updateFileContent(uri, createFile(uri).getId(), content);
    }
    
    @Override
    public FileInfo updateFileContent(String uri, String id, InputStream content) {
        RestResource response;
        try {
            response = putForResource(uri + "/" + id, content);
        } catch (IOException e) {
            throw new FileStoreTemplateException("Failed to update file content", e);
        }
        if (!response.getStatus().equals(RestResource.SUCCESS)) {
            throw new FileStoreTemplateException("Failed to update file content");
        }
        return this.objectMapper.convertValue(response.getData(), FileInfo.class);
    }
    
    private InputStream getForInputStream(String uri) throws IOException {
        ClientHttpResponse response = this.restResourceTemplate.getRequestFactory()
                .createRequest(URI.create(uri), HttpMethod.GET).execute();
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new FileStoreTemplateException("Failed to request InputStream");
        }
        return response.getBody();
    }
    
    private RestResource putForResource(String uri, InputStream request) throws IOException {
        ClientHttpRequest httpRequest = this.restResourceTemplate.getRequestFactory()
                .createRequest(URI.create(uri), HttpMethod.PUT);
        StreamUtils.copy(request, httpRequest.getBody());
        ClientHttpResponse response = httpRequest.execute();
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new FileStoreTemplateException("Failed to put InputStream");
        }
        return this.objectMapper.readValue(response.getBody(), RestResource.class);
    }
    
    public RestResourceTemplate getRestResourceTemplate() {
        return restResourceTemplate;
    }
    
    public void setRestResourceTemplate(RestResourceTemplate restResourceTemplate) {
        this.restResourceTemplate = restResourceTemplate;
    }
    
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
    
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
