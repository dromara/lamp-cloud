package top.tangyh.lamp.authority.controller.auth;


import cn.hutool.core.util.RandomUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import top.tangyh.basic.annotation.security.PreAuth;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.controller.SuperCacheController;
import top.tangyh.lamp.authority.dto.auth.ApplicationPageQuery;
import top.tangyh.lamp.authority.dto.auth.ApplicationSaveDTO;
import top.tangyh.lamp.authority.dto.auth.ApplicationUpdateDTO;
import top.tangyh.lamp.authority.entity.auth.Application;
import top.tangyh.lamp.authority.service.auth.ApplicationService;
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
@ApiSupport(author = "zuihou")
@PreAuth(replace = "authority:application:")
public class ApplicationController extends SuperCacheController<ApplicationService, Long, Application, ApplicationPageQuery, ApplicationSaveDTO, ApplicationUpdateDTO> {

    @Override
    public R<Application> handlerSave(ApplicationSaveDTO applicationSaveDTO) {
        applicationSaveDTO.setClientId(RandomUtil.randomString(24));
        applicationSaveDTO.setClientSecret(RandomUtil.randomString(32));
        return super.handlerSave(applicationSaveDTO);
    }

}
