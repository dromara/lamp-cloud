package com.github.zuihou.order.controller.validator;

import com.github.zuihou.base.R;
import com.github.zuihou.order.entity.Order;
import com.github.zuihou.security.annotation.LoginUser;
import com.github.zuihou.security.model.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author zuihou
 * @date 2020年03月31日10:10:36
 */
@Slf4j
@RestController
@RequestMapping("/validator")
@AllArgsConstructor
@Api(value = "validator", tags = "validator")
@Validated
public class ValidatorTestController {

    @PostMapping(value = "/postJson")
    public R postJson(@ApiIgnore @LoginUser SysUser user, @Validated @RequestBody Order order) {
        log.info("order={}", order);
        return R.success(order);
    }


    @PostMapping(value = "/postFrom")
    public R postFrom(@Valid Order order) {
        log.info("user={}", order);
        return R.success(order);
    }


    @ApiOperation(value = "附件上传", notes = "附件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isSingle", value = "是否单文件", dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(name = "id", value = "文件id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "bizId", value = "业务id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "bizType", value = "业务类型", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "file", value = "附件", dataType = "MultipartFile", allowMultiple = true, required = true),
    })
    @PostMapping(value = "/upload")
    public R upload(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "isSingle", required = false, defaultValue = "false") Boolean isSingle,
            @Max(value = 10, message = "id 不能大于10") @RequestParam(value = "id", required = false) Long id,
            @NotNull(message = "bizId不能为空") @RequestParam(value = "bizId", required = false) String bizId,
            @RequestParam(value = "bizType", required = false) String bizType) {
        return R.success();
    }
}
