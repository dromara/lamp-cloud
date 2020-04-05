package com.github.zuihou.authority.controller.common;


import com.github.zuihou.authority.dto.common.ParameterPageDTO;
import com.github.zuihou.authority.dto.common.ParameterSaveDTO;
import com.github.zuihou.authority.dto.common.ParameterUpdateDTO;
import com.github.zuihou.authority.entity.common.Parameter;
import com.github.zuihou.authority.service.common.ParameterService;
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
 * 参数配置
 * </p>
 *
 * @author zuihou
 * @date 2020-02-05
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/parameter")
@Api(value = "Parameter", tags = "参数配置")
@PreAuth(replace = "parameter:")
public class ParameterController extends SuperController<ParameterService, Long, Parameter, ParameterPageDTO, ParameterSaveDTO, ParameterUpdateDTO> {

}
