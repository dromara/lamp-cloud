package com.github.zuihou.authority.controller.test;

import com.github.zuihou.authority.controller.test.model.EnumDTO;
import com.github.zuihou.authority.controller.test.model.Orddder;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.log.entity.OptLogDTO;
import com.github.zuihou.user.annotation.LoginUser;
import com.github.zuihou.user.model.SysUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperationSupport;
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
 * 注入登录用户信息 测试类
 *
 * @author zuihou
 * @date 2019/07/10
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/test")
@Api(value = "Test", tags = "测试类")
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

    @PostMapping("post3")
    public R<EnumDTO> post3(@RequestBody EnumDTO data) {
        return success(data);
    }

    @PostMapping("post4")
    public R<EnumDTO> post4(@RequestBody EnumDTO data) {
        int a = 1 / 0;
        return success(data);
    }

    @PostMapping("post5")
    public R<EnumDTO> post5(@RequestBody EnumDTO data) throws Exception {
        new EnumDTO().testEx();
        return success(data);
    }

    @ApiOperationSupport(ignoreParameters = {"id", "list.id"})
    @PostMapping("post6")
    public R<EnumDTO> post6(@RequestBody EnumDTO data) throws Exception {

        return success(data);
    }


    @ApiOperationSupport(ignoreParameters = {"id", "producttt.id"})
    @PostMapping("post7")
    public R<Orddder> post7(@RequestBody Orddder data) throws Exception {

        return success(data);
    }


}
