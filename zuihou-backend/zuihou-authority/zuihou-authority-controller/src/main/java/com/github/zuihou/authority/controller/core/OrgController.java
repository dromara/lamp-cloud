package com.github.zuihou.authority.controller.core;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.core.OrgSaveDTO;
import com.github.zuihou.authority.dto.core.OrgTreeDTO;
import com.github.zuihou.authority.dto.core.OrgUpdateDTO;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.service.core.OrgService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.dozer.DozerUtils;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.utils.BizAssert;
import com.github.zuihou.utils.TreeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.github.zuihou.utils.StrPool.DEF_PARENT_ID;
import static com.github.zuihou.utils.StrPool.DEF_ROOT_PATH;


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
public class OrgController extends BaseController {

    @Autowired
    private OrgService orgService;
    @Autowired
    private DozerUtils dozer;

    /**
     * 分页查询组织
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询组织", notes = "分页查询组织")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "分页条数", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询组织")
    public R<IPage<Org>> page(Org data) {
        IPage<Org> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<Org> query = Wraps.lbQ(data);
        orgService.page(page, query);
        return success(page);
    }

    /**
     * 查询组织
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询组织", notes = "查询组织")
    @GetMapping("/{id}")
    @SysLog("查询组织")
    public R<Org> get(@PathVariable Long id) {
        return success(orgService.getById(id));
    }

    /**
     * 新增组织
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增组织", notes = "新增组织不为空的字段")
    @PostMapping
    @SysLog("新增组织")
    public R<Org> save(@RequestBody @Validated OrgSaveDTO data) {
        Org org = dozer.map(data, Org.class);
        if (org.getParentId() == null || org.getParentId() <= 0) {
            org.setParentId(DEF_PARENT_ID);
            org.setTreePath(DEF_ROOT_PATH);
        } else {
            Org parent = orgService.getById(org.getParentId());
            BizAssert.notNull(parent, "父组织不能为空");

            org.setTreePath(StringUtils.join(parent.getTreePath(), parent.getId(), DEF_ROOT_PATH));
        }
        orgService.save(org);
        return success(org);
    }

    /**
     * 修改组织
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改组织", notes = "修改组织不为空的字段")
    @PutMapping
    @SysLog("修改组织")
    public R<Org> update(@RequestBody @Validated(SuperEntity.Update.class) OrgUpdateDTO data) {
        Org org = dozer.map(data, Org.class);
        orgService.updateById(org);
        return success(org);
    }

    @ApiOperation(value = "移动", notes = "修改不为空的字段")
    @PutMapping("/move")
    @SysLog("移动")
    public R<Boolean> move() {
        return success();
    }

    /**
     * 删除组织
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除组织", notes = "根据id物理删除组织")
    @DeleteMapping(value = "/{id}")
    @SysLog("删除组织")
    public R<Boolean> delete(@PathVariable Long id) {
        orgService.removeById(id);
        return success(true);
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
    public R<List<OrgTreeDTO>> tree(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "status", required = false) Boolean status) {
        List<Org> list = orgService.list(Wraps.<Org>lbQ().like(Org::getName, name)
                .eq(Org::getStatus, status).orderByAsc(Org::getSortValue));
        List<OrgTreeDTO> treeList = dozer.mapList(list, OrgTreeDTO.class);
        return success(TreeUtil.builderTreeOrdered(treeList));
    }
}
