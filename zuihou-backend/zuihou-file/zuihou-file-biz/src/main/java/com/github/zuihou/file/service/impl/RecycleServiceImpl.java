package com.github.zuihou.file.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.file.dao.FileMapper;
import com.github.zuihou.file.dao.RecycleMapper;
import com.github.zuihou.file.domain.FileDeleteDO;
import com.github.zuihou.file.domain.FileQueryDO;
import com.github.zuihou.file.entity.File;
import com.github.zuihou.file.entity.Recycle;
import com.github.zuihou.file.service.FileService;
import com.github.zuihou.file.service.RecycleService;
import com.github.zuihou.file.strategy.FileStrategy;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 文件回收站
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Slf4j
@Service
public class RecycleServiceImpl extends ServiceImpl<RecycleMapper, Recycle> implements RecycleService {
    /**
     * 文件夹提示判断符
     */
    private final static String FOLDER_TIP = "【】";
    @Autowired
    private FileService fileService;
    @Autowired
    private FileMapper fileMapper;
    @Resource
    private FileStrategy fileStrategy;

    /**
     * 1，先查询源文件以及所有子集文件 留着备用
     * 2，删除回收站
     * 3，删除文件表
     * 4，删除源文件
     *
     * @param
     * @return
     * @author tangyh
     * @date 2019-05-07 09:31
     */
    @Override
    public void deleteBatch(Long userId, Long[] ids) {
        //查询ids对应的文件或者文件夹，并查询所有文件下的子类
        LambdaQueryWrapper<File> query = getFileWrapper(userId, ids);
        List<File> files = fileService.list(query);

        LambdaQueryWrapper<Recycle> remove = new LambdaQueryWrapper<>();
        Set<Long> fileIds = files.stream().mapToLong(File::getId).boxed().collect(Collectors.toSet());
        fileIds.addAll(Arrays.asList(ids));
        remove.eq(Recycle::getCreateUser, userId).in(Recycle::getId, fileIds);
        super.remove(remove);

        LambdaQueryWrapper<File> removeFile = getFileWrapper(userId, ids);
        fileService.remove(removeFile);

        deleteFile(files);
    }

    private LambdaQueryWrapper<File> getFileWrapper(Long userId, Set<Long> ids) {
        return getFileWrapper(userId, ids.toArray(new Long[ids.size()]));
    }

    private LambdaQueryWrapper<File> getFileWrapper(Long userId, Long[] ids) {
        LambdaQueryWrapper<File> removeFile = new LambdaQueryWrapper<>();
        removeFile.eq(File::getCreateUser, userId);
        String applySql = String.format("MATCH(tree_path) AGAINST('%s' IN BOOLEAN MODE)", StringUtils.join(ids, " "));
        log.info("applySql={}", applySql);
        removeFile.and((q) -> q.in(File::getId, ids).or().apply(applySql));
        return removeFile;
    }

    /**
     * 1，分页查询回收站的数据
     * 2，批量删除回收站数据
     * 3，批量删除 文件表数据
     *
     * @param userId 指定用户
     */
    @Override
    public void clear(Long userId) {
        LambdaQueryWrapper<Recycle> query = new LambdaQueryWrapper<>();
        query.eq(Recycle::getCreateUser, userId);
        IPage<Recycle> page = new Page<>(1, 100);
        page = super.page(page, query);

        if (page.getRecords().isEmpty()) {
            return;
        }
        for (int current = 0; current < page.getPages(); current++) {
            if (current != 0) {
                page = new Page<>(current, 100);
                page = super.page(page, query);
            }

            //删除回收站数据
            LambdaQueryWrapper<Recycle> delete = new LambdaQueryWrapper<>();
            delete.eq(Recycle::getCreateUser, userId);
            super.remove(delete);

            //转换回收站id
            Set<Long> ids = page.getRecords().stream().mapToLong(Recycle::getId).boxed().collect(Collectors.toSet());
            LambdaQueryWrapper<File> removeWrapper = getFileWrapper(userId, ids);
            //查询所有源文件以及子文件
            List<File> list = fileService.list(removeWrapper);

            //删除源文件数据
            fileService.remove(removeWrapper);

            //删除源文件
            deleteFile(list);
        }
    }

    private void deleteFile(List<File> list) {
        List<FileDeleteDO> deleteFiles = list.stream().map((file) ->
                FileDeleteDO.builder()
                        .group(file.getGroup())
                        .path(file.getPath())
                        .fileName(file.getFilename())
                        .relativePath(file.getRelativePath())
                        .file(true)
                        .id(file.getId())
                        .build())
                .collect(Collectors.toList());
        fileStrategy.delete(deleteFiles);
    }


    /**
     * 1，判断是否存在父类节点 已经被删除的情况，提示不允许还原
     * 2，查询父节点没有被删除的文件，直接还原
     * 3，删除回收站表
     *
     * @param userId 用户id
     * @param ids    文件id
     */
    @Override
    public void reset(Long userId, Long[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return;
        }
        List<FileQueryDO> list = fileMapper.findByIds(userId, ids);

        String parentFolderName = list.stream().filter((file) -> file.getParent() != null && file.getParent().getIsDelete())
                .map(FileQueryDO::getFolderName).collect(Collectors.joining(" ", "【", "】"));

        //还原根节点
        if (!FOLDER_TIP.equals(parentFolderName)) {
            throw new BizException(-1, "父文件夹%s已经被删除，请先还原被删除的父文件夹", parentFolderName);
        }
        List<Long> resetIds = list.stream().filter((file) -> file.getParent() == null || !file.getParent().getIsDelete())
                .mapToLong(FileQueryDO::getId).boxed().collect(Collectors.toList());

        //直接还原
        if (!resetIds.isEmpty()) {
            Wrapper<File> resetWrapper = Wrappers.<File>lambdaUpdate()
                    .eq(File::getCreateUser, userId).in(File::getId, resetIds);
            fileService.update(File.builder().isDelete(false).build(), resetWrapper);
        }

        Wrapper<Recycle> removeWrapper = Wrappers.<Recycle>lambdaQuery()
                .eq(Recycle::getCreateUser, userId).in(Recycle::getId, ids);
        super.remove(removeWrapper);
    }
}
