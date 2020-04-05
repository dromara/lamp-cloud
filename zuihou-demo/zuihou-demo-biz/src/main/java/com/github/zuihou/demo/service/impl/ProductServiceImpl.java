package com.github.zuihou.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.demo.dao.ProductMapper;
import com.github.zuihou.demo.entity.Product;
import com.github.zuihou.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 业务实现类
 * 商品
 * </p>
 *
 * @author zuihou
 * @date 2019-08-13
 */
@Slf4j
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Product entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveEx(Product data) {
        boolean bool = super.save(data);
        int a = 1 / 0;
        return bool;
    }
}
