package com.github.zuihou.authority.controller.defaults;


import com.github.zuihou.authority.dto.defaults.GlobalUserPageDTO;
import com.github.zuihou.authority.dto.defaults.GlobalUserSaveDTO;
import com.github.zuihou.authority.dto.defaults.GlobalUserUpdateDTO;
import com.github.zuihou.authority.entity.defaults.GlobalUser;
import com.github.zuihou.authority.service.defaults.GlobalUserService;
import com.github.zuihou.base.R;
import com.github.zuihou.base.controller.SuperController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 全局账号
 * </p>
 *
 * @author zuihou
 * @date 2019-10-25
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/globalUser")
@Api(value = "GlobalUser", tags = "全局账号")
public class GlobalUserController extends SuperController<GlobalUserService, Long, GlobalUser, GlobalUserPageDTO, GlobalUserSaveDTO, GlobalUserUpdateDTO> {
    @Override
    public R<GlobalUser> handlerSave(GlobalUserSaveDTO model) {
        return success(baseService.save(model));
    }

    @Override
    public R<GlobalUser> handlerUpdate(GlobalUserUpdateDTO model) {
        return success(baseService.update(model));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantCode", value = "企业编码", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "account", value = "账号", dataType = "string", paramType = "query"),
    })
    @ApiOperation(value = "检测账号是否可用", notes = "检测账号是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam String tenantCode, @RequestParam String account) {
        return success(baseService.check(tenantCode, account));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantCode", value = "企业编码", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "ids[]", value = "id", dataType = "array", paramType = "query"),
    })
    @ApiOperation(value = "批量删除", notes = "批量删除")
    @DeleteMapping(value = "/remove")
    public R<Boolean> remove(@RequestParam String tenantCode, @RequestParam("ids[]") List<Long> ids) {
        return success(baseService.removeByIds(tenantCode, ids));
    }
}
