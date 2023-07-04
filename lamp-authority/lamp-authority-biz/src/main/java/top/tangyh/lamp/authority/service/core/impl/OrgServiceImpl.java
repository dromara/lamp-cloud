package top.tangyh.lamp.authority.service.core.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.SuperCacheServiceImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.database.mybatis.conditions.query.LbqWrapper;
import top.tangyh.basic.model.cache.CacheKeyBuilder;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.CollHelper;
import top.tangyh.lamp.authority.dao.auth.UserMapper;
import top.tangyh.lamp.authority.dao.core.OrgMapper;
import top.tangyh.lamp.authority.entity.auth.RoleOrg;
import top.tangyh.lamp.authority.entity.auth.User;
import top.tangyh.lamp.authority.entity.core.Org;
import top.tangyh.lamp.authority.service.auth.RoleOrgService;
import top.tangyh.lamp.authority.service.core.OrgService;
import top.tangyh.lamp.common.cache.core.OrgCacheKeyBuilder;
import top.tangyh.lamp.common.constant.DefValConstants;
import top.tangyh.lamp.model.enumeration.base.OrgTypeEnum;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * <p>
 * 业务实现类
 * 组织
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrgServiceImpl extends SuperCacheServiceImpl<OrgMapper, Org> implements OrgService {
    private final RoleOrgService roleOrgService;
    private final UserMapper userMapper;

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new OrgCacheKeyBuilder();
    }

    @Override
    public boolean check(Long id, Long parentId, String name) {
        LbqWrapper<Org> wrap = Wraps.<Org>lbQ().eq(Org::getLabel, name).eq(Org::getParentId, parentId).ne(Org::getId, id);
        return count(wrap) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Org model) {
        ArgumentAssert.isFalse(check(null, model.getParentId(), model.getLabel()), StrUtil.format("组织[{}]已经存在", model.getLabel()));
        return super.save(model);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(Org model) {
        ArgumentAssert.isFalse(check(model.getId(), model.getParentId(), model.getLabel()), StrUtil.format("组织[{}]已经存在", model.getLabel()));
        return super.updateById(model);
    }

    @Override
    public List<Org> findChildren(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        // MySQL 全文索引
        String applySql = String.format(" MATCH(tree_path) AGAINST('%s' IN BOOLEAN MODE) ", CollUtil.join(ids, " "));

        return super.list(Wraps.<Org>lbQ().in(Org::getId, ids).or(query -> query.apply(applySql)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean remove(List<Long> ids) {
        if (ids.isEmpty()) {
            return true;
        }
        Long userCount = userMapper.selectCount(Wraps.<User>lbQ().in(User::getOrgId, ids));
        ArgumentAssert.isFalse(userCount > 0, "您选择的组织下还存在用户，禁止删除！请先情况改组织下所有用户后，在进行删除！");

        List<Org> list = this.findChildren(ids);
        List<Long> idList = list.stream().mapToLong(Org::getId).boxed().collect(Collectors.toList());

        boolean bool = super.removeByIds(idList);

        // 删除自定义类型的数据权限范围
        roleOrgService.remove(Wraps.<RoleOrg>lbQ().in(RoleOrg::getOrgId, idList));
        return bool;
    }

    private List<Org> findOrg(Set<Serializable> ids) {
        return findByIds(ids,
                missIds -> super.listByIds(missIds.stream().filter(Objects::nonNull).map(Convert::toLong).collect(Collectors.toList()))
        );
    }

    @Transactional(readOnly = true)
    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> ids) {
        return CollHelper.uniqueIndex(findOrg(ids), Org::getId, org -> org);
    }


    @Override
    public Long getMainDeptIdByUserId(Long userId) {
        Org baseOrg = baseMapper.getDeptByUserId(userId);
        return baseOrg != null ? baseOrg.getId() : null;
    }

    @Override
    public List<Long> findDeptAndChildrenIdByUserId(Long userId) {
        Org baseOrg = baseMapper.getDeptByUserId(userId);
        if (baseOrg == null) {
            return Collections.emptyList();
        }
        String parentIdStr = DefValConstants.ROOT_PATH + baseOrg.getId() + DefValConstants.ROOT_PATH;
        List<Org> list = list(Wraps.<Org>lbQ().like(Org::getTreePath, parentIdStr));
        list.add(baseOrg);
        return list.stream().map(Org::getId).collect(Collectors.toList());
    }

    @Override
    public Long getMainCompanyIdByUserId(Long userId) {
        Org mainCompany = getMainCompanyByUserId(userId);
        return mainCompany != null ? mainCompany.getId() : null;
    }

    @Override
    public Org getMainCompanyByUserId(Long userId) {
        // 用户所在部门
        Org baseOrg = baseMapper.getDeptByUserId(userId);
        if (baseOrg == null) {
            return null;
        }
        // 用户直接挂在单位上，就直接返回此单位
        if (OrgTypeEnum.COMPANY.eq(baseOrg.getType())) {
            return baseOrg;
        }
        // 用户挂在部门上，就向上查询单位
        List<String> parentIdStrList = StrUtil.split(baseOrg.getTreePath(), DefValConstants.ROOT_PATH, true, true);
        List<Long> parentIdList = Convert.toList(Long.class, parentIdStrList);
        // 若部门上级没有单位直接返回部门
        if (CollUtil.isEmpty(parentIdList)) {
            return baseOrg;
        }
        List<Org> parentList = listByIds(parentIdList);
        ImmutableMap<Long, Org> map = CollHelper.uniqueIndex(parentList, Org::getId, org -> org);
        return getMainCompany(map, baseOrg.getParentId());
    }


    private static Org getMainCompany(ImmutableMap<Long, Org> map, Long parentId) {
        Org parent = map.get(parentId);
        if (parent == null) {
            return null;
        }
        if (OrgTypeEnum.COMPANY.eq(parent.getType())) {
            return parent;
        }

        return getMainCompany(map, parent.getParentId());
    }

    @Override
    public List<Long> findCompanyAndChildrenIdByUserId(Long userId) {
        Org mainCompany = getMainCompanyByUserId(userId);
        if (mainCompany == null) {
            return Collections.emptyList();
        }
        String parentIdStr = DefValConstants.ROOT_PATH + mainCompany.getId() + DefValConstants.ROOT_PATH;
        List<Org> list = list(Wraps.<Org>lbQ().like(Org::getTreePath, parentIdStr));
        list.add(mainCompany);
        return list.stream().map(Org::getId).collect(Collectors.toList());
    }

}
