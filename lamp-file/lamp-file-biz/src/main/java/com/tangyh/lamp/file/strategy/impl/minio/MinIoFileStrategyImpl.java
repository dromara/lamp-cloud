package com.tangyh.lamp.file.strategy.impl.minio;

import cn.hutool.core.util.StrUtil;
import com.tangyh.lamp.file.domain.FileDeleteDO;
import com.tangyh.lamp.file.dto.AttachmentGetVO;
import com.tangyh.lamp.file.entity.Attachment;
import com.tangyh.lamp.file.properties.FileServerProperties;
import com.tangyh.lamp.file.strategy.impl.AbstractFileStrategy;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.http.Method;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zuihou
 * @date 2020/11/22 5:00 下午
 */
@Slf4j
public class MinIoFileStrategyImpl extends AbstractFileStrategy {
    private final MinioClient minioClient;

    public MinIoFileStrategyImpl(FileServerProperties fileProperties, MinioClient minioClient) {
        super(fileProperties);
        this.minioClient = minioClient;
    }

    @Override
    protected void uploadFile(Attachment file, MultipartFile multipartFile, String bucket) throws Exception {
        //生成文件名
        String uniqueFileName = getUniqueFileName(file);

        // 企业id/功能点/年/月/日/file
        String path = getPath(uniqueFileName, file.getBizType());

        if (StrUtil.isEmpty(bucket)) {
            bucket = fileProperties.getMinIo().getBucket();
        }

        minioClient.putObject(PutObjectArgs.builder()
                .stream(multipartFile.getInputStream(), multipartFile.getSize(), PutObjectArgs.MIN_MULTIPART_SIZE)
                .object(path)
                .contentType(multipartFile.getContentType())
                .bucket(bucket)
                .build());

        file.setGroup(bucket);
        file.setPath(path);
        file.setUniqueFileName(uniqueFileName);
//        String url = getUrl(bucket, path, 172800);
        file.setUrl(path);
    }

    @SneakyThrows
    @Override
    protected void delete(FileDeleteDO file) {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(file.getGroup()).object(file.getPath()).build());
    }

    @Override
    @SneakyThrows
    public List<String> getUrls(List<AttachmentGetVO> paths, Integer expiry) {
        List<String> list = new ArrayList<>();
        for (AttachmentGetVO attachmentGet : paths) {
            String bucket = StrUtil.isEmpty(attachmentGet.getGroup()) ? fileProperties.getMinIo().getBucket() : attachmentGet.getGroup();
            String url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(bucket).object(attachmentGet.getPath()).
                            method(Method.GET).expiry(expiry).build());
            list.add(url);
        }
        return list;
    }

    @Override
    public String getUrl(String bucket, String path, Integer expiry) {
        bucket = StrUtil.isEmpty(bucket) ? fileProperties.getMinIo().getBucket() : bucket;
        return getUrls(Arrays.asList(AttachmentGetVO.builder().group(bucket).path(path).build()), expiry).get(0);
    }
}
