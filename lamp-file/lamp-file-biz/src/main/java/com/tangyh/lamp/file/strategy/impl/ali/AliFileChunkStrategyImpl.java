package com.tangyh.lamp.file.strategy.impl.ali;

import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CompleteMultipartUploadRequest;
import com.aliyun.oss.model.CompleteMultipartUploadResult;
import com.aliyun.oss.model.InitiateMultipartUploadRequest;
import com.aliyun.oss.model.InitiateMultipartUploadResult;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PartETag;
import com.aliyun.oss.model.UploadPartCopyRequest;
import com.aliyun.oss.model.UploadPartCopyResult;
import com.aliyun.oss.model.UploadPartRequest;
import com.aliyun.oss.model.UploadPartResult;
import com.tangyh.basic.base.R;
import com.tangyh.basic.utils.StrPool;
import com.tangyh.lamp.file.dto.chunk.FileChunksMergeDTO;
import com.tangyh.lamp.file.entity.Attachment;
import com.tangyh.lamp.file.properties.FileServerProperties;
import com.tangyh.lamp.file.service.AttachmentService;
import com.tangyh.lamp.file.strategy.impl.AbstractFileChunkStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/**
 * @author zuihou
 * @date 2020/11/22 4:58 下午
 */
@Slf4j
public class AliFileChunkStrategyImpl extends AbstractFileChunkStrategy {
    public AliFileChunkStrategyImpl(AttachmentService fileService, FileServerProperties fileProperties) {
        super(fileService, fileProperties);
    }

    @Override
    protected void copyFile(Attachment file) {
        FileServerProperties.Ali ali = fileProperties.getAli();
        String sourceBucketName = ali.getBucketName();
        String destinationBucketName = ali.getBucketName();
        OSS ossClient = new OSSClientBuilder().build(ali.getEndpoint(), ali.getAccessKeyId(),
                ali.getAccessKeySecret());

        String sourceObjectName = file.getPath();
        String fileName = UUID.randomUUID() + StrPool.DOT + file.getExt();
        String destinationObjectName = StrUtil.subBefore(file.getPath(), "/" + file.getUniqueFileName(), true) + StrPool.SLASH + fileName;
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
        metadata.setContentDisposition("attachment;fileName=" + file.getOriginalFileName());
        metadata.setContentType(file.getContentType());
        // 初始化拷贝任务。可以通过InitiateMultipartUploadRequest指定目标文件元信息。
        InitiateMultipartUploadRequest initiateMultipartUploadRequest = new InitiateMultipartUploadRequest(destinationBucketName, destinationObjectName, metadata);
        InitiateMultipartUploadResult initiateMultipartUploadResult = ossClient.initiateMultipartUpload(initiateMultipartUploadRequest);
        String uploadId = initiateMultipartUploadResult.getUploadId();

        // 分片拷贝。
        List<PartETag> partTags = new ArrayList<>();
        for (int i = 0; i < partCount; i++) {
            // 计算每个分片的大小。
            long skipBytes = partSize * i;
            long size = Math.min(partSize, contentLength - skipBytes);

            // 创建UploadPartCopyRequest。可以通过UploadPartCopyRequest指定限定条件。
            UploadPartCopyRequest uploadPartCopyRequest =
                    new UploadPartCopyRequest(sourceBucketName, sourceObjectName, destinationBucketName, destinationObjectName);
            uploadPartCopyRequest.setUploadId(uploadId);
            uploadPartCopyRequest.setPartSize(size);
            uploadPartCopyRequest.setBeginIndex(skipBytes);
            uploadPartCopyRequest.setPartNumber(i + 1);
            UploadPartCopyResult uploadPartCopyResult = ossClient.uploadPartCopy(uploadPartCopyRequest);

            // 将返回的分片ETag保存到partTags中。
            partTags.add(uploadPartCopyResult.getPartETag());
        }

        // 提交分片拷贝任务。
        CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(
                destinationBucketName, destinationObjectName, uploadId, partTags);
        ossClient.completeMultipartUpload(completeMultipartUploadRequest);

        String url = ali.getUriPrefix() + destinationObjectName;
        file.setUrl(StrUtil.replace(url, "\\\\", StrPool.SLASH));
        file.setUniqueFileName(fileName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Override
    protected R<Attachment> merge(List<java.io.File> files, String path, String fileName, FileChunksMergeDTO info) throws IOException {
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

        // partTags是PartETag的集合。PartETag由分片的ETag和分片号组成。
        List<PartETag> partTags = new ArrayList<>();
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
            partTags.add(uploadPartResult.getPartETag());
        }

        /* 步骤3：完成分片上传。 */
        // 排序。partETags必须按分片号升序排列。
        partTags.sort(Comparator.comparingInt(PartETag::getPartNumber));

        // 在执行该操作时，需要提供所有有效的partETags。OSS收到提交的partETags后，会逐一验证每个分片的有效性。当所有的数据分片验证通过后，OSS将把这些分片组合成一个完整的文件。
        CompleteMultipartUploadRequest completeMultipartUploadRequest =
                new CompleteMultipartUploadRequest(bucketName, relativeFileName, uploadId, partTags);

        CompleteMultipartUploadResult uploadResult = ossClient.completeMultipartUpload(completeMultipartUploadRequest);

        String url = ali.getUriPrefix() +
                relativePath +
                StrPool.SLASH +
                fileName;
        Attachment filePo = Attachment.builder()
                .group(uploadResult.getBucketName())
                .path(path)
                .url(StrUtil.replace(url, "\\\\", StrPool.SLASH))
                .build();

        // 关闭OSSClient。
        ossClient.shutdown();
        return R.success(filePo);
    }
}
