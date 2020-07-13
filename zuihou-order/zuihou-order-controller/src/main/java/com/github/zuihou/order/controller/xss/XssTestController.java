package com.github.zuihou.order.controller.xss;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author zuihou
 * @date 2020年03月31日10:10:36
 */
@Slf4j
@RestController
@RequestMapping("/xss")
@AllArgsConstructor
@Api(value = "xss", tags = "xss")
public class XssTestController {

    @PostMapping(value = "/postJson")
    public R postJson(@ApiIgnore @LoginUser SysUser user, @RequestBody Order order) {
        log.info("order={}", order);
        return R.success(order);
    }

    /**
     * 该方法始终会被Xss-stater过滤
     *
     * @param user
     * @param order
     * @return
     */
    @PostMapping(value = "/noxss/postJson")
    public R noxssJson(@ApiIgnore @LoginUser SysUser user, @RequestBody Order order) {
        log.info("order={}", order);
        return R.success(order);
    }


    @PostMapping(value = "/noxss/postFrom")
    public R noxssForm(Order order) {
        log.info("user={}", order);
        return R.success(order);
    }

    @PostMapping(value = "/postFrom")
    public R postFrom(Order order) {
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
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "bizId", required = false) String bizId,
            @RequestParam(value = "bizType", required = false) String bizType,
            String noxss

    ) {
        return R.success(bizType);
    }
}
