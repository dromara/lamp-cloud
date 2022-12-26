package top.tangyh.lamp.authority.controller.auth;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.log.SysLog;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.controller.SuperController;
import top.tangyh.basic.base.entity.SuperEntity;
import top.tangyh.basic.base.request.PageParams;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.basic.utils.BeanPlusUtil;
import top.tangyh.basic.utils.StrHelper;
import top.tangyh.lamp.authority.dto.auth.GlobalUserPageDTO;
import top.tangyh.lamp.authority.dto.auth.GlobalUserSaveDTO;
import top.tangyh.lamp.authority.dto.auth.GlobalUserUpdateDTO;
import top.tangyh.lamp.authority.dto.auth.UserUpdatePasswordDTO;
import top.tangyh.lamp.authority.entity.auth.User;
import top.tangyh.lamp.authority.service.auth.UserService;
import top.tangyh.lamp.common.constant.BizConstant;

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
@Tag(name = "全局账号")
@SysLog(enabled = false)
@RequiredArgsConstructor
public class GlobalUserController extends SuperController<UserService, Long, User, GlobalUserPageDTO, GlobalUserSaveDTO, GlobalUserUpdateDTO> {

    @Override
    public R<User> handlerSave(GlobalUserSaveDTO model) {
        ContextUtil.setTenant(model.getTenantCode());
        User user = BeanPlusUtil.toBean(model, User.class);
        user.setName(StrHelper.getOrDef(model.getName(), model.getAccount()));
        if (StrUtil.isEmpty(user.getPassword())) {
            user.setPassword(BizConstant.DEF_PASSWORD);
        }
        user.setState(true);
        baseService.initUser(user);
        return success(user);
    }

    @Override
    public R<User> handlerUpdate(GlobalUserUpdateDTO model) {
        ContextUtil.setTenant(model.getTenantCode());
        User user = BeanPlusUtil.toBean(model, User.class);
        baseService.updateUser(user);
        return success(user);
    }

    @Parameters({
            @Parameter(name = "tenantCode", description = "企业编码", schema = @Schema(type = "string"), in = ParameterIn.QUERY),
            @Parameter(name = "account", description = "账号", schema = @Schema(type = "string"), in = ParameterIn.QUERY),
    })
    @Operation(summary = "检测账号是否可用", description = "检测账号是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam String tenantCode, @RequestParam String account) {
        ContextUtil.setTenant(tenantCode);
        return success(baseService.check(null, account));
    }

    @Override
    public IPage<User> query(PageParams<GlobalUserPageDTO> params) {
        IPage<User> page = params.buildPage(User.class);
        GlobalUserPageDTO model = params.getModel();
        ContextUtil.setTenant(model.getTenantCode());

        baseService.pageByRole(page, params);

        page.getRecords().forEach(item -> {
            item.setPassword(null);
            item.setSalt(null);
        });
        return page;
    }


    @Operation(summary = "删除用户")
    @DeleteMapping("/delete")
    @Parameters({
            @Parameter(name = "tenantCode", description = "企业编码", schema = @Schema(type = "string"), in = ParameterIn.QUERY),
            @Parameter(name = "ids[]", description = "主键id", schema = @Schema(type = "string"), in = ParameterIn.QUERY),
    })
    public R<Boolean> delete(@RequestParam String tenantCode, @RequestParam("ids[]") List<Long> ids) {
        ContextUtil.setTenant(tenantCode);
        return success(baseService.remove(ids));
    }


    /**
     * 修改密码
     *
     * @param model 修改实体
     */
    @Operation(summary = "修改密码", description = "修改密码")
    @PutMapping("/reset")
    public R<Boolean> updatePassword(@RequestBody @Validated(SuperEntity.Update.class) UserUpdatePasswordDTO model) {
        ContextUtil.setTenant(model.getTenantCode());
        return success(baseService.reset(model));
    }
}
