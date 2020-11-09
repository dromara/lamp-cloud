package com.github.zuihou.order.controller.databases;

import com.github.zuihou.base.R;
import com.github.zuihou.order.entity.Order;
import com.github.zuihou.order.service.OrderService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zuihou
 * @date 2020/11/9 6:19 下午
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/localTransactional")
@Api(value = "localTransactional", tags = "localTransactional")
public class LocalTransactionalController {

    private final OrderService orderService;

    @PostMapping("/tran1")
    public R save1(@RequestBody Order data) {
        orderService.save1(data);
        return R.success(true);
    }

    @PostMapping("/tran2")
    public R save2(@RequestBody Order data) {
        orderService.save2(data);
        return R.success(true);
    }
}
