package com.github.zuihou.order.controller;


import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.order.dto.OrderSaveDTO;
import com.github.zuihou.order.dto.OrderUpdateDTO;
import com.github.zuihou.order.entity.Order;
import com.github.zuihou.order.service.OrderService;
import com.github.zuihou.utils.BeanPlusUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     * 查询订单
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询订单", notes = "查询订单")
    @GetMapping("/{id}")
    @SysLog("查询订单")
    public R<Order> get(@PathVariable Long id) {
        return R.success(orderService.getById(id));
    }

    /**
     * 新增订单
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增订单", notes = "新增订单不为空的字段")
    @PostMapping
    @SysLog("新增订单")
    public R<Order> save(@RequestBody @Validated OrderSaveDTO data) {
        Order order = BeanPlusUtil.toBean(data, Order.class);
        orderService.save(order);
        return R.success(order);
    }

    /**
     * 修改订单
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改订单", notes = "修改订单不为空的字段")
    @PutMapping
    @SysLog("修改订单")
    public R<Order> update(@RequestBody @Validated(SuperEntity.Update.class) OrderUpdateDTO data) {
        Order order = BeanPlusUtil.toBean(data, Order.class);
        orderService.updateById(order);
        return R.success(order);
    }

    /**
     * 删除订单
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除订单", notes = "根据id物理删除订单")
    @DeleteMapping(value = "/{id}")
    @SysLog("删除订单")
    public R<Boolean> delete(@PathVariable Long id) {
        orderService.removeById(id);
        return R.success(true);
    }

}
