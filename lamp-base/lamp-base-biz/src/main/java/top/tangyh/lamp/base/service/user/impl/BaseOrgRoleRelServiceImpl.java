package top.tangyh.lamp.base.service.user.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.impl.SuperServiceImpl;
import top.tangyh.lamp.base.entity.user.BaseOrgRoleRel;
import top.tangyh.lamp.base.manager.user.BaseOrgRoleRelManager;
import top.tangyh.lamp.base.service.user.BaseOrgRoleRelService;


/**
 * <p>
 * 业务实现类
 * 组织的角色
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class BaseOrgRoleRelServiceImpl extends SuperServiceImpl<BaseOrgRoleRelManager, Long, BaseOrgRoleRel> implements BaseOrgRoleRelService {

}
