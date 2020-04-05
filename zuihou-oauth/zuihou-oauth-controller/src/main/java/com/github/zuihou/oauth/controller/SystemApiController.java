package com.github.zuihou.oauth.controller;


import com.github.zuihou.authority.dto.auth.SystemApiScanSaveDTO;
import com.github.zuihou.authority.service.auth.SystemApiService;
import com.github.zuihou.base.R;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.security.annotation.PreAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * API接口
 * </p>
 *
 * @author zuihou
 * @date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/systemApi")
@Api(value = "SystemApi", tags = "API接口")
public class SystemApiController {

    @Autowired
    private SystemApiService systemApiService;

    /**
     * 新增API接口
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "批量新增API接口", notes = "批量新增API接口不为空的字段")
    @PostMapping("/batch")
    @SysLog("批量新增API接口")
    @PreAuth
    public R<Boolean> batchSave(@RequestBody @Validated SystemApiScanSaveDTO data) {
        return R.success(systemApiService.batchSave(data));
    }


}
