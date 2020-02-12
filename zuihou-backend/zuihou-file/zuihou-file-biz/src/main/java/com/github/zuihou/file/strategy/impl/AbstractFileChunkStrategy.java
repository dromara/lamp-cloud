package com.github.zuihou.file.strategy.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.zuihou.base.R;
import com.github.zuihou.file.domain.FileAttrDO;
import com.github.zuihou.file.dto.chunk.FileChunksMergeDTO;
import com.github.zuihou.file.entity.File;
import com.github.zuihou.file.enumeration.IconType;
import com.github.zuihou.file.properties.FileServerProperties;
import com.github.zuihou.file.service.FileService;
import com.github.zuihou.file.strategy.FileChunkStrategy;
import com.github.zuihou.file.strategy.FileLock;
import com.github.zuihou.file.utils.FileDataTypeUtil;
import com.github.zuihou.utils.DateUtils;
import com.github.zuihou.utils.StrPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.Lock;


/**
 * 文件分片处理 抽象策略类
 *
 * @author zuihou
 * @date 2019/06/19
 */
@Slf4j
public abstract class AbstractFileChunkStrategy implements FileChunkStrategy {
    @Autowired
    protected FileService fileService;
    @Autowired
    protected FileServerProperties fileProperties;

    /**
     * 秒传验证
     * 根据文件的MD5签名判断该文件是否已经存在
     *
     * @param md5 文件的md5签名
     * @return 若存在则返回该文件的路径，不存在则返回null
     */
    private File md5Check(String md5) {
        return fileService.getOne(Wrappers.<File>lambdaQuery()
                .eq(File::getFileMd5, md5).eq(File::getIsDelete, false), false);
    }

    /**
     * 设置创建日期
     *
     * @param file
     */
    private void setDate(File file) {
        LocalDateTime now = LocalDateTime.now();
        file.setCreateMonth(DateUtils.formatAsYearMonthEn(now))
                .setCreateWeek(DateUtils.formatAsYearWeekEn(now))
                .setCreateDay(DateUtils.formatAsDateEn(now));
    }

    @Override
    public File md5Check(String md5, Long folderId, Long accountId) {
        File file = md5Check(md5);
        if (file == null) {
            return null;
        }

        //分片存在，不需上传， 复制一条数据，重新插入
        copyFile(file);

        file.setId(null)
                .setCreateUser(accountId)
                .setCreateTime(LocalDateTime.now());
        file.setUpdateTime(LocalDateTime.now())
                .setUpdateUser(accountId);
        setDate(file);

        FileAttrDO attr = fileService.getFileAttrDo(folderId);
        file.setFolderId(folderId)
                .setTreePath(attr.getTreePath())
                .setGrade(attr.getGrade())
                .setFolderName(attr.getFolderName());

        fileService.save(file);
        return file;
    }

    /**
     * 让子类自己实现复制
     *
     * @param file
     */
    protected abstract void copyFile(File file);

    @Override
    public R<File> chunksMerge(FileChunksMergeDTO info) {


        String filename = new StringBuilder(info.getName())
                .append(StrPool.DOT)
                .append(info.getExt()).toString();
        R<File> result = chunksMerge(info, filename);

        log.info("path={}", result);
        if (result.getIsSuccess() && result.getData() != null) {
            //文件名
            File filePo = result.getData();

            filePo.setDataType(FileDataTypeUtil.getDataType(info.getContextType()))
                    .setSubmittedFileName(info.getSubmittedFileName())
                    .setIsDelete(false)
                    .setSize(info.getSize())
                    .setFileMd5(info.getMd5())
                    .setContextType(info.getContextType())
                    .setFilename(filename)
                    .setExt(info.getExt())
                    .setIcon(IconType.getIcon(info.getExt()).getIcon());
            setDate(filePo);

            FileAttrDO attr = fileService.getFileAttrDo(info.getFolderId());
            filePo.setTreePath(attr.getTreePath())
                    .setGrade(attr.getGrade())
                    .setFolderId(info.getFolderId())
                    .setFolderName(attr.getFolderName());

            fileService.save(filePo);
            return R.success(filePo);
        }
        return result;
    }

    private R<File> chunksMerge(FileChunksMergeDTO info, String fileName) {
        String path = FileDataTypeUtil.getUploadPathPrefix(fileProperties.getStoragePath());
        int chunks = info.getChunks();
        String folder = info.getName();
        String md5 = info.getMd5();

        int chunksNum = this.getChunksNum(Paths.get(path, folder).toString());
        log.info("chunks={}, chunksNum={}", chunks, chunksNum);
        //检查是否满足合并条件：分片数量是否足够
        if (chunks == chunksNum) {
            //同步指定合并的对象
            Lock lock = FileLock.getLock(folder);
            try {
                lock.lock();
                //检查是否满足合并条件：分片数量是否足够
                List<java.io.File> files = new ArrayList<>(Arrays.asList(this.getChunks(Paths.get(path, folder).toString())));
                if (chunks == files.size()) {
                    //按照名称排序文件，这里分片都是按照数字命名的

                    //这里存放的文件名一定是数字
                    files.sort(Comparator.comparingInt(f -> Convert.toInt(f.getName(), 0)));

                    R<File> result = merge(files, path, fileName, info);
                    files = null;

                    //清理：文件夹，tmp文件
                    this.cleanSpace(folder, path);
                    return result;
                }
            } catch (Exception ex) {
                log.error("数据分片合并失败", ex);
                return R.fail("数据分片合并失败");
            } finally {
                //解锁
                lock.unlock();
                //清理锁对象
                FileLock.removeLock(folder);
            }
        }
        //去持久层查找对应md5签名，直接返回对应path
        File file = this.md5Check(md5);
        if (file == null) {
            log.error("文件[签名:" + md5 + "]数据不完整，可能该文件正在合并中");
            return R.fail("数据不完整，可能该文件正在合并中, 也有可能是上传过程中某些分片丢失");
        }
        return R.success(file);
    }


    /**
     * 子类实现具体的合并操作
     *
     * @param files    文件
     * @param path     路径
     * @param fileName 唯一名 含后缀
     * @param info     文件信息
     * @return
     * @throws IOException
     */
    protected abstract R<File> merge(List<java.io.File> files, String path, String fileName, FileChunksMergeDTO info) throws IOException;


    /**
     * 清理分片上传的相关数据
     * 文件夹，tmp文件
     *
     * @param folder 文件夹名称
     * @param path   上传文件根路径
     * @return
     */
    protected boolean cleanSpace(String folder, String path) {
        //删除分片文件夹
        java.io.File garbage = new java.io.File(Paths.get(path, folder).toString());
        if (!FileUtils.deleteQuietly(garbage)) {
            return false;
        }
        //删除tmp文件
        garbage = new java.io.File(Paths.get(path, folder + ".tmp").toString());
        if (!FileUtils.deleteQuietly(garbage)) {
            return false;
        }
        return true;
    }


    /**
     * 获取指定文件的分片数量
     *
     * @param folder 文件夹路径
     * @return
     */
    private int getChunksNum(String folder) {
        java.io.File[] filesList = this.getChunks(folder);
        return filesList.length;
    }

    /**
     * 获取指定文件的所有分片
     *
     * @param folder 文件夹路径
     * @return
     */
    private java.io.File[] getChunks(String folder) {
        java.io.File targetFolder = new java.io.File(folder);
        return targetFolder.listFiles((file) -> {
            if (file.isDirectory()) {
                return false;
            }
            return true;
        });
    }

}
