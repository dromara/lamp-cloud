package top.tangyh.lamp.file.storage;

import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import top.tangyh.lamp.file.properties.FileServerProperties;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class FileAutoConfigure {
    private final FileServerProperties fileServerProperties;

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

    /**
     * 华东机房,配置自己空间所在的区域
     */
    @Bean
    public com.qiniu.storage.Configuration qiNiuConfig() {
        FileServerProperties.QiNiu qiNiu = fileServerProperties.getQiNiu();
        if ("z0".equals(qiNiu.getZone())) {
            return new com.qiniu.storage.Configuration(Region.region0());
        } else if ("z1".equals(qiNiu.getZone())) {
            return new com.qiniu.storage.Configuration(Region.region1());
        } else if ("z2".equals(qiNiu.getZone())) {
            return new com.qiniu.storage.Configuration(Region.region2());
        } else if ("na0".equals(qiNiu.getZone())) {
            return new com.qiniu.storage.Configuration(Region.regionNa0());
        } else if ("as0".equals(qiNiu.getZone())) {
            return new com.qiniu.storage.Configuration(Region.regionAs0());
        } else {
            return new com.qiniu.storage.Configuration(Region.region0());
        }

    }

    /**
     * 构建一个七牛上传工具实例
     */
    @Bean
    public UploadManager uploadManager() {
        return new UploadManager(qiNiuConfig());
    }

    /**
     * 认证信息实例
     *
     * @return
     */
    @Bean
    public Auth auth() {
        FileServerProperties.QiNiu qiNiu = fileServerProperties.getQiNiu();
        return Auth.create(qiNiu.getAccessKey(), qiNiu.getSecretKey());
    }

    /**
     * 构建七牛空间管理实例
     */
    @Bean
    public BucketManager bucketManager() {
        return new BucketManager(auth(), qiNiuConfig());
    }

}
