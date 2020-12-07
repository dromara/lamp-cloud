package com.tangyh.lamp.file.strategy.impl.ali;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.tangyh.basic.context.ContextUtil;
import com.tangyh.basic.utils.StrPool;
import com.tangyh.lamp.file.domain.FileDeleteDO;
import com.tangyh.lamp.file.entity.Attachment;
import com.tangyh.lamp.file.properties.FileServerProperties;
import com.tangyh.lamp.file.strategy.impl.AbstractFileStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static com.tangyh.basic.utils.DateUtils.DEFAULT_MONTH_FORMAT_SLASH;

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
    protected void uploadFile(Attachment file, MultipartFile multipartFile) throws Exception {
        FileServerProperties.Ali ali = fileProperties.getAli();
        OSS ossClient = new OSSClientBuilder().build(ali.getEndpoint(), ali.getAccessKeyId(),
                ali.getAccessKeySecret());
        String bucketName = ali.getBucketName();
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
        }

        //生成文件名
        String fileName = StrUtil.join(StrPool.EMPTY, UUID.randomUUID().toString(), StrPool.DOT, file.getExt());
        //日期文件夹
        String tenant = ContextUtil.getTenant();
        String relativePath = tenant + StrPool.SLASH + LocalDate.now().format(DateTimeFormatter.ofPattern(DEFAULT_MONTH_FORMAT_SLASH));
        // web服务器存放的绝对路径
        String relativeFileName = relativePath + StrPool.SLASH + fileName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentDisposition("attachment;fileName=" + file.getSubmittedFileName());
        metadata.setContentType(file.getContextType());
        PutObjectRequest request = new PutObjectRequest(bucketName, relativeFileName, multipartFile.getInputStream(), metadata);
        PutObjectResult result = ossClient.putObject(request);

        log.info("result={}", JSONObject.toJSONString(result));

        String url = ali.getUriPrefix() + relativeFileName;
        file.setUrl(StrUtil.replace(url, "\\\\", StrPool.SLASH));
        file.setFilename(fileName);
        file.setRelativePath(relativePath);

        file.setGroup(result.getETag());
        file.setPath(result.getRequestId());

        ossClient.shutdown();
    }

    @Override
    protected void delete(List<FileDeleteDO> list, FileDeleteDO file) {
        FileServerProperties.Ali ali = fileProperties.getAli();
        String bucketName = ali.getBucketName();
        OSS ossClient = new OSSClientBuilder().build(ali.getEndpoint(), ali.getAccessKeyId(),
                ali.getAccessKeySecret());
        ossClient.deleteObject(bucketName, file.getRelativePath() + StrPool.SLASH + file.getFileName());
        ossClient.shutdown();
    }

}
