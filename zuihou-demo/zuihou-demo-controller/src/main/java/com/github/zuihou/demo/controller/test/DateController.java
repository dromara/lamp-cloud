package com.github.zuihou.demo.controller.test;

import com.github.zuihou.authority.dto.test.DateDTO;
import com.github.zuihou.authority.entity.auth.Menu;
import com.github.zuihou.base.R;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * 日期参数 测试
 *
 * @author zuihou
 * @date 2019/07/24
 */
@Slf4j
@RestController
@RequestMapping("/date")
@Api(value = "Date", tags = "时间类型验证器")
public class DateController {

    @PostMapping("/post1")
    public R<DateDTO> bodyPos1(@RequestBody DateDTO data) {
        log.info("post1={}", data);
        return R.success(data);
    }

    @PostMapping("/post2")
    public R<Menu> bodyPos2(@RequestBody Menu data) {
        log.info("post2={}", data);
        return R.success(data);
    }


    @GetMapping("/get1")
    public R<DateDTO> get(DateDTO data) {
        log.info("get1={}", data);
        return R.success(data);
    }

    @GetMapping("/get2")
    public R<DateDTO> get2(@RequestParam(required = false, value = "date") Date date,
                           @RequestParam(required = false, value = "dt") LocalDateTime dt,
                           @RequestParam(required = false, value = "d") LocalDate d,
                           @RequestParam(required = false, value = "t") LocalTime t) {
        DateDTO dateDTO = new DateDTO();
        dateDTO.setD(LocalDate.now());
        dateDTO.setDate(new Date());
        dateDTO.setDt(LocalDateTime.now());
        dateDTO.setT(LocalTime.now());
        return R.success(dateDTO);
    }


}
