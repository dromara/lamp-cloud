package com.github.zuihou.file.storage;

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

import com.github.zuihou.base.R;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.file.domain.FileDeleteDO;
import com.github.zuihou.file.entity.File;
import com.github.zuihou.file.properties.FileServerProperties;
import com.github.zuihou.file.strategy.impl.AbstractFileChunkStrategy;
import com.github.zuihou.file.strategy.impl.AbstractFileStrategy;
import com.github.zuihou.file.utils.FileDataTypeUtil;
import com.github.zuihou.utils.StrHelper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

            file.setUrl(fileProperties.getUriPrefix() + secDir + "/" + fileName + "?attname=" + StrHelper.encode(file.getSubmittedFileName()));
            file.setFilename(fileName);
            file.setRelativePath(secDir);
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
        protected R<File> merge(List<java.io.File> files, String path, String md5, String folder, String fileName, String submittedFileName, String ext) throws IOException {
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
                        log.error("分片[" + folder + "=>" + file.getName() + "]删除失败");
                    }
                }
            } catch (FileNotFoundException e) {
                log.error("文件输出失败", e);
                return R.fail("文件输出失败");
            }

            //将MD5签名和合并后的文件path存入持久层
            if (this.saveMd52FileMap(md5, outputFile.getName())) {
                log.info("文件[" + md5 + "=>" + outputFile.getName() + "]保存关系到持久成失败，但并不影响文件上传，只会导致日后该文件可能被重复上传而已");
            }

            String relativePath = FileDataTypeUtil.getRelativePath(fileProperties.getStoragePath(), outputFile.getAbsolutePath());
            String url = new StringBuilder(fileProperties.getUriPrefix())
                    .append(relativePath)
                    .append(URI_SEPARATOR)
                    .append(fileName)
                    .append("?attname=" + StrHelper.encode(submittedFileName))
                    .toString();
            File filePo = File.builder()
                    .relativePath(relativePath)
                    .url(StringUtils.replace(url, "\\\\", URI_SEPARATOR))
                    .build();
            return R.success(filePo);
        }
    }

}
