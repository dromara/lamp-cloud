package com.github.zuihou.authority.controller.auth;


import com.github.zuihou.authority.dto.auth.ApplicationSystemApiSaveDTO;
import com.github.zuihou.authority.dto.auth.ApplicationSystemApiUpdateDTO;
import com.github.zuihou.authority.entity.auth.ApplicationSystemApi;
import com.github.zuihou.authority.service.auth.ApplicationSystemApiService;
import com.github.zuihou.base.controller.SuperController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 应用接口
 * </p>
 *
 * @author zuihou
 * @date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/applicationSystemApi")
@Api(value = "ApplicationSystemApi", tags = "应用接口")
public class ApplicationSystemApiController extends SuperController<ApplicationSystemApiService, Long, ApplicationSystemApi, ApplicationSystemApi, ApplicationSystemApiSaveDTO, ApplicationSystemApiUpdateDTO> {

}
