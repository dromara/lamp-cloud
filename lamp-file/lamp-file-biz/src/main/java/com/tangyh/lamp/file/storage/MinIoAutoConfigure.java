package com.tangyh.lamp.file.storage;

import com.tangyh.lamp.file.properties.FileServerProperties;
import com.tangyh.lamp.file.service.AttachmentService;
import com.tangyh.lamp.file.strategy.FileChunkStrategy;
import com.tangyh.lamp.file.strategy.FileStrategy;
import com.tangyh.lamp.file.strategy.impl.minio.MinIoFileChunkStrategyImpl;
import com.tangyh.lamp.file.strategy.impl.minio.MinIoFileStrategyImpl;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = FileServerProperties.PREFIX, name = "type", havingValue = "MIN_IO")
@Slf4j
public class MinIoAutoConfigure {

    /**
     * 初始化minio客户端,不用每次都初始化
     *
     * @return MinioClient
     * @author zuihou
     */
    @Bean
    public MinioClient minioClient(FileServerProperties properties) {
        return new MinioClient.Builder()
                .endpoint(properties.getMinIo().getEndpoint())
                .credentials(properties.getMinIo().getAccessKey(), properties.getMinIo().getSecretKey())
                .build();
    }

    @Bean
    public FileStrategy getFileStrategy(FileServerProperties properties, MinioClient minioClient) {
        return new MinIoFileStrategyImpl(properties, minioClient);
    }

    @Bean
    public FileChunkStrategy getFileChunkStrategy(AttachmentService fileService, FileServerProperties properties) {
        return new MinIoFileChunkStrategyImpl(fileService, properties);
    }
}
