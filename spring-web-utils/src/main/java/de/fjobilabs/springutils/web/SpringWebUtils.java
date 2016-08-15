package de.fjobilabs.springutils.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Felix Jordan
 * @since 16.08.2016 - 00:12:34
 * @version 1.0
 */
@Configuration
@ComponentScan
@ImportResource("spring-web-utils-context.xml")
public class SpringWebUtils {
}
