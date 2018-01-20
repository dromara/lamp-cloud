package com.github.zuihou.admin.repository.authority.service.impl;

import com.github.zuihou.admin.constant.ResourcesType;
import com.github.zuihou.admin.entity.authority.po.AdminResources;
import com.github.zuihou.admin.entity.authority.po.AdminRole;
import com.github.zuihou.admin.entity.authority.po.ApplicationRole;
import com.github.zuihou.admin.entity.authority.po.RoleResource;
import com.github.zuihou.admin.repository.authority.dao.AdminResourcesMapper;
import com.github.zuihou.admin.repository.authority.dao.AdminRoleMapper;
import com.github.zuihou.admin.repository.authority.dao.ApplicationRoleMapper;
import com.github.zuihou.admin.repository.authority.dao.RoleResourceMapper;
import com.github.zuihou.admin.repository.authority.example.AdminResourcesExample;
import com.github.zuihou.admin.repository.authority.example.AdminRoleExample;
import com.github.zuihou.admin.repository.authority.example.ApplicationRoleExample;
import com.github.zuihou.admin.repository.authority.example.RoleResourceExample;
import com.github.zuihou.admin.repository.authority.service.AdminRoleService;
import com.github.zuihou.base.dao.BaseDao;
import com.github.zuihou.base.service.impl.BaseServiceImpl;
import com.github.zuihou.commons.constant.DeleteStatus;
import com.github.zuihou.commons.constant.EnableStatus;
import com.github.zuihou.commons.context.BaseContextHandler;
import com.github.zuihou.commons.context.CommonConstants;
import com.github.zuihou.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zuihou
 * @createTime 2017-12-15 10:49
 */
@Service
@Slf4j
public class AdminRoleServiceImpl extends BaseServiceImpl<Long, AdminRole, AdminRoleExample> implements AdminRoleService {
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Autowired
    private ApplicationRoleMapper applicationRoleMapper;
    @Autowired
    private AdminResourcesMapper adminResourcesMapper;

    @Override
    protected BaseDao<Long, AdminRole, AdminRoleExample> getDao() {
        return adminRoleMapper;
    }


    @Override
    public boolean check(String appId, String code) {
        AdminRoleExample example = new AdminRoleExample();
        example.createCriteria().andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal())
                .andAppIdEqualTo(appId).andCodeEqualTo(code);
        if (adminRoleMapper.countByExample(example) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void authorityAdmin(String appId, Long roleId, List<Long> applicationIdList) {
        ApplicationRoleExample example = new ApplicationRoleExample();
        example.createCriteria().andAppIdEqualTo(appId).andRoleIdEqualTo(roleId);
        applicationRoleMapper.deleteByExample(example);
        String userName = BaseContextHandler.getUserName();

        List<ApplicationRole> ruList = applicationIdList.stream().map((applicationId) -> {
            ApplicationRole ru = new ApplicationRole();
            ru.setAppId(appId);
            ru.setApplicationsId(applicationId);
            ru.setRoleId(roleId);
            ru.setCreateTime(new Date());
            ru.setUpdateTime(new Date());
            ru.setCreateUser(userName);
            ru.setUpdateUser(userName);
            return ru;
        }).collect(Collectors.toList());

        if (!ruList.isEmpty()) {
            applicationRoleMapper.batchInsert(ruList);
        }
    }

    @Override
    public void authorityResources(String appId, String menuGroupCode, Long roleId, List<Long> elementIdList) {
        if (StringUtils.isEmpty(menuGroupCode)) {
            menuGroupCode = CommonConstants.MENU_GROUP_CODE_DEF;
        }

        //删除该角色，该菜单组的所有权限
        RoleResourceExample raExample = new RoleResourceExample();
        raExample.createCriteria().andAppIdEqualTo(appId).andRoleIdEqualTo(roleId);
        roleResourceMapper.deleteByExample(raExample);

        //id=1 的 子菜单是id=2 ,授权时，勾选了菜单2，那么菜单1也必须被授权
        //以下代码就是重新计算具有层级关系的菜单和资源
        AdminResourcesExample rExample = new AdminResourcesExample();
        rExample.createCriteria().andAppIdEqualTo(appId)
                .andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal())
                .andIsEnableEqualTo(EnableStatus.ENABLE.getVal())
                .andTypeIn(Arrays.asList(ResourcesType.DIR.toString(), ResourcesType.MENU.toString(),
                        ResourcesType.URI.toString(), ResourcesType.BUTTON.toString()))
                .andMenuGroupCodeEqualTo(menuGroupCode);
        //查询 appid + 菜单组下，所有的菜单+资源
        List<AdminResources> resourcesList = adminResourcesMapper.selectByExample(rExample);
        if (resourcesList.isEmpty()) {
            return;
        }
        //将自定菜单组的所有菜单+资源查询出来， 转换成map (key=id, value=parentId)
        Map<Long, Long> resourceIdMap = resourcesList.stream().
                collect(Collectors.toMap(AdminResources::getId, AdminResources::getParentId));
        //list 转 set 去重
        Set<Long> relationResources = elementIdList.stream().collect(Collectors.toSet());
        //计算待授权的资源id的父资源
        elementIdList.stream().forEach((id) -> findParentId(resourceIdMap, relationResources, id));
        log.info("relationResources={}", JSONUtils.toJsonString(relationResources, true));

        String userName = BaseContextHandler.getUserName();
        List<RoleResource> raList = relationResources.stream()
                .map((resourceId) -> {
                    RoleResource rr = new RoleResource();
                    rr.setAppId(appId);
                    rr.setRoleId(roleId);
                    rr.setResourceId(resourceId);
                    rr.setUpdateTime(new Date());
                    rr.setCreateTime(new Date());
                    rr.setCreateUser(userName);
                    rr.setUpdateUser(userName);
                    return rr;
                }).collect(Collectors.toList());
        if (!raList.isEmpty()) {
            roleResourceMapper.batchInsert(raList);
        }
    }

    private void findParentId(Map<Long, Long> map, Set<Long> relationResources, Long id) {
        Long parentId = map.get(id);
        if (CommonConstants.PARENT_ID_DEF.equals(parentId) || parentId == null) {
            return;
        }

        relationResources.add(parentId);
        findParentId(map, relationResources, parentId);
    }

    @Override
    public List<AdminRole> findRole(String appId, Long applicationId) {
        return adminRoleMapper.findRole(appId, applicationId);
    }
}
