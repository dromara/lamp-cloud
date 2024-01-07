package top.tangyh.lamp.base.service.user.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.impl.SuperCacheServiceImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.database.mybatis.conditions.query.LbQueryWrap;
import top.tangyh.basic.interfaces.echo.EchoService;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.TreeUtil;
import top.tangyh.lamp.base.entity.user.BaseEmployeeOrgRel;
import top.tangyh.lamp.base.entity.user.BaseOrg;
import top.tangyh.lamp.base.entity.user.BaseOrgRoleRel;
import top.tangyh.lamp.base.manager.user.BaseEmployeeOrgRelManager;
import top.tangyh.lamp.base.manager.user.BaseOrgManager;
import top.tangyh.lamp.base.manager.user.BaseOrgRoleRelManager;
import top.tangyh.lamp.base.service.user.BaseOrgService;
import top.tangyh.lamp.base.vo.query.user.BaseOrgPageQuery;
import top.tangyh.lamp.base.vo.result.user.BaseOrgResultVO;
import top.tangyh.lamp.base.vo.save.user.BaseOrgRoleRelSaveVO;
import top.tangyh.lamp.base.vo.save.user.BaseOrgSaveVO;
import top.tangyh.lamp.base.vo.update.user.BaseOrgUpdateVO;
import top.tangyh.lamp.common.cache.base.user.OrgRoleCacheKeyBuilder;
import top.tangyh.lamp.common.constant.DefValConstants;
import top.tangyh.lamp.model.enumeration.base.OrgTypeEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 组织
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 */
@Slf4j
@Service
@RequiredArgsConstructor

