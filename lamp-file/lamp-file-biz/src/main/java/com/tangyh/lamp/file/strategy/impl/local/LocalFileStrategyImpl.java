package com.tangyh.lamp.file.strategy.impl.local;

import com.tangyh.lamp.file.domain.FileDeleteDO;
import com.tangyh.lamp.file.dto.AttachmentGetVO;
import com.tangyh.lamp.file.entity.Attachment;
import com.tangyh.lamp.file.properties.FileServerProperties;
import com.tangyh.lamp.file.strategy.impl.AbstractFileStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author zuihou
 * @date 2020/11/22 5:00 下午
 */
@Slf4j
public class LocalFileStrategyImpl extends AbstractFileStrategy {
    public LocalFileStrategyImpl(FileServerProperties fileProperties) {
        super(fileProperties);
    }


    @Override
    protected void uploadFile(Attachment file, MultipartFile multipartFile, String bucket) throws Exception {
        //生成文件名
        String uniqueFileName = getUniqueFileName(file);

        String path = getPath(uniqueFileName, file.getBizType());

        // web服务器存放的绝对路径
        String absolutePath = Paths.get(fileProperties.getStoragePath(), path).toString();

        java.io.File outFile = new java.io.File(absolutePath);
        FileUtils.writeByteArrayToFile(outFile, multipartFile.getBytes());

        String url = fileProperties.getUriPrefix() + path;

        file.setUrl(url);
        file.setUniqueFileName(uniqueFileName);
        file.setPath(path);
        file.setGroup(bucket);
    }

    @Override
    protected void delete(FileDeleteDO file) {
        File ioFile = new File(Paths.get(fileProperties.getStoragePath(), file.getPath()).toString());
        FileUtils.deleteQuietly(ioFile);
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
