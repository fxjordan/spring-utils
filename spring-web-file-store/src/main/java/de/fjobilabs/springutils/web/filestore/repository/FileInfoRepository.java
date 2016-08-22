package de.fjobilabs.springutils.web.filestore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.fjobilabs.springutils.web.filestore.domain.FileInfo;

/**
 * @author Felix Jordan
 * @since 11.08.2016 - 18:21:51
 * @version 1.0
 */
public interface FileInfoRepository extends MongoRepository<FileInfo, String> {
}
