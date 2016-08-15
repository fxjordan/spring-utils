package de.fjobilabs.springutils.requestidmanager.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import de.fjobilabs.springutils.requestidmanager.util.PropertyUtils;

/**
 * @author Felix Jordan
 * @since 14.08.2016 - 22:52:41
 * @version 1.0
 */
@Service
public class RequestIdService {
    
    private static final String PATH_PROPERTY_PREFIX = "fjobilabs.requestidservice.paths.";
    
    private static final Logger logger = LoggerFactory.getLogger(RequestIdService.class);
    
    @Autowired
    @Qualifier("requestIdGenerator")
    private IdGenerator idGenerator;
    
    @Autowired
    private Environment environment;
    
    private List<String> paths;
    
    private ThreadLocal<String> requestId = new ThreadLocal<>();
    
    @PostConstruct
    public void init() {
        if (this.paths == null) {
            this.paths = new ArrayList<>();
        }
        PropertyUtils.getPropertiesForPrefix(environment, PATH_PROPERTY_PREFIX).values()
                .forEach((path) -> this.paths.addAll(Arrays.asList(path.toString().split(","))));
        this.paths.replaceAll((path) -> path.trim());
        logger.info("paths: " + paths);
    }
    
    public String getRequestId() {
        return this.requestId.get();
    }
    
    void onRequestInitialized(String path) {
        if (path == null) {
            this.requestId.set(null);
            logger.warn("Request initialized without id");
        }
        if (startsWith(path, this.paths)) {
            this.requestId.set(this.idGenerator.generateId().toString());
            logger.debug("Request " + getRequestId() + " initialized");
        }
    }
    
    void onRequestDestroyed() {
        this.requestId.set(null);
    }
    
    /**
     * Returns whether the string starts with one of the given prefixes.
     * 
     * @param string
     * @param prefixes
     * @return
     */
    private boolean startsWith(String string, List<String> prefixes) {
        return prefixes.stream().anyMatch((prefix) -> string.startsWith(prefix));
    }
}
