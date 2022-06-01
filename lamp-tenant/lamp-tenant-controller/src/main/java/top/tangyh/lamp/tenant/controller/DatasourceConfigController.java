package top.tangyh.lamp.tenant.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.log.SysLog;
import top.tangyh.basic.annotation.security.PreAuth;
import top.tangyh.basic.base.controller.SuperController;
import top.tangyh.lamp.tenant.dto.DatasourceConfigPageQuery;
import top.tangyh.lamp.tenant.dto.DatasourceConfigSaveDTO;
import top.tangyh.lamp.tenant.dto.DatasourceConfigUpdateDTO;
import top.tangyh.lamp.tenant.entity.DatasourceConfig;
import top.tangyh.lamp.tenant.service.DatasourceConfigService;


/**
 * <p>
 * 前端控制器
 * 数据源
 * </p>
 *
 * @author zuihou
 * @date 2020-08-21
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/datasourceConfig")
@Api(value = "DatasourceConfig", tags = "数据源")
@PreAuth(enabled = false)
@SysLog(enabled = false)
public class DatasourceConfigController extends SuperController<DatasourceConfigService, Long, DatasourceConfig, DatasourceConfigPageQuery, DatasourceConfigSaveDTO, DatasourceConfigUpdateDTO> {

}
