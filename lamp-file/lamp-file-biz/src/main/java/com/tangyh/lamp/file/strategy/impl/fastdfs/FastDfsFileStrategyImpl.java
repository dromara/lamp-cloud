package com.tangyh.lamp.file.strategy.impl.fastdfs;


import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.tangyh.lamp.file.dao.FileMapper;
import com.tangyh.lamp.file.domain.FileDeleteBO;
import com.tangyh.lamp.file.domain.FileGetUrlBO;
import com.tangyh.lamp.file.entity.File;
import com.tangyh.lamp.file.enumeration.FileStorageType;
import com.tangyh.lamp.file.properties.FileServerProperties;
import com.tangyh.lamp.file.strategy.impl.AbstractFileStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author zuihou
 * @date 2020/11/22 5:17 下午
 */

@Component("FAST_DFS")
public class FastDfsFileStrategyImpl extends AbstractFileStrategy {
    private final FastFileStorageClient storageClient;

    public FastDfsFileStrategyImpl(FileServerProperties fileProperties, FastFileStorageClient storageClient,
                                   FileMapper fileMapper) {
        super(fileProperties, fileMapper);
        this.storageClient = storageClient;
    }

    @Override
    protected void uploadFile(File file, MultipartFile multipartFile, String bucket) throws Exception {
        StorePath storePath = storageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(), file.getSuffix(), null);
//        file.setUrl(fileProperties.getf() + storePath.getFullPath());
        file.setBucket(storePath.getGroup());
        file.setPath(storePath.getPath());
        file.setStorageType(FileStorageType.FAST_DFS);
    }

    @Override
    public boolean delete(FileDeleteBO file) {
        storageClient.deleteFile(file.getBucket(), file.getPath());
        return true;
    }

    @Override
    public Map<String, String> findUrl(List<FileGetUrlBO> paths) {
        return null;
    }
}

