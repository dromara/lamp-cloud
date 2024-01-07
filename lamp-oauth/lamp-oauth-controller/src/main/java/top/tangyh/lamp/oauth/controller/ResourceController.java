package top.tangyh.lamp.oauth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.user.LoginUser;
import top.tangyh.basic.base.R;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.lamp.base.vo.result.user.VueRouter;
import top.tangyh.lamp.common.properties.IgnoreProperties;
import top.tangyh.lamp.model.entity.system.SysUser;
import top.tangyh.lamp.oauth.biz.ResourceBiz;
import top.tangyh.lamp.oauth.vo.result.VisibleResourceVO;

import java.util.Collections;
import java.util.List;

import static top.tangyh.lamp.common.constant.SwaggerConstants.DATA_TYPE_LONG;
import static top.tangyh.lamp.common.constant.SwaggerConstants.DATA_TYPE_STRING;


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
@RequestMapping("/anyone")
@AllArgsConstructor
@Tag(name = "资源-菜单-应用")
@EnableConfigurationProperties({IgnoreProperties.class})
public class ResourceController {
    private final IgnoreProperties ignoreProperties;
    private final ResourceBiz oauthResourceBiz;


    /**
     * 查询用户可用的所有资源
     *
     * @param applicationId 应用id
     * @param employeeId    当前登录人id
     */
    @Operation(summary = "查询用户可用的所有资源", description = "根据员工ID和应用ID查询员工在某个应用下可用的资源")
    @GetMapping("/visible/resource")
    public R<VisibleResourceVO> visible(@Parameter(hidden = true) @LoginUser SysUser sysUser,
                                        @RequestParam(value = "employeeId", required = false) Long employeeId,
                                        @RequestParam Long applicationId) {
        if (employeeId == null || employeeId <= 0) {
            employeeId = sysUser.getEmployeeId();
        }
        return R.success(VisibleResourceVO.builder()
                .roleList(Collections.singletonList("PT_ADMIN"))
                .resourceList(oauthResourceBiz.findVisibleResource(employeeId, applicationId))
                .caseSensitive(ignoreProperties.getCaseSensitive())
                .enabled(ignoreProperties.getAuthEnabled())
                .build());
    }

    @Operation(summary = "查询用户可用的所有资源", description = "查询用户可用的所有资源")
    @GetMapping("/findVisibleResource")
    public R<List<String>> visibleResource(@RequestParam(value = "employeeId") Long employeeId,
                                           @RequestParam(value = "applicationId", required = false) Long applicationId) {
        return R.success(oauthResourceBiz.findVisibleResource(employeeId, applicationId));
    }

    /**
     * 检查员工是否有指定uri的访问权限
     *
     * @param path   请求路径
     * @param method 请求方法
     */
    @Operation(summary = "检查员工是否有指定uri的访问权限", description = "检查员工是否有指定uri的访问权限")
    @GetMapping("/checkUri")
    public R<Boolean> checkUri(@RequestParam String path, @RequestParam String method) {
        long apiStart = System.currentTimeMillis();
        Boolean check = oauthResourceBiz.checkUri(path, method);
        long apiEnd = System.currentTimeMillis();
        log.info("controller 校验api权限:{} - {}  耗时:{}", path, method, (apiEnd - apiStart));
        return R.success(check);
    }

    /**
     * 检查员工是否有指定应用的权限
     *
     * @param employeeId    员工id
     * @param applicationId 应用ID
     */
    @Operation(summary = "检查员工是否有指定应用的权限", description = "检查员工是否有指定应用的权限")
    @GetMapping("/checkApplication")
    public R<Boolean> checkApplication(@RequestParam Long applicationId, @RequestParam Long employeeId) {
        return R.success(true);
    }

    /**
     * 检测员工是否拥有指定应用的权限
     *
     * @param applicationId 应用id
     */
    @Operation(summary = "检测员工是否拥有指定应用的权限", description = "检测员工是否拥有指定应用的权限")
    @GetMapping("/checkEmployeeHaveApplication")
    public R<Boolean> checkEmployeeHaveApplication(@RequestParam Long applicationId) {
        return R.success(oauthResourceBiz.checkEmployeeHaveApplication(ContextUtil.getEmployeeId(), applicationId));
    }

    @Parameters({
            @Parameter(name = "subGroup", description = "菜单组", schema = @Schema(type = DATA_TYPE_STRING), in = ParameterIn.QUERY),
            @Parameter(name = "employeeId", description = "模板编码: 在「运营平台」-「消息模板」-「模板标识」配置一个邮件模板", schema = @Schema(type = DATA_TYPE_LONG), in = ParameterIn.QUERY),
            @Parameter(name = "applicationId", description = "应用id", schema = @Schema(type = DATA_TYPE_LONG), in = ParameterIn.QUERY),
    })
    @Operation(summary = "查询用户可用的所有菜单路由树", description = "根据员工ID和应用ID查询员工在某个应用下可用的菜单路由树")
    @GetMapping("/visible/router")
    public R<List<VueRouter>> myRouter(@RequestParam Long applicationId,
                                       @RequestParam(value = "subGroup", required = false) String subGroup,
                                       @RequestParam(value = "employeeId", required = false) Long employeeId,
                                       @Parameter(hidden = true) @LoginUser SysUser sysUser) {
        if (employeeId == null || employeeId <= 0) {
            employeeId = sysUser.getEmployeeId();
        }
        return R.success(oauthResourceBiz.findVisibleRouter(applicationId, employeeId, subGroup));
    }

    @Parameters({
            @Parameter(name = "subGroup", description = "菜单组", schema = @Schema(type = DATA_TYPE_STRING), in = ParameterIn.QUERY),
            @Parameter(name = "employeeId", description = "模板编码: 在「运营平台」-「消息模板」-「模板标识」配置一个邮件模板", schema = @Schema(type = DATA_TYPE_LONG), in = ParameterIn.QUERY),
    })
    @Operation(summary = "查询用户所有应用的可用路由树", description = "查询用户所有应用的可用路由树")
    @GetMapping("/visible/allRouter")
    public R<List<VueRouter>> allRouter(@RequestParam(value = "subGroup", required = false) String subGroup,
                                        @RequestParam(value = "employeeId", required = false) Long employeeId,
                                        @Parameter(hidden = true) @LoginUser SysUser sysUser) {
        if (employeeId == null || employeeId <= 0) {
            employeeId = sysUser.getEmployeeId();
        }
        return R.success(oauthResourceBiz.findAllVisibleRouter(employeeId, subGroup));
    }

}
