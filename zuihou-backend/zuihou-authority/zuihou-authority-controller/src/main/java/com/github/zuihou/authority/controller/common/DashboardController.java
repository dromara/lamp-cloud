package com.github.zuihou.authority.controller.common;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/dashboard")
@Api(value = "dashboard", tags = "首页")
public class DashboardController extends BaseController {

    /**
     * 最近10天访问记录
     *
     * @param startTime 开始日期
     * @param endTime   介绍日期
     * @return
     */
    @GetMapping("/visit")
    public R<Map<String, Object>> index(@RequestParam(value = "startTime", required = false) LocalDateTime startTime,
                                        @RequestParam(value = "endTime", required = false) LocalDateTime endTime) {
        Map<String, Object> data = new HashMap<>();
        // 获取系统访问记录
        Long totalVisitCount = 20L;
        data.put("totalVisitCount", totalVisitCount);
        Long todayVisitCount = 10L;
        data.put("todayVisitCount", todayVisitCount);
        Long todayIp = 8L;
        data.put("todayIp", todayIp);
        //TODO 记录操作日志
        // 获取近期系统访问记录
        List<Map<String, Object>> lastTenVisitCount = new ArrayList<>();
        Map<String, Object> map = new HashMap<>(1);
        map = new HashMap<>(1);
        map.put("10-11", 30);
        lastTenVisitCount.add(map);
        map = new HashMap<>(1);
        map.put("10-12", 36);
        lastTenVisitCount.add(map);
        map = new HashMap<>(1);
        map.put("10-13", 48);
        lastTenVisitCount.add(map);
        map = new HashMap<>(1);
        map.put("10-14", 77);
        lastTenVisitCount.add(map);
        map = new HashMap<>(1);
        map.put("10-15", 44);
        lastTenVisitCount.add(map);
        map = new HashMap<>(1);
        map.put("10-16", 55);
        lastTenVisitCount.add(map);
        map = new HashMap<>(1);
        map.put("10-17", 37);
        lastTenVisitCount.add(map);
        map = new HashMap<>(1);
        map.put("10-18", 23);
        lastTenVisitCount.add(map);
        map = new HashMap<>(1);
        map.put("10-19", 67);
        lastTenVisitCount.add(map);
        map = new HashMap<>(1);
        map.put("10-20", 80);
        lastTenVisitCount.add(map);
        data.put("lastTenVisitCount", lastTenVisitCount);

        List<Map<String, Object>> lastTenUserVisitCount = new ArrayList<>();
        map = new HashMap<>(1);
        map.put("10-11", 26);
        lastTenUserVisitCount.add(map);
        map = new HashMap<>(1);
        map.put("10-12", 19);
        lastTenUserVisitCount.add(map);
        map = new HashMap<>(1);
        map.put("10-13", 40);
        lastTenUserVisitCount.add(map);
        map = new HashMap<>(1);
        map.put("10-14", 60);
        lastTenUserVisitCount.add(map);
        map = new HashMap<>(1);
        map.put("10-15", 29);
        lastTenUserVisitCount.add(map);
        map = new HashMap<>(1);
        map.put("10-16", 48);
        lastTenUserVisitCount.add(map);
        map = new HashMap<>(1);
        map.put("10-17", 37);
        lastTenUserVisitCount.add(map);
        map = new HashMap<>(1);
        map.put("10-18", 20);
        lastTenUserVisitCount.add(map);
        map = new HashMap<>(1);
        map.put("10-19", 60);
        lastTenUserVisitCount.add(map);
        map = new HashMap<>(1);
        map.put("10-20", 75);
        lastTenUserVisitCount.add(map);

        data.put("lastTenUserVisitCount", lastTenUserVisitCount);
        return success(data);
    }
}
