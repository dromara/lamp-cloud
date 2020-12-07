package com.tangyh.lamp.file.storage;

import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.tangyh.lamp.file.dao.AttachmentMapper;
import com.tangyh.lamp.file.properties.FileServerProperties;
import com.tangyh.lamp.file.service.AttachmentService;
import com.tangyh.lamp.file.strategy.FileChunkStrategy;
import com.tangyh.lamp.file.strategy.FileStrategy;
import com.tangyh.lamp.file.strategy.impl.fastdfs.FastDfsFileChunkStrategyImpl;
import com.tangyh.lamp.file.strategy.impl.fastdfs.FastDfsFileStrategyImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FastDFS配置
 *
 * @author zuihou
 */
@EnableConfigurationProperties(FileServerProperties.class)
@Configuration
@Slf4j
@ConditionalOnProperty(prefix = FileServerProperties.PREFIX, name = "type", havingValue = "FAST_DFS")
public class FastDfsAutoConfigure {
    @Bean
    public FileStrategy getFileStrategy(FileServerProperties fileProperties, FastFileStorageClient storageClient, AttachmentMapper attachmentMapper) {
        return new FastDfsFileStrategyImpl(fileProperties, storageClient, attachmentMapper);
    }

    @Bean
    public FileChunkStrategy getFileChunkStrategy(AttachmentService fileService, FileServerProperties fileProperties, AppendFileStorageClient storageClient) {
        return new FastDfsFileChunkStrategyImpl(fileService, fileProperties, storageClient);
    }
}
