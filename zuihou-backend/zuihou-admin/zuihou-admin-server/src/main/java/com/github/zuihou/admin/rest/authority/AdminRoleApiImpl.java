package com.github.zuihou.admin.rest.authority;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.zuihou.admin.entity.authority.po.AdminRole;
import com.github.zuihou.admin.repository.authority.example.AdminRoleExample;
import com.github.zuihou.admin.repository.authority.service.AdminRoleService;
import com.github.zuihou.admin.rest.authority.api.AdminRoleApi;
import com.github.zuihou.admin.rest.authority.dto.AdminRoleDto;
import com.github.zuihou.admin.rest.authority.dto.AdminRolePageReqDto;
import com.github.zuihou.admin.rest.authority.dto.AdminRoleSaveDto;
import com.github.zuihou.admin.rest.authority.dto.AdminRoleUpdateDto;
import com.github.zuihou.admin.rest.dozer.DozerUtils;
import com.github.zuihou.base.Result;
import com.github.zuihou.commons.constant.DeleteStatus;
import com.github.zuihou.commons.constant.EnableStatus;
import com.github.zuihou.commons.context.BaseContextHandler;
import com.github.zuihou.commons.exception.core.ExceptionCode;
import com.github.zuihou.page.plugins.openapi.OpenApiReq;
import com.github.zuihou.utils.BizAssert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.github.zuihou.utils.BizAssert.assertNotEmpty;
import static com.github.zuihou.utils.BizAssert.assertNotNull;

/**
 * @author zuihou
 * @createTime 2017-12-12 20:49
 */
@Api(value = "API - AdminRoleApiImpl", description = "角色管理")
@RestController
@RequestMapping("role")
@Slf4j
public class AdminRoleApiImpl implements AdminRoleApi {

    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private DozerUtils dozerUtils;

    @Override
    @ApiOperation(value = "查找角色", notes = "根据角色id[id]查找角色")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result<AdminRoleDto> getRoleByAppIdAndId(@RequestParam("id") Long id) {
        String appId = BaseContextHandler.getAppId();
        AdminRole role = adminRoleService.getByAppIdAndId(appId, id);
        if (role.getIsDelete()) {
            return Result.success(null);
        }
        return Result.success(dozerUtils.map(role, AdminRoleDto.class));
    }

    @Override
    @ApiOperation(value = "查找角色", notes = "根据角色编码[code]查找角色")
    @RequestMapping(value = "/getByCode", method = RequestMethod.GET)
    public Result<AdminRoleDto> getRoleByAppIdAndCode(@RequestParam("code") String code) {
        String appId = BaseContextHandler.getAppId();
        AdminRoleExample example = new AdminRoleExample();
        example.createCriteria().andAppIdEqualTo(appId).andCodeEqualTo(code)
                .andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal());
        return Result.success(dozerUtils.map(adminRoleService.getUnique(example), AdminRoleDto.class));
    }

    @Override
    @ApiOperation(value = "获取角色分页信息", notes = "获取角色分页信息 name/description 左模糊")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "第几页", defaultValue = "1", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条", defaultValue = "10", dataType = "int", paramType = "query")
    })
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Result<PageInfo<AdminRoleDto>> page(OpenApiReq openApiReq, AdminRolePageReqDto rolePageReqDto) {
        String appId = BaseContextHandler.getAppId();
        AdminRoleExample example = new AdminRoleExample();
        example.createCriteria().andAppIdEqualTo(appId).andCodeEqualTo(rolePageReqDto.getCode())
                .andNameLike(AdminRoleExample.leftLike(rolePageReqDto.getName()))
                .andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal()).andIsEnableEqualTo(rolePageReqDto.getIsEnable())
                .andDescriptionLike(AdminRoleExample.leftLike(rolePageReqDto.getDescription()));
        example.setOrderByClause("create_time desc");
        PageHelper.startPage(openApiReq.getPageNo(), openApiReq.getPageSize());
        List<AdminRole> roleList = adminRoleService.find(example);
        return Result.success(new PageInfo<>(dozerUtils.mapPage(roleList, AdminRoleDto.class)));

    }

    @Override
    @ApiOperation(value = "新增角色", notes = "新增角色, 角色编码[code]不能重复")
    @ApiResponses({
            @ApiResponse(code = 52000, message = "角色信息不能为空"),
            @ApiResponse(code = 52001, message = "角色编码[code]不能为空"),
            @ApiResponse(code = 52002, message = "角色编码[code]已存在"),
    })
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<AdminRoleDto> save(@RequestBody AdminRoleSaveDto adminRoleSaveDto) {
        assertNotNull(ExceptionCode.ROLE_NULL, adminRoleSaveDto);
        assertNotEmpty(ExceptionCode.ROLE_CODE_EMPTY, adminRoleSaveDto.getCode());
        String appId = BaseContextHandler.getAppId();
        String userName = BaseContextHandler.getUserName();
        BizAssert.assertFalse(ExceptionCode.ROLE_CODE_EXIST, adminRoleService.check(appId, adminRoleSaveDto.getCode()));

        AdminRole role = dozerUtils.map(adminRoleSaveDto, AdminRole.class);
        role.setAppId(appId);
        role.setIsDelete(DeleteStatus.UN_DELETE.getVal());
        role.setIsEnable(EnableStatus.ENABLE.getVal());
        role.setCreateUser(userName);
        role.setUpdateUser(userName);
        role = adminRoleService.saveSelective(role);
        return Result.success(dozerUtils.map(role, AdminRoleDto.class));
    }

    @Override
    @ApiOperation(value = "修改角色", notes = "修改指定id的角色")
    @ApiResponses({
            @ApiResponse(code = 52000, message = "角色信息不能为空"),
            @ApiResponse(code = 52003, message = "角色[id]不能为空"),
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Boolean> update(@RequestBody AdminRoleUpdateDto adminRoleUpdateDto) {
        assertNotNull(ExceptionCode.ROLE_NULL, adminRoleUpdateDto);
        assertNotNull(ExceptionCode.ROLE_ID_NULL, adminRoleUpdateDto.getId());

        String appId = BaseContextHandler.getAppId();
        String userName = BaseContextHandler.getUserName();

        AdminRole role = dozerUtils.map(adminRoleUpdateDto, AdminRole.class);
        role.setAppId(appId);
        role.setUpdateUser(userName);
        adminRoleService.updateByAppIdAndIdSelective(appId, role);
        return Result.success(true);
    }

    @Override
    @ApiOperation(value = "删除角色", notes = "删除指定id的角色")
    @ApiImplicitParam(name = "id", value = "角色id", required = true, paramType = "query", dataType = "long")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public Result<Boolean> remove(@RequestParam("id") Long id) {
        String appId = BaseContextHandler.getAppId();
        adminRoleService.removeByAppIdAndId(appId, id);
        return Result.success(true);
    }
}
