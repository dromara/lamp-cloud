package com.tangyh.lamp.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tangyh.basic.annotation.security.PreAuth;
import com.tangyh.basic.base.controller.SuperController;
import com.tangyh.basic.echo.core.EchoService;
import com.tangyh.lamp.demo.dto.ProductPageQuery;
import com.tangyh.lamp.demo.dto.ProductSaveDTO;
import com.tangyh.lamp.demo.dto.ProductUpdateDTO;
import com.tangyh.lamp.demo.entity.Product;
import com.tangyh.lamp.demo.service.ProductService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/product")
@Api(value = "ProductController", tags = "商品")
@PreAuth(replace = "demo:product:", enabled = false)
@RequiredArgsConstructor
public class ProductController extends SuperController<ProductService, Long, Product, ProductPageQuery, ProductSaveDTO, ProductUpdateDTO> {
    private final EchoService echoService;

    @Override
    public void handlerResult(IPage<Product> page) {
        echoService.action(page);
    }
}
