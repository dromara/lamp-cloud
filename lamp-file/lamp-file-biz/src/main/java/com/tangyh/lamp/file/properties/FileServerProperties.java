package com.tangyh.lamp.file.properties;


import com.tangyh.basic.utils.StrPool;
import com.tangyh.lamp.file.enumeration.FileStorageType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.io.File;


/**
 * @author zuihou
 */
@Setter
@Getter
@ConfigurationProperties(prefix = FileServerProperties.PREFIX)
@RefreshScope
public class FileServerProperties {
    public static final String PREFIX = "lamp.file";
    /**
     * 以下6个值，指定不同的默认上传存储策略
     * LOCAL: 本地存储
     * FAST_DFS: FastDFS
     * MIN_IO: MinIO存储
     * QINIU_OSS：七牛oss
     * ALI_OSS：阿里云oss
     * HUAWEI_OSS： 华为云 OBS
     */
    private FileStorageType storageType = FileStorageType.LOCAL;
    /** 调用接口删除附件时，是否删除文件系统中的文件 */
    private Boolean delFile = false;

    private Ali ali = new Ali();
    private QiNiu qiNiu = new QiNiu();
    private Huawei huawei = new Huawei();
    private MinIo minIo = new MinIo();
    private Local local = new Local();

    @Data
    public static class Local {
        /**
         * 文件访问前缀
         */
        private String endpoint = "http://127.0.0.1:10000/";
        /**
         * 文件存储路径
         */
        private String storagePath = "/Users/tangyh/webs/projects/uploadfile/file/";
        /**
         * 内网通道前缀 主要用于解决文件下载时， 文件服务无法通过uriPrefix访问文件时，通过此参数转换
         */
        private String innerUriPrefix = "";

        public String getInnerUriPrefix() {
            return innerUriPrefix;
        }

        public String getEndpoint() {
            if (!endpoint.endsWith(StrPool.SLASH)) {
                endpoint += StrPool.SLASH;
            }
            return endpoint;
        }

        public String getStoragePath() {
            if (!storagePath.endsWith(File.separator)) {
                storagePath += File.separator;
            }
            return storagePath;
        }


    }

    @Data
    public static class Huawei {
        private String uriPrefix = "";
        private String endpoint = "";
        private String accessKey = "";
        private String secretKey = "";
        private String bucket = "";
        private String location = "cn-southwest-2";
        /**
         * 默认 URL有效期 2小时
         */
        private Integer expiry = 3600;
    }

    @Data
    public static class Ali {
        private String uriPrefix;
        private String endpoint;
        private String accessKeyId;
        private String accessKeySecret;
        private String bucketName;
        /**
         * 默认 URL有效期 2小时
         */
        private Integer expiry = 3600;
    }

    @Data
    public static class QiNiu {
        /** 华东 z0 华北 z1 华南 z2 北美 na0 东南亚 as0 */
        private String zone = "z0";
        private String accessKey = "1";
        private String secretKey = "2";
        private String bucket = "zuihou_admin_cloud";
        /**
         * 默认 URL有效期 2小时
         */
        private Integer expiry = 3600;
    }

    @Data
    public static class MinIo {
        /**
         * minio地址+端口号
         */
        private String endpoint = "http://127.0.0.1:9000";

        /**
         * minio用户名
         */
        private String accessKey = "minioadmin";

        /**
         * minio密码
         */
        private String secretKey = "minioadmin";

        /**
         * 文件桶的名称
         */
        private String bucket = "dev";
        /**
         * 默认 URL有效期 2小时
         */
        private Integer expiry = 3600;
    }
}
