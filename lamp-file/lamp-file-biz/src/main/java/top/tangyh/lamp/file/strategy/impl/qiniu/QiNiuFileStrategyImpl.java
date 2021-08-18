package top.tangyh.lamp.file.strategy.impl.qiniu;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.DownloadUrl;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.tangyh.basic.jackson.JsonUtil;
import top.tangyh.basic.utils.CollHelper;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.lamp.file.dao.FileMapper;
import top.tangyh.lamp.file.domain.FileDeleteBO;
import top.tangyh.lamp.file.domain.FileGetUrlBO;
import top.tangyh.lamp.file.entity.File;
import top.tangyh.lamp.file.enumeration.FileStorageType;
import top.tangyh.lamp.file.properties.FileServerProperties;
import top.tangyh.lamp.file.strategy.impl.AbstractFileStrategy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zuihou
 * @date 2020/11/22 4:57 下午
 */
@Slf4j

@Component("QINIU_OSS")
public class QiNiuFileStrategyImpl extends AbstractFileStrategy {
    private static StringMap PUT_POLICY = new StringMap();

    static {
        PUT_POLICY.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":$(imageInfo.width), \"height\":${imageInfo.height}}");
    }

    private final UploadManager uploadManager;
    private final BucketManager bucketManager;
    private final Auth auth;

    public QiNiuFileStrategyImpl(FileServerProperties fileProperties, FileMapper fileMapper,
                                 UploadManager uploadManager, BucketManager bucketManager, Auth auth) {
        super(fileProperties, fileMapper);
        this.uploadManager = uploadManager;
        this.bucketManager = bucketManager;
        this.auth = auth;
    }

    /**
     * 获取上传凭证
     *
     * @return
     */
    private String getUploadToken(String bucket) {
        FileServerProperties.QiNiu qiNiu = fileProperties.getQiNiu();
        String token = this.auth.uploadToken(bucket, null, qiNiu.getExpiry(), PUT_POLICY);
        log.info("token={}", token);
        return token;
    }

    @Override
    protected void uploadFile(File file, MultipartFile multipartFile, String bucket) throws Exception {
        FileServerProperties.QiNiu qiNiu = fileProperties.getQiNiu();
        if (StrUtil.isEmpty(bucket)) {
            bucket = qiNiu.getBucket();
        }

        //生成文件名
        String uniqueFileName = getUniqueFileName(file);

        // 企业id/功能点/年/月/日/file
        String path = getPath(file.getBizType(), uniqueFileName);

        StringMap params = new StringMap();
        params.put("x-qn-meta-contentDisposition", "attachment;fileName=" + file.getOriginalFileName());
        Response response = this.uploadManager.put(multipartFile.getInputStream(), path, getUploadToken(bucket),
                params, file.getContentType());

        log.info("response={}", JsonUtil.toJson(response));

        if (response.statusCode == 200) {
            DefaultPutRet defaultPutRet = JsonUtil.parse(response.bodyString(), DefaultPutRet.class);
            log.info("defaultPutRet={}", JsonUtil.toJson(defaultPutRet));

            file.setUniqueFileName(uniqueFileName);
            file.setBucket(bucket);
            file.setPath(path);
        }
        file.setUrl(qiNiu.getUrlPrefix() + path);
        file.setStorageType(FileStorageType.QINIU_OSS);
    }

    @SneakyThrows
    @Override
    public boolean delete(FileDeleteBO file) {
        FileServerProperties.QiNiu qiNiu = fileProperties.getQiNiu();
        String bucket = StrUtil.isEmpty(file.getBucket()) ? qiNiu.getBucket() : file.getBucket();
        Response response = bucketManager.delete(bucket, file.getPath());
        log.info("response={}", JsonUtil.toJson(response));
        return true;
    }


    @SneakyThrows
    @Override
    public Map<String, String> findUrl(List<FileGetUrlBO> fileGets) {
        Map<String, String> map = new LinkedHashMap<>(CollHelper.initialCapacity(fileGets.size()));

        FileServerProperties.QiNiu qiNiu = fileProperties.getQiNiu();
        Set<String> publicBucket = fileProperties.getPublicBucket();

        for (FileGetUrlBO fileGet : fileGets) {
            String bucket = StrUtil.isEmpty(fileGet.getBucket()) ? qiNiu.getBucket() : fileGet.getBucket();
            try {
                if (CollUtil.isNotEmpty(publicBucket) && publicBucket.contains(bucket)) {
                    StringBuilder url = new StringBuilder(qiNiu.getUrlPrefix())
                            .append(fileGet.getBucket())
                            .append(StrPool.SLASH)
                            .append(fileGet.getPath());
                    map.put(fileGet.getPath(), url.toString());
                } else {
                    DownloadUrl url = new DownloadUrl(qiNiu.getDomain(), false, fileGet.getPath());
                    url.setAttname(fileGet.getOriginalFileName());
                    long deadline = System.currentTimeMillis() / 1000 + qiNiu.getExpiry();
                    String urlString = url.buildURL(auth, deadline);
                    map.put(fileGet.getPath(), urlString);
                }
            } catch (Exception e) {
                log.warn("加载文件url地址失败，请确保yml中第三方存储参数配置正确. bucket={}, , 文件名={} path={}", bucket, fileGet.getOriginalFileName(), fileGet.getPath(), e);
                map.put(fileGet.getPath(), StrPool.EMPTY);
            }
        }
        return map;
    }


}
