package top.tangyh.lamp.base.service.user.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.request.PageParams;
import top.tangyh.basic.base.service.impl.SuperCacheServiceImpl;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.database.mybatis.conditions.query.LbQueryWrap;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.lamp.base.entity.user.BaseEmployee;
import top.tangyh.lamp.base.entity.user.BaseEmployeeOrgRel;
import top.tangyh.lamp.base.entity.user.BaseEmployeeRoleRel;
import top.tangyh.lamp.base.manager.user.BaseEmployeeManager;
import top.tangyh.lamp.base.manager.user.BaseEmployeeOrgRelManager;
import top.tangyh.lamp.base.manager.user.BaseEmployeeRoleRelManager;
import top.tangyh.lamp.base.service.user.BaseEmployeeService;
import top.tangyh.lamp.base.vo.query.user.BaseEmployeePageQuery;
import top.tangyh.lamp.base.vo.result.user.BaseEmployeeResultVO;
import top.tangyh.lamp.base.vo.save.user.BaseEmployeeRoleRelSaveVO;
import top.tangyh.lamp.base.vo.save.user.BaseEmployeeSaveVO;
import top.tangyh.lamp.base.vo.update.user.BaseEmployeeUpdateVO;
import top.tangyh.lamp.common.cache.base.user.EmployeeOrgCacheKeyBuilder;
import top.tangyh.lamp.common.cache.base.user.EmployeeRoleCacheKeyBuilder;
import top.tangyh.lamp.common.constant.BizConstant;

