package com.github.zuihou.authority.controller.auth;


import cn.hutool.core.util.StrUtil;
import com.github.zuihou.authority.dto.auth.SystemApiSaveDTO;
import com.github.zuihou.authority.dto.auth.SystemApiScanSaveDTO;
import com.github.zuihou.authority.dto.auth.SystemApiUpdateDTO;
import com.github.zuihou.authority.entity.auth.SystemApi;
import com.github.zuihou.authority.service.auth.SystemApiService;
import com.github.zuihou.base.R;
import com.github.zuihou.base.controller.SuperCacheController;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.utils.BeanPlusUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
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
public class SystemApiController extends SuperCacheController<SystemApiService, Long, SystemApi, SystemApi, SystemApiSaveDTO, SystemApiUpdateDTO> {


    /**
     * 新增API接口
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "批量新增API接口", notes = "批量新增API接口不为空的字段")
    @PostMapping("/batch")
    @SysLog("批量新增API接口")
    public R<Boolean> batchSave(@RequestBody @Validated SystemApiScanSaveDTO data) {
        return success(baseService.batchSave(data));
    }

    @Override
    public R<SystemApi> handlerSave(SystemApiSaveDTO data) {
        SystemApi systemApi = BeanPlusUtil.toBean(data, SystemApi.class);
        systemApi.setIsPersist(false);
        if (StrUtil.isEmpty(systemApi.getCode())) {
            systemApi.setCode(DigestUtils.md5Hex(systemApi.getServiceId() + systemApi.getPath()));
        }

        baseService.save(systemApi);
        return success(systemApi);
    }

}
