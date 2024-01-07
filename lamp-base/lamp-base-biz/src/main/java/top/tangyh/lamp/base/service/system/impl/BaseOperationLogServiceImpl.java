package top.tangyh.lamp.base.service.system.impl;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.impl.SuperServiceImpl;
import top.tangyh.lamp.base.entity.system.BaseOperationLog;
import top.tangyh.lamp.base.entity.system.BaseOperationLogExt;
import top.tangyh.lamp.base.manager.system.BaseOperationLogManager;
import top.tangyh.lamp.base.mapper.system.BaseOperationLogExtMapper;
import top.tangyh.lamp.base.service.system.BaseOperationLogService;
import top.tangyh.lamp.base.vo.result.system.BaseOperationLogResultVO;
import top.tangyh.lamp.base.vo.save.system.BaseOperationLogSaveVO;

import java.time.LocalDateTime;

/**
 * <p>
 * 业务实现类
 * 操作日志
 * </p>
 *
 * @author zuihou
 * @date 2021-11-08
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class BaseOperationLogServiceImpl extends SuperServiceImpl<BaseOperationLogManager, Long, BaseOperationLog> implements BaseOperationLogService {

    private final BaseOperationLogExtMapper baseOperationLogExtMapper;

    @Override
    public BaseOperationLogResultVO getDetail(Long id) {
        BaseOperationLog operationLog = superManager.getById(id);
        BaseOperationLogExt ext = baseOperationLogExtMapper.selectById(id);

        BaseOperationLogResultVO result = BeanUtil.toBean(ext, BaseOperationLogResultVO.class);
        BeanUtil.copyProperties(operationLog, result);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum) {
        return superManager.clearLog(clearBeforeTime, clearBeforeNum) > 0;
    }

    @Override
    public <SaveVO> BaseOperationLog save(SaveVO saveVO) {
        BaseOperationLogSaveVO logSaveVO = (BaseOperationLogSaveVO) saveVO;
        BaseOperationLogExt baseOperationLogExt = BeanUtil.toBean(saveVO, BaseOperationLogExt.class);
        baseOperationLogExtMapper.insert(baseOperationLogExt);
        logSaveVO.setId(baseOperationLogExt.getId());
        return super.save(logSaveVO);
    }
}