import top.tangyh.lamp.common.constant.RoleConstant;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 业务实现类
 * 员工
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class BaseEmployeeServiceImpl extends SuperCacheServiceImpl<BaseEmployeeManager, Long, BaseEmployee> implements BaseEmployeeService {
    private final BaseEmployeeRoleRelManager baseEmployeeRoleRelManager;
    private final BaseEmployeeOrgRelManager baseEmployeeOrgRelManager;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveBatch(Collection<BaseEmployee> entityList) {
        return superManager.saveBatch(entityList);
    }

    @Override
    public IPage<BaseEmployeeResultVO> findPageResultVO(PageParams<BaseEmployeePageQuery> params) {
        IPage<BaseEmployee> page = params.buildPage(BaseEmployee.class);
        BaseEmployeePageQuery model = params.getModel();
        LbQueryWrap<BaseEmployee> wrap = Wraps.lbQ();
        wrap.like(BaseEmployee::getRealName, model.getRealName())
                .in(BaseEmployee::getPositionStatus, model.getPositionStatus())
                .in(BaseEmployee::getPositionId, model.getPositionId())
                .eq(BaseEmployee::getActiveStatus, model.getActiveStatus())
                .eq(BaseEmployee::getState, model.getState())
                .in(BaseEmployee::getUserId, model.getUserIdList())
                .inSql(CollUtil.isNotEmpty(model.getOrgIdList()), BaseEmployee::getId,
                        " select eor.employee_id from base_employee_org_rel eor where eor.employee_id = e.id " +
                                "  and eor.org_id in ( " + StrUtil.join(",", model.getOrgIdList()) + " )  ")
        ;

        if (StrUtil.equalsAny(model.getScope(), BizConstant.SCOPE_BIND, BizConstant.SCOPE_UN_BIND) && model.getRoleId() != null) {
            String sql = " select err.employee_id from base_employee_role_rel err where err.employee_id = e.id \n" +
                    "  and err.role_id =   " + model.getRoleId();
            if (BizConstant.SCOPE_BIND.equals(model.getScope())) {
                wrap.inSql(BaseEmployee::getId, sql);
            } else {
                wrap.notInSql(BaseEmployee::getId, sql);
            }
        }

        return superManager.selectPageResultVO(page, wrap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> saveEmployeeRole(BaseEmployeeRoleRelSaveVO saveVO) {
        if (saveVO.getFlag() == null) {
            saveVO.setFlag(true);
        }

        baseEmployeeRoleRelManager.remove(Wraps.<BaseEmployeeRoleRel>lbQ().eq(BaseEmployeeRoleRel::getEmployeeId, saveVO.getEmployeeId())
                .in(BaseEmployeeRoleRel::getRoleId, saveVO.getRoleIdList()));

        if (saveVO.getFlag() && CollUtil.isNotEmpty(saveVO.getRoleIdList())) {
            List<BaseEmployeeRoleRel> list = saveVO.getRoleIdList().stream()
                    .map(roleId -> BaseEmployeeRoleRel.builder()
                            .roleId(roleId).employeeId(saveVO.getEmployeeId())
                            .build()).toList();
            baseEmployeeRoleRelManager.saveBatch(list);
        }

        cacheOps.del(EmployeeRoleCacheKeyBuilder.build(saveVO.getEmployeeId()));
        return findEmployeeRoleByEmployeeId(saveVO.getEmployeeId());
    }

    @Override
    public List<Long> findEmployeeRoleByEmployeeId(Long employeeId) {
        return baseEmployeeRoleRelManager.listObjs(Wrappers.<BaseEmployeeRoleRel>lambdaQuery()
                        .select(BaseEmployeeRoleRel::getRoleId)
                        .eq(BaseEmployeeRoleRel::getEmployeeId, employeeId),
                Convert::toLong
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <SaveVO> BaseEmployee save(SaveVO saveVO) {
        BaseEmployeeSaveVO employeeSaveVO = (BaseEmployeeSaveVO) saveVO;
        BaseEmployee baseEmployee = BeanUtil.toBean(employeeSaveVO, BaseEmployee.class);
        baseEmployee.setCreatedOrgId(ContextUtil.getCurrentDeptId());
        superManager.save(baseEmployee);
        List<Long> orgIdList = employeeSaveVO.getOrgIdList();
        saveEmployeeOrg(baseEmployee, orgIdList);
        return baseEmployee;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <UpdateVO> BaseEmployee updateById(UpdateVO updateVO) {
        BaseEmployeeUpdateVO employeeUpdateVO = (BaseEmployeeUpdateVO) updateVO;
        BaseEmployee baseEmployee = BeanUtil.toBean(updateVO, BaseEmployee.class);
        superManager.updateById(baseEmployee);
        List<Long> orgIdList = employeeUpdateVO.getOrgIdList();

        saveEmployeeOrg(baseEmployee, orgIdList);
        return baseEmployee;
    }

    private void saveEmployeeOrg(BaseEmployee baseEmployee, List<Long> orgIdList) {
        baseEmployeeOrgRelManager.removeByEmployeeId(baseEmployee.getId());
        if (CollUtil.isNotEmpty(orgIdList)) {
            List<BaseEmployeeOrgRel> eoList = orgIdList.stream().map(orgId ->
                    BaseEmployeeOrgRel.builder()
                            .employeeId(baseEmployee.getId())
                            .orgId(orgId)
                            .build()).toList();
            baseEmployeeOrgRelManager.saveBatch(eoList);
        }

        cacheOps.del(EmployeeOrgCacheKeyBuilder.build(baseEmployee.getId()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<Long> idList) {
        boolean flag = superManager.removeByIds(idList);
        baseEmployeeOrgRelManager.removeByEmployeeIds(idList);
        baseEmployeeRoleRelManager.removeByEmployeeIds(idList);
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveBatchBaseEmployeeAndRole(List<BaseEmployee> employeeList) {
        ArgumentAssert.notEmpty(employeeList, "员工列表不能为空");
        superManager.saveBatch(employeeList);

        List<Long> employeeIdList = employeeList.stream().map(BaseEmployee::getId).toList();
        return baseEmployeeRoleRelManager.bindRole(employeeIdList, RoleConstant.TENANT_ADMIN);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(BaseEmployee baseEmployee) {
        return superManager.updateById(baseEmployee);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAllById(BaseEmployee baseEmployee) {
        return superManager.updateAllById(baseEmployee);
    }

    @Override
    public BaseEmployee getEmployeeByUser(Long userId) {
        return superManager.getEmployeeByUser(userId);
    }

    @Override
    public List<BaseEmployeeResultVO> listEmployeeByUserId(Long userId) {
        return superManager.listEmployeeByUserId(userId);
    }
}
