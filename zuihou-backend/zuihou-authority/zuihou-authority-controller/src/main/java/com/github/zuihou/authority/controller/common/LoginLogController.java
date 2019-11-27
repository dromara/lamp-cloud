package com.github.zuihou.authority.controller.common;


import java.util.List;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.entity.common.LoginLog;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.authority.service.common.LoginLogService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.log.util.AddressUtil;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class LoginLogController extends BaseController {

    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private UserService userService;

    /**
     * 分页查询登录日志
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询登录日志", notes = "分页查询登录日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    public R<IPage<LoginLog>> page(LoginLog data) {
        IPage<LoginLog> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<LoginLog> query = Wraps.<LoginLog>lbQ()
                .eq(LoginLog::getUserId, data.getUserId())
                .likeRight(LoginLog::getAccount, data.getAccount())
                .likeRight(LoginLog::getRequestIp, data.getRequestIp())
                .like(LoginLog::getLocation, data.getLocation())
                .leFooter(LoginLog::getCreateTime, getEndCreateTime())
                .geHeader(LoginLog::getCreateTime, getStartCreateTime())
                .orderByDesc(LoginLog::getId);
        loginLogService.page(page, query);
        return success(page);
    }

    /**
     * 查询登录日志
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询登录日志", notes = "查询登录日志")
    @GetMapping("/{id}")
    public R<LoginLog> get(@PathVariable Long id) {
        return success(loginLogService.getById(id));
    }

    /**
     * 新增登录日志
     *
     * @param account 用户名
     * @return 新增结果
     */
    @ApiOperation(value = "新增登录日志", notes = "新增登录日志不为空的字段")
    @GetMapping("/success/{account}")
    public R<LoginLog> save(@NotBlank(message = "用户名不能为为空") @PathVariable String account) {
        String ua = StrUtil.sub(request.getHeader("user-agent"), 0, 500);
        String ip = ServletUtil.getClientIP(request);
        String location = AddressUtil.getCityInfo(ip);
        // update last login time
        this.userService.updateLoginTime(account);
        LoginLog loginLog = loginLogService.save(account, ua, ip, location);
        return success(loginLog);
    }


    /**
     * 删除系统日志
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除日志", notes = "根据id物理删除系统日志")
    @DeleteMapping
    @SysLog("删除登录日志")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        loginLogService.removeByIds(ids);
        return success(true);
    }
}
