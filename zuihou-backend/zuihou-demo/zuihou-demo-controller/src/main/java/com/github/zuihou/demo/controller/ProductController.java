package com.github.zuihou.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.demo.dto.ProductSaveDTO;
import com.github.zuihou.demo.dto.ProductUpdateDTO;
import com.github.zuihou.demo.entity.Product;
import com.github.zuihou.demo.service.ProductService;
import com.github.zuihou.log.annotation.SysLog;
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
 * 商品
 * </p>
 *
 * @author zuihou
 * @date 2019-08-13
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/product")
@Api(value = "Product", tags = "商品")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    /**
     * 分页查询商品
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询商品", notes = "分页查询商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询商品")
    public R<IPage<Product>> page(Product data) {
        IPage<Product> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<Product> query = Wraps.lbQ(data);
        productService.page(page, query);
        return success(page);
    }

    /**
     * 查询商品
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询商品", notes = "查询商品")
    @GetMapping("/{id}")
    @SysLog("查询商品")
    public R<Product> get(@PathVariable Long id) {
        return success(productService.getById(id));
    }

    /**
     * 新增商品
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增商品", notes = "新增商品不为空的字段")
    @PostMapping
    @SysLog("新增商品")
    public R<Product> save(@RequestBody @Validated ProductSaveDTO data) {
        Product product = BeanPlusUtil.toBean(data, Product.class);
        productService.save(product);
        return success(product);
    }

    /**
     * 修改商品
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改商品", notes = "修改商品不为空的字段")
    @PutMapping
    @SysLog("修改商品")
    public R<Product> update(@RequestBody @Validated(SuperEntity.Update.class) ProductUpdateDTO data) {
        Product product = BeanPlusUtil.toBean(data, Product.class);
        productService.updateById(product);
        return success(product);
    }

    /**
     * 删除商品
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除商品", notes = "根据id物理删除商品")
    @DeleteMapping(value = "/{id}")
    @SysLog("删除商品")
    public R<Boolean> delete(@PathVariable Long id) {
        productService.removeById(id);
        return success(true);
    }

}
