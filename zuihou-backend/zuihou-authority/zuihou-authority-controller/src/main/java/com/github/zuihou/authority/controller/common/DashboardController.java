package com.github.zuihou.authority.controller.common;

import java.util.HashMap;
import java.util.Map;

import com.github.zuihou.authority.service.common.LoginLogService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.id.IdGenerate;
import com.github.zuihou.user.annotation.LoginUser;
import com.github.zuihou.user.model.SysUser;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 前端控制器
 * 首页
 * </p>
 *
 * @author zuihou
 * @date 2019-10-20
 */
@Slf4j
@Validated
@RestController
@Api(value = "dashboard", tags = "首页")
public class DashboardController extends BaseController {

    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private IdGenerate<Long> idGenerate;

    /**
     * 最近10天访问记录
     *
     * @return
     */
    @GetMapping("/dashboard/visit")
    public R<Map<String, Object>> index(@ApiIgnore @LoginUser SysUser user) {
        Map<String, Object> data = new HashMap<>();
        // 获取系统访问记录
        data.put("totalVisitCount", loginLogService.findTotalVisitCount());
        data.put("todayVisitCount", loginLogService.findTodayVisitCount());
        data.put("todayIp", loginLogService.findTodayIp());

        data.put("lastTenVisitCount", loginLogService.findLastTenDaysVisitCount(null));
        data.put("lastTenUserVisitCount", loginLogService.findLastTenDaysVisitCount(user.getAccount()));

        data.put("browserCount", loginLogService.findByBrowser());
        data.put("operatingSystemCount", loginLogService.findByOperatingSystem());

        return success(data);
    }

    /**
     * 生成id
     *
     * @return
     */
    @GetMapping("/common/generateId")
    public R<Long> generate() {
        return success(idGenerate.generate());
    }
}
