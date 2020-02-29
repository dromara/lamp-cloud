package com.github.zuihou.order.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.order.dto.OrderSaveDTO;
import com.github.zuihou.order.dto.OrderUpdateDTO;
import com.github.zuihou.order.entity.Order;
import com.github.zuihou.order.service.OrderService;
import com.github.zuihou.utils.BeanPlusUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    /**
     * 分页查询订单
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询订单", notes = "分页查询订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询订单")
    public R<IPage<Order>> page(Order data) {
        IPage<Order> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<Order> query = Wraps.lbQ(data);
        orderService.page(page, query);
        return success(page);
    }

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
        return success(orderService.getById(id));
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
        return success(order);
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
        return success(order);
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
        return success(true);
    }

}
