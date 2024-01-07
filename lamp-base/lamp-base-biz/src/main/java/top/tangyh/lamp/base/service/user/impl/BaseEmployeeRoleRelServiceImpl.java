package top.tangyh.lamp.base.service.user.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.impl.SuperServiceImpl;
import top.tangyh.lamp.base.entity.user.BaseEmployeeRoleRel;
import top.tangyh.lamp.base.manager.user.BaseEmployeeRoleRelManager;
import top.tangyh.lamp.base.service.user.BaseEmployeeRoleRelService;


import java.util.List;

/**
 * <p>
 * 业务实现类
 * 员工的角色
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class BaseEmployeeRoleRelServiceImpl extends SuperServiceImpl<BaseEmployeeRoleRelManager, Long, BaseEmployeeRoleRel> implements BaseEmployeeRoleRelService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bindRole(List<Long> employeeIdList, String code) {
        return superManager.bindRole(employeeIdList, code);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean unBindRole(List<Long> employeeIdList, String code) {
        return superManager.unBindRole(employeeIdList, code);
    }
}
