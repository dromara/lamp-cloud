package top.tangyh.lamp.base.controller.system;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
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
import top.tangyh.basic.base.request.PageParams;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.database.mybatis.conditions.query.LbQueryWrap;
import top.tangyh.basic.database.mybatis.conditions.query.QueryWrap;
import top.tangyh.basic.interfaces.echo.EchoService;
import top.tangyh.basic.utils.BeanPlusUtil;
import top.tangyh.lamp.base.biz.system.BaseRoleBiz;
import top.tangyh.lamp.base.entity.system.BaseRole;
import top.tangyh.lamp.base.service.system.BaseRoleService;
import top.tangyh.lamp.base.vo.query.system.BaseRolePageQuery;
import top.tangyh.lamp.base.vo.result.system.BaseRoleResultVO;
import top.tangyh.lamp.base.vo.save.system.BaseRoleResourceRelSaveVO;
import top.tangyh.lamp.base.vo.save.system.BaseRoleSaveVO;
import top.tangyh.lamp.base.vo.save.system.RoleEmployeeSaveVO;
import top.tangyh.lamp.base.vo.update.system.BaseRoleUpdateVO;
import top.tangyh.lamp.common.constant.BizConstant;
import top.tangyh.lamp.model.enumeration.base.RoleCategoryEnum;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 前端控制器
 * 角色
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/baseRole")
@Tag(name = "角色")
public class BaseRoleController extends SuperCacheController<BaseRoleService, Long, BaseRole, BaseRoleSaveVO, BaseRoleUpdateVO, BaseRolePageQuery, BaseRoleResultVO> {

    private final EchoService echoService;
    private final BaseRoleBiz baseRoleBiz;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public QueryWrap<BaseRole> handlerWrapper(BaseRole model, PageParams<BaseRolePageQuery> params) {
        QueryWrap<BaseRole> wrap = Wraps.q(null, params.getExtra(), getEntityClass());
        // category = ? and state = ? and (code like ? or name like ? or remarks like ?)
        wrap.lambda()
                .eq(BaseRole::getCategory, model.getCategory())
                .eq(BaseRole::getState, model.getState())
                .and(StrUtil.isNotEmpty(model.getName()), w ->
                        w.like(BaseRole::getCode, model.getName()).or().like(BaseRole::getName, model.getName())
                                .or().like(BaseRole::getRemarks, model.getRemarks())
                );
        return wrap;
    }

    @Operation(summary = "分页查询员工的角色", description = "分页查询员工的角色")
    @PostMapping("/pageMyRole")
    @WebLog(value = "'分页查询员工的角色:第' + #params?.current + '页, 显示' + #params?.size + '行'", response = false)
    public R<IPage<BaseRoleResultVO>> pageMyRole(@RequestBody @Validated PageParams<BaseRolePageQuery> params) {
        IPage<BaseRole> page = params.buildPage(BaseRole.class);
        BaseRolePageQuery query = params.getModel();
        BaseRole model = BeanUtil.toBean(query, BaseRole.class);
        LbQueryWrap<BaseRole> wraps = Wraps.lbq(model, params.getExtra(), BaseRole.class);

        if (StrUtil.equals(query.getScopeType(), BizConstant.SCOPE_TYPE_EMPLOYEE)) {
            if (StrUtil.equalsAny(query.getScope(), BizConstant.SCOPE_BIND, BizConstant.SCOPE_UN_BIND) && query.getEmployeeId() != null) {
                String sql = "select 1 from base_employee_role_rel er where  er.role_id = base_role.id and er.employee_id = {0}";
                if (BizConstant.SCOPE_BIND.equals(query.getScope())) {
                    wraps.exists(sql, query.getEmployeeId());
                } else {
                    wraps.notExists(sql, query.getEmployeeId());
                }
            }
        } else if (StrUtil.equals(query.getScopeType(), BizConstant.SCOPE_TYPE_ORG)) {
            if (StrUtil.equalsAny(query.getScope(), BizConstant.SCOPE_BIND, BizConstant.SCOPE_UN_BIND) && query.getOrgId() != null) {
                String sql = "select 1 from base_org_role_rel er where  er.role_id = base_role.id and er.org_id = {0}";
                if (BizConstant.SCOPE_BIND.equals(query.getScope())) {
                    wraps.exists(sql, query.getOrgId());
                } else {
                    wraps.notExists(sql, query.getOrgId());
                }
            }
        }

        superService.page(page, wraps);
        IPage<BaseRoleResultVO> pageVO = BeanPlusUtil.toBeanPage(page, BaseRoleResultVO.class);
        echoService.action(pageVO);
        return R.success(pageVO);
    }


    @Operation(summary = "检测角色编码")
    @GetMapping("/check")
    @WebLog("检测角色编码")
    public R<Boolean> check(@RequestParam String code, @RequestParam(required = false) Long id) {
        return success(superService.check(code, id));
    }


    /**
     * 给角色分配员工
     *
     * @param roleEmployeeSaveVO 给角色分配员工参数
     * @return 新增结果
     */
    @Operation(summary = "给角色分配员工", description = "给角色分配员工")
    @PostMapping("/roleEmployee")
    @WebLog("给角色分配用户")
    public R<List<Long>> saveRoleEmployee(@RequestBody RoleEmployeeSaveVO roleEmployeeSaveVO) {
        return success(superService.saveRoleEmployee(roleEmployeeSaveVO));
    }

    /**
     * 给角色配置资源
     *
     * @param saveVO 角色权限授权对象
     * @return 新增结果
     */
    @Operation(summary = "给角色配置资源")
    @PostMapping("/roleResource")
    @WebLog("给角色配置权限")
    public R<Boolean> saveRoleResource(@RequestBody BaseRoleResourceRelSaveVO saveVO) {
        return success(superService.saveRoleResource(saveVO));
    }

    /**
     * 查询角色绑定的员工
     *
     * @param roleId 角色id
     * @return 新增结果
     */
    @Operation(summary = "查询角色绑定的员工")
    @GetMapping("/employeeList")
    @WebLog("查询角色的用户")
    public R<List<Long>> findEmployeeIdByRoleId(@RequestParam Long roleId) {
        return success(superService.findEmployeeIdByRoleId(roleId));
    }


    /**
     * 查询角色拥有的资源id
     *
     * @param roleId 角色id
     * @return 新增结果
     */
    @Operation(summary = "查询角色拥有的资源id集合")
    @GetMapping("/resourceList")
    @WebLog("查询角色拥有的资源")
    public R<Map<Long, Collection<Long>>> findResourceIdByRoleId(@RequestParam Long roleId) {
        return success(baseRoleBiz.findResourceIdByRoleId(roleId));
    }

    /**
     * 查询角色拥有的数据权限ID
     *
     * @param roleId 角色id
     * @return 新增结果
     */
    @Operation(summary = "查询角色拥有的数据权限ID")
    @GetMapping("/findResourceDataScopeIdByRoleId")
    @WebLog("查询角色拥有的数据权限")
    public R<Map<Long, Collection<Long>>> findResourceDataScopeIdByRoleId(@RequestParam Long roleId) {
        return success(superService.findResourceIdByRoleId(roleId, RoleCategoryEnum.DATA_SCOPE));
    }


    @Operation(summary = "查询员工拥有的角色编码")
    @GetMapping("/findRoleCodeByEmployeeId")
    public R<List<String>> findRoleCodeByEmployeeId(@RequestParam(value = "employeeId") Long employeeId) {
        return success(superService.findRoleCodeByEmployeeId(employeeId));
    }


}
