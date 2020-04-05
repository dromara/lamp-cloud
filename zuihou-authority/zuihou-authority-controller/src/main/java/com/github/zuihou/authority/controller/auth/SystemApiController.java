package com.github.zuihou.authority.controller.auth;


import cn.hutool.core.util.StrUtil;
import com.github.zuihou.authority.dto.auth.SystemApiSaveDTO;
import com.github.zuihou.authority.dto.auth.SystemApiUpdateDTO;
import com.github.zuihou.authority.entity.auth.SystemApi;
import com.github.zuihou.authority.service.auth.SystemApiService;
import com.github.zuihou.base.R;
import com.github.zuihou.base.controller.SuperCacheController;
import com.github.zuihou.security.annotation.PreAuth;
import com.github.zuihou.utils.BeanPlusUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.validation.annotation.Validated;
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
@PreAuth(replace = "systemApi:")
public class SystemApiController extends SuperCacheController<SystemApiService, Long, SystemApi, SystemApi, SystemApiSaveDTO, SystemApiUpdateDTO> {

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
