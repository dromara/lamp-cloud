package com.github.zuihou.order.controller;

import com.github.zuihou.authority.dto.test.DateDTO;
import com.github.zuihou.base.R;
import com.github.zuihou.order.api.TestDateApi;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * 日期测试类
 *
 * @author zuihou
 * @date 2019/07/24
 */
@Slf4j
@RestController
@RequestMapping("/date")
@Api(value = "Date", tags = "时间类型验证器")
public class DateController {

    @Autowired
    private TestDateApi testDateApi;

    @PostMapping("/post1")
    public R<DateDTO> bodyPos1(@RequestBody DateDTO data) {
        log.info("post1={}", data);
        return testDateApi.bodyPos1(data);
    }


    @GetMapping("/get1")
    public R<DateDTO> get(DateDTO data) {
        log.info("get1={}", data);
        return testDateApi.get(data);
    }

    @GetMapping("/get2")
    public R<DateDTO> get2(@RequestParam(required = false, value = "date") Date date,
                           @RequestParam(required = false, value = "dt") LocalDateTime dt,
                           @RequestParam(required = false, value = "d") LocalDate d,
                           @RequestParam(required = false, value = "t") LocalTime t) {
        return testDateApi.get2(date, dt, d, t);
    }


}
