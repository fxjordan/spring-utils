package de.fjobilabs.springutils.requestidmanager.service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Felix Jordan
 * @since 14.08.2016 - 18:13:59
 * @version 1.0
 */
@WebListener
public class RequestListener implements ServletRequestListener {
    
    private static final Logger logger = LoggerFactory.getLogger(RequestListener.class);
    
    @Autowired
    private RequestIdService requestIdService;
    
    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        if (!(servletRequest instanceof HttpServletRequest)) {
            logger.warn(
                    "Cannot get path from ServletRequest (no HttpServletRequest). Setting no request-id");
            this.requestIdService.onRequestInitialized(null);
        } else {
            String path = ((HttpServletRequest) servletRequest).getRequestURI();
            this.requestIdService.onRequestInitialized(path);
        }
    }
    
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        this.requestIdService.onRequestDestroyed();
    }
}
