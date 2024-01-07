package top.tangyh.lamp.system.service.application.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Multimap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.impl.SuperCacheServiceImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.exception.BizException;
import top.tangyh.basic.jackson.JsonUtil;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.BeanPlusUtil;
import top.tangyh.basic.utils.CollHelper;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.basic.utils.TreeUtil;
import top.tangyh.basic.utils.ValidatorUtil;
import top.tangyh.lamp.common.cache.tenant.application.ApplicationResourceCacheKeyBuilder;
import top.tangyh.lamp.common.cache.tenant.application.ResourceResourceApiCacheKeyBuilder;
import top.tangyh.lamp.common.constant.DefValConstants;
import top.tangyh.lamp.model.enumeration.system.ResourceTypeEnum;
import top.tangyh.lamp.system.entity.application.DefResource;
import top.tangyh.lamp.system.entity.application.DefResourceApi;
import top.tangyh.lamp.system.manager.application.DefResourceApiManager;
import top.tangyh.lamp.system.manager.application.DefResourceManager;
import top.tangyh.lamp.system.service.application.DefResourceService;
import top.tangyh.lamp.system.vo.result.application.DefResourceApiResultVO;
import top.tangyh.lamp.system.vo.result.application.DefResourceResultVO;
import top.tangyh.lamp.system.vo.save.application.DefResourceApiSaveVO;
import top.tangyh.lamp.system.vo.save.application.DefResourceSaveVO;
import top.tangyh.lamp.system.vo.update.application.DefResourceUpdateVO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务实现类
 * 资源
 * </p>
 *
 * @author zuihou
 * @date 2021-09-13
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class DefResourceServiceImpl extends SuperCacheServiceImpl<DefResourceManager, Long, DefResource> implements DefResourceService {
    private final DefResourceApiManager defResourceApiManager;

    @Override
    public Map<Long, Collection<Long>> findResource() {
        List<DefResource> list = super.list(Wraps.<DefResource>lbQ().eq(DefResource::getState, true));
        Multimap<Long, Long> map = CollHelper.iterableToMultiMap(list, DefResource::getApplicationId, DefResource::getId);
        return map.asMap();
    }

    @Override
    public List<DefResource> findResourceListByApplicationId(List<Long> applicationIdList, Collection<String> resourceTypes) {
        return superManager.findResourceListByApplicationId(applicationIdList, resourceTypes);
    }

    @Override
    public List<DefResource> findByIdsAndType(Collection<? extends Serializable> idList, Collection<String> types) {
        return superManager.findByIdsAndType(idList, types);
    }

    @Override
    public List<DefResourceApi> findApiByResourceId(List<Long> resourceIdList) {
        return defResourceApiManager.findApiByResourceId(resourceIdList);
    }

    @Override
    public List<DefResourceApi> findResourceApi(List<Long> applicationIdList, Collection<String> resourceTypes) {
        return defResourceApiManager.findResourceApi(applicationIdList, resourceTypes);
    }

    @Override
    public Boolean check(Long id, String code) {
        return superManager.count(Wraps.<DefResource>lbQ().ne(DefResource::getId, id).eq(DefResource::getCode, code)) > 0;
    }

    @Override
    public Boolean checkPath(Long id, Long applicationId, String path) {
        return superManager.count(Wraps.<DefResource>lbQ().ne(DefResource::getId, id)
                .eq(DefResource::getApplicationId, applicationId).eq(DefResource::getPath, path)) > 0;
    }

    @Override
    public Boolean checkName(Long id, Long applicationId, String name) {
        return superManager.count(Wraps.<DefResource>lbQ().ne(DefResource::getId, id)
                .eq(DefResource::getApplicationId, applicationId)
                .in(DefResource::getResourceType, ResourceTypeEnum.MENU.getCode(), ResourceTypeEnum.VIEW.getCode())
                .eq(DefResource::getName, name)) > 0;
    }

    @Override
    public DefResourceResultVO getResourceById(Long id) {
        DefResource defResource = getById(id);
        if (defResource == null) {
            return null;
        }
        DefResourceResultVO vo = BeanPlusUtil.toBean(defResource, DefResourceResultVO.class);
        List<DefResourceApi> resourceApis = defResourceApiManager.findByResourceId(id);
        vo.setResourceApiList(BeanPlusUtil.toBeanList(resourceApis, DefResourceApiResultVO.class));
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIdWithCache(List<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return false;
        }
        long count = superManager.count(Wraps.<DefResource>lbQ().in(DefResource::getParentId, ids));
        ArgumentAssert.isFalse(count > 0, "您要删除的资源存在子资源，请先删除子资源！");

        List<DefResource> defResources = superManager.listByIds(ids);
        List<Long> applicationIdList = defResources.stream().map(DefResource::getApplicationId).distinct().toList();
        cacheOps.del(applicationIdList.stream().map(ApplicationResourceCacheKeyBuilder::build).toList());

        boolean result = this.removeByIds(ids);
        defResourceApiManager.removeByResourceId(ids);
        // TODO 删除租户下的 角色资源关系表 员工资源关系表
        // deleteRoleResourceRelByResourceId(ids);
        return result;
    }

    @Override

    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleResourceRelByResourceId(List<Long> resourceIds) {
        superManager.deleteRoleResourceRelByResourceId(resourceIds);
    }

    /**
     * 批量保存资源下绑定的接口
     *
     * @param resourceApiList 接口api
     * @param resourceId      资源id
     * @author tangyh
     * @date 2021/9/17 9:52 下午
     * @create [2021/9/17 9:52 下午 ] [tangyh] [初始创建]
     */
    private void saveResourceApi(Long resourceId, List<DefResourceApiSaveVO> resourceApiList) {
        if (CollUtil.isNotEmpty(resourceApiList)) {
            List<DefResourceApi> list =
                    resourceApiList.stream().map(vo -> {
                        DefResourceApi api = new DefResourceApi();
                        BeanPlusUtil.copyProperties(vo, api);
                        api.setResourceId(resourceId);
                        return api;
                    }).toList();
            defResourceApiManager.saveBatch(list);
        }
    }

    private void fill(DefResource resource) {
        if (resource.getParentId() == null || resource.getParentId() <= 0) {
            resource.setParentId(DefValConstants.PARENT_ID);
            resource.setTreePath(DefValConstants.TREE_PATH_SPLIT);
            resource.setTreeGrade(DefValConstants.TREE_GRADE);
        } else {
            DefResource parent = this.superManager.getByIdCache(resource.getParentId());
            ArgumentAssert.notNull(parent, "请正确填写父级");

            resource.setTreeGrade(parent.getTreeGrade() + 1);
            resource.setTreePath(TreeUtil.getTreePath(parent.getTreePath(), parent.getId()));
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void moveResource(Long id, Long parentId) {
        ArgumentAssert.notNull(id, "当前资源信息不存在");

        DefResource current = superManager.getByIdCache(id);
        ArgumentAssert.notNull(current, "当前资源信息不存在");
        List<DefResource> childrenList = superManager.findAllChildrenByParentId(id);


        // 是否跟节点移动到根节点 （跟节点可能为null)
        boolean isTopMoveTop = TreeUtil.isRoot(current.getParentId()) && TreeUtil.isRoot(parentId);
        // 是否自己移动到自己下
        boolean isSelfMoveSelf = current.getParentId() != null && current.getParentId().equals(parentId);
        if (isTopMoveTop || isSelfMoveSelf) {
            log.info("没有改变 id={}, parentId={}", id, parentId);
            return;
        }

        DefResource parent = null;
        if (parentId != null) {
            ArgumentAssert.isFalse(id.equals(parentId), "不能移动到自己的子节点");
            boolean flag = childrenList.stream().anyMatch(item -> item.getId().equals(parentId));
            ArgumentAssert.isFalse(flag, "不能移动到自己的子节点");
            parent = superManager.getByIdCache(parentId);
            ArgumentAssert.notNull(parent, "需要移动到的父资源不存在");
        }

        fill(current, parent);
        if (CollUtil.isNotEmpty(childrenList)) {
            List<DefResource> tree = TreeUtil.buildTree(childrenList);
            recursiveFill(tree, current);

            superManager.updateBatchById(childrenList);
        }
        superManager.updateById(current);

        // 只修改了 父ID、path等字段，清理资源缓存，无需清理 资源API的缓存
        List<Long> allIdList = childrenList.stream().map(DefResource::getId).toList();
        allIdList.add(current.getId());
        superManager.delCache(allIdList);
    }

    private void recursiveFill(List<DefResource> tree, DefResource parent) {
        for (DefResource node : tree) {
            fill(node, parent);

            if (CollUtil.isNotEmpty(node.getChildren())) {
                recursiveFill(node.getChildren(), node);
            }
        }
    }

    private void fill(DefResource node, DefResource parent) {
        node.setParentId(parent == null ? DefValConstants.PARENT_ID : parent.getId());
        node.setTreeGrade(parent == null ? DefValConstants.TREE_GRADE : parent.getTreeGrade() + 1);
        node.setTreePath(parent == null ? DefValConstants.TREE_PATH_SPLIT : TreeUtil.getTreePath(parent.getTreePath(), parent.getId()));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public DefResource saveWithCache(DefResourceSaveVO data) {
        if (StrUtil.containsAny(data.getResourceType(), ResourceTypeEnum.MENU.getCode(), ResourceTypeEnum.VIEW.getCode())) {
            ArgumentAssert.notEmpty(data.getPath(), "请填写【地址栏路径】");
            ArgumentAssert.notEmpty(data.getComponent(), "请填写【页面路径】");
            ArgumentAssert.isFalse(checkName(null, data.getApplicationId(), data.getName()), "【名称】:{}重复", data.getName());
            if (!ValidatorUtil.isUrl(data.getPath())) {
                ArgumentAssert.isFalse(checkPath(null, data.getApplicationId(), data.getPath()), "【地址栏路径】:{}重复", data.getPath());
            }
        }
        ArgumentAssert.isFalse(check(null, data.getCode()), "【资源编码】：{}重复", data.getCode());
        data.setMetaJson(parseMetaJson(data.getMetaJson()));
        DefResource resource = BeanPlusUtil.toBean(data, DefResource.class);

        checkGeneral(resource, false);

        fill(resource);
        superManager.save(resource);
        saveResourceApi(resource.getId(), data.getResourceApiList());

        // 淘汰资源下绑定的接口
        cacheOps.del(ResourceResourceApiCacheKeyBuilder.builder(resource.getId()));
        return resource;
    }

    private String parseMetaJson(String metaJson) {
        if (StrUtil.isNotEmpty(metaJson)) {
            try {
                return JsonUtil.toJson(JsonUtil.parse(metaJson, HashMap.class));
            } catch (Exception e) {
                throw new BizException("【元数据】须满足JSON格式");
            }
        }
        return StrPool.EMPTY;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DefResource updateWithCacheById(DefResourceUpdateVO data) {
        if (StrUtil.containsAny(data.getResourceType(), ResourceTypeEnum.MENU.getCode(), ResourceTypeEnum.VIEW.getCode())) {
            ArgumentAssert.notEmpty(data.getPath(), "【地址栏路径】不能为空");
            ArgumentAssert.notEmpty(data.getComponent(), "【页面路径】不能为空");
            ArgumentAssert.isFalse(checkName(data.getId(), data.getApplicationId(), data.getName()), "【资源名称】:{}重复", data.getName());
            if (!ValidatorUtil.isUrl(data.getPath())) {
                ArgumentAssert.isFalse(checkPath(data.getId(), data.getApplicationId(), data.getPath()), "【地址栏路径】:{}重复", data.getPath());
            }
        }
        ArgumentAssert.isFalse(check(data.getId(), data.getCode()), "【资源编码】:{}重复", data.getCode());
        data.setMetaJson(parseMetaJson(data.getMetaJson()));

        DefResource resource = BeanPlusUtil.toBean(data, DefResource.class);
        checkGeneral(resource, true);
        fill(resource);
        superManager.updateById(resource);
        defResourceApiManager.removeByResourceId(Collections.singletonList(resource.getId()));
        saveResourceApi(resource.getId(), data.getResourceApiList());

        // 淘汰资源下绑定的接口
        cacheOps.del(ResourceResourceApiCacheKeyBuilder.builder(resource.getId()));
        return resource;
    }

    private void checkGeneral(DefResource data, boolean isUpdate) {
        final boolean isGeneral = data.getIsGeneral() != null && data.getIsGeneral();
        boolean state = data.getState() == null || data.getState();

        // isGeneral 子节点 改成是，父节点全是
        if (!TreeUtil.isRoot(data.getParentId())) {
            DefResource parent = superManager.getById(data.getParentId());
            ArgumentAssert.notNull(parent, "父节点不存在");
            if (isGeneral) {
                ArgumentAssert.isTrue(parent.getIsGeneral(), "请先将父节点【{}】-“公共资源”字段修改为：“是”，在修改本节点", parent.getName());
            }
            if (state) {
                ArgumentAssert.isTrue(parent.getState(), "请先将父节点【{}】-“状态”字段修改为：“启用”，在修改本节点", parent.getName());
            }
        }

        if (isUpdate) {
            // isGeneral 父节点 改成否，子节点全否
            List<DefResource> childrenList = superManager.findChildrenByParentId(data.getId());
            if (CollUtil.isNotEmpty(childrenList)) {
                childrenList.forEach(item -> {
                    if (!isGeneral) {

                        ArgumentAssert.isFalse(item.getIsGeneral(), "请将所有子节点的“公共资源”修改为”否”后，在修改当前节点", item.getName());
                    }
                    if (!state) {
                        ArgumentAssert.isFalse(item.getState(), "请将所有子节点的“状态”修改为“禁用”后，在修改当前节点");
                    }
                });
            }
        }
    }


}
