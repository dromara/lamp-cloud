package com.github.zuihou.admin.repository.authority.service.impl;

import com.github.zuihou.admin.constant.ResourcesType;
import com.github.zuihou.admin.entity.authority.po.AdminResources;
import com.github.zuihou.admin.repository.authority.dao.AdminResourcesMapper;
import com.github.zuihou.admin.repository.authority.example.AdminResourcesExample;
import com.github.zuihou.admin.repository.authority.service.AdminResourcesService;
import com.github.zuihou.base.dao.BaseDao;
import com.github.zuihou.base.service.impl.BaseServiceImpl;
import com.github.zuihou.commons.constant.DeleteStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zuihou
 * @createTime 2017-12-15 11:16
 */
@Service
public class AdminResourcesServiceImpl extends BaseServiceImpl<Long, AdminResources, AdminResourcesExample> implements AdminResourcesService {
    @Autowired
    private AdminResourcesMapper adminResourcesMapper;

    @Override
    protected BaseDao<Long, AdminResources, AdminResourcesExample> getDao() {
        return adminResourcesMapper;
    }


    @Override
    public boolean check(String appId, String code) {
        AdminResourcesExample example = new AdminResourcesExample();
        example.createCriteria().andAppIdEqualTo(appId).andCodeEqualTo(code)
                .andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal());
        if (super.count(example) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkChildren(String appId, Long id) {
        if (adminResourcesMapper.countChildren(appId, id) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkMenu(String appId, Long menuGroupId) {
        if (adminResourcesMapper.checkMenu(appId, menuGroupId) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<AdminResources> findResources(String appId) {
        List<ResourcesType> resourcesTypeList = ResourcesType.listResourcesType();
        return adminResourcesMapper.findResourcesByApplicationId(appId, null, resourcesTypeList);
    }

    @Override
    public List<AdminResources> findMenu(String appId) {
        List<ResourcesType> menuTypeList = ResourcesType.listMenuType();
        return adminResourcesMapper.findResourcesByApplicationId(appId, null, menuTypeList);
    }
}
