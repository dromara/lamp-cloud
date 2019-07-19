package com.github.zuihou.file.storage;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import com.github.zuihou.file.domain.FileDeleteDO;
import com.github.zuihou.file.entity.File;
import com.github.zuihou.file.properties.FileServerProperties;
import com.github.zuihou.file.strategy.impl.AbstractFileStrategy;
import com.github.zuihou.utils.StringHelper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 本地上传配置
 *
 * @author zuihou
 * @date 2019/06/18
 */

@EnableConfigurationProperties(FileServerProperties.class)
@Configuration
@ConditionalOnProperty(name = "zuihou.file.type", havingValue = "LOCAL")
@Slf4j
public class LocalAutoConfigure {

    @Service
    public class LocalServiceImpl extends AbstractFileStrategy {
        @Override
        protected void uploadFile(File file, MultipartFile multipartFile) throws Exception {
            //生成文件名
            String fileName = UUID.randomUUID().toString() + "." + file.getExt();
            //日期文件夹
            String secDir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
            // web服务器存放的绝对路径
            String absolutePath = Paths.get(fileProperties.getStoragePath(), secDir).toString();

            java.io.File outFile = new java.io.File(Paths.get(absolutePath, fileName).toString());
            org.apache.commons.io.FileUtils.writeByteArrayToFile(outFile, multipartFile.getBytes());

            file.setUrl(fileProperties.getUriPrefix() + secDir + "/" + fileName + "?attname=" + StringHelper.encode(file.getSubmittedFileName()));
            file.setFilename(fileName);
            file.setRelativePath(secDir);
        }

        @Override
        protected void delete(List<FileDeleteDO> list, FileDeleteDO file) {
            java.io.File ioFile = new java.io.File(Paths.get(fileProperties.getStoragePath(), file.getRelativePath(), file.getFileName()).toString());
            org.apache.commons.io.FileUtils.deleteQuietly(ioFile);
        }
    }

}
