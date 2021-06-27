package com.tangyh.lamp.file.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baidu.fsg.uid.UidGenerator;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tangyh.basic.base.service.SuperServiceImpl;
import com.tangyh.basic.database.mybatis.conditions.Wraps;
import com.tangyh.basic.database.mybatis.conditions.query.LbqWrapper;
import com.tangyh.basic.exception.BizException;
import com.tangyh.basic.utils.BeanPlusUtil;
import com.tangyh.lamp.file.biz.FileBiz;
import com.tangyh.lamp.file.dao.AttachmentMapper;
import com.tangyh.lamp.file.domain.FileDO;
import com.tangyh.lamp.file.domain.FileDeleteDO;
import com.tangyh.lamp.file.dto.AttachmentGetVO;
import com.tangyh.lamp.file.dto.AttachmentResultDTO;
import com.tangyh.lamp.file.dto.AttachmentUploadVO;
import com.tangyh.lamp.file.dto.FilePageReqDTO;
import com.tangyh.lamp.file.entity.Attachment;
import com.tangyh.lamp.file.enumeration.FileStorageType;
import com.tangyh.lamp.file.properties.FileServerProperties;
import com.tangyh.lamp.file.service.AttachmentService;
import com.tangyh.lamp.file.strategy.FileStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 附件
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Slf4j
@Service

@RequiredArgsConstructor
public class AttachmentServiceImpl extends SuperServiceImpl<AttachmentMapper, Attachment> implements AttachmentService {
    private final UidGenerator uidGenerator;
    private final FileStrategy fileStrategy;
    private final FileServerProperties fileProperties;
    private final FileBiz fileBiz;

    @Override
    public IPage<Attachment> page(IPage<Attachment> page, FilePageReqDTO data) {
        Attachment attachment = BeanPlusUtil.toBean(data, Attachment.class);

        // ${ew.customSqlSegment} 语法一定要手动eq like 等 不能用lbQ!
        LbqWrapper<Attachment> wrapper = Wraps.<Attachment>lbQ()
                .eq(Attachment::getFileType, attachment.getFileType())
                .eq(Attachment::getStorageType, attachment.getStorageType())
                .like(Attachment::getOriginalFileName, attachment.getOriginalFileName())
                .like(Attachment::getContentType, attachment.getContentType())
                .like(Attachment::getGroup, attachment.getGroup())
                .like(Attachment::getBizType, attachment.getBizType())
                .like(Attachment::getBizId, attachment.getBizId())
                .orderByDesc(Attachment::getId);
        return baseMapper.selectPage(page, wrapper);
    }

    @Override
    public Attachment upload(MultipartFile multipartFile, AttachmentUploadVO attachmentVO) {
        String bizId = attachmentVO.getBizId();
        String bizType = attachmentVO.getBizType();
        Boolean single = attachmentVO.getSingle();
        Long id = attachmentVO.getAttachmentId();

        // 若前端没有生成业务id，我们生成后可以返回给前端，前端同一个页面下一次上传带上上次的bizId即可
        if (StrUtil.isEmpty(bizId)) {
            bizId = String.valueOf(uidGenerator.getUid());
        }
        Attachment attachment = fileStrategy.upload(multipartFile, attachmentVO.getGroup(), attachmentVO.getBizType());
        attachment.setBizId(bizId);
        attachment.setBizType(bizType);
        attachment.setStorageType(attachmentVO.getStorageType() == null ? fileProperties.getType() : attachmentVO.getStorageType());

        if (single) {
            super.remove(Wraps.<Attachment>lbQ().eq(Attachment::getBizId, attachmentVO.getBizId()).eq(Attachment::getBizType, bizType));
        }

        if (id != null && id > 0) {
            //当前端传递了文件id时，修改这条记录
            attachment.setId(id);
            super.updateById(attachment);
        } else {
            super.save(attachment);
        }
        return attachment;
    }

    @Override
    public boolean remove(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return false;
        }

        List<Attachment> list = super.list(Wrappers.<Attachment>lambdaQuery().in(Attachment::getId, ids));
        if (list.isEmpty()) {
            return false;
        }
        boolean bool = super.removeByIds(ids);
        if (fileProperties.getDelFile()) {
            boolean boolDel = fileStrategy.delete(list.stream().map((fi) -> FileDeleteDO.builder()
                    .group(fi.getGroup())
                    .path(fi.getPath())
                    .build())
                    .collect(Collectors.toList()));
            return bool && boolDel;
        }
        return bool;
    }

    @Override
    public boolean removeByBizIdAndBizType(String bizId, String bizType) {
        List<Attachment> list = super.list(
                Wraps.<Attachment>lbQ()
                        .eq(Attachment::getBizId, bizId)
                        .eq(Attachment::getBizType, bizType));
        if (list.isEmpty()) {
            return false;
        }
        return remove(list.stream().mapToLong(Attachment::getId).boxed().collect(Collectors.toList()));
    }

    @Override
    public List<AttachmentResultDTO> find(String[] bizTypes, String[] bizIds) {
        return baseMapper.find(bizTypes, bizIds);
    }

    @Override
    public void download(HttpServletRequest request, HttpServletResponse response, Long[] ids) throws Exception {
        List<Attachment> list = super.listByIds(Arrays.asList(ids));
        down(request, response, list);
    }

    @Override
    public void downloadByBiz(HttpServletRequest request, HttpServletResponse response, String[] bizTypes, String[] bizIds) throws Exception {
        List<Attachment> list = super.list(
                Wraps.<Attachment>lbQ()
                        .in(Attachment::getBizType, bizTypes)
                        .in(Attachment::getBizId, bizIds));

        down(request, response, list);
    }

    @Override
    public void downloadByUrl(HttpServletRequest request, HttpServletResponse response, String url, String filename) throws Exception {
        if (StrUtil.isEmpty(filename)) {
            filename = "未知文件名.txt";
        }
        List<Attachment> list = Arrays.asList(Attachment.builder()
                .url(url).originalFileName(filename).size(0L).build());
        down(request, response, list);
    }

    @Override
    public void downloadByPath(HttpServletRequest request, HttpServletResponse response, String group, String path) throws Exception {
        List<Attachment> list = list(Wraps.<Attachment>lbQ().eq(Attachment::getGroup, group).eq(Attachment::getPath, path));
        down(request, response, list);
    }

    private void down(HttpServletRequest request, HttpServletResponse response, List<Attachment> list) throws Exception {
        if (list.isEmpty()) {
            throw BizException.wrap("您下载的文件不存在");
        }
        List<FileDO> listDO;
        if (FileStorageType.MIN_IO.eq(fileProperties.getType())) {
            listDO = list.stream().map(file ->
                    FileDO.builder()
                            .url(getUrl(file.getGroup(), file.getPath(), 172800))
                            .originalFileName(file.getOriginalFileName())
                            .size(file.getSize())
                            .build())
                    .collect(Collectors.toList());
        } else {
            listDO = list.stream().map(file ->
                    FileDO.builder()
                            .url(file.getUrl())
                            .originalFileName(file.getOriginalFileName())
                            .size(file.getSize())
                            .build())
                    .collect(Collectors.toList());
        }
        fileBiz.down(listDO, request, response);
    }

    @Override
    public List<String> getUrls(List<AttachmentGetVO> paths, Integer expiry) {
        return fileStrategy.getUrls(paths, expiry);
    }

    @Override
    public String getUrl(String group, String path, Integer expiry) {
        return fileStrategy.getUrl(group, path, expiry);
    }
}
