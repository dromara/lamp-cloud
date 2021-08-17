package top.tangyh.lamp.file.properties;


import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.lamp.file.enumeration.FileStorageType;

import java.io.File;
import java.util.HashSet;
import java.util.Set;


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
    /**
     * 公开桶
     * <p>
     * 配置后获取连接时改桶下的 url = urlPrefix + path
     * 使用场景：
     * 1. 富文本编辑器
     * 2. 需要url永久访问的场景
     */
    private Set<String> publicBucket = new HashSet<>();

    private Ali ali = new Ali();
    private QiNiu qiNiu = new QiNiu();
    private Huawei huawei = new Huawei();
    private MinIo minIo = new MinIo();
    private Local local = new Local();
    private FastDfs fastDfs = new FastDfs();

    @Data
    public static class FastDfs {
        /**
         * 文件访问前缀
         */
        private String urlPrefix = "http://127.0.0.1:10000/";

        public String getUrlPrefix() {
            if (!urlPrefix.endsWith(StrPool.SLASH)) {
                urlPrefix += StrPool.SLASH;
            }
            return urlPrefix;
        }
    }

    @Data
    public static class Local {
        private String bucket = "dev";
        /**
         * 文件访问前缀
         */
        private String urlPrefix = "https://static.tangyh.top/file/";
        /**
         * 文件存储路径
         */
        private String storagePath = "/Users/tangyh/webs/projects/uploadfile/file/";
        /**
         * 内网通道前缀 主要用于解决文件下载时， 文件服务无法通过urlPrefix访问文件时，通过此参数转换
         */
        private String innerUrlPrefix = "";

        public String getInnerUrlPrefix() {
            if (!innerUrlPrefix.endsWith(StrPool.SLASH)) {
                innerUrlPrefix += StrPool.SLASH;
            }
            return innerUrlPrefix;
        }

        public String getUrlPrefix() {
            if (!urlPrefix.endsWith(StrPool.SLASH)) {
                urlPrefix += StrPool.SLASH;
            }
            return urlPrefix;
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
        private String urlPrefix = "";
        private String endpoint = "";
        private String accessKey = "";
        private String secretKey = "";
        private String bucket = "";
        private String location = "cn-southwest-2";
        /**
         * 默认 URL有效期 2小时
         */
        private Integer expiry = 3600;

        public String getUrlPrefix() {
            if (StrUtil.isEmpty(urlPrefix)) {
                return getBucket() + StrPool.DOT + getEndpoint();
            }
            if (!urlPrefix.endsWith(StrPool.SLASH)) {
                urlPrefix += StrPool.SLASH;
            }
            return urlPrefix;
        }
    }

    @Data
    public static class Ali {
        private String urlPrefix = "";
        private String endpoint = "";
        private String accessKeyId;
        private String accessKeySecret;
        private String bucket = "";
        /**
         * 默认 URL有效期 2小时
         */
        private Integer expiry = 3600;

        public String getUrlPrefix() {
            if (StrUtil.isEmpty(urlPrefix)) {
                return getBucket() + StrPool.DOT + getEndpoint();
            }
            if (!urlPrefix.endsWith(StrPool.SLASH)) {
                urlPrefix += StrPool.SLASH;
            }
            return urlPrefix;
        }
    }

    public enum Region {
        z0,
        z1,
        z2,
        na0,
        as0,
        ;
    }


    @Data
    public static class QiNiu {
        /**
         * 访问域名
         */
        private String domain = "qiniu.tangyh.top";
        private Boolean useHttps = false;

        /** 华东 z0 华北 z1 华南 z2 北美 na0 东南亚 as0 */
        private Region zone = Region.z0;
        private String accessKey = "1";
        private String secretKey = "2";
        private String bucket = "zuihou_admin_cloud";

        /**
         * 默认 URL有效期 2小时
         */
        private Integer expiry = 3600;

        public String getUrlPrefix() {
            String prefix = useHttps ? StrPool.HTTPS_PREFIX : StrPool.HTTP_PREFIX;
            return prefix + domain + StrPool.SLASH;
        }
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

        public String getUrlPrefix() {
            return endpoint;
        }
    }
}
