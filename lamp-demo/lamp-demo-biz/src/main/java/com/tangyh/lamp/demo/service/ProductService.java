package com.tangyh.lamp.demo.service;

import com.tangyh.basic.base.service.SuperService;
import com.tangyh.lamp.demo.entity.Product;

/**
 * <p>
 * 业务接口
 * 商品
 * </p>
 *
 * @author zuihou
 * @date 2019-08-13
 */
public interface ProductService extends SuperService<Product> {

    boolean saveEx(Product data);
}
