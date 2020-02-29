package com.github.zuihou.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zuihou.order.entity.Order;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 订单
 * </p>
 *
 * @author zuihou
 * @date 2019-08-13
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {

}
