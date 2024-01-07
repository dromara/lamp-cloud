package top.tangyh.lamp.datascope.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.utils.CollHelper;
import top.tangyh.lamp.common.constant.DefValConstants;

import top.tangyh.lamp.datascope.entity.BaseOrgBO;
import top.tangyh.lamp.datascope.mapper.DataScopeMapper;
import top.tangyh.lamp.model.enumeration.base.OrgTypeEnum;

import java.util.Collections;
import java.util.List;

/**
 * @author tangyh
 * @version v1.0
 * @date 2022/4/12 9:17 AM
 * @create [2022/4/12 9:17 AM ] [tangyh] [初始创建]
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrgHelperService {
    private final DataScopeMapper dataScopeMapper;

    private static BaseOrgBO getMainCompany(ImmutableMap<Long, BaseOrgBO> map, Long parentId) {
        BaseOrgBO parent = map.get(parentId);
        if (parent == null) {
            return null;
        }
        if (OrgTypeEnum.COMPANY.eq(parent.getType())) {
            return parent;
        }

        return getMainCompany(map, parent.getParentId());
    }

    /**
     * 根据员工ID查询主部门ID
     *
     * @param employeeId 员工ID
     * @return java.lang.Long
     * @author tangyh
     * @date 2022/4/12 12:33 PM
     * @create [2022/4/12 12:33 PM ] [tangyh] [初始创建]
     */
    
    public Long getMainDeptIdByEmployeeId(Long employeeId) {
        BaseOrgBO baseOrg = dataScopeMapper.getMainDeptIdByEmployeeId(employeeId);
        return baseOrg != null ? baseOrg.getId() : null;
    }

    /**
     * 根据员工ID查询他所属的 机构及其子机构
     * 本级及子级
     * 本级：员工所属
     *
     * @param employeeId employeeId
     * @return java.util.List<java.lang.Long>
     * @author tangyh
     * @date 2022/4/12 12:34 PM
     * @create [2022/4/12 12:34 PM ] [tangyh] [初始创建]
     */
    
    public List<Long> findDeptAndChildrenIdByEmployeeId(Long employeeId) {
        BaseOrgBO baseOrg = dataScopeMapper.getMainDeptIdByEmployeeId(employeeId);
        if (baseOrg == null) {
            return Collections.emptyList();
        }
        String parentIdStr = DefValConstants.TREE_PATH_SPLIT + baseOrg.getId() + DefValConstants.TREE_PATH_SPLIT;
        List<BaseOrgBO> list = dataScopeMapper.selectList(Wraps.<BaseOrgBO>lbQ().like(BaseOrgBO::getTreePath, parentIdStr));
        list.add(baseOrg);
        return list.stream().map(BaseOrgBO::getId).toList();
    }

    /**
     * 根据员工ID查询主单位
     *
     * @param employeeId employeeId
     * @return java.lang.Long
     * @author tangyh
     * @date 2022/4/12 12:34 PM
     * @create [2022/4/12 12:34 PM ] [tangyh] [初始创建]
     */
    
    public Long getMainCompanyIdByEmployeeId(Long employeeId) {
        BaseOrgBO mainCompany = getMainCompanyByEmployeeId(employeeId);
        return mainCompany != null ? mainCompany.getId() : null;
    }

    /**
     * 根据员工ID查询所属单位以及子单位和子部门
     *
     * @param employeeId employeeId
     * @return java.util.List<java.lang.Long>
     * @author tangyh
     * @date 2022/4/12 12:53 PM
     * @create [2022/4/12 12:53 PM ] [tangyh] [初始创建]
     */
    
    public List<Long> findCompanyAndChildrenIdByEmployeeId(Long employeeId) {
        BaseOrgBO mainCompany = getMainCompanyByEmployeeId(employeeId);
        if (mainCompany == null) {
            return Collections.emptyList();
        }
        String parentIdStr = DefValConstants.TREE_PATH_SPLIT + mainCompany.getId() + DefValConstants.TREE_PATH_SPLIT;
        List<BaseOrgBO> list = dataScopeMapper.selectList(Wraps.<BaseOrgBO>lbQ().like(BaseOrgBO::getTreePath, parentIdStr));
        list.add(mainCompany);
        return list.stream().map(BaseOrgBO::getId).toList();
    }

    private BaseOrgBO getMainCompanyByEmployeeId(Long employeeId) {
        // 用户所在部门
        BaseOrgBO baseOrg = dataScopeMapper.getMainDeptIdByEmployeeId(employeeId);
        if (baseOrg == null) {
            return null;
        }
        // 用户直接挂在单位上，就直接返回此单位
        if (OrgTypeEnum.COMPANY.eq(baseOrg.getType())) {
            return baseOrg;
        }
        // 用户挂在部门上，就向上查询单位
        List<String> parentIdStrList = StrUtil.split(baseOrg.getTreePath(), DefValConstants.TREE_PATH_SPLIT, true, true);
        List<Long> parentIdList = Convert.toList(Long.class, parentIdStrList);
        // 若部门上级没有单位直接返回部门
        if (CollUtil.isEmpty(parentIdList)) {
            return baseOrg;
        }
        List<BaseOrgBO> parentList = dataScopeMapper.selectBatchIds(parentIdList);
        ImmutableMap<Long, BaseOrgBO> map = CollHelper.uniqueIndex(parentList, BaseOrgBO::getId, org -> org);
        return getMainCompany(map, baseOrg.getParentId());
    }
}
