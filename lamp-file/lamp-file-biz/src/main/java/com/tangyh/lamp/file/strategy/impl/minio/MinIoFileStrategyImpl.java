package com.tangyh.lamp.file.strategy.impl.minio;

import com.tangyh.basic.context.ContextUtil;
import com.tangyh.basic.utils.StrPool;
import com.tangyh.lamp.file.domain.FileDeleteDO;
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
import java.util.StringJoiner;
import java.util.UUID;

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
    protected void uploadFile(Attachment file, MultipartFile multipartFile) throws Exception {
        //生成文件名
        String fileName = UUID.randomUUID().toString() + StrPool.DOT + file.getExt();

        String tenant = ContextUtil.getTenant();

        String dateFolder = getDateFolder();

        // 企业id/应用id/功能点/年/月/日/file
        String path = new StringJoiner(StrPool.SLASH)
                .add(tenant).add(dateFolder).add(fileName).toString();

        minioClient.putObject(PutObjectArgs.builder()
                .stream(multipartFile.getInputStream(), multipartFile.getSize(), PutObjectArgs.MIN_MULTIPART_SIZE)
                .object(path)
                .contentType(multipartFile.getContentType())
                .bucket(fileProperties.getMinIo().getBucket())
                .build());

        file.setPath(path);
        file.setFilename(fileName);

        String url = getUrl(path, 172800);
        file.setUrl(url);
    }

    @SneakyThrows
    @Override
    protected void delete(List<FileDeleteDO> list, FileDeleteDO file) {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(fileProperties.getMinIo().getBucket()).object(file.getPath()).build());
    }

    @Override
    @SneakyThrows
    public List<String> getUrls(List<String> paths, Integer expiry) {
        List<String> list = new ArrayList<>();
        for (String path : paths) {
            String url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(fileProperties.getMinIo().getBucket()).object(path).
                            method(Method.GET).expiry(expiry).build());
            list.add(url);
        }
        return list;
    }

    @Override
    public String getUrl(String path, Integer expiry) {
        return getUrls(Arrays.asList(path), expiry).get(0);
    }
}
