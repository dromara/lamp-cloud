package com.github.zuihou.order.controller;


import com.github.zuihou.base.controller.SuperCacheController;
import com.github.zuihou.order.dto.OrderPageDTO;
import com.github.zuihou.order.dto.OrderSaveDTO;
import com.github.zuihou.order.dto.OrderUpdateDTO;
import com.github.zuihou.order.entity.Order;
import com.github.zuihou.order.service.OrderService;
import com.github.zuihou.security.annotation.PreAuth;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 订单
 * </p>
 *
 * @author zuihou
 * @date 2019-08-13
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/order")
@Api(value = "Order", tags = "订单")
@PreAuth(replace = "order:", enabled = false)
public class OrderController extends SuperCacheController<OrderService, Long, Order, OrderPageDTO, OrderSaveDTO, OrderUpdateDTO> {

}
