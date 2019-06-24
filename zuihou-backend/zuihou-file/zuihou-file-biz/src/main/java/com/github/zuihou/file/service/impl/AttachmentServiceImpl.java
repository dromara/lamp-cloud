package com.github.zuihou.file.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.base.id.IdGenerate;
import com.github.zuihou.common.utils.context.DozerUtils;
import com.github.zuihou.file.biz.FileBiz;
import com.github.zuihou.file.dao.AttachmentMapper;
import com.github.zuihou.file.domain.FileDO;
import com.github.zuihou.file.domain.FileDeleteDO;
import com.github.zuihou.file.dto.AttachmentDTO;
import com.github.zuihou.file.dto.AttachmentResultDTO;
import com.github.zuihou.file.entity.Attachment;
import com.github.zuihou.file.entity.File;
import com.github.zuihou.file.enumeration.DataType;
import com.github.zuihou.file.properties.FileServerProperties;
import com.github.zuihou.file.service.AttachmentService;
import com.github.zuihou.file.strategy.FileStrategy;
import com.github.zuihou.mybatis.conditions.Wraps;
import com.github.zuihou.utils.DateUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements AttachmentService {
    @Autowired
    private IdGenerate<Long> idGenerate;
    @Autowired
    private DozerUtils dozerUtils;
    @Resource
    private FileStrategy fileStrategy;
    @Autowired
    private FileServerProperties fileProperties;
    @Autowired
    private FileBiz fileBiz;

    @Override
    public AttachmentDTO upload(MultipartFile multipartFile, String appCode, Long id, String bizType, String bizId) {
        //根据业务类型来判断是否生成业务id
        if (StringUtils.isNotEmpty(bizType) && StringUtils.isEmpty(bizId)) {
            bizId = String.valueOf(idGenerate.generate());
        }
        File file = fileStrategy.upload(multipartFile);

        Attachment attachment = dozerUtils.map2(file, Attachment.class);

        attachment.setBizId(bizId);
        attachment.setBizType(bizType);
        attachment.setAppCode(appCode);
        setDate(attachment);

        if (id != null && id > 0) {
            //当前端传递了文件id时，修改这条记录
            attachment.setId(id);
            super.updateById(attachment);
        } else {
            super.save(attachment);
        }

        AttachmentDTO dto = dozerUtils.map2(attachment, AttachmentDTO.class);
        dto.setDownloadUrlByBizId(fileProperties.getDownByBizId(bizId));
        dto.setDownloadUrlById(fileProperties.getDownById(file.getId()));
        dto.setDownloadUrlByUrl(fileProperties.getDownByUrl(file.getUrl(), file.getSubmittedFileName()));
        return dto;
    }


    private void setDate(Attachment file) {
        LocalDateTime now = LocalDateTime.now();
        file.setCreateMonth(DateUtils.formatAsYearMonthEn(now));
        file.setCreateWeek(DateUtils.formatAsYearWeekEn(now));
        file.setCreateDay(DateUtils.formatAsDateEn(now));
    }

    @Override
    public void remove(Long[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return;
        }

        List<Attachment> list = super.list(Wrappers.<Attachment>lambdaQuery().in(Attachment::getId, ids));
        if (list.isEmpty()) {
            return;
        }
        super.removeByIds(Arrays.asList(ids));

        fileStrategy.delete(list.stream().map((fi) -> FileDeleteDO.builder()
                .relativePath(fi.getRelativePath())
                .fileName(fi.getFilename())
                .group(fi.getGroup())
                .path(fi.getPath())
                .file(false)
                .build())
                .collect(Collectors.toList()));
    }

    @Override
    public void removeByBizIdAndBizType(String bizId, String bizType) {
        List<Attachment> list = super.list(
                Wraps.<Attachment>lbQ()
                        .eqNe(Attachment::getBizId, bizId)
                        .eqNe(Attachment::getBizType, bizType));
        if (list.isEmpty()) {
            return;
        }
        remove(list.stream().mapToLong(Attachment::getId).boxed().toArray(Long[]::new));
    }

    @Override
    public List<AttachmentResultDTO> find(String[] bizTypes, String[] bizIds) {
        return baseMapper.find(bizTypes, bizIds);
    }

    @Override
    public void download(HttpServletRequest request, HttpServletResponse response, Long[] ids) throws Exception {
        List<Attachment> list = (List<Attachment>) super.listByIds(Arrays.asList(ids));
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
        if (StringUtils.isEmpty(filename)) {
            filename = "未知文件名.txt";
        }
        List<Attachment> list = Arrays.asList(Attachment.builder()
                .url(url).submittedFileName(filename).size(0L).dataType(DataType.DOC).build());
        down(request, response, list);
    }

    private void down(HttpServletRequest request, HttpServletResponse response, List<Attachment> list) throws Exception {
        if (list.isEmpty()) {
            return;
        }
        List<FileDO> listDO = list.stream().map((file) ->
                FileDO.builder()
                        .url(file.getUrl())
                        .submittedFileName(file.getSubmittedFileName())
                        .size(file.getSize())
                        .dataType(file.getDataType())
                        .build())
                .collect(Collectors.toList());
        fileBiz.down(listDO, request, response);
    }

}
