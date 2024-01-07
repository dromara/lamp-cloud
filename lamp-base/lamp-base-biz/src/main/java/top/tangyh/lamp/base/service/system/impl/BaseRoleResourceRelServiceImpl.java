package top.tangyh.lamp.base.service.system.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.impl.SuperServiceImpl;
import top.tangyh.lamp.base.entity.system.BaseRoleResourceRel;
import top.tangyh.lamp.base.manager.system.BaseRoleResourceRelManager;
import top.tangyh.lamp.base.service.system.BaseRoleResourceRelService;


/**
 * <p>
 * 业务实现类
 * 角色的资源
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class BaseRoleResourceRelServiceImpl extends SuperServiceImpl<BaseRoleResourceRelManager, Long, BaseRoleResourceRel>
        implements BaseRoleResourceRelService {
}
