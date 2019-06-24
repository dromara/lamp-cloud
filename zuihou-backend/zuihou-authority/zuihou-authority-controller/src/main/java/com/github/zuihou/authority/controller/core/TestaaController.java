package com.github.zuihou.authority.controller.core;

import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.Result;
import com.github.zuihou.base.entity.SuperEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 地区表
 * </p>
 *
 * @author zuihou
 * @date 2019-06-23
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/test")
@Api(value = "Test", description = "地区表")
public class TestaaController extends BaseController {


    @ApiOperation(value = "测试一下", notes = "测试一下")
    @PostMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public Result<String> page() {

        return success("测试一下");
    }


}
