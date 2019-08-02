package com.github.zuihou.authority.controller.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.RoleAuthoritySaveDTO;
import com.github.zuihou.authority.dto.auth.RoleSaveDTO;
import com.github.zuihou.authority.dto.auth.RoleUpdateDTO;
import com.github.zuihou.authority.dto.auth.UserRoleSaveDTO;
import com.github.zuihou.authority.entity.auth.Role;
import com.github.zuihou.authority.entity.auth.RoleAuthority;
import com.github.zuihou.authority.entity.auth.UserRole;
import com.github.zuihou.authority.enumeration.auth.AuthorizeType;
import com.github.zuihou.authority.service.auth.RoleAuthorityService;
import com.github.zuihou.authority.service.auth.RoleService;
import com.github.zuihou.authority.service.auth.UserRoleService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 角色
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/role")
@Api(value = "Role", tags = "角色")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleAuthorityService roleAuthorityService;
    @Autowired
    private DozerUtils dozer;

    /**
     * 分页查询角色
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询角色", notes = "分页查询角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "分页条数", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询角色")
    public R<IPage<Role>> page(Role data) {
        IPage<Role> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<Role> query = Wraps.lbQ(data);
        roleService.page(page, query);
        return success(page);
    }

    /**
     * 查询角色
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询角色", notes = "查询角色")
    @GetMapping("/{id}")
    @SysLog("查询角色")
    public R<Role> get(@PathVariable Long id) {
        return success(roleService.getById(id));
    }

    /**
     * 新增角色
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增角色", notes = "新增角色不为空的字段")
    @PostMapping
    @SysLog("新增角色")
    public R<RoleSaveDTO> save(@RequestBody @Validated RoleSaveDTO data) {
        roleService.saveRole(data, getUserId());
        return success(data);
    }

    /**
     * 修改角色
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改角色", notes = "修改角色不为空的字段")
    @PutMapping
    @SysLog("修改角色")
    public R<RoleUpdateDTO> update(@RequestBody @Validated(SuperEntity.Update.class) RoleUpdateDTO data) {
        roleService.updateRole(data, getUserId());
        return success(data);
    }

    /**
     * 删除角色
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除角色", notes = "根据id物理删除角色")
    @DeleteMapping(value = "/{id}")
    @SysLog("删除角色")
    public R<Boolean> delete(@PathVariable Long id) {
        roleService.removeById(id);
        return success(true);
    }

    /**
     * 给角色分配用户
     *
     * @param userRole 用户角色授权对象
     * @return 新增结果
     */
    @ApiOperation(value = "给角色分配用户", notes = "给角色分配用户")
    @PostMapping("/user")
    @SysLog("给角色分配用户")
    public R<Boolean> saveUserRole(@RequestBody UserRoleSaveDTO userRole) {
        roleAuthorityService.remove(Wraps.<RoleAuthority>lbQ().eq(RoleAuthority::getRoleId, userRole.getRoleId()));
        List<UserRole> list = userRole.getUserIdList()
                .stream()
                .map((userId) -> UserRole.builder()
                        .userId(userId)
                        .roleId(userRole.getRoleId())
                        .build())
                .collect(Collectors.toList());
        return success(userRoleService.saveBatch(list));
    }

    /**
     * 给角色配置权限
     *
     * @param roleAuthoritySaveDTO 角色权限授权对象
     * @return 新增结果
     */
    @ApiOperation(value = "给角色配置权限", notes = "给角色配置权限")
    @PostMapping("/menu")
    @SysLog("给角色配置权限")
    public R<Boolean> save(@RequestBody RoleAuthoritySaveDTO roleAuthoritySaveDTO) {
        List<RoleAuthority> list = new ArrayList<>();

        roleAuthorityService.remove(Wraps.<RoleAuthority>lbQ().eq(RoleAuthority::getRoleId, roleAuthoritySaveDTO.getRoleId()));

        if (roleAuthoritySaveDTO.getMenuIdList().isEmpty()) {
            List<RoleAuthority> menuList = roleAuthoritySaveDTO.getMenuIdList()
                    .stream()
                    .map((menuId) -> RoleAuthority.builder()
                            .authorityType(AuthorizeType.MENU)
                            .authorityId(menuId)
                            .roleId(roleAuthoritySaveDTO.getRoleId())
                            .build())
                    .collect(Collectors.toList());
            list.addAll(menuList);
        }
        if (roleAuthoritySaveDTO.getResourceIdList().isEmpty()) {
            List<RoleAuthority> resourceList = roleAuthoritySaveDTO.getResourceIdList()
                    .stream()
                    .map((resourceId) -> RoleAuthority.builder()
                            .authorityType(AuthorizeType.RESOURCE)
                            .authorityId(resourceId)
                            .roleId(roleAuthoritySaveDTO.getRoleId())
                            .build())
                    .collect(Collectors.toList());
            list.addAll(resourceList);
        }
        roleAuthorityService.saveBatch(list);
        return success();
    }


    /**
     * 根据角色编码查询用户ID
     *
     * @param codes 编码集合
     * @return 查询结果
     */
    @ApiOperation(value = "根据角色编码查询用户ID", notes = "根据角色编码查询用户ID")
    @GetMapping("/codes")
    @SysLog("根据角色编码查询用户ID")
    public R<List<Long>> findUserIdByCode(@RequestParam(value = "codes") String[] codes) {
        return success(roleService.findUserIdByCode(codes));
    }

}
