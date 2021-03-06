package de.fjobilabs.springutils.web.filestore.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Felix Jordan
 * @since 10.08.2016 - 14:43:20
 * @version 1.0
 */
@Document(collection="files")
public class FileInfo {
    
    public static final String EMPTY = "empty";
    public static final String UPLOADED = "uploaded";
    public static final String DELETED = "deleted";
    
    @Id
    private String id;
    
    private String status;
    private Date uploadTime;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @JsonProperty("upload-time")
    @JsonInclude(Include.NON_NULL)
    @Field("upload-time")
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    public Date getUploadTime() {
        return uploadTime;
    }
    
    @JsonProperty("upload-time")
    @Field("upload-time")
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
    
    @Override
    public String toString() {
        return String.format("FileInfo(id=%s; status=%s; uploadTime=%s)",
                id, status, String.valueOf(uploadTime));
    }
}
