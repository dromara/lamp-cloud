package com.github.zuihou.demo.controller.test;

import com.github.zuihou.authority.dto.auth.ApplicationUpdateDTO;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.demo.controller.test.model.InnerDTO;
import com.github.zuihou.demo.controller.test.model.ValidatorDTO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 表单验证测试类
 * 测试后，总结出自己的规则：
 * 必须在参数上获取注解 @Validated
 *
 * @author zuihou
 * @date 2019/07/03
 */
@Slf4j
@RestController
@RequestMapping("/valid1")
@Api(value = "Valid", tags = "验证1")
public class ObjValidateController {

    /**
     * 可以验证
     *
     * @param data
     * @return
     */
    @GetMapping("/obj/get1")
    @Validated
    public String objGet1(@Valid ApplicationUpdateDTO data) {
        return "可以验证";
    }

    @GetMapping("/obj/get4")
    @Valid
    public String objGet4(@Validated ApplicationUpdateDTO data) {
        return "可以验证";
    }

    /**
     * 可以验证
     *
     * @param data
     * @return
     */
    @GetMapping("/obj/get2")
    public String objGet2(@Validated @Valid ApplicationUpdateDTO data) {
        return "可以验证";
    }

    /**
     * 可以验证
     *
     * @param data
     * @return
     */
    @GetMapping("/obj/get3")
    public String objGet3(@Validated InnerDTO data) {
        return "可以验证";
    }

    @GetMapping("/obj/get32")
    public String objGet32(@Validated ValidatorDTO data) {
        return "可以验证";
    }


    @GetMapping("/obj/get31")
    public String objGet31(@Validated(SuperEntity.Update.class) ApplicationUpdateDTO data) {
        return "可以验证";
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
     * 不能验证
     *
     * @param data
     * @return
     */
    @GetMapping("/obj/get7")
    @Validated
    public String objGet6(ApplicationUpdateDTO data) {
        return "不能验证";
    }


    /**
     * 不能验证
     *
     * @param data
     * @return
     */
    @GetMapping("/obj/get8")
    @Valid
    public String objGet8(ApplicationUpdateDTO data) {
        return "不能验证";
    }

}
