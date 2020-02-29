package com.github.zuihou.demo.controller.test;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * 表单验证测试类
 * 必须在类上加 @Validated
 *
 * @author zuihou
 * @date 2019/07/03
 */
@Slf4j
@RestController
@RequestMapping("/valid2")
@Api(value = "Valid", tags = "验证2")
@Validated
public class ParamValidateController {
    /**
     * 不能
     *
     * @param code
     * @return
     */
    @GetMapping("/requestParam/get1")
    @Validated
    public String paramGet1(@NotEmpty(message = "不能为空")
                            @RequestParam(value = "code", required = false) String code) {
        return "不能验证";
    }

    /**
     * 不能验证
     *
     * @param code
     * @return
     */
    @GetMapping("/requestParam/get2")
    public String paramGet2(@NotEmpty(message = "不能为空")
                            @RequestParam(value = "code", required = false) String code) {
        return "不能验证";
    }


    @GetMapping("/requestParam/get3")
    public String paramGet3(@Validated @NotEmpty(message = "code 不能为空")
                            @RequestParam(value = "code", required = false) String code,
                            @NotEmpty(message = "name 不能空")
                            @RequestParam(value = "name", required = false) String name
    ) {
        return "不能验证";
    }

    /**
     * 不能
     *
     * @param code
     * @return
     */
    @GetMapping("/requestParam/get4")
    @Valid
    public String paramGet4(@NotEmpty(message = "不能为空")
                            @RequestParam(value = "code", required = false) String code) {
        return "不能验证";
    }

    /**
     * 不能验证
     *
     * @param code
     * @return
     */
    @GetMapping("/requestParam/get5")
    public String paramGet5(@Valid @NotEmpty(message = "不能为空")
                            @RequestParam(value = "code", required = false) String code) {
        return "不能验证";
    }


    @GetMapping("/requestParam/get6")
    public String paramGet6(@Valid @NotEmpty(message = "code 不能为空")
                            @RequestParam(value = "code", required = false) String code,
                            @NotEmpty(message = "name 不能空")
                            @RequestParam(value = "name", required = false) String name
    ) {
        return "不能验证";
    }


}
