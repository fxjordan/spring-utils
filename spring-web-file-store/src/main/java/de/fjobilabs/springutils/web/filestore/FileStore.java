package de.fjobilabs.springutils.web.filestore;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Felix Jordan
 * @since 22.08.2016 - 13:05:49
 * @version 1.0
 */
@Configuration
@ComponentScan
@EnableMongoRepositories
@ImportResource("file-store-context.xml")
@PropertySource("file-store.properties")
public class FileStore {
}
