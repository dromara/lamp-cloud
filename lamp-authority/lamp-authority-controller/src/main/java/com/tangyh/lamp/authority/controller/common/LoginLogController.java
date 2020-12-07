package com.tangyh.lamp.authority.controller.common;


import com.tangyh.basic.annotation.log.SysLog;
import com.tangyh.basic.annotation.security.PreAuth;
import com.tangyh.basic.base.R;
import com.tangyh.basic.base.controller.SuperController;
import com.tangyh.basic.base.request.PageParams;
import com.tangyh.basic.database.mybatis.conditions.query.QueryWrap;
import com.tangyh.lamp.authority.dto.common.LoginLogUpdateDTO;
import com.tangyh.lamp.authority.entity.common.LoginLog;
import com.tangyh.lamp.authority.service.common.LoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@PreAuth(replace = "authority:loginLog:")
public class LoginLogController extends SuperController<LoginLogService, Long, LoginLog, LoginLog, LoginLog, LoginLogUpdateDTO> {

    /**
     * 分页查询登录日志
     *
     * @param model  对象
     * @param params 分页查询参数
     * @return 查询结果
     */
    @Override
    public QueryWrap<LoginLog> handlerWrapper(LoginLog model, PageParams<LoginLog> params) {
        QueryWrap<LoginLog> wrapper = super.handlerWrapper(model, params);

        wrapper.lambda()
                // 忽略 Wraps.q(model); 时， account  和 requestIp 字段的默认查询规则，
                .ignore(LoginLog::setAccount)
                .ignore(LoginLog::setRequestIp)
                // 使用 自定义的查询规则
                .likeRight(LoginLog::getAccount, model.getAccount())
                .likeRight(LoginLog::getRequestIp, model.getRequestIp());
        return wrapper;
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
            // 清理一千条以前日志数据
            clearBeforeNum = 1000;
        } else if (type == 6) {
            // 清理一万条以前日志数据
            clearBeforeNum = 10000;
        } else if (type == 7) {
            // 清理三万条以前日志数据
            clearBeforeNum = 30000;
        } else if (type == 8) {
            // 清理十万条以前日志数据
            clearBeforeNum = 100000;
        }

        return success(baseService.clearLog(clearBeforeTime, clearBeforeNum));
    }

}
