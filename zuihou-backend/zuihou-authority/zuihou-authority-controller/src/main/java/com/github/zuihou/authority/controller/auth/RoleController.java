package com.github.zuihou.authority.controller.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.*;
import com.github.zuihou.authority.entity.auth.Role;
import com.github.zuihou.authority.entity.auth.RoleAuthority;
import com.github.zuihou.authority.entity.auth.UserRole;
import com.github.zuihou.authority.enumeration.auth.AuthorizeType;
import com.github.zuihou.authority.service.auth.RoleAuthorityService;
import com.github.zuihou.authority.service.auth.RoleOrgService;
import com.github.zuihou.authority.service.auth.RoleService;
import com.github.zuihou.authority.service.auth.UserRoleService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.auth.DataScopeType;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.utils.BeanPlusUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    private RoleAuthorityService roleAuthorityService;
    @Autowired
    private RoleOrgService roleOrgService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 分页查询角色
     *
     * @param param 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询角色", notes = "分页查询角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询角色")
    public R<IPage<Role>> page(RolePageDTO param) {
        IPage<Role> page = getPage();
        Role role = BeanPlusUtil.toBean(param, Role.class);
        // 构建值不为null的查询条件
        LbqWrapper<Role> query = Wraps.lbQ(role)
                .geHeader(Role::getCreateTime, param.getStartCreateTime())
                .leFooter(Role::getCreateTime, param.getEndCreateTime())
                .orderByDesc(Role::getId);
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
    public R<RoleQueryDTO> get(@PathVariable Long id) {
        Role role = roleService.getByIdWithCache(id);
        RoleQueryDTO query = BeanPlusUtil.toBean(role, RoleQueryDTO.class);
        if (query.getDsType() != null && DataScopeType.CUSTOMIZE.eq(query.getDsType())) {
            List<Long> orgList = roleOrgService.listOrgByRoleId(role.getId());
            query.setOrgList(orgList);
        }
        return success(query);
    }

    @ApiOperation(value = "检测角色编码", notes = "检测角色编码")
    @GetMapping("/check/{code}")
    @SysLog("新增角色")
    public R<Boolean> check(@PathVariable String code) {
        return success(roleService.check(code));
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
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除角色", notes = "根据id物理删除角色")
    @DeleteMapping
    @SysLog("删除角色")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        roleService.removeByIdWithCache(ids);
        return success(true);
    }

    /**
     * 给用户分配角色
     *
     * @param userRole 用户角色授权对象
     * @return 新增结果
     */
    @ApiOperation(value = "给用户分配角色", notes = "给用户分配角色")
    @PostMapping("/user")
    @SysLog("给角色分配用户")
    public R<Boolean> saveUserRole(@RequestBody UserRoleSaveDTO userRole) {
        return success(roleAuthorityService.saveUserRole(userRole));
    }

    /**
     * 查询角色的用户
     *
     * @param roleId 角色id
     * @return 新增结果
     */
    @ApiOperation(value = "查询角色的用户", notes = "查询角色的用户")
    @GetMapping("/user/{roleId}")
    @SysLog("查询角色的用户")
    public R<List<Long>> findUserIdByRoleId(@PathVariable Long roleId) {
        List<UserRole> list = userRoleService.list(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, roleId));
        return success(list.stream().mapToLong(UserRole::getUserId).boxed().collect(Collectors.toList()));
    }

    /**
     * 查询角色拥有的资源id
     *
     * @param roleId 角色id
     * @return 新增结果
     */
    @ApiOperation(value = "查询角色拥有的资源id集合", notes = "查询角色拥有的资源id集合")
    @GetMapping("/authority/{roleId}")
    @SysLog("查询角色拥有的资源")
    public R<RoleAuthoritySaveDTO> findAuthorityIdByRoleId(@PathVariable Long roleId) {
        List<RoleAuthority> list = roleAuthorityService.list(Wraps.<RoleAuthority>lbQ().eq(RoleAuthority::getRoleId, roleId));
        List<Long> menuIdList = list.stream().filter(item -> AuthorizeType.MENU.eq(item.getAuthorityType())).mapToLong(RoleAuthority::getAuthorityId).boxed().collect(Collectors.toList());
        List<Long> resourceIdList = list.stream().filter(item -> AuthorizeType.RESOURCE.eq(item.getAuthorityType())).mapToLong(RoleAuthority::getAuthorityId).boxed().collect(Collectors.toList());
        RoleAuthoritySaveDTO roleAuthority = RoleAuthoritySaveDTO.builder()
                .menuIdList(menuIdList).resourceIdList(resourceIdList)
                .build();
        return success(roleAuthority);
    }


    /**
     * 给角色配置权限
     *
     * @param roleAuthoritySaveDTO 角色权限授权对象
     * @return 新增结果
     */
    @ApiOperation(value = "给角色配置权限", notes = "给角色配置权限")
    @PostMapping("/authority")
    @SysLog("给角色配置权限")
    public R<Boolean> saveRoleAuthority(@RequestBody RoleAuthoritySaveDTO roleAuthoritySaveDTO) {
        return success(roleAuthorityService.saveRoleAuthority(roleAuthoritySaveDTO));
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
