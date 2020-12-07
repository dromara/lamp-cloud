package com.tangyh.lamp.example.controller.databases;

import com.tangyh.basic.base.R;
import com.tangyh.lamp.demo.entity.Product;
import com.tangyh.lamp.example.api.DemoTestApi;
import com.tangyh.lamp.example.entity.Order;
import com.tangyh.lamp.example.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
public class SeataTxController {

    private final OrderService orderService;
    private final DemoTestApi seataTestApi;

    /**
     * 正常的提交事务
     *
     * @param data
     * @return
     */
    @PostMapping("/save")
    @GlobalTransactional
    public R<Product> saveCommitSuccess(@RequestBody Product data) {
        log.info("data={}", data);
        this.seataTestApi.save(data);
        Order entity = Order.builder()
                .code(data.getName() + "CODE")
                .name(data.getName())
                .build();
        this.orderService.save(entity);
        return R.success(data);
    }

    /**
     * 失败的回滚！  因为没有 @GlobalTransactional 注解
     *
     * @param data
     * @return
     */
    @PostMapping("/save/rollback/fail")
    public R<Product> saveRollbackFail(@RequestBody Product data) {
        log.info("data={}", data);
        this.seataTestApi.save(data);
        int a = 1 / 0;
        log.info("a=", a);
        Order entity = Order.builder()
                .code(data.getName() + "CODE")
                .name(data.getName())
                .build();
        this.orderService.save(entity);
        return R.success(data);
    }

    /**
     * 正常回滚, 是分布式事务回滚的效果
     *
     * @param
     * @return
     */
    @PostMapping("/save/rollback")
    @GlobalTransactional
    public Boolean placeOrderRollback() {
        Product data = Product.builder()
                .name("你的名字")
                .stock(123)
                .build();
        log.info("data={}", data);
        R<Product> save = this.seataTestApi.save(data);
        log.info("a={}", save);
        //在这里打断点可以看到 m_product 表的数据已经插入
        //但等执行完整个方法，发现 m_product 数据被删除

        Order entity = Order.builder()
                .code(data.getName() + "code")
                .name(data.getName())
                .build();
        this.orderService.save(entity);
        int a = 1 / 0;


        return true;
    }

    /**
     * 正常回滚， 是demo服务的本地事务回滚的效果
     *
     * @param data
     * @return
     */
    @PostMapping("/save/rollback2")
    @GlobalTransactional
    public R<Product> saveRollbackSuccess2(@RequestBody Product data) {
        log.info("data={}", data);
        this.seataTestApi.saveEx(data);

        Order entity = Order.builder()
                .code(data.getName() + "CODE")
                .name(data.getName())
                .build();
        this.orderService.save(entity);
        return R.success(data);
    }
}
