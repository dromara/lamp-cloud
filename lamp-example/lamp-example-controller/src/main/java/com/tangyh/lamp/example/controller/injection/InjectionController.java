package com.tangyh.lamp.example.controller.injection;

import com.tangyh.basic.base.R;
import com.tangyh.basic.echo.core.EchoService;
import com.tangyh.lamp.example.entity.Order;
import com.tangyh.lamp.example.service.OrderService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zuihou
 * @date 2020/6/19 上午8:48
 */
@Slf4j
@RestController
@RequestMapping("/injection")
@AllArgsConstructor
@Api(value = "injection", tags = "injection")
public class InjectionController {
    private final OrderService orderService;
    private final EchoService echoService;


    /**
     * education: 本地注入
     * org: 远程注入
     * nation: 远程注入
     *
     * @param data
     * @return
     */
    @PostMapping("/injection")
    public R injection(@RequestBody Order data) {
        List<Order> orders = orderService.find(data);
        echoService.action(orders, "education");
        return R.success(orders);
    }

    @PostMapping("/autoInjection")
    public R autoInjection(@RequestBody Order data) {
        List<Order> orders = orderService.findInjectionResult(data);
        return R.success(orders);
    }


}
