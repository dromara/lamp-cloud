package top.tangyh.lamp.system.manager.application.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.manager.impl.SuperManagerImpl;
import top.tangyh.lamp.system.entity.application.DefUserApplication;
import top.tangyh.lamp.system.manager.application.DefUserApplicationManager;
import top.tangyh.lamp.system.mapper.application.DefUserApplicationMapper;

/**
 * <p>
 * 通用业务实现类
 * 用户的默认应用
 * </p>
 *
 * @author zuihou
 * @date 2022-03-06
 * @create [2022-03-06] [zuihou] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DefUserApplicationManagerImpl extends SuperManagerImpl<DefUserApplicationMapper, DefUserApplication> implements DefUserApplicationManager {
}
