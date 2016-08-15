package de.fjobilabs.springutils.requestidmanager;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Felix Jordan
 * @since 14.08.2016 - 18:17:29
 * @version 1.0
 */
@Configuration
@ComponentScan
@ServletComponentScan
@ImportResource("request-id-manager-context.xml")
public class RequestIdManager {
}
