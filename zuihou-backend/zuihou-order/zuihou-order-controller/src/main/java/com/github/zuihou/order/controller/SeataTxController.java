package com.github.zuihou.order.controller;

import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.order.api.DemoTestApi;
import com.github.zuihou.order.entity.Order;
import com.github.zuihou.order.service.OrderService;

import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分布式事务测试类
 *
 * @author zuihou
 * @date 2019/08/12
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/seata")
@Api(value = "SeataTxController", tags = "分布式事务测试类")
public class SeataTxController extends BaseController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private DemoTestApi seataTestApi;


    @PostMapping("/save")
    @GlobalTransactional
    public R<Org> save(@RequestBody Org data) {
        log.info("data={}", data);
        seataTestApi.save(data);
        Order entity = Order.builder()
                .code(data.getName())
                .name(data.getName())
                .build();
        orderService.save(entity);
        return success(data);
    }

    @PostMapping("/save2")
    public R<Org> save2(@RequestBody Org data) {
        log.info("data={}", data);
        seataTestApi.save(data);
        int i = 1 / 0;
        Order entity = Order.builder()
                .code(data.getName())
                .name(data.getName())
                .build();
        orderService.save(entity);
        return success(data);
    }

    @PostMapping("/saveEx")
    @GlobalTransactional
    public R<Org> saveEx(@RequestBody Org data) {
        log.info("data={}", data);
        seataTestApi.save(data);

        int i = 1 / 0;
        Order entity = Order.builder()
                .code(data.getName())
                .name(data.getName())
                .build();
        orderService.save(entity);

        return success(data);
    }

    @PostMapping("/saveEx2")
    @GlobalTransactional
    public R<Org> saveEx2(@RequestBody Org data) {
        log.info("data={}", data);
        seataTestApi.saveEx(data);

        Order entity = Order.builder()
                .code(data.getName())
                .name(data.getName())
                .build();
        orderService.save(entity);
        return success(data);
    }
}
