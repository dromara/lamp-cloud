package com.tangyh.lamp.file.strategy.impl;

import cn.hutool.core.util.StrUtil;
import com.tangyh.basic.context.ContextUtil;
import com.tangyh.basic.exception.BizException;
import com.tangyh.basic.utils.StrPool;
import com.tangyh.lamp.file.domain.FileDeleteDO;
import com.tangyh.lamp.file.entity.Attachment;
import com.tangyh.lamp.file.properties.FileServerProperties;
import com.tangyh.lamp.file.strategy.FileStrategy;
import com.tangyh.lamp.file.utils.FileTypeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

import static com.tangyh.basic.exception.code.ExceptionCode.BASE_VALID_PARAM;
import static com.tangyh.basic.utils.DateUtils.SLASH_DATE_FORMAT;


/**
 * 文件抽象策略 处理类
 *
 * @author zuihou
 * @date 2019/06/17
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractFileStrategy implements FileStrategy {

    private static final String FILE_SPLIT = ".";
    protected final FileServerProperties fileProperties;

    /**
     * 上传文件
     *
     * @param multipartFile 文件
     * @return 附件
     */
    @Override
    public Attachment upload(MultipartFile multipartFile, String bucket, String bizType) {
        try {
            if (!StrUtil.contains(multipartFile.getOriginalFilename(), FILE_SPLIT)) {
                throw BizException.wrap(BASE_VALID_PARAM.build("文件缺少后缀名"));
            }

            Attachment file = Attachment.builder()
                    .originalFileName(multipartFile.getOriginalFilename())
                    .contentType(multipartFile.getContentType())
                    .size(multipartFile.getSize())
                    .bizType(bizType)
                    .ext(FilenameUtils.getExtension(multipartFile.getOriginalFilename()))
                    .build();
            file.setFileType(FileTypeUtil.getFileType(multipartFile.getContentType()));
            uploadFile(file, multipartFile, bucket);
            return file;
        } catch (Exception e) {
            log.error("ex", e);
            throw BizException.wrap(BASE_VALID_PARAM.build("文件上传失败"), e);
        }
    }

    /**
     * 具体类型执行上传操作
     *
     * @param file          附件
     * @param multipartFile 文件
     * @param bucket        bucket
     * @throws Exception 异常
     */
    protected abstract void uploadFile(Attachment file, MultipartFile multipartFile, String bucket) throws Exception;


    @Override
    public boolean delete(List<FileDeleteDO> list) {
        if (list.isEmpty()) {
            return true;
        }
        boolean flag = false;
        for (FileDeleteDO file : list) {
            try {
                delete(file);
                flag = true;
            } catch (Exception e) {
                log.error("删除文件失败", e);
            }
        }
        return flag;
    }

    /**
     * 具体执行删除方法， 无需处理异常
     *
     * @param file 文件
     * @author zuihou
     * @date 2019-05-07
     */
    protected abstract void delete(FileDeleteDO file);


    /**
     * 获取年月日 2020/09/01
     *
     * @return
     */
    protected String getDateFolder() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(SLASH_DATE_FORMAT));
    }

    /** 企业/年/月/日/业务类型/唯一文件名 */
    protected String getPath(String uniqueFileName, String bizType) {
        return new StringJoiner(StrPool.SLASH).add(ContextUtil.getTenant()).add(getDateFolder()).add(bizType).add(uniqueFileName).toString();
    }

    protected String getUniqueFileName(Attachment file) {
        return UUID.randomUUID().toString().replace("-", "") + StrPool.DOT + file.getExt();
    }
}