public class BaseOrgServiceImpl extends SuperCacheServiceImpl<BaseOrgManager, Long, BaseOrg>
        implements BaseOrgService {
    private final BaseEmployeeOrgRelManager baseEmployeeOrgRelManager;
    private final BaseOrgRoleRelManager baseOrgRoleRelManager;
    private final EchoService echoService;

    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> ids) {
        return superManager.findByIds(ids.stream().map(Convert::toLong).collect(Collectors.toSet()));
    }

    private void fillOrg(BaseOrg org) {
        if (org.getParentId() == null || org.getParentId() <= 0) {
            org.setParentId(DefValConstants.PARENT_ID);
            org.setTreePath(DefValConstants.TREE_PATH_SPLIT);
            org.setTreeGrade(DefValConstants.TREE_GRADE);
        } else {
            BaseOrg parent = this.superManager.getByIdCache(org.getParentId());
            ArgumentAssert.notNull(parent, "请正确填写父级组织");

            org.setTreeGrade(parent.getTreeGrade() + 1);
            org.setTreePath(TreeUtil.getTreePath(parent.getTreePath(), parent.getId()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean check(String name, Long parentId, Long id) {
        ArgumentAssert.notEmpty(name, "请填写名称");
        LbQueryWrap<BaseOrg> wrap = Wraps.<BaseOrg>lbQ().eq(BaseOrg::getName, name).eq(BaseOrg::getParentId, parentId).ne(BaseOrg::getId, id);
        return superManager.count(wrap) > 0;
    }

    @Override
    protected <SaveVO> BaseOrg saveBefore(SaveVO saveVO) {
        BaseOrgSaveVO baseOrgSaveVO = (BaseOrgSaveVO) saveVO;
        ArgumentAssert.isFalse(check(baseOrgSaveVO.getName(), baseOrgSaveVO.getParentId(), null), StrUtil.format("组织[{}]已经存在", baseOrgSaveVO.getName()));
        BaseOrg baseOrg = super.saveBefore(baseOrgSaveVO);
        fillOrg(baseOrg);
        return baseOrg;
    }

    @Override
    protected <UpdateVO> BaseOrg updateBefore(UpdateVO updateVO) {
        BaseOrgUpdateVO baseOrgUpdateVO = (BaseOrgUpdateVO) updateVO;
        ArgumentAssert.isFalse(check(baseOrgUpdateVO.getName(), baseOrgUpdateVO.getParentId(), baseOrgUpdateVO.getId()), StrUtil.format("组织[{}]已经存在", baseOrgUpdateVO.getName()));
        BaseOrg baseOrg = super.updateBefore(baseOrgUpdateVO);
        fillOrg(baseOrg);
        return baseOrg;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<Long> idList) {
        if (idList.isEmpty()) {
            return false;
        }
        long userCount = baseEmployeeOrgRelManager.count(Wraps.<BaseEmployeeOrgRel>lbQ().in(BaseEmployeeOrgRel::getOrgId, idList));
        ArgumentAssert.isFalse(userCount > 0, "您选择的组织下还存在用户，禁止删除！请先移除该组织下所有用户后在进行删除！");
        long childrenCount = superManager.count(Wraps.<BaseOrg>lbQ().in(BaseOrg::getParentId, idList));
        ArgumentAssert.isFalse(childrenCount > 0, "您选择的组织下还存在子组织，禁止删除！请先移除该组织下所有子组织后在进行删除！");

        boolean flag = superManager.removeByIds(idList);

        baseOrgRoleRelManager.deleteByOrg(idList);
        baseEmployeeOrgRelManager.deleteByOrg(idList);
        return flag;
    }

    @Override
    public List<BaseOrgResultVO> tree(BaseOrgPageQuery query) {
        List<BaseOrg> list = superManager.list(Wraps.<BaseOrg>lbQ()
                .like(BaseOrg::getName, query.getName()).eq(BaseOrg::getState, query.getState()).orderByAsc(BaseOrg::getSortValue));
        List<BaseOrgResultVO> treeList = BeanUtil.copyToList(list, BaseOrgResultVO.class);
        echoService.action(treeList);
        return TreeUtil.buildTree(treeList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> saveOrgRole(BaseOrgRoleRelSaveVO saveVO) {
        if (saveVO.getFlag() == null) {
            saveVO.setFlag(true);
        }

        baseOrgRoleRelManager.remove(Wraps.<BaseOrgRoleRel>lbQ().eq(BaseOrgRoleRel::getOrgId, saveVO.getOrgId())
                .in(BaseOrgRoleRel::getRoleId, saveVO.getRoleIdList()));

        if (saveVO.getFlag() && CollUtil.isNotEmpty(saveVO.getRoleIdList())) {
            List<BaseOrgRoleRel> list = saveVO.getRoleIdList().stream()
                    .map(roleId -> BaseOrgRoleRel.builder()
                            .roleId(roleId).orgId(saveVO.getOrgId())
                            .build()).toList();
            baseOrgRoleRelManager.saveBatch(list);
        }

        cacheOps.del(OrgRoleCacheKeyBuilder.build(saveVO.getOrgId()));
        return findOrgRoleByOrgId(saveVO.getOrgId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Long> findOrgRoleByOrgId(Long orgId) {
        return baseOrgRoleRelManager.listObjs(Wrappers.<BaseOrgRoleRel>lambdaQuery()
                        .select(BaseOrgRoleRel::getRoleId)
                        .eq(BaseOrgRoleRel::getOrgId, orgId),
                Convert::toLong
        );
    }


    @Override
    @Transactional(readOnly = true)
    public List<BaseOrg> findDeptByEmployeeId(Long employeeId, Long companyId) {
        // 员工所属的机构 ID （可能含有单位或部门）
        List<Long> orgIdList = baseEmployeeOrgRelManager.findOrgIdByEmployeeId(employeeId);
        // 员工所属的机构 实体类
        List<BaseOrg> orgList = findByIds(orgIdList, null);

        /*
         * 有可能 companyId 为空，但 orgIdList 不为空
         * 原因： 在维护机构数据时， 没有将 部门 挂在 单位 下，而是直接将 部门 作为根节点，并挂载 子部门。
         */

        return orgList.stream()
                // 只查找部门
                .filter(item -> OrgTypeEnum.DEPT.eq(item.getType()))
                // 限定查找 companyId 的下级部门
                .filter(item -> companyId == null || StrUtil.contains(item.getTreePath(), TreeUtil.buildTreePath(companyId)))
                .toList();
    }


    @Override
    public BaseOrg getDefaultOrg(List<BaseOrg> orgList, Long lastOrgId) {
        if (CollUtil.isEmpty(orgList)) {
            return null;
        }
        BaseOrg sysOrg = null;
        if (lastOrgId != null) {
            sysOrg = orgList.stream().filter(item -> lastOrgId.equals(item.getId())).findFirst().orElse(null);
        }
        if (sysOrg == null && !orgList.isEmpty()) {
            sysOrg = orgList.get(0);
        }
        return sysOrg;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BaseOrg> findCompanyByEmployeeId(Long employeeId) {
        // 下文中提到的机构：指 base_org 中的数据，无论它的 type 为单位或部门

        // 员工所属的机构 ID
        List<Long> orgIdList = baseEmployeeOrgRelManager.findOrgIdByEmployeeId(employeeId);
        // 员工所属的机构 实体类
        List<BaseOrg> orgList = findByIds(orgIdList, null);

        // 员工所属的机构的所有上级ID
        List<Long> parentIdList = orgList.stream()
                .map(item -> StrUtil.splitToArray(item.getTreePath(), DefValConstants.TREE_PATH_SPLIT))
                // 数组流 转 字符串流
                .flatMap(Arrays::stream)
                .distinct()
                // 去除空数据
                .filter(ObjectUtil::isNotEmpty)
                .map(Convert::toLong)
                // 类型转换
                .toList();

        // 员工所属的机构 以及 上级机构

        List<BaseOrg> sysOrgList = new ArrayList<>(orgList);
        if (CollUtil.isNotEmpty(parentIdList)) {
            List<BaseOrg> parentList = superManager.findByIds(parentIdList, null);
            sysOrgList.addAll(parentList);
        }

        // 员工所属的 单位或上级单位
        List<BaseOrg> companyList = new ArrayList<>();
        Set<Long> companyIdSet = new HashSet<>();
        for (BaseOrg sysOrg : sysOrgList) {
            if (OrgTypeEnum.COMPANY.eq(sysOrg.getType()) && !companyIdSet.contains(sysOrg.getId())) {
                companyList.add(sysOrg);
                companyIdSet.add(sysOrg.getId());
            }
        }
        return companyList;
    }

}
