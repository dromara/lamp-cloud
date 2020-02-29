package com.github.zuihou.demo.controller.test;

import com.github.zuihou.authority.dto.auth.ApplicationUpdateDTO;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.demo.controller.test.model.ValidatorDTO;
import com.github.zuihou.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * 表单验证测试类
 *
 * @author zuihou
 * @date 2019/07/03
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/valid")
@Api(value = "Valid", tags = "验证")
public class HibernateValidateController {

    /**
     * ok
     * 普通对象验证， @Validated 注解需要写在参数上
     *
     * @param data
     * @return
     */
    @GetMapping("/obj/get2")
    public String objGet2(@Validated(SuperEntity.Update.class) @Valid ApplicationUpdateDTO data) {
        return "验证Update组 成功";
    }

    /**
     * ok
     * 普通对象验证， @Validated 注解需要写在参数上
     *
     * @param data
     * @return
     */
    @GetMapping("/obj/get3")
    @SysLog("测试")
    public String objGet3(@Validated @Valid ApplicationUpdateDTO data) {
        return "验证默认组成功";
    }


    /**
     * 就算类上面有 @Validated 注解
     *
     * @param data
     * @return
     */
    @GetMapping("/obj/get4")
    @SysLog("测试")
    public String objGet4(ApplicationUpdateDTO data) {
        return "无法验证";
    }

    /**
     * 可以验证
     *
     * @param data
     * @return
     */
    @GetMapping("/obj/get5")
    public String objGet5(@Valid ApplicationUpdateDTO data) {
        return "可以验证";
    }

    /**
     * 可以验证
     *
     * @param data
     * @return
     */
    @GetMapping("/obj/get6")
    @Validated
    public String objGet6(@Valid ApplicationUpdateDTO data) {
        return "可以验证";
    }

    /**
     * 有 @RequestBody 注解的方法，  @Validated 注解需要写在方法上
     *
     * @param data
     * @return
     */
    @PostMapping("/requestBody/post")
    @Validated(SuperEntity.Update.class)
    public String bodyPost(@Valid @RequestBody ValidatorDTO data) {
        return "类上有 Validated，方法上有@Validated（Update）， 参数有 Valid";
    }


    @PostMapping("/requestBody/post3")
    public String bodyPost3(@Validated @Valid @RequestBody ValidatorDTO data) {
        return "类上有 Validated，方法上有@Validated， 参数有 Valid";
    }


    @PostMapping("/requestBody/post5")
    public String bodyPost5(@Valid @RequestBody ValidatorDTO data) {
        return "类上有 Validated，参数有 Valid";
    }

    @PostMapping("/requestBody/post6")
    public String bodyPost6(@RequestBody ValidatorDTO data) {
        return "类上有 Validated，方法和参数没有";
    }


    /**
     * ok
     * 方法上的@Validated注解，一般用来指定 验证组
     *
     * @param code
     * @return
     */
    @GetMapping("/requestParam/get")
    @Validated
    public String paramGet(@Length(max = 3)
                           @NotEmpty(message = "不能为空")
                           @RequestParam(value = "code", required = false) String code) {
        return "方法上的@Validated注解，一般用来指定 验证组";
    }

    /**
     * ok
     * 方法上没有 @Validated 注解，就用类上的 @Validated 注解，
     *
     * @param code
     * @return
     */
    @GetMapping("/requestParam/get2")
    public String paramGet2(@NotEmpty(message = "不能为空")
                            @RequestParam(value = "code", required = false) String code) {
        return "方法上没有 @Validated 注解，就用类上的 @Validated 注解";
    }
}
