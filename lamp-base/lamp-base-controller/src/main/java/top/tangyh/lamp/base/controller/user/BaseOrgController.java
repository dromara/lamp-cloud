package top.tangyh.lamp.base.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.log.WebLog;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.controller.SuperCacheController;
import top.tangyh.basic.interfaces.echo.EchoService;
import top.tangyh.lamp.base.entity.user.BaseOrg;
import top.tangyh.lamp.base.service.user.BaseOrgService;
import top.tangyh.lamp.base.vo.query.user.BaseOrgPageQuery;
import top.tangyh.lamp.base.vo.result.user.BaseOrgResultVO;
import top.tangyh.lamp.base.vo.save.user.BaseOrgRoleRelSaveVO;
import top.tangyh.lamp.base.vo.save.user.BaseOrgSaveVO;
import top.tangyh.lamp.base.vo.update.user.BaseOrgUpdateVO;

import java.util.List;

import static top.tangyh.lamp.common.constant.SwaggerConstants.DATA_TYPE_LONG;
import static top.tangyh.lamp.common.constant.SwaggerConstants.DATA_TYPE_STRING;


/**
 * <p>
 * 前端控制器
 * 组织
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/baseOrg")
@Tag(name = "组织")
public class BaseOrgController extends SuperCacheController<BaseOrgService, Long, BaseOrg, BaseOrgSaveVO, BaseOrgUpdateVO, BaseOrgPageQuery, BaseOrgResultVO> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }


    @Parameters({
            @Parameter(name = "name", description = "name", required = true, schema = @Schema(type = DATA_TYPE_STRING), in = ParameterIn.QUERY),
            @Parameter(name = "parentId", description = "parentId", schema = @Schema(type = DATA_TYPE_LONG), in = ParameterIn.QUERY),
            @Parameter(name = "id", description = "ID", schema = @Schema(type = DATA_TYPE_LONG), in = ParameterIn.QUERY),
    })
    @Operation(summary = "检测名称是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam String name, @RequestParam Long parentId, @RequestParam(required = false) Long id) {
        return success(superService.check(name, parentId, id));
    }

    /**
     * 按树结构查询地区
     *
     * @param pageQuery 查询参数
     * @return 查询结果
     */
    @Operation(summary = "按树结构查询地区")
    @PostMapping("/tree")
    @WebLog("级联查询地区")
    public R<List<BaseOrgResultVO>> tree(@RequestBody BaseOrgPageQuery pageQuery) {
        return success(superService.tree(pageQuery));
    }

    /**
     * 给机构分配角色
     *
     * @param orgRoleSaveVO 参数
     * @return 新增结果
     */
    @Operation(summary = "给机构分配角色", description = "给机构分配角色")
    @PostMapping("/orgRole")
    @WebLog("给机构分配角色")
    public R<List<Long>> saveOrgRole(@RequestBody BaseOrgRoleRelSaveVO orgRoleSaveVO) {
        return success(superService.saveOrgRole(orgRoleSaveVO));
    }

    /**
     * 查询机构的角色
     *
     * @param orgId 员工id
     * @return 新增结果
     */
    @Operation(summary = "查询机构的角色")
    @GetMapping("/findOrgRoleByOrgId")
    @WebLog("查询机构的角色")
    public R<List<Long>> findOrgRoleByOrgId(@RequestParam Long orgId) {
        return success(superService.findOrgRoleByOrgId(orgId));
    }

    @Operation(summary = "查询员工的公司")
    @GetMapping("/findCompanyByEmployeeId")
    public R<List<BaseOrg>> findCompanyByEmployeeId(@RequestParam("employeeId") Long employeeId) {
        return success(superService.findCompanyByEmployeeId(employeeId));
    }

    @Operation(summary = "查询员工{employeeId}的在指定公司{companyId}下的所有部门")
    @GetMapping("/findDeptByEmployeeId")
    public R<List<BaseOrg>> findDeptByEmployeeId(@RequestParam("employeeId") Long employeeId,
                                                 @RequestParam("companyId") Long companyId) {
        return success(superService.findDeptByEmployeeId(employeeId, companyId));
    }
}
