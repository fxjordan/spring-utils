package de.fjobilabs.springutils.web.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author Felix Jordan
 * @since 24.06.2016 - 23:40:13
 * @version 1.0
 */
public class RestResource {
    
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final String ERROR = "error";
    
    private String requestId;
    private String status;
    private Object data;
    
    @JsonView(RestResourceView.class)
    @JsonProperty("request-id")
    public String getRequestId() {
        return requestId;
    }
    
    @JsonProperty("request-id")
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    
    @JsonView(RestResourceView.class)
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @JsonView(RestResourceView.class)
    public Object getData() {
        return data;
    }
    
    public void setData(Object data) {
        this.data = data;
    }
}
