package com.github.zuihou.file.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static com.github.zuihou.file.config.FileProperties.FASTDFS_PREFIX;

/**
 * 文件配置
 *
 * @author zuihou
 * @createTime 2018-01-24 16:40
 */
@ConfigurationProperties(prefix = FASTDFS_PREFIX)
@Data
@NoArgsConstructor
@Configuration
public class FileProperties {
    public static final String FASTDFS_PREFIX = "zuihou.file";

    /**
     * 文件上传到服务器的临时目录前缀
     */
    private String uploadPathPrefix = "/home/uploadfile/";
    /**
     * 远程文件服务器 访问url前缀
     */
    private String remoteUriPrefix = "http://file.server.com/";

    private Fastdfs fastdfs = new Fastdfs();

    @Data
    @NoArgsConstructor
    public static class Fastdfs {
        /**
         * 是否启动
         */
        private boolean enabled = true;
    }
}
