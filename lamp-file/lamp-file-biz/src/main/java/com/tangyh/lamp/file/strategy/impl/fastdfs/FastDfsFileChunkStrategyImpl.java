package com.tangyh.lamp.file.strategy.impl.fastdfs;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import com.tangyh.basic.base.R;
import com.tangyh.lamp.file.dto.chunk.FileChunksMergeDTO;
import com.tangyh.lamp.file.entity.Attachment;
import com.tangyh.lamp.file.properties.FileServerProperties;
import com.tangyh.lamp.file.service.AttachmentService;
import com.tangyh.lamp.file.strategy.impl.AbstractFileChunkStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author zuihou
 * @date 2020/11/22 5:18 下午
 */
@Slf4j
public class FastDfsFileChunkStrategyImpl extends AbstractFileChunkStrategy {
    protected final AppendFileStorageClient storageClient;

    public FastDfsFileChunkStrategyImpl(AttachmentService fileService, FileServerProperties fileProperties, AppendFileStorageClient storageClient) {
        super(fileService, fileProperties);
        this.storageClient = storageClient;
    }

    @Override
    protected void copyFile(Attachment file) {
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
    protected R<Attachment> merge(List<File> files, String path, String fileName, FileChunksMergeDTO info) throws IOException {
        StorePath storePath = null;

        long start = System.currentTimeMillis();
        for (int i = 0; i < files.size(); i++) {
            java.io.File file = files.get(i);

            FileInputStream in = FileUtils.openInputStream(file);
            if (i == 0) {
                storePath = storageClient.uploadAppenderFile(null, in,
                        file.length(), info.getExt());
            } else {
                storageClient.appendFile(storePath.getGroup(), storePath.getPath(),
                        in, file.length());
            }
        }
        if (storePath == null) {
            return R.fail("上传失败");
        }

        long end = System.currentTimeMillis();
        log.info("上传耗时={}", (end - start));
        String url = fileProperties.getUriPrefix() +
                storePath.getFullPath();
        Attachment filePo = Attachment.builder()
                .url(url)
                .group(storePath.getGroup())
                .path(storePath.getPath())
                .build();
        return R.success(filePo);
    }
}
