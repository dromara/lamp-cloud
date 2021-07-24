package com.tangyh.lamp.file.strategy.impl.ali;

import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import com.tangyh.basic.jackson.JsonUtil;
import com.tangyh.basic.utils.CollHelper;
import com.tangyh.lamp.file.dao.FileMapper;
import com.tangyh.lamp.file.domain.FileDeleteBO;
import com.tangyh.lamp.file.domain.FileGetUrlBO;
import com.tangyh.lamp.file.entity.File;
import com.tangyh.lamp.file.enumeration.FileStorageType;
import com.tangyh.lamp.file.properties.FileServerProperties;
import com.tangyh.lamp.file.strategy.impl.AbstractFileStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zuihou
 * @date 2020/11/22 4:57 下午
 */
@Slf4j

@Component("ALI_OSS")
public class AliFileStrategyImpl extends AbstractFileStrategy {
    public AliFileStrategyImpl(FileServerProperties fileProperties, FileMapper fileMapper) {
        super(fileProperties, fileMapper);
    }

    @Override
    protected void uploadFile(File file, MultipartFile multipartFile, String bucket) throws Exception {
        FileServerProperties.Ali ali = fileProperties.getAli();
        OSS ossClient = new OSSClientBuilder().build(ali.getEndpoint(), ali.getAccessKeyId(),
                ali.getAccessKeySecret());
        if (StrUtil.isEmpty(bucket)) {
            bucket = ali.getBucketName();
        }
        if (!ossClient.doesBucketExist(bucket)) {
            ossClient.createBucket(bucket);
        }

        //生成文件名
        String uniqueFileName = getUniqueFileName(file);

        // 企业id/功能点/年/月/日/file
        String path = getPath(file.getBizType(), uniqueFileName);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentDisposition("attachment;fileName=" + file.getOriginalFileName());
        metadata.setContentType(file.getContentType());
        PutObjectRequest request = new PutObjectRequest(bucket, path, multipartFile.getInputStream(), metadata);
        PutObjectResult result = ossClient.putObject(request);

        log.info("result={}", JsonUtil.toJson(result));

        file.setUniqueFileName(uniqueFileName);
        file.setBucket(bucket);
        file.setPath(path);
        file.setStorageType(FileStorageType.ALI_OSS);

        ossClient.shutdown();
    }

    @Override
    public boolean delete(FileDeleteBO file) {
        FileServerProperties.Ali ali = fileProperties.getAli();
        String bucketName = StrUtil.isEmpty(file.getBucket()) ? ali.getBucketName() : file.getBucket();
        OSS ossClient = new OSSClientBuilder().build(ali.getEndpoint(), ali.getAccessKeyId(), ali.getAccessKeySecret());
        ossClient.deleteObject(bucketName, file.getPath());
        ossClient.shutdown();
        return true;
    }

    @Override
    public Map<String, String> findUrl(List<FileGetUrlBO> fileGets) {
        OSS ossClient = createOss();

        Map<String, String> map = new LinkedHashMap<>(CollHelper.initialCapacity(fileGets.size()));

        for (FileGetUrlBO fileGet : fileGets) {
            map.put(fileGet.getPath(), generatePresignedUrl(fileGet.getBucket(), fileGet.getPath()));
        }
        ossClient.shutdown();
        return map;
    }

    /**
     * 获取一个ossclient
     *
     * @return
     */
    public OSS createOss() {
        FileServerProperties.Ali ali = fileProperties.getAli();
        String accessKeyId = ali.getAccessKeyId();
        String accessKeySecret = ali.getAccessKeySecret();
        String endPoint = ali.getEndpoint();
        return new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
    }

    /**
     * 获取有访问权限的路径地址
     *
     * @param bucketName
     * @param key
     * @return
     */
    private String generatePresignedUrl(String bucketName, String key) {
        FileServerProperties.Ali ali = fileProperties.getAli();
        bucketName = StrUtil.isEmpty(bucketName) ? ali.getBucketName() : bucketName;
        OSS oss = createOss();
        Date date = new Date(System.currentTimeMillis() + ali.getExpiry());
        URL url = oss.generatePresignedUrl(bucketName, key, date);
        return url.toString();
    }
}
