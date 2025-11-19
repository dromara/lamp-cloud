package top.tangyh.lamp.oauth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.user.LoginUser;
import top.tangyh.basic.base.R;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.lamp.base.service.system.BaseRoleService;
import top.tangyh.lamp.common.properties.IgnoreProperties;
import top.tangyh.lamp.model.entity.system.SysUser;
import top.tangyh.lamp.oauth.biz.ResourceBiz;
import top.tangyh.lamp.oauth.biz.StpInterfaceBiz;
import top.tangyh.lamp.oauth.vo.result.VisibleResourceVO;
import top.tangyh.lamp.system.enumeration.system.ClientTypeEnum;

import java.util.List;


/**
 * <p>
 * 前端控制器
 * 资源 角色 菜单
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@RestController
@RequestMapping
@AllArgsConstructor
@Tag(name = "资源-菜单-应用")
public class ResourceController {
    private final IgnoreProperties ignoreProperties;
    private final ResourceBiz oauthResourceBiz;
    private final BaseRoleService baseRoleService;
    private final StpInterfaceBiz stpInterfaceBiz;


    /**
     * 查询用户可用的所有资源
     *
     * @param type 应用类型 根据类型决定返回的数据格式
     * @param applicationId 应用id
     *                          应用id为空，则返回所有应用的菜单数据
     * @param employeeId    当前登录人id
     */
    @Operation(summary = "查询用户可用的所有路由和资源和角色", description = "根据员工ID和应用ID查询员工在某个应用下可用的资源")
    @GetMapping("/anyone/visible/resource")
    public R<VisibleResourceVO> visible(@Parameter(hidden = true) @LoginUser SysUser sysUser,
                                        @RequestParam(value = "type", required = false) ClientTypeEnum type,
                                        @RequestParam(value = "employeeId", required = false) Long employeeId,
                                        @RequestParam(value = "applicationId", required = false) Long applicationId,
                                        @RequestParam(value = "subGroup", required = false) String subGroup
    ) {
        if (employeeId == null || employeeId <= 0) {
            employeeId = sysUser.getEmployeeId();
        }
        return R.success(VisibleResourceVO.builder()
                .enabled(ignoreProperties.getAuthEnabled())
                .caseSensitive(ignoreProperties.getCaseSensitive())
                .roleList(baseRoleService.findRoleCodeByEmployeeId(employeeId))
                .resourceList(oauthResourceBiz.findVisibleResource(employeeId, applicationId))
                .routerList(
                        applicationId == null ? oauthResourceBiz.findAllVisibleRouter(employeeId, subGroup, type) : oauthResourceBiz.findVisibleRouter(applicationId, employeeId, subGroup, type)
                )
                .build());
    }

    @Operation(summary = "查询用户可用的所有资源", description = "查询用户可用的所有资源")
    @GetMapping("/anyone/findVisibleResource")
    public R<List<String>> visibleResource(@RequestParam(value = "employeeId") Long employeeId,
                                           @RequestParam(value = "applicationId", required = false) Long applicationId) {
        return R.success(oauthResourceBiz.findVisibleResource(employeeId, applicationId));
    }

    /**
     * 检测员工是否拥有指定应用的权限
     *
     * @param applicationId 应用id
     */
    @Operation(summary = "检测员工是否拥有指定应用的权限", description = "检测员工是否拥有指定应用的权限")
    @GetMapping("/anyone/checkEmployeeHaveApplication")
    public R<Boolean> checkEmployeeHaveApplication(@RequestParam Long applicationId) {
        return R.success(oauthResourceBiz.checkEmployeeHaveApplication(ContextUtil.getEmployeeId(), applicationId));
    }


    @Operation(summary = "查询用户的资源权限列表", description = "查询用户的资源权限列表")
    @GetMapping("/anyone/getPermissionList")
    public R<List<String>> getPermissionList() {
        return R.success(stpInterfaceBiz.getPermissionList());
    }

    @Operation(summary = "查询用户的角色列表", description = "查询用户的角色列表")
    @GetMapping("/anyone/getRoleList")
    public R<List<String>> getRoleList() {
        return R.success(stpInterfaceBiz.getRoleList());
    }
}
