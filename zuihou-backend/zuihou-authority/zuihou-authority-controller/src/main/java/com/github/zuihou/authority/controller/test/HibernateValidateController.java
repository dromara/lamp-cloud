package com.github.zuihou.authority.controller.test;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.github.zuihou.authority.dto.auth.ApplicationUpdateDTO;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.log.annotation.SysLog;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a Description
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
     * TODO 这里达不到只验证 OnlyQuery 组的效果
     * 普通对象验证， @Validated 注解需要写在参数上
     *
     * @param data
     * @return
     */
    @GetMapping("/obj/get")
    public String objGet(@Validated(SuperEntity.OnlyQuery.class) @Valid ApplicationUpdateDTO data) {
        return "aa";
    }

    /**
     * ok
     * 普通对象验证， @Validated 注解需要写在参数上
     *
     * @param data
     * @return
     */
    @GetMapping("/obj/get2")
    public String objGet2(@Validated(SuperEntity.Update.class) @Valid ApplicationUpdateDTO data) {
        return "aa";
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
        return "aa";
    }


    /**
     * ok
     * 普通对象验证， @Validated 注解需要写在参数上
     *
     * @param data
     * @return
     */
    @GetMapping("/obj/get4")
    @SysLog("测试")
    public String objGet4(ApplicationUpdateDTO data) {
        return "aa";
    }

    /**
     * 自定义分组，且不继承default时，只有@RequestBody 才能生效
     *
     * @param data
     * @return
     */
    @PostMapping("/obj/post")
    @Validated(SuperEntity.OnlyQuery.class)
    public String bodyPost2(@Valid @RequestBody ApplicationUpdateDTO data) {
        return "aa";
    }

    /**
     * 有 @RequestBody 注解的方法，  @Validated 注解需要写在方法上
     *
     * @param data
     * @return
     */
    @PostMapping("/requestBody/post")
    @Validated(SuperEntity.Update.class)
    public String bodyPost(@Valid @RequestBody ApplicationUpdateDTO data) {
        return "aa";
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
    @SysLog("测试")
    public String paramGet(@Length(max = 3)
                           @NotEmpty(message = "不能为空")
                           @RequestParam(value = "code", required = false) String code) {
        return "aa";
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
        return "aa";
    }
}
