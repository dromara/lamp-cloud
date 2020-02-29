package com.github.zuihou.demo.controller.test;

import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.demo.controller.test.model.InnerDTO;
import com.github.zuihou.demo.controller.test.model.ValidatorDTO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 测试后，总结出自己的规则：
 * 必须在参数上获取注解 @Validated
 *
 * @author zuihou
 * @date 2019/07/03
 */
@Slf4j
@RestController
@RequestMapping("/valid3")
@Api(value = "Valid", tags = "验证3")
public class BodyValidateController {

    @PostMapping("/post1")
    public String bodyPos1(@Validated @Valid @RequestBody ValidatorDTO data) {
        return "类上没有 Validated，参数上有@Validated， 参数有 Valid  ok ";
    }

    @PostMapping("/post2")
    @Validated
    public String bodyPost2(@Valid @RequestBody ValidatorDTO data) {
        return "类上没有 Validated，参数上有@Validated， 参数有 Valid   ok ";
    }

    @PostMapping("/post3")
    @Valid
    public String bodyPost3(@Validated @RequestBody ValidatorDTO data) {
        return "类上和方法没有 Validated，参数上有@Validated， 参数没有 Valid ok";
    }

    @PostMapping("/post4")
    @Validated
    public String bodyPost4(@RequestBody ValidatorDTO data) {
        return "类上没有 Validated，方法上有@Validated， 参数有 Valid  不行";
    }

    @PostMapping("/post5")
    @Valid
    public String bodyPost5(@RequestBody ValidatorDTO data) {
        return "类上没有 Validated，方法上有@Validated， 参数有 Valid  不行" + data.toString();
    }

    @PostMapping("/post6")
    public String bodyPost6(@Validated @RequestBody ValidatorDTO data) {
        return "类上没有 Validated，方法上有@Validated， 可以";
    }

    @PostMapping("/post62")
    public String bodyPost62(@Validated @RequestBody InnerDTO data) {
        return "类上没有 Validated，方法上有@Validated， 参数有 Valid  不行";
    }

    @PostMapping("/post61")
    public String bodyPost61(@Validated(SuperEntity.Update.class) @RequestBody ValidatorDTO data) {
        return "类上没有 Validated，方法上有@Validated， 参数有 Valid  不行";
    }

    @PostMapping("/post7")
    public String bodyPost7(@Valid @RequestBody ValidatorDTO data) {
        return "类上没有 Validated，方法上有@Validated， 参数有 Valid  不行";
    }


}
