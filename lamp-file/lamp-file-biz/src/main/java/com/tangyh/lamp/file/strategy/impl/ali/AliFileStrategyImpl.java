package com.tangyh.lamp.file.strategy.impl.ali;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.tangyh.lamp.file.domain.FileDeleteDO;
import com.tangyh.lamp.file.dto.AttachmentGetVO;
import com.tangyh.lamp.file.entity.Attachment;
import com.tangyh.lamp.file.properties.FileServerProperties;
import com.tangyh.lamp.file.strategy.impl.AbstractFileStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zuihou
 * @date 2020/11/22 4:57 下午
 */
@Slf4j
public class AliFileStrategyImpl extends AbstractFileStrategy {
    public AliFileStrategyImpl(FileServerProperties fileProperties) {
        super(fileProperties);
    }

    @Override
    protected void uploadFile(Attachment file, MultipartFile multipartFile, String bucket) throws Exception {
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
        String path = getPath(uniqueFileName, file.getBizType());

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentDisposition("attachment;fileName=" + file.getOriginalFileName());
        metadata.setContentType(file.getContentType());
        PutObjectRequest request = new PutObjectRequest(bucket, path, multipartFile.getInputStream(), metadata);
        PutObjectResult result = ossClient.putObject(request);

        log.info("result={}", JSONObject.toJSONString(result));

        String url = ali.getUriPrefix() + path;
        file.setUniqueFileName(uniqueFileName);
        file.setGroup(bucket);
        file.setPath(path);
        // TODO 这里可能有bug， 私有桶的文件url无法访问
        file.setUrl(url);

        ossClient.shutdown();
    }

    @Override
    protected void delete(FileDeleteDO file) {
        FileServerProperties.Ali ali = fileProperties.getAli();
        String bucketName = StrUtil.isEmpty(file.getGroup()) ? ali.getBucketName() : file.getGroup();
        OSS ossClient = new OSSClientBuilder().build(ali.getEndpoint(), ali.getAccessKeyId(), ali.getAccessKeySecret());
        ossClient.deleteObject(bucketName, file.getPath());
        ossClient.shutdown();
    }

    @Override
    public List<String> getUrls(List<AttachmentGetVO> paths, Integer expiry) {
        return null;
    }

    @Override
    public String getUrl(String bucket, String path, Integer expiry) {
        return null;
    }
}
