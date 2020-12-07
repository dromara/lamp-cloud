package com.tangyh.lamp.authority.controller.common;


import com.tangyh.basic.annotation.security.PreAuth;
import com.tangyh.basic.base.controller.SuperController;
import com.tangyh.lamp.authority.dto.common.ParameterPageQuery;
import com.tangyh.lamp.authority.dto.common.ParameterSaveDTO;
import com.tangyh.lamp.authority.dto.common.ParameterUpdateDTO;
import com.tangyh.lamp.authority.entity.common.Parameter;
import com.tangyh.lamp.authority.service.common.ParameterService;
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
@PreAuth(replace = "authority:parameter:")
public class ParameterController extends SuperController<ParameterService, Long, Parameter, ParameterPageQuery, ParameterSaveDTO, ParameterUpdateDTO> {

}
