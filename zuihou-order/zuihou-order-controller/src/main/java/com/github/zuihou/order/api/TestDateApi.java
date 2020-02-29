package com.github.zuihou.order.api;

import com.github.zuihou.authority.dto.test.DateDTO;
import com.github.zuihou.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * 测试日期类型API接口
 *
 * @author zuihou
 * @date 2019/07/24
 */
@FeignClient(name = "${zuihou.feign.demo-server:zuihou-demo-server}", path = "/date")
public interface TestDateApi {
    /**
     * 测试
     *
     * @param data
     * @return
     */
    @PostMapping("/post1")
    R<DateDTO> bodyPos1(@RequestBody DateDTO data);

    /**
     * 测试
     *
     * @param data
     * @return
     */
    @GetMapping("/get1")
    R<DateDTO> get(DateDTO data);

    /**
     * 测试
     *
     * @param date
     * @param dt
     * @param d
     * @param t
     * @return
     */
    @GetMapping("/get2")
    R<DateDTO> get2(@RequestParam(required = false, value = "date") Date date,
                    @RequestParam(required = false, value = "dt") LocalDateTime dt,
                    @RequestParam(required = false, value = "d") LocalDate d,
                    @RequestParam(required = false, value = "t") LocalTime t);
}
