package top.tangyh.lamp.file.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.tangyh.basic.base.service.impl.SuperServiceImpl;
import top.tangyh.basic.exception.BizException;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.BeanPlusUtil;
import top.tangyh.lamp.file.entity.File;
import top.tangyh.lamp.file.manager.FileManager;
import top.tangyh.lamp.file.properties.FileServerProperties;
import top.tangyh.lamp.file.service.FileService;
import top.tangyh.lamp.file.strategy.FileContext;
import top.tangyh.lamp.file.vo.param.FileUploadVO;
import top.tangyh.lamp.file.vo.result.FileResultVO;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务实现类
 * 增量文件上传日志
 * </p>
 *
 * @author tangyh
 * @date 2021-06-30
 * @create [2021-06-30] [tangyh] [初始创建]
 */
@Slf4j
@Service
@Transactional(readOnly = true)

public class FileServiceImpl extends SuperServiceImpl<FileManager, Long, File> implements FileService {
    @Resource
    private FileContext fileContext;
    @Resource
    private FileManager fileManager;
    @Resource
    private FileServerProperties fileServerProperties;

    @Override
    public List<FileResultVO> listByBizIdAndBizType(Long bizId, String bizType) {
        ArgumentAssert.notNull(bizId, "请传入业务id");
        return superManager.listByBizIdAndBizType(bizId, bizType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FileResultVO upload(MultipartFile file, FileUploadVO fileUploadVO) {
        // 忽略路径字段,只处理文件类型
        if (file.isEmpty()) {
            throw new BizException("请上传有效文件");
        }

        if (!fileServerProperties.validSuffix(file.getOriginalFilename())) {
            throw new BizException("文件后缀不支持");
        }
        if (StrUtil.containsAny(file.getOriginalFilename(), "../", "./")) {
            throw new BizException("文件名不能含有特殊字符");
        }

        File fileFile = fileContext.upload(file, fileUploadVO);
        fileManager.save(fileFile);
        return BeanPlusUtil.toBean(fileFile, FileResultVO.class);
    }

    @Override
    public Map<String, String> findUrlByPath(List<String> paths) {
        if (CollUtil.isEmpty(paths)) {
            return Collections.emptyMap();
        }
        return fileContext.findUrlByPath(paths);
    }

    @Override
    public Map<Long, String> findUrlById(List<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyMap();
        }
        return fileContext.findUrlById(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return false;
        }
        List<File> list = list(Wrappers.<File>lambdaQuery().in(File::getId, ids));
        if (list.isEmpty()) {
            return false;
        }
        fileManager.removeByIds(ids);
        return fileContext.delete(list);
    }

    @Override
    public void download(HttpServletRequest request, HttpServletResponse response, List<Long> ids) throws Exception {
        List<File> list = fileManager.listByIds(ids);
        ArgumentAssert.notEmpty(list, "请配置正确的文件存储类型");
        fileContext.download(request, response, list);
    }

    @Override
    public void download(HttpServletRequest request, HttpServletResponse response, Long id) throws Exception {
        File file = fileManager.getById(id);
        ArgumentAssert.notNull(file, "请配置正确的文件存储类型");
        fileContext.download(request, response, file);
    }
}
