package com.tangyh.lamp.file.strategy.impl.fastdfs;


import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.tangyh.lamp.file.dao.AttachmentMapper;
import com.tangyh.lamp.file.domain.FileDeleteDO;
import com.tangyh.lamp.file.dto.AttachmentGetVO;
import com.tangyh.lamp.file.entity.Attachment;
import com.tangyh.lamp.file.properties.FileServerProperties;
import com.tangyh.lamp.file.strategy.impl.AbstractFileStrategy;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zuihou
 * @date 2020/11/22 5:17 下午
 */

public class FastDfsFileStrategyImpl extends AbstractFileStrategy {
    private final FastFileStorageClient storageClient;
    private final AttachmentMapper attachmentMapper;

    public FastDfsFileStrategyImpl(FileServerProperties fileProperties, FastFileStorageClient storageClient, AttachmentMapper attachmentMapper) {
        super(fileProperties);
        this.storageClient = storageClient;
        this.attachmentMapper = attachmentMapper;
    }

    @Override
    protected void uploadFile(Attachment file, MultipartFile multipartFile, String bucket) throws Exception {
        StorePath storePath = storageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(), file.getExt(), null);
        file.setUrl(fileProperties.getUriPrefix() + storePath.getFullPath());
        file.setGroup(storePath.getGroup());
        file.setPath(storePath.getPath());
    }

    @Override
    protected void delete(FileDeleteDO file) {
        storageClient.deleteFile(file.getGroup(), file.getPath());
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

