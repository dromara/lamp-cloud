package com.github.zuihou.file.storage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import com.github.zuihou.base.Result;
import com.github.zuihou.file.entity.File;
import com.github.zuihou.file.strategy.impl.AbstractFileChunkStrategy;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * FastDFS配置
 *
 * @author zlt
 */
@Configuration
@Slf4j
@ConditionalOnProperty(name = "zuihou.file.type", havingValue = "FASTDFS")
public class FastdfsFileAutoConfigure {

    @Service
    public class FastdfsChunkServiceImpl extends AbstractFileChunkStrategy {
        @Autowired
        protected AppendFileStorageClient storageClient;

        @Override
        protected void copyFile(File file) {
            // 由于大文件下载然后在上传会内存溢出， 所以 FastDFS 不复制，删除时通过业务手段
//            DownloadByteArray callback = new DownloadByteArray();
//            byte[] content = storageClient.downloadFile(file.getGroup(), file.getPath(), callback);
//            InputStream in = new ByteArrayInputStream(content);
//            StorePath storePath = storageClient.uploadFile(file.getGroup(), in, file.getSize(), file.getExt());
//            file.setUrl(fileProperties.getUriPrefix() + storePath.getFullPath());
//            file.setGroup(storePath.getGroup());
//            file.setPath(storePath.getPath());
        }

        @Override
        protected Result<File> merge(List<java.io.File> files, String path, String md5, String folder, String fileName, String ext) throws IOException {
            StorePath storePath = null;

            long start = System.currentTimeMillis();
            for (int i = 0; i < files.size(); i++) {
                java.io.File file = files.get(i);

                FileInputStream in = FileUtils.openInputStream(file);
                if (i == 0) {
                    storePath = storageClient.uploadAppenderFile(null, in,
                            file.length(), ext);
                } else {
                    storageClient.appendFile(storePath.getGroup(), storePath.getPath(),
                            in, file.length());
                }
            }
            long end = System.currentTimeMillis();
            log.info("上传耗时={}", (end - start));
            String url = new StringBuilder(fileProperties.getUriPrefix())
                    .append(storePath.getFullPath()).toString();
            File filePo = File.builder()
                    .url(url)
                    .group(storePath.getGroup())
                    .path(storePath.getPath())
                    .build();
            return Result.success(filePo);
        }
    }
}
