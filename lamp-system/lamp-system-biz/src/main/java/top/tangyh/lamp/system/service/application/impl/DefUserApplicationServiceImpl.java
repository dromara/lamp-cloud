package top.tangyh.lamp.system.service.application.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.impl.SuperServiceImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;

import top.tangyh.lamp.system.entity.application.DefUserApplication;
import top.tangyh.lamp.system.manager.application.DefUserApplicationManager;
import top.tangyh.lamp.system.service.application.DefUserApplicationService;

/**
 * <p>
 * 业务实现类
 * 用户的默认应用
 * </p>
 *
 * @author zuihou
 * @date 2022-03-06
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class DefUserApplicationServiceImpl extends SuperServiceImpl<DefUserApplicationManager, Long, DefUserApplication> implements DefUserApplicationService {
    @Override
    public Long getMyDefAppByUserId(Long userId) {
        DefUserApplication userApplication = superManager.getOne(Wraps.<DefUserApplication>lbQ().eq(DefUserApplication::getUserId, userId), false);
        return userApplication != null ? userApplication.getApplicationId() : null;
    }
}
