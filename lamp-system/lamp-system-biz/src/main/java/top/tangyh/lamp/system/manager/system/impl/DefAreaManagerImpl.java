package top.tangyh.lamp.system.manager.system.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.manager.impl.SuperManagerImpl;
import top.tangyh.lamp.system.entity.system.DefArea;
import top.tangyh.lamp.system.manager.system.DefAreaManager;
import top.tangyh.lamp.system.mapper.system.DefAreaMapper;

/**
 * <p>
 * 通用业务实现类
 * 地区表
 * </p>
 *
 * @author zuihou
 * @date 2021-10-13
 * @create [2021-10-13] [zuihou] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DefAreaManagerImpl extends SuperManagerImpl<DefAreaMapper, DefArea> implements DefAreaManager {
}
