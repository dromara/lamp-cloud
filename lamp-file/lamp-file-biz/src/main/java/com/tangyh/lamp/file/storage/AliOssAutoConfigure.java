package com.tangyh.lamp.file.storage;

import com.tangyh.lamp.file.properties.FileServerProperties;
import com.tangyh.lamp.file.service.AttachmentService;
import com.tangyh.lamp.file.strategy.FileChunkStrategy;
import com.tangyh.lamp.file.strategy.FileStrategy;
import com.tangyh.lamp.file.strategy.impl.ali.AliFileChunkStrategyImpl;
import com.tangyh.lamp.file.strategy.impl.ali.AliFileStrategyImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里OSS
 *
 * @author zuihou
 * @date 2019/08/09
 */
@EnableConfigurationProperties(FileServerProperties.class)
@Configuration
@Slf4j
@ConditionalOnProperty(prefix = FileServerProperties.PREFIX, name = "type", havingValue = "ALI")
public class AliOssAutoConfigure {

    @Bean
    public FileStrategy getFileStrategy(FileServerProperties fileProperties) {
        return new AliFileStrategyImpl(fileProperties);
    }

    @Bean
    public FileChunkStrategy getFileChunkStrategy(AttachmentService fileService, FileServerProperties fileProperties) {
        return new AliFileChunkStrategyImpl(fileService, fileProperties);
    }
}
