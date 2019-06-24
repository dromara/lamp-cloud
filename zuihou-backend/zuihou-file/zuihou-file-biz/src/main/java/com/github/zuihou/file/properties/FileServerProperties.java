package com.github.zuihou.file.properties;


import com.github.zuihou.file.enumeration.FileStorageType;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import static com.github.zuihou.file.utils.FileDataTypeUtil.URI_SEPARATOR;


/**
 * @author zlt
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "zuihou.file")
@RefreshScope
public class FileServerProperties {
    /**
     * 为以下3个值，指定不同的自动化配置
     * qiniu：七牛oss
     * aliyun：阿里云oss
     * fastdfs：本地部署的fastDFS
     */
    private FileStorageType type = FileStorageType.LOCAL;
    /**
     * 文件访问前缀
     */
    private String uriPrefix = "http://127.0.0.1:10086/";
    /**
     * 文件存储路径
     */
    private String storagePath = "/projects/uploadfile/file/";
    /**
     * 内网通道前缀 主要用于解决某些服务器的无法访问外网ip的问题
     */
    private String innerUriPrefix = "";
    /**
     * 分享文件后的访问地址
     */
    private String shareFileUrl = "";
    private String downByUrl = "";
    private String downByBizId = "";
    private String downById = "";

    public String getDownByUrl(Object... param) {
        return String.format(downByUrl, param);
    }

    public String getDownByBizId(Object... param) {
        return String.format(downByBizId, param);
    }

    public String getDownById(Object... param) {
        return String.format(downById, param);
    }

    public String getShareFileUrl(Object... param) {
        return String.format(shareFileUrl, param);
    }

    public String getInnerUriPrefix() {
        if (!innerUriPrefix.endsWith(URI_SEPARATOR)) {
            innerUriPrefix += URI_SEPARATOR;
        }
        return innerUriPrefix;
    }

    public String getUriPrefix() {
        if (!uriPrefix.endsWith(URI_SEPARATOR)) {
            uriPrefix += URI_SEPARATOR;
        }
        return uriPrefix;
    }

    public String getStoragePath() {
        if (!storagePath.endsWith(URI_SEPARATOR)) {
            storagePath += URI_SEPARATOR;
        }
        return storagePath;
    }
}
