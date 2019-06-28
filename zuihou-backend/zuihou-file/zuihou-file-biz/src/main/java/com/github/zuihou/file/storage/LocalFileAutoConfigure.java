package com.github.zuihou.file.storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import com.github.zuihou.base.R;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.file.entity.File;
import com.github.zuihou.file.properties.FileServerProperties;
import com.github.zuihou.file.strategy.impl.AbstractFileChunkStrategy;
import com.github.zuihou.file.utils.FileDataTypeUtil;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import static com.github.zuihou.file.utils.FileDataTypeUtil.URI_SEPARATOR;


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
public class LocalFileAutoConfigure {

    @Service
    public class LocalChunkServiceImpl extends AbstractFileChunkStrategy {
        /**
         * 为上传的文件生成随机名称
         *
         * @param originalName 文件的原始名称，主要用来获取文件的后缀名
         * @return
         */
        private String randomFileName(String originalName) {
            String[] ext = originalName.split("\\.");
            return UUID.randomUUID().toString() + "." + ext[ext.length - 1];
        }

        @Override
        protected void copyFile(File file) {
            String inputFile = Paths.get(fileProperties.getStoragePath(), file.getRelativePath(),
                    file.getFilename()).toString();

            String filename = randomFileName(file.getFilename());
            String outputFile = Paths.get(fileProperties.getStoragePath(), file.getRelativePath(), filename).toString();

            try {
                org.apache.commons.io.FileUtils.copyFile(new java.io.File(inputFile), new java.io.File(outputFile));
            } catch (Exception e) {
                log.error("复制文件异常", e);
                throw new BizException("复制文件异常");
            }

            file.setFilename(filename);
            String url = file.getUrl();
            String newUrl = StringUtils.substring(url, 0, StringUtils.lastIndexOf(url, URI_SEPARATOR) + 1);
            file.setUrl(newUrl + filename);
        }


        @Override
        protected R<File> merge(List<java.io.File> files, String path, String md5, String folder, String fileName, String ext) throws IOException {
            //创建合并后的文件
            java.io.File outputFile = new java.io.File(Paths.get(path, fileName).toString());
            if (outputFile.exists()) {
                log.error("文件[" + folder + "]随机命名冲突");

                return R.fail("文件随机命名冲突");
            }
            boolean newFile = outputFile.createNewFile();
            if (!newFile) {
                return R.fail("创建文件失败");
            }
            FileChannel outChannel = new FileOutputStream(outputFile).getChannel();

            //同步nio 方式对分片进行合并, 有效的避免文件过大导致内存溢出
            FileChannel inChannel;
            for (java.io.File file : files) {
                inChannel = new FileInputStream(file).getChannel();
                inChannel.transferTo(0, inChannel.size(), outChannel);
                inChannel.close();

                //删除分片
                if (!file.delete()) {
                    log.error("分片[" + folder + "=>" + file.getName() + "]删除失败");
                }
            }
            if (outChannel != null) {
                outChannel.close();
            }

            //将MD5签名和合并后的文件path存入持久层
            if (this.saveMd52FileMap(md5, outputFile.getName())) {
                log.info("文件[" + md5 + "=>" + outputFile.getName() + "]保存关系到持久成失败，但并不影响文件上传，只会导致日后该文件可能被重复上传而已");
            }

            String relativePath = FileDataTypeUtil.getRelativePath(fileProperties.getStoragePath(), outputFile.getAbsolutePath());
            String url = new StringBuilder(fileProperties.getUriPrefix())
                    .append(relativePath)
                    .append(URI_SEPARATOR)
                    .append(fileName).toString();
            File filePo = File.builder()
                    .relativePath(relativePath)
                    .url(StringUtils.replace(url, "\\\\", URI_SEPARATOR))
                    .build();
            return R.success(filePo);
        }
    }
}
