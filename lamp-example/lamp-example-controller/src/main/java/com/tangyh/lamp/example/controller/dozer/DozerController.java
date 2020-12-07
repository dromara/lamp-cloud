package com.tangyh.lamp.example.controller.dozer;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.tangyh.basic.base.R;
import com.tangyh.basic.dozer.DozerUtils;
import com.tangyh.basic.utils.BeanPlusUtil;
import com.tangyh.lamp.example.dto.OrderDozerTest;
import com.tangyh.lamp.example.dto.OrderDozerTest2;
import com.tangyh.lamp.example.dto.OrderPageDTO;
import com.tangyh.lamp.example.entity.Order;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 日期测试类
 *
 * @author zuihou
 * @date 2019/07/24
 */
@Slf4j
@RestController
@RequestMapping("/dozer")
@Api(value = "dozer", tags = "dozer")
@RequiredArgsConstructor
public class DozerController {

    private final DozerUtils dozer;

    @PostMapping("/map")
    public R map(@RequestBody Order data) {
        OrderPageDTO page = dozer.map(data, OrderPageDTO.class);

        OrderPageDTO bean = BeanUtil.toBean(data, OrderPageDTO.class);

        OrderPageDTO page2 = dozer.map2(data, OrderPageDTO.class);

        log.info("bean={}, page2={}, page={}", bean, page2, page);
        return R.success(page);
    }

    @PostMapping("/mapList")
    public R mapList(@RequestBody Order data) {
        List<Order> list = new ArrayList<>();
        list.add(data);
        List<OrderPageDTO> list2 = dozer.mapList(list, OrderPageDTO.class);

        List<OrderPageDTO> beanList = BeanPlusUtil.toBeanList(list, OrderPageDTO.class);
        log.info("beanList={}", beanList);
        return R.success(list2);
    }

    @PostMapping("/mapSet")
    public R mapSet(@RequestBody Order data) {
        Set<Order> list = new HashSet<>();
        list.add(data);
        Set<OrderPageDTO> list2 = dozer.mapSet(list, OrderPageDTO.class);

        return R.success(list2);
    }


    @PostMapping("/map2")
    public R map2(@RequestBody OrderDozerTest data) {

        OrderDozerTest2 page = dozer.map(data, OrderDozerTest2.class);


        Map<String, String> fieldMapping = new HashMap<>();
        fieldMapping.put("ldt", "ldt2");
        fieldMapping.put("code", "code2");
        OrderDozerTest2 page2 = BeanUtil.toBean(data, OrderDozerTest2.class, CopyOptions.create().setFieldMapping(fieldMapping));

        return R.success(page);
    }


    @PostMapping("/map3")
    public R map3(@RequestBody OrderDozerTest2 data) {
        OrderDozerTest page2 = dozer.map(data, OrderDozerTest.class);
        OrderDozerTest page = BeanUtil.toBean(data, OrderDozerTest.class);

        return R.success(page);
    }
}
