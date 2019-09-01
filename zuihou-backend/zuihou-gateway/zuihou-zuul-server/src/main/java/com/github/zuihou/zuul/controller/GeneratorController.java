package com.github.zuihou.zuul.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.github.zuihou.authority.api.AuthorityGeneralApi;
import com.github.zuihou.authority.api.DictionaryItemApi;
import com.github.zuihou.base.R;
import com.github.zuihou.common.constant.DictionaryCode;
import com.github.zuihou.file.api.FileGeneralApi;
import com.github.zuihou.msgs.api.MsgsGeneralApi;
import com.github.zuihou.utils.StrPool;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 常用Controller
 *
 * @author zuihou
 * @date 2019-06-21 18:22
 */
@Controller
public class GeneratorController {

    @Resource
    private AuthorityGeneralApi authorityGeneralApi;
    @Resource
    private FileGeneralApi fileGeneralApi;
    @Resource
    private MsgsGeneralApi msgsGeneralApi;
    @Resource
    private DictionaryItemApi dictionaryItemApi;

    /**
     * 解决swagger-bootstrap-ui的一个bug
     * <p>
     * 有个前提： nginx的端口要和访问端口一致，否则重定向出错
     *
     * @param service 服务
     * @param group   组
     * @param ext     uri 后缀
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取指定服务的swagger", notes = "获取当前系统所有数据字典和枚举")
    @GetMapping("${server.servlet.context-path}/{service}/v2/{ext}")
    public String apiDocs(@PathVariable String service, @PathVariable String ext, String group) throws Exception {
        if (group == null) {
            group = "default";
        }
        String newGroup = group;
        if (group.contains(StrPool.DASH)) {
            newGroup = StrUtil.subSuf(group, group.indexOf(StrPool.DASH) + 1);
        }
        return "redirect:/" + service + "/v2/" + ext + "?group=" + URLEncoder.encode(newGroup, "UTF-8");
    }

    /**
     * 获取当前系统所有数据字典和枚举
     *
     * @return
     */
    @ApiOperation(value = "获取当前系统所有数据字典和枚举", notes = "获取当前系统所有数据字典和枚举")
    @GetMapping("/dictionary/enums")
    @ResponseBody
    public R<Map<String, Map<String, String>>> dictionaryAndEnum() {
        Map<String, Map<String, String>> map = new HashMap<>(3);

        //权限服务的枚举
        R<Map<String, Map<String, String>>> authorityResult = authorityGeneralApi.enums();
        if (authorityResult.getIsSuccess()) {
            map.putAll(authorityResult.getData());
        }

        //文件服务的枚举
        R<Map<String, Map<String, String>>> fileResult = fileGeneralApi.enums();
        if (fileResult.getIsSuccess()) {
            map.putAll(fileResult.getData());
        }

        R<Map<String, Map<String, String>>> msgsResult = msgsGeneralApi.enums();
        if (msgsResult.getIsSuccess()) {
            map.putAll(msgsResult.getData());
        }

        //整个系统的数据字典
        R<Map<String, Map<String, String>>> itemMap = dictionaryItemApi.map(DictionaryCode.ALL);
        if (itemMap.getIsSuccess()) {
            map.putAll(itemMap.getData());
        }
        return R.success(map);
    }

}
