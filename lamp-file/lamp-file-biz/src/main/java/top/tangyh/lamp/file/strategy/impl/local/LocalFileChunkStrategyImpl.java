package top.tangyh.lamp.file.strategy.impl.local;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.StrUtil;
import top.tangyh.basic.base.R;
import top.tangyh.basic.exception.BizException;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.lamp.file.dao.FileMapper;
import top.tangyh.lamp.file.dto.chunk.FileChunksMergeDTO;
import top.tangyh.lamp.file.entity.File;
import top.tangyh.lamp.file.properties.FileServerProperties;
import top.tangyh.lamp.file.strategy.impl.AbstractFileChunkStrategy;
import top.tangyh.lamp.file.utils.FileTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

/**
 * @author zuihou
 * @date 2020/11/22 5:02 下午
 */
@Slf4j
@Primary
public class LocalFileChunkStrategyImpl extends AbstractFileChunkStrategy {
    public LocalFileChunkStrategyImpl(FileMapper fileMapper, FileServerProperties fileProperties) {
        super(fileMapper, fileProperties);
    }

    /**
     * 为上传的文件生成随机名称
     *
     * @param originalName 文件的原始名称，主要用来获取文件的后缀名
     * @return 随机名
     */
    private String randomFileName(String originalName) {
        String[] ext = StrUtil.splitToArray(originalName, StrPool.DOT);
        return UUID.randomUUID() + StrPool.DOT + ext[ext.length - 1];
    }

    @Override
    protected void copyFile(File file) {
        String inputFile = Paths.get(fileProperties.getLocal().getStoragePath(), file.getPath()).toString();

        String filename = randomFileName(file.getUniqueFileName());
        String outputFile = Paths.get(fileProperties.getLocal().getStoragePath(), StrUtil.subBefore(file.getPath(), "/" + file.getUniqueFileName(), true), filename).toString();

        try {
            FileUtil.copy(inputFile, outputFile, true);
        } catch (IORuntimeException e) {
            log.error("复制文件异常", e);
            throw new BizException("复制文件异常");
        }

        file.setUniqueFileName(filename);
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

        String relativePath = FileTypeUtil.getRelativePath(Paths.get(fileProperties.getLocal().getStoragePath()).toString(), outputFile.getAbsolutePath());
        log.info("relativePath={}, getStoragePath={}, getAbsolutePath={}", relativePath, fileProperties.getLocal().getStoragePath(), outputFile.getAbsolutePath());
        String url = fileProperties.getLocal().getUrlPrefix() +
                relativePath +
                StrPool.SLASH +
                fileName;
        File filePo = File.builder()
                .url(StrUtil.replace(url, "\\\\", StrPool.SLASH))
                .build();
        return R.success(filePo);
    }
}
