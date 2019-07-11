package com.github.zuihou.authority.controller.test;

import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.common.annotation.LoginUser;
import com.github.zuihou.common.model.SysUser;
import com.github.zuihou.log.entity.OptLogDTO;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2019/07/10
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/test")
@Api(value = "Test", description = "测试类")
public class TestController extends BaseController {

    @GetMapping("/{id}")
    public R<String> get(@PathVariable Long id, @ApiIgnore @LoginUser(isFull = true) SysUser user) {
        return success("Id");
    }

    @GetMapping("/get")
    public R<String> get2(@RequestParam("id") Long id, @ApiIgnore @LoginUser SysUser user) {
        return success("Id");
    }

    @PostMapping
    public R<OptLogDTO> save(@RequestBody OptLogDTO data) {
        return success(data);
    }

    @PostMapping("post2")
    public R<OptLogDTO> post2(@RequestBody OptLogDTO data, @ApiIgnore @LoginUser(isOrg = true, isStation = true) SysUser user) {
        return success(data);
    }


    @GetMapping("get3")
    public R<OptLogDTO> get3(OptLogDTO data, @ApiIgnore @LoginUser(isOrg = true, isStation = true) SysUser user) {
        return success(data);
    }
}
