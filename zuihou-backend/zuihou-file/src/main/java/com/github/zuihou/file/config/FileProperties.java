package com.github.zuihou.file.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.github.zuihou.file.config.FileProperties.FILE_PREFIX;

/**
 * 文件配置
 *
 * @author zuihou
 * @createTime 2018-01-24 16:40
 */
@ConfigurationProperties(prefix = FILE_PREFIX)
@Data
@NoArgsConstructor
//@Configuration
public class FileProperties {
    public static final String FILE_PREFIX = "zuihou.file";
    /**
     * 文件上传到服务器的临时目录
     */
    private String uploadPath = "/home/uploadfile/";
    /** 远程文件服务器 url前缀 */
    private String remoteUriPrefix = "http://file.server.com/";
}
