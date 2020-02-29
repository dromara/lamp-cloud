package com.github.zuihou.file.storage;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import com.github.zuihou.base.R;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.file.domain.FileDeleteDO;
import com.github.zuihou.file.dto.chunk.FileChunksMergeDTO;
import com.github.zuihou.file.entity.File;
import com.github.zuihou.file.properties.FileServerProperties;
import com.github.zuihou.file.strategy.impl.AbstractFileChunkStrategy;
import com.github.zuihou.file.strategy.impl.AbstractFileStrategy;
import com.github.zuihou.utils.StrPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static com.github.zuihou.utils.DateUtils.DEFAULT_MONTH_FORMAT_SLASH;

/**
 * 阿里OSS
 *
 * @author zuihou
 * @date 2019/08/09
 */
@EnableConfigurationProperties(FileServerProperties.class)
@Configuration
@Slf4j
@ConditionalOnProperty(name = "zuihou.file.type", havingValue = "ALI")
public class AliOssAutoConfigure {

    @Service
    public class AliServiceImpl extends AbstractFileStrategy {
        @Override
        protected void uploadFile(File file, MultipartFile multipartFile) throws Exception {
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
            String tenant = BaseContextHandler.getTenant();
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


    @Service
    public class AliChunkServiceImpl extends AbstractFileChunkStrategy {
        @Override
        protected void copyFile(File file) {
            FileServerProperties.Ali ali = fileProperties.getAli();
            String sourceBucketName = ali.getBucketName();
            String destinationBucketName = ali.getBucketName();
            OSS ossClient = new OSSClientBuilder().build(ali.getEndpoint(), ali.getAccessKeyId(),
                    ali.getAccessKeySecret());

            String sourceObjectName = file.getRelativePath() + StrPool.SLASH + file.getFilename();
            String fileName = UUID.randomUUID().toString() + StrPool.DOT + file.getExt();
            String destinationObjectName = file.getRelativePath() + StrPool.SLASH + fileName;
            ObjectMetadata objectMetadata = ossClient.getObjectMetadata(sourceBucketName, sourceObjectName);
            // 获取被拷贝文件的大小。

            // 获取被拷贝文件的大小。
            long contentLength = objectMetadata.getContentLength();

            // 设置分片大小为10MB。
            long partSize = 1024 * 1024 * 10;

            // 计算分片总数。
            int partCount = (int) (contentLength / partSize);
            if (contentLength % partSize != 0) {
                partCount++;
            }
            log.info("total part count:{}", partCount);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentDisposition("attachment;fileName=" + file.getSubmittedFileName());
            metadata.setContentType(file.getContextType());
            // 初始化拷贝任务。可以通过InitiateMultipartUploadRequest指定目标文件元信息。
            InitiateMultipartUploadRequest initiateMultipartUploadRequest = new InitiateMultipartUploadRequest(destinationBucketName, destinationObjectName, metadata);
            InitiateMultipartUploadResult initiateMultipartUploadResult = ossClient.initiateMultipartUpload(initiateMultipartUploadRequest);
            String uploadId = initiateMultipartUploadResult.getUploadId();

            // 分片拷贝。
            List<PartETag> partETags = new ArrayList<>();
            for (int i = 0; i < partCount; i++) {
                // 计算每个分片的大小。
                long skipBytes = partSize * i;
                long size = partSize < contentLength - skipBytes ? partSize : contentLength - skipBytes;

                // 创建UploadPartCopyRequest。可以通过UploadPartCopyRequest指定限定条件。
                UploadPartCopyRequest uploadPartCopyRequest =
                        new UploadPartCopyRequest(sourceBucketName, sourceObjectName, destinationBucketName, destinationObjectName);
                uploadPartCopyRequest.setUploadId(uploadId);
                uploadPartCopyRequest.setPartSize(size);
                uploadPartCopyRequest.setBeginIndex(skipBytes);
                uploadPartCopyRequest.setPartNumber(i + 1);
                UploadPartCopyResult uploadPartCopyResult = ossClient.uploadPartCopy(uploadPartCopyRequest);

                // 将返回的分片ETag保存到partETags中。
                partETags.add(uploadPartCopyResult.getPartETag());
            }

            // 提交分片拷贝任务。
            CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(
                    destinationBucketName, destinationObjectName, uploadId, partETags);
            ossClient.completeMultipartUpload(completeMultipartUploadRequest);

            String url = new StringBuilder(ali.getUriPrefix())
                    .append(file.getRelativePath())
                    .append(StrPool.SLASH)
                    .append(fileName)
                    .toString();
            file.setUrl(StringUtils.replace(url, "\\\\", StrPool.SLASH));
            file.setFilename(fileName);

            // 关闭OSSClient。
            ossClient.shutdown();
        }

        @Override
        protected R<File> merge(List<java.io.File> files, String path, String fileName, FileChunksMergeDTO info) throws IOException {
            FileServerProperties.Ali ali = fileProperties.getAli();
            String bucketName = ali.getBucketName();
            OSS ossClient = new OSSClientBuilder().build(ali.getEndpoint(), ali.getAccessKeyId(),
                    ali.getAccessKeySecret());

            //日期文件夹
            String relativePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
            // web服务器存放的绝对路径
            String relativeFileName = relativePath + StrPool.SLASH + fileName;

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentDisposition("attachment;fileName=" + info.getSubmittedFileName());
            metadata.setContentType(info.getContextType());
            //步骤1：初始化一个分片上传事件。
            InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, relativeFileName, metadata);
            InitiateMultipartUploadResult result = ossClient.initiateMultipartUpload(request);
            // 返回uploadId，它是分片上传事件的唯一标识，您可以根据这个ID来发起相关的操作，如取消分片上传、查询分片上传等。
            String uploadId = result.getUploadId();

            // partETags是PartETag的集合。PartETag由分片的ETag和分片号组成。
            List<PartETag> partETags = new ArrayList<PartETag>();
            for (int i = 0; i < files.size(); i++) {
                java.io.File file = files.get(i);
                FileInputStream in = FileUtils.openInputStream(file);

                UploadPartRequest uploadPartRequest = new UploadPartRequest();
                uploadPartRequest.setBucketName(bucketName);
                uploadPartRequest.setKey(relativeFileName);
                uploadPartRequest.setUploadId(uploadId);
                uploadPartRequest.setInputStream(in);
                // 设置分片大小。除了最后一个分片没有大小限制，其他的分片最小为100KB。
                uploadPartRequest.setPartSize(file.length());
                // 设置分片号。每一个上传的分片都有一个分片号，取值范围是1~10000，如果超出这个范围，OSS将返回InvalidArgument的错误码。
                uploadPartRequest.setPartNumber(i + 1);

                // 每个分片不需要按顺序上传，甚至可以在不同客户端上传，OSS会按照分片号排序组成完整的文件。
                UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);

                // 每次上传分片之后，OSS的返回结果会包含一个PartETag。PartETag将被保存到partETags中。
                partETags.add(uploadPartResult.getPartETag());
            }

            /* 步骤3：完成分片上传。 */
            // 排序。partETags必须按分片号升序排列。
            partETags.sort(Comparator.comparingInt(PartETag::getPartNumber));

            // 在执行该操作时，需要提供所有有效的partETags。OSS收到提交的partETags后，会逐一验证每个分片的有效性。当所有的数据分片验证通过后，OSS将把这些分片组合成一个完整的文件。
            CompleteMultipartUploadRequest completeMultipartUploadRequest =
                    new CompleteMultipartUploadRequest(bucketName, relativeFileName, uploadId, partETags);

            CompleteMultipartUploadResult uploadResult = ossClient.completeMultipartUpload(completeMultipartUploadRequest);

            String url = new StringBuilder(ali.getUriPrefix())
                    .append(relativePath)
                    .append(StrPool.SLASH)
                    .append(fileName)
                    .toString();
            File filePo = File.builder()
                    .relativePath(relativePath)
                    .group(uploadResult.getETag())
                    .path(uploadResult.getRequestId())
                    .url(StringUtils.replace(url, "\\\\", StrPool.SLASH))
                    .build();

            // 关闭OSSClient。
            ossClient.shutdown();
            return R.success(filePo);
        }
    }
}
