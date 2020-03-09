package com.github.zuihou.authority.controller.core;

import cn.hutool.core.convert.Convert;
import com.github.zuihou.authority.dto.core.OrgSaveDTO;
import com.github.zuihou.authority.dto.core.OrgUpdateDTO;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.service.core.OrgService;
import com.github.zuihou.base.R;
import com.github.zuihou.base.controller.SuperCacheController;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.utils.BeanPlusUtil;
import com.github.zuihou.utils.BizAssert;
import com.github.zuihou.utils.TreeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.zuihou.utils.StrPool.*;


/**
 * <p>
 * 前端控制器
 * 组织
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@RestController
@RequestMapping("/org")
@Api(value = "Org", tags = "组织")
public class OrgController extends SuperCacheController<OrgService, Long, Org, Org, OrgSaveDTO, OrgUpdateDTO> {

    @Override
    public R<Org> handlerSave(OrgSaveDTO model) {
        Org org = BeanPlusUtil.toBean(model, Org.class);
        fillOrg(org);
        this.baseService.save(org);
        return success(org);
    }

    @Override
    public R<Org> handlerUpdate(OrgUpdateDTO model) {
        Org org = BeanPlusUtil.toBean(model, Org.class);
        fillOrg(org);
        this.baseService.updateAllById(org);
        return success(org);
    }

    private Org fillOrg(Org org) {
        if (org.getParentId() == null || org.getParentId() <= 0) {
            org.setParentId(DEF_PARENT_ID);
            org.setTreePath(DEF_ROOT_PATH);
        } else {
            Org parent = this.baseService.getByIdCache(org.getParentId());
            BizAssert.notNull(parent, "父组织不能为空");

            org.setTreePath(StringUtils.join(parent.getTreePath(), parent.getId(), DEF_ROOT_PATH));
        }
        return org;
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        return this.success(baseService.remove(ids));
    }


    /**
     * 查询系统所有的组织树
     *
     * @param status 状态
     * @return
     * @author zuihou
     * @date 2019-07-29 11:59
     */
    @ApiOperation(value = "查询系统所有的组织树", notes = "查询系统所有的组织树")
    @GetMapping("/tree")
    @SysLog("查询系统所有的组织树")
    public R<List<Org>> tree(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "status", required = false) Boolean status) {
        List<Org> list = this.baseService.list(Wraps.<Org>lbQ()
                .like(Org::getLabel, name).eq(Org::getStatus, status).orderByAsc(Org::getSortValue));
        return this.success(TreeUtil.buildTree(list));
    }

    /**
     * 调用方传递的参数类型是 Set<Serializable> ，但接收方必须指定为Long类型（实体的主键类型），否则在调用mp提供的方法时，会使得mysql出现类型隐式转换问题。
     * 问题如下： select * from org where id in ('100');
     * <p>
     * 强制转换成Long后，sql就能正常执行： select * from org where id in (100);
     *
     * <p>
     * 接口和实现类的类型不一致，但也能调用，归功于 SpingBoot 的自动转换功能
     * {@link com.github.zuihou.authority.api.OrgApi#findOrgByIds} 方法的实现类
     *
     * @param ids id
     * @return
     */
    @GetMapping("/findOrgByIds")
    public Map<Serializable, Object> findOrgByIds(@RequestParam("ids") Set<Serializable> ids) {
        return baseService.findOrgByIds(ids);
    }

    /**
     * 调用方传递的参数类型是 Set<Serializable> ，但接收方必须指定为Long类型（实体的主键类型），否则在调用mp提供的方法时，会使得mysql出现类型隐式转换问题。
     * 问题如下： select * from org where id in ('100');
     * <p>
     * 强制转换成Long后，sql就能正常执行： select * from org where id in (100);
     *
     * <p>
     * 接口和实现类的类型不一致，但也能调用，归功于 SpingBoot 的自动转换功能
     * {@link com.github.zuihou.authority.api.OrgApi#findUserNameByIds} 方法的实现类
     *
     * @param ids id
     * @return
     */
    @GetMapping("/findOrgNameByIds")
    public Map<Serializable, Object> findOrgNameByIds(@RequestParam("ids") Set<Serializable> ids) {
        return baseService.findOrgNameByIds(ids);
    }


    @Override
    public R<Boolean> handlerImport(List<Map<String, String>> list) {
        List<Org> userList = list.stream().map((map) -> {
            Org item = new Org();
            item.setDescribe(map.getOrDefault("描述", EMPTY));
            item.setLabel(map.getOrDefault("名称", EMPTY));
            item.setAbbreviation(map.getOrDefault("简称", EMPTY));
            item.setStatus(Convert.toBool(map.getOrDefault("状态", EMPTY)));
            return item;
        }).collect(Collectors.toList());

        return R.success(baseService.saveBatch(userList));
    }

}
