package top.tangyh.lamp.system.controller.tenant;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.log.WebLog;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.controller.SuperController;
import top.tangyh.basic.base.request.PageParams;
import top.tangyh.basic.interfaces.echo.EchoService;
import top.tangyh.lamp.system.entity.tenant.DefUser;
import top.tangyh.lamp.system.service.tenant.DefUserService;
import top.tangyh.lamp.system.vo.query.tenant.DefUserPageQuery;
import top.tangyh.lamp.system.vo.result.tenant.DefUserResultVO;
import top.tangyh.lamp.system.vo.save.tenant.DefUserSaveVO;
import top.tangyh.lamp.system.vo.update.tenant.DefUserPasswordResetVO;
import top.tangyh.lamp.system.vo.update.tenant.DefUserUpdateVO;

import java.util.List;


/**
 * <p>
 * 前端控制器
 * 用户
 * </p>
 *
 * @author zuihou
 * @date 2021-10-09
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defUser")
@Tag(name = "用户")
public class DefUserController extends SuperController<DefUserService, Long, DefUser, DefUserSaveVO, DefUserUpdateVO, DefUserPageQuery, DefUserResultVO> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }


    @Operation(summary = "检测用户名是否存在")
    @GetMapping("/checkUsername")
    @WebLog("'检测用户名是否存在, username=' + #username + ', id=' + #id")
    public R<Boolean> checkUsername(@RequestParam String username, @RequestParam(required = false) Long id) {
        return success(superService.checkUsername(username, id));
    }

    @Operation(summary = "检测邮箱是否存在")
    @GetMapping("/checkEmail")
    @WebLog("'检测邮箱是否存在, email=' + #email + ', id=' + #id")
    public R<Boolean> checkEmail(@RequestParam String email, @RequestParam(required = false) Long id) {
        return success(superService.checkEmail(email, id));
    }

    @Operation(summary = "检测身份证是否存在")
    @GetMapping("/checkIdCard")
    @WebLog("'检测身份证是否存在, idCard=' + #idCard + ', id=' + #id")
    public R<Boolean> checkIdCard(@RequestParam String idCard, @RequestParam(required = false) Long id) {
        return success(superService.checkIdCard(idCard, id));
    }

    @Operation(summary = "检测手机号是否存在")
    @GetMapping("/checkMobile")
    @WebLog("'检测手机号是否存在, mobile=' + #mobile + ', id=' + #id")
    public R<Boolean> checkMobile(@RequestParam String mobile, @RequestParam(required = false) Long id) {
        return success(superService.checkMobile(mobile, id));
    }

    /**
     * 重置密码
     *
     * @param data 修改实体
     * @return 是否成功
     */
    @Operation(summary = "重置密码", description = "重置密码")
    @PutMapping("/resetPassword")
    @WebLog("'修改密码:' + #data.id")
    public R<Boolean> resetPassword(@RequestBody @Validated DefUserPasswordResetVO data) {
        return success(superService.resetPassword(data));
    }

    /**
     * 修改状态
     *
     * @param id    用户id
     * @param state 用户状态
     * @return 是否成功
     */
    @Operation(summary = "修改状态", description = "修改状态")
    @PutMapping("/updateState")
    @WebLog("'修改状态:id=' + #id + ', state=' + #state")
    public R<Boolean> updateState(
            @NotNull(message = "请选择用户") @RequestParam Long id,
            @NotNull(message = "请设置正确的状态值") @RequestParam Boolean state) {
        return success(superService.updateState(id, state));
    }

    @Operation(summary = "查询所有的用户id", description = "查询所有的用户id")
    @PostMapping(value = "/findAllUserId")
    @WebLog("'查询所有的用户id")
    public R<List<Long>> findAllUserId() {
        return R.success(superService.findUserIdList(null));
    }

    @Operation(summary = "查找同一企业下的用户", description = "查找同一企业下的用户")
    @PostMapping(value = "/pageUser")
    @WebLog("'查找同一企业下的用户")
    public R<IPage<DefUserResultVO>> pageUser(@RequestBody @Validated PageParams<DefUserPageQuery> params) {
        IPage<DefUserResultVO> page = superService.pageUser(params);
        echoService.action(page);
        return R.success(page);
    }

    @Operation(summary = "邀请员工进入企业前精确查询用户", description = "邀请员工进入企业前精确查询用户")
    @PostMapping(value = "/queryUser")
    @WebLog("'邀请员工进入企业前精确查询用户")
    public R<List<DefUserResultVO>> queryUser(@RequestBody DefUserPageQuery params) {
        return R.success(superService.queryUser(params));
    }
}
