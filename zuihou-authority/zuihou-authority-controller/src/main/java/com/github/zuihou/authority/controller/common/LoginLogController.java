package com.github.zuihou.authority.controller.common;


import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.github.zuihou.authority.dto.common.LoginLogUpdateDTO;
import com.github.zuihou.authority.entity.common.LoginLog;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.authority.service.common.LoginLogService;
import com.github.zuihou.base.R;
import com.github.zuihou.base.controller.SuperController;
import com.github.zuihou.base.request.PageParams;
import com.github.zuihou.database.mybatis.conditions.query.QueryWrap;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.log.util.AddressUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * 登录日志
 * </p>
 *
 * @author zuihou
 * @date 2019-10-20
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/loginLog")
@Api(value = "LoginLog", tags = "登录日志")
public class LoginLogController extends SuperController<LoginLogService, Long, LoginLog, LoginLog, LoginLog, LoginLogUpdateDTO> {

    @Autowired
    private UserService userService;

    /**
     * 分页查询登录日志
     *
     * @param wrapper 分页查询条件
     * @param params  分页查询参数
     * @return 查询结果
     */
    @Override
    public void handlerWrapper(QueryWrap<LoginLog> wrapper, PageParams<LoginLog> params) {
        super.handlerWrapper(wrapper, params);
        LoginLog model = params.getModel();

        wrapper.lambda()
                // 忽略 Wraps.q(model); 时， account  和 requestIp 字段的默认查询规则，
                .ignore(LoginLog::setAccount)
                .ignore(LoginLog::setRequestIp)
                // 使用 自定义的查询规则
                .likeRight(LoginLog::getAccount, model.getAccount())
                .likeRight(LoginLog::getRequestIp, model.getRequestIp());
    }


    /**
     * 新增登录日志
     *
     * @param account 用户名
     * @return 新增结果
     */
    @ApiOperation(value = "新增登录日志", notes = "新增登录日志不为空的字段")
    @GetMapping("/anno/login/{account}")
    public R<LoginLog> save(@NotBlank(message = "用户名不能为为空") @PathVariable String account,
                            @RequestParam(required = false, defaultValue = "登陆成功") String description,
                            @ApiIgnore HttpServletRequest request) {
        String ua = StrUtil.sub(request.getHeader("user-agent"), 0, 500);
        String ip = ServletUtil.getClientIP(request);
        String location = AddressUtil.getRegion(ip);
        // update last login time
        this.userService.updateLoginTime(account);
        LoginLog loginLog = baseService.save(account, ua, ip, location, description);
        return this.success(loginLog);
    }


    @ApiOperation("清空日志")
    @DeleteMapping("clear")
    @SysLog("清空日志")
    public R<Boolean> clear(@RequestParam(required = false, defaultValue = "1") Integer type) {
        LocalDateTime clearBeforeTime = null;
        Integer clearBeforeNum = null;
        if (type == 1) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-1);
        } else if (type == 2) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-3);
        } else if (type == 3) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-6);
        } else if (type == 4) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-12);
        } else if (type == 5) {
            clearBeforeNum = 1000;        // 清理一千条以前日志数据
        } else if (type == 6) {
            clearBeforeNum = 10000;        // 清理一万条以前日志数据
        } else if (type == 7) {
            clearBeforeNum = 30000;        // 清理三万条以前日志数据
        } else if (type == 8) {
            clearBeforeNum = 100000;    // 清理十万条以前日志数据
        } else if (type == 9) {
            clearBeforeNum = null;            // 清理所有日志数据
        } else {
            return R.validFail("参数错误");
        }

        return success(baseService.clearLog(clearBeforeTime, clearBeforeNum));
    }
}
