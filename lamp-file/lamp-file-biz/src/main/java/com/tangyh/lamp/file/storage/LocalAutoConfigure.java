package com.tangyh.lamp.file.storage;

import com.tangyh.lamp.file.properties.FileServerProperties;
import com.tangyh.lamp.file.service.AttachmentService;
import com.tangyh.lamp.file.strategy.FileChunkStrategy;
import com.tangyh.lamp.file.strategy.FileStrategy;
import com.tangyh.lamp.file.strategy.impl.local.LocalFileChunkStrategyImpl;
import com.tangyh.lamp.file.strategy.impl.local.LocalFileStrategyImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 本地上传配置
 *
 * @author zuihou
 * @date 2019/06/18
 */

@EnableConfigurationProperties(FileServerProperties.class)
@Configuration
@ConditionalOnProperty(prefix = FileServerProperties.PREFIX, name = "type", havingValue = "LOCAL", matchIfMissing = true)
@Slf4j
public class LocalAutoConfigure {

    @Bean
    public FileStrategy getFileStrategy(FileServerProperties fileProperties) {
        return new LocalFileStrategyImpl(fileProperties);
    }

    @Bean
    public FileChunkStrategy getFileChunkStrategy(AttachmentService fileService, FileServerProperties fileProperties) {
        return new LocalFileChunkStrategyImpl(fileService, fileProperties);
    }

}
