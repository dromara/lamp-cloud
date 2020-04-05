package com.github.zuihou.authority.controller.auth;


import cn.hutool.core.util.RandomUtil;
import com.github.zuihou.authority.dto.auth.ApplicationPageDTO;
import com.github.zuihou.authority.dto.auth.ApplicationSaveDTO;
import com.github.zuihou.authority.dto.auth.ApplicationUpdateDTO;
import com.github.zuihou.authority.entity.auth.Application;
import com.github.zuihou.authority.service.auth.ApplicationService;
import com.github.zuihou.base.R;
import com.github.zuihou.base.controller.SuperController;
import com.github.zuihou.security.annotation.PreAuth;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 应用
 * </p>
 *
 * @author zuihou
 * @date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/application")
@Api(value = "Application", tags = "应用")
@PreAuth(replace = "application:")
public class ApplicationController extends SuperController<ApplicationService, Long, Application, ApplicationPageDTO, ApplicationSaveDTO, ApplicationUpdateDTO> {

    @Override
    public R<Application> handlerSave(ApplicationSaveDTO applicationSaveDTO) {
        applicationSaveDTO.setClientId(RandomUtil.randomString(24));
        applicationSaveDTO.setClientSecret(RandomUtil.randomString(32));
        return super.handlerSave(applicationSaveDTO);
    }

}
