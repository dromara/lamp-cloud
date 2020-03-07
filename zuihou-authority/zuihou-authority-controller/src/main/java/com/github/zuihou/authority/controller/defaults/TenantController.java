package com.github.zuihou.authority.controller.defaults;


import com.github.zuihou.authority.dto.defaults.TenantPageDTO;
import com.github.zuihou.authority.dto.defaults.TenantSaveDTO;
import com.github.zuihou.authority.dto.defaults.TenantSaveInitDTO;
import com.github.zuihou.authority.dto.defaults.TenantUpdateDTO;
import com.github.zuihou.authority.entity.defaults.Tenant;
import com.github.zuihou.authority.enumeration.defaults.TenantStatusEnum;
import com.github.zuihou.authority.service.defaults.TenantService;
import com.github.zuihou.base.R;
import com.github.zuihou.base.controller.SuperCacheController;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.zuihou.authority.enumeration.defaults.TenantStatusEnum.NORMAL;

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
public class TenantController extends SuperCacheController<TenantService, Long, Tenant, TenantPageDTO, TenantSaveDTO, TenantUpdateDTO> {


    @ApiOperation(value = "查询所有企业", notes = "查询所有企业")
    @GetMapping("/all")
    public R<List<Tenant>> list() {
        return success(baseService.list(Wraps.<Tenant>lbQ().eq(Tenant::getStatus, NORMAL)));
    }

    /**
     * 新增企业
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "初始化企业", notes = "快速初始化企业")
    @PostMapping("/init")
    public R<Tenant> saveInit(@RequestBody @Validated TenantSaveInitDTO data) {
        Tenant tenant = baseService.saveInit(data);
        return success(tenant);
    }

    @Override
    public R<Tenant> handlerSave(TenantSaveDTO model) {
        Tenant tenant = baseService.save(model);
        return success(tenant);
    }

    @ApiOperation(value = "检测租户是否存在", notes = "检测租户是否存在")
    @GetMapping("/check/{code}")
    public R<Boolean> check(@PathVariable("code") String code) {
        return success(baseService.check(code));
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        return success(baseService.update(Wraps.<Tenant>lbU().set(Tenant::getStatus, TenantStatusEnum.FORBIDDEN).in(Tenant::getId, ids)));
    }
}
