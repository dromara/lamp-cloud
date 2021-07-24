package com.tangyh.lamp.file.strategy.impl.huawei;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;

import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.model.HttpMethodEnum;
import com.obs.services.model.ObjectMetadata;
import com.obs.services.model.ObsBucket;
import com.obs.services.model.PutObjectRequest;
import com.obs.services.model.TemporarySignatureRequest;
import com.obs.services.model.TemporarySignatureResponse;
import com.tangyh.basic.utils.CollHelper;
import com.tangyh.lamp.file.dao.FileMapper;
import com.tangyh.lamp.file.domain.FileDeleteBO;
import com.tangyh.lamp.file.domain.FileGetUrlBO;
import com.tangyh.lamp.file.entity.File;
import com.tangyh.lamp.file.enumeration.FileStorageType;
import com.tangyh.lamp.file.properties.FileServerProperties;
import com.tangyh.lamp.file.strategy.impl.AbstractFileStrategy;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zuihou
 * @date 2020/11/22 5:00 下午
 */
@Slf4j

@Component("HUAWEI_OSS")
public class HuaweiFileStrategyImpl extends AbstractFileStrategy {
    private static OkHttpClient httpClient = new OkHttpClient.Builder().followRedirects(false)
            .retryOnConnectionFailure(false).cache(null).build();


    public HuaweiFileStrategyImpl(FileServerProperties fileProperties, FileMapper fileMapper) {
        super(fileProperties, fileMapper);
    }

    private static Request.Builder getBuilder(TemporarySignatureResponse res) {
        Request.Builder builder = new Request.Builder();
        for (Map.Entry<String, String> entry : res.getActualSignedRequestHeaders().entrySet()) {
            builder.header(entry.getKey(), entry.getValue());
        }
        return builder.url(res.getSignedUrl());
    }

    private static String getResponse(Request request) throws IOException {
        Call c = httpClient.newCall(request);
        Response res = c.execute();
        log.info("\tStatus:" + res.code());
        if (res.body() != null) {
            String content = res.body().string();

            if (content == null || content.trim().equals("")) {
                log.info("\n");
            } else {
                log.info("\tContent:" + content + "\n\n");
            }
            return content;
        } else {
            log.info("\n");
        }
        res.close();
        return "";
    }

    @Override
    protected void uploadFile(File file, MultipartFile multipartFile, String bucket) throws Exception {
        FileServerProperties.Huawei huawei = fileProperties.getHuawei();

        //生成文件名
        String uniqueFileName = getUniqueFileName(file);
        String path = getPath(file.getBizType(), uniqueFileName);
        bucket = StrUtil.isEmpty(bucket) ? huawei.getBucket() : bucket;

        try (ObsClient obsClient = new ObsClient(huawei.getAccessKey(), huawei.getSecretKey(), huawei.getEndpoint())) {
//            createBucket(obsClient, bucket);

            PutObjectRequest request = new PutObjectRequest(bucket, path, multipartFile.getInputStream());
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(multipartFile.getContentType());
            metadata.setContentDisposition("attachment;fileName=" + URLUtil.encode(file.getOriginalFileName()));
            request.setMetadata(metadata);
            obsClient.putObject(request);

            String url = huawei.getEndpoint() + path;
            file.setUrl(url);
        }
        file.setBucket(bucket);
        file.setPath(path);
        file.setUniqueFileName(uniqueFileName);
        file.setStorageType(FileStorageType.HUAWEI_OSS);
    }

    private void createBucket(ObsClient obsClient, String bucket) throws ObsException {
        boolean exists = obsClient.headBucket(bucket);
        if (!exists) {
            ObsBucket obsBucket = new ObsBucket();
            obsBucket.setBucketName(bucket);
            obsBucket.setLocation(fileProperties.getHuawei().getLocation());
//        // 设置桶访问权限为公共读，默认是私有读写
//        obsBucket.setAcl(AccessControlList.REST_CANNED_PUBLIC_READ);
            obsClient.createBucket(obsBucket);
        }
    }

    @Override
    @SneakyThrows
    public boolean delete(FileDeleteBO file) {
        FileServerProperties.Huawei huawei = fileProperties.getHuawei();
        try (ObsClient obsClient = new ObsClient(huawei.getAccessKey(), huawei.getSecretKey(),
                huawei.getEndpoint())) {
            obsClient.deleteObject(file.getBucket(), file.getPath());
        }
        return true;
    }

    @Override
    @SneakyThrows
    public Map<String, String> findUrl(List<FileGetUrlBO> fileGets) {
        FileServerProperties.Huawei huawei = fileProperties.getHuawei();

        Map<String, String> map = new LinkedHashMap<>(CollHelper.initialCapacity(fileGets.size()));
        try (ObsClient obsClient = new ObsClient(huawei.getAccessKey(), huawei.getSecretKey(),
                huawei.getEndpoint())) {
            for (FileGetUrlBO fileGet : fileGets) {
                TemporarySignatureRequest req = new TemporarySignatureRequest(HttpMethodEnum.GET, 300);
                req.setBucketName(fileGet.getBucket());
                req.setObjectKey(fileGet.getPath());
                req.setExpires(huawei.getExpiry());
                TemporarySignatureResponse res = obsClient.createTemporarySignature(req);
                map.put(fileGet.getPath(), res.getSignedUrl());
            }
        }
        return map;
    }


}
