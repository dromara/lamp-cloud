package com.github.zuihou.file.storage;

import java.util.List;
import java.util.stream.Collectors;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.zuihou.file.dao.AttachmentMapper;
import com.github.zuihou.file.domain.FileDeleteDO;
import com.github.zuihou.file.entity.File;
import com.github.zuihou.file.strategy.impl.AbstractFileStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * FastDFS配置
 *
 * @author zlt
 */
@Configuration
@ConditionalOnProperty(name = "zuihou.file.type", havingValue = "FASTDFS")
public class FastdfsAutoConfigure {
    @Service
    public class FastdfsServiceImpl extends AbstractFileStrategy {
        @Autowired
        private FastFileStorageClient storageClient;
        @Autowired
        private AttachmentMapper attachmentMapper;

        @Override
        protected void uploadFile(File file, MultipartFile multipartFile) throws Exception {
            StorePath storePath = storageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(), file.getExt(), null);
            file.setUrl(fileProperties.getUriPrefix() + storePath.getFullPath());
            file.setGroup(storePath.getGroup());
            file.setPath(storePath.getPath());
        }

        @Override
        protected void delete(List<FileDeleteDO> list, FileDeleteDO file) {
            if (file.getFile()) {
                List<Long> ids = list.stream().mapToLong(FileDeleteDO::getId).boxed().collect(Collectors.toList());
                Integer count = attachmentMapper.countByGroup(ids, file.getGroup(), file.getPath());
                if (count > 0) {
                    return;
                }
            }
            storageClient.deleteFile(file.getGroup(), file.getPath());
        }

    }

}
