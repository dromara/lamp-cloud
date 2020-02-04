package com.github.zuihou.general.controller;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.StaticLog;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.entity.core.Station;
import com.github.zuihou.authority.enumeration.auth.ApplicationAppTypeEnum;
import com.github.zuihou.authority.enumeration.auth.AuthorizeType;
import com.github.zuihou.authority.enumeration.auth.Sex;
import com.github.zuihou.authority.enumeration.common.LogType;
import com.github.zuihou.authority.enumeration.defaults.TenantStatusEnum;
import com.github.zuihou.authority.enumeration.defaults.TenantTypeEnum;
import com.github.zuihou.authority.service.core.OrgService;
import com.github.zuihou.authority.service.core.StationService;
import com.github.zuihou.base.BaseEnum;
import com.github.zuihou.base.R;
import com.github.zuihou.common.enums.HttpMethod;
import com.github.zuihou.database.mybatis.auth.DataScopeType;
import com.github.zuihou.utils.MapHelper;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用 控制器
 *
 * @author zuihou
 * @date 2019/07/25
 */
@Slf4j
@RestController
@Api(value = "Common", tags = "通用Controller")
public class AuthorityGeneralController {

    @Autowired
    private OrgService orgService;
    @Autowired
    private StationService stationService;

    //                @Autowired
    private RabbitTemplate rabbitTemplate;

    @ApiOperation(value = "获取当前系统所有枚举", notes = "获取当前系统所有枚举")
    @GetMapping("/enums")
    public R<Map<String, Map<String, String>>> enums() {
        Map<String, Map<String, String>> map = new HashMap<>(7);
        map.put(HttpMethod.class.getSimpleName(), BaseEnum.getMap(HttpMethod.values()));
        map.put(DataScopeType.class.getSimpleName(), BaseEnum.getMap(DataScopeType.values()));
        map.put(LogType.class.getSimpleName(), BaseEnum.getMap(LogType.values()));
        map.put(AuthorizeType.class.getSimpleName(), BaseEnum.getMap(AuthorizeType.values()));
        map.put(Sex.class.getSimpleName(), BaseEnum.getMap(Sex.values()));
        map.put(TenantTypeEnum.class.getSimpleName(), BaseEnum.getMap(TenantTypeEnum.values()));
        map.put(TenantStatusEnum.class.getSimpleName(), BaseEnum.getMap(TenantStatusEnum.values()));
        map.put(ApplicationAppTypeEnum.class.getSimpleName(), BaseEnum.getMap(ApplicationAppTypeEnum.values()));
        return R.success(map);
    }


    /**
     * 查询所有组织
     *
     * @return 查询结果
     */
    @ApiOperation(value = "查询所有组织", notes = "查询所有组织")
    @GetMapping("/orgs")
    public R<Map<String, Map<Long, String>>> find() {
        Map<String, Map<Long, String>> map = new HashMap<>(2);
        List<Station> stationList = this.stationService.list();
        List<Org> orgList = this.orgService.list();
        ImmutableMap<Long, String> stationMap = MapHelper.uniqueIndex(stationList, Station::getId, Station::getName);
        ImmutableMap<Long, String> orgMap = MapHelper.uniqueIndex(orgList, Org::getId, Org::getName);
        map.put(Org.class.getSimpleName(), orgMap);
        map.put(Station.class.getSimpleName(), stationMap);
        return R.success(map);
    }


    @Value("${server.port}")
    private String port;

    private static final Log hutoolLog = LogFactory.get();

    /**
     * 测试网关熔断和超时，
     *
     * @param millis
     * @param request
     * @return
     */
    @GetMapping("/test")
    @SneakyThrows
    public R<Object> test(@RequestParam(value = "millis", defaultValue = "29") Long millis, HttpServletRequest request) {
        String clientIP = ServletUtil.getClientIP(request);

        log.info("clientIP={}", clientIP);
//        this.rabbitTemplate.convertAndSend("aaa", "123");
//        Thread.sleep(millis);

        hutoolLog.info("哈哈={}", clientIP);

        try {
            int a = 1 / 0;
        } catch (Exception e) {
//            log.error("错了{}, {}", "aaa" , "aaa", e);

            hutoolLog.error(e, "hutoolerror ={}", clientIP);
            System.out.println();
            String message = ExceptionUtil.getMessage(e);

            System.out.println();
        }

//        Console.log("");
        StaticLog.error("aa {} ", 123);
        return R.success(clientIP + "--port=" + this.port);
    }

//    @Autowired
//    private AttachmentApi attachmentApi;
//
//    @ApiOperation(value = "附件上传", notes = "附件上传")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "isSingle", value = "是否单文件", dataType = "boolean", paramType = "query"),
//            @ApiImplicitParam(name = "id", value = "文件id", dataType = "long", paramType = "query"),
//            @ApiImplicitParam(name = "bizId", value = "业务id", dataType = "long", paramType = "query"),
//            @ApiImplicitParam(name = "bizType", value = "业务类型", dataType = "long", paramType = "query"),
//            @ApiImplicitParam(name = "file", value = "附件", dataType = "MultipartFile", allowMultiple = true, required = true),
//    })
//    @PostMapping(value = "/upload")
//    @SysLog("上传附件")
//    public R<AttachmentDTO> upload(
//            @RequestParam(value = "file") MultipartFile file,
//            @RequestParam(value = "isSingle", required = false, defaultValue = "false") Boolean isSingle,
//            @RequestParam(value = "id", required = false) Long id,
//            @RequestParam(value = "bizId", required = false) String bizId,
//            @RequestParam(value = "bizType", required = false) String bizType) {
//        return attachmentApi.upload(file,isSingle, id, bizId, bizType);
//    }

}
