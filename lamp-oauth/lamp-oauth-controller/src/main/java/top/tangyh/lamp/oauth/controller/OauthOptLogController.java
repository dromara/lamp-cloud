package top.tangyh.lamp.oauth.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Hidden;
import top.tangyh.basic.base.R;
import top.tangyh.basic.model.log.OptLogDTO;
import top.tangyh.basic.utils.BeanPlusUtil;
import top.tangyh.lamp.authority.service.common.OptLogService;

/**
 * <p>
 * 前端控制器
 * 系统日志
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/optLog")
@Tag(name = "系统日志")
@AllArgsConstructor
@Hidden
public class OauthOptLogController {

    private final OptLogService optLogService;

    /**
     * 保存系统日志
     *
     * @param data 保存对象
     * @return 保存结果
     */
    @PostMapping
    @Operation(summary = "保存系统日志", description = "保存系统日志不为空的字段")
    public R<OptLogDTO> save(@RequestBody OptLogDTO data) {
        optLogService.save(data);
        return R.success(BeanPlusUtil.toBean(data, OptLogDTO.class));
    }

}
