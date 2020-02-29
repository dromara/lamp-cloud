package com.github.zuihou.file.storage;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.StrUtil;
import com.github.zuihou.base.R;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.file.domain.FileDeleteDO;
import com.github.zuihou.file.dto.chunk.FileChunksMergeDTO;
import com.github.zuihou.file.entity.File;
import com.github.zuihou.file.properties.FileServerProperties;
import com.github.zuihou.file.strategy.impl.AbstractFileChunkStrategy;
import com.github.zuihou.file.strategy.impl.AbstractFileStrategy;
import com.github.zuihou.file.utils.FileDataTypeUtil;
import com.github.zuihou.utils.StrPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static com.github.zuihou.utils.DateUtils.DEFAULT_MONTH_FORMAT_SLASH;


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
            String fileName = UUID.randomUUID().toString() + StrPool.DOT + file.getExt();

            String tenant = BaseContextHandler.getTenant();

            //日期文件夹
            String relativePath = Paths.get(tenant, LocalDate.now().format(DateTimeFormatter.ofPattern(DEFAULT_MONTH_FORMAT_SLASH))).toString();
            // web服务器存放的绝对路径
            String absolutePath = Paths.get(fileProperties.getStoragePath(), relativePath).toString();

            java.io.File outFile = new java.io.File(Paths.get(absolutePath, fileName).toString());
            org.apache.commons.io.FileUtils.writeByteArrayToFile(outFile, multipartFile.getBytes());

            String url = new StringBuilder(fileProperties.getUriPrefix())
                    .append(relativePath)
                    .append(StrPool.SLASH)
                    .append(fileName)
                    .toString();
            //替换掉windows环境的\路径
            url = StrUtil.replace(url, "\\\\", StrPool.SLASH);
            url = StrUtil.replace(url, "\\", StrPool.SLASH);
            file.setUrl(url);
            file.setFilename(fileName);
            file.setRelativePath(relativePath);
        }

        @Override
        protected void delete(List<FileDeleteDO> list, FileDeleteDO file) {
            java.io.File ioFile = new java.io.File(Paths.get(fileProperties.getStoragePath(), file.getRelativePath(), file.getFileName()).toString());
            org.apache.commons.io.FileUtils.deleteQuietly(ioFile);
        }
    }

    @Service
    public class LocalChunkServiceImpl extends AbstractFileChunkStrategy {
        /**
         * 为上传的文件生成随机名称
         *
         * @param originalName 文件的原始名称，主要用来获取文件的后缀名
         * @return
         */
        private String randomFileName(String originalName) {
            String[] ext = StrUtil.split(originalName, ".");
            return UUID.randomUUID().toString() + StrPool.DOT + ext[ext.length - 1];
        }

        @Override
        protected void copyFile(File file) {
            String inputFile = Paths.get(fileProperties.getStoragePath(), file.getRelativePath(),
                    file.getFilename()).toString();

            String filename = randomFileName(file.getFilename());
            String outputFile = Paths.get(fileProperties.getStoragePath(), file.getRelativePath(), filename).toString();

            try {
                FileUtil.copy(inputFile, outputFile, true);
            } catch (IORuntimeException e) {
                log.error("复制文件异常", e);
                throw new BizException("复制文件异常");
            }

            file.setFilename(filename);
            String url = file.getUrl();
            String newUrl = StrUtil.subPre(url, StrUtil.lastIndexOfIgnoreCase(url, StrPool.SLASH) + 1);
            file.setUrl(newUrl + filename);
        }


        @Override
        protected R<File> merge(List<java.io.File> files, String path, String fileName, FileChunksMergeDTO info) throws IOException {
            //创建合并后的文件
            log.info("path={},fileName={}", path, fileName);
            java.io.File outputFile = new java.io.File(Paths.get(path, fileName).toString());
            if (!outputFile.exists()) {
                boolean newFile = outputFile.createNewFile();
                if (!newFile) {
                    return R.fail("创建文件失败");
                }
                try (FileChannel outChannel = new FileOutputStream(outputFile).getChannel()) {
                    //同步nio 方式对分片进行合并, 有效的避免文件过大导致内存溢出
                    for (java.io.File file : files) {
                        try (FileChannel inChannel = new FileInputStream(file).getChannel()) {
                            inChannel.transferTo(0, inChannel.size(), outChannel);
                        } catch (FileNotFoundException ex) {
                            log.error("文件转换失败", ex);
                            return R.fail("文件转换失败");
                        }
                        //删除分片
                        if (!file.delete()) {
                            log.error("分片[" + info.getName() + "=>" + file.getName() + "]删除失败");
                        }
                    }
                } catch (FileNotFoundException e) {
                    log.error("文件输出失败", e);
                    return R.fail("文件输出失败");
                }

            } else {
                log.warn("文件[{}], fileName={}已经存在", info.getName(), fileName);
            }

            String relativePath = FileDataTypeUtil.getRelativePath(Paths.get(fileProperties.getStoragePath()).toString(), outputFile.getAbsolutePath());
            log.info("relativePath={}, getStoragePath={}, getAbsolutePath={}", relativePath, fileProperties.getStoragePath(), outputFile.getAbsolutePath());
            String url = new StringBuilder(fileProperties.getUriPrefix())
                    .append(relativePath)
                    .append(StrPool.SLASH)
                    .append(fileName)
                    .toString();
            File filePo = File.builder()
                    .relativePath(relativePath)
                    .url(StringUtils.replace(url, "\\\\", StrPool.SLASH))
                    .build();
            return R.success(filePo);
        }
    }
}
