package com.github.zuihou.authority.controller.defaults;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.defaults.TenantSaveDTO;
import com.github.zuihou.authority.dto.defaults.TenantUpdateDTO;
import com.github.zuihou.authority.entity.defaults.Tenant;
import com.github.zuihou.authority.enumeration.defaults.TenantStatusEnum;
import com.github.zuihou.authority.service.defaults.TenantService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.dozer.DozerUtils;
import com.github.zuihou.log.annotation.SysLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 企业
 * </p>
 *
 * @author zuihou
 * @date 2019-10-24
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/tenant")
@Api(value = "Tenant", tags = "企业")
public class TenantController extends BaseController {

    @Autowired
    private TenantService tenantService;
    @Autowired
    private DozerUtils dozer;

    /**
     * 分页查询企业
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询企业", notes = "分页查询企业")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "分页条数", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询企业")
    public R<IPage<Tenant>> page(Tenant data) {
        IPage<Tenant> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<Tenant> query = Wraps.lbQ(data);
        tenantService.page(page, query);
        return success(page);
    }

    /**
     * 查询企业
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询企业", notes = "查询企业")
    @GetMapping("/{id}")
    @SysLog("查询企业")
    public R<Tenant> get(@PathVariable Long id) {
        return success(tenantService.getById(id));
    }

    /**
     * 新增企业
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增企业", notes = "新增企业不为空的字段")
    @PostMapping
    @SysLog("新增企业")
    public R<Tenant> save(@RequestBody @Validated TenantSaveDTO data) {
        Tenant tenant = tenantService.save(data);
        return success(tenant);
    }

    /**
     * 修改企业
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改企业", notes = "修改企业不为空的字段")
    @PutMapping
    @SysLog("修改企业")
    public R<Tenant> update(@RequestBody @Validated(SuperEntity.Update.class) TenantUpdateDTO data) {
        Tenant tenant = dozer.map(data, Tenant.class);
        tenantService.updateById(tenant);
        return success(tenant);
    }


    /**
     * 删除企业
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除企业", notes = "根据id物理删除企业")
    @DeleteMapping(value = "/{id}")
    @SysLog("删除企业")
    public R<Boolean> delete(@PathVariable Long id) {
        tenantService.update(Wraps.<Tenant>lbU().set(Tenant::getStatus, TenantStatusEnum.FORBIDDEN).eq(Tenant::getId, id));
        return success(true);
    }

}
