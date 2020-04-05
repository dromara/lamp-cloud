package com.github.zuihou.authority.service.defaults.strategy;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.github.zuihou.authority.entity.auth.*;
import com.github.zuihou.authority.entity.common.Dictionary;
import com.github.zuihou.authority.entity.common.DictionaryItem;
import com.github.zuihou.authority.entity.common.Parameter;
import com.github.zuihou.authority.enumeration.auth.ApplicationAppTypeEnum;
import com.github.zuihou.authority.enumeration.auth.AuthorizeType;
import com.github.zuihou.authority.service.auth.*;
import com.github.zuihou.authority.service.common.DictionaryItemService;
import com.github.zuihou.authority.service.common.DictionaryService;
import com.github.zuihou.authority.service.common.ParameterService;
import com.github.zuihou.authority.service.defaults.InitSystemStrategy;
import com.github.zuihou.common.constant.ParameterKey;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.database.mybatis.auth.DataScopeType;
import com.github.zuihou.database.properties.DatabaseProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化规则:
 * 手动创建数据
 *
 * @author zuihou
 * @date 2020年04月05日13:14:28
 */
@Service("COLUMN")
@Slf4j
public class ColumnInitSystemStrategy implements InitSystemStrategy {
    private static final String ORG = "org";
    private static final String STATION = "station";
    private static final String USER = "user";
    private static final String MENU = "menu";
    private static final String RESOURCE = "resource";
    private static final String ROLE = "role";
    private static final String DICT = "dict";
    private static final String AREA = "area";
    private static final String PARAMETER = "parameter";
    private static final String APPLICATION = "application";
    private static final String SYSTEM_API = "systemApi";
    private static final String DB = "db";
    private static final String OPT_LOG = "optLog";
    private static final String LOGIN_LOG = "loginLog";
    private static final String SMS_MANAGE = "sms:manage";
    private static final String SMS_TEMPLATE = "sms:template";
    private static final String MSGS = "msgs";
    private static final String FILE = "file";
    @Autowired
    private MenuService menuService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleAuthorityService roleAuthorityService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private DictionaryItemService dictionaryItemService;
    @Autowired
    private ParameterService parameterService;
    @Autowired
    private DatabaseProperties databaseProperties;

    /**
     * 我*，这种方式太脑残了，但想不出更好的方式初始化数据，希望各位大神有好的初始化方法记得跟我说声！！！
     * 写这段代码写得想去si ~~~
     * <p>
     * 不能用 SCHEMA 模式的初始化脚本方法： 因为id 会重复，租户编码会重复！
     *
     * @param tenant 待初始化租户编码
     * @return
     */
    @Override
    public boolean init(String tenant) {
        // 初始化数据
        //1, 生成并关联 ID TENANT
        DatabaseProperties.Id id = databaseProperties.getId();
        Snowflake snowflake = IdUtil.getSnowflake(id.getWorkerId(), id.getDataCenterId());

        BaseContextHandler.setTenant(tenant);

        // 菜单 资源 角色 角色_资源 字典 参数
        List<Menu> menuList = new ArrayList<>();
        Map<String, Long> menuMap = new HashMap<>();
        boolean menuFlag = initMenu(snowflake, menuList, menuMap);

        List<Resource> resourceList = new ArrayList<>();
        boolean resourceFlag = initResource(resourceList, menuMap);

        // 角色
        Long roleId = snowflake.nextId();
        boolean roleFlag = initRole(roleId);

        // 资源权限
        boolean roleAuthorityFlag = initRoleAuthority(menuList, resourceList, roleId);

        // 字典
        initDict();

        //参数
        initParameter();

        initApplication();

        return menuFlag && resourceFlag && roleFlag && roleAuthorityFlag;
    }

    private boolean initApplication() {
        List<Application> list = new ArrayList<>();
        list.add(Application.builder().clientId("zuihou_ui").clientSecret("zuihou_ui_secret").website("http://tangyh.top:10000/zuihou-ui/").name("SaaS微服务管理后台").appType(ApplicationAppTypeEnum.PC).status(true).build());
        list.add(Application.builder().clientId("zuihou_admin_ui").clientSecret("zuihou_admin_ui_secret").website("http://tangyh.top:180/zuihou-admin-ui/").name("SaaS微服务管理后台").appType(ApplicationAppTypeEnum.PC).status(true).build());
        return applicationService.saveBatch(list);
    }

    private boolean initParameter() {
        List<Parameter> list = new ArrayList<>();
        list.add(Parameter.builder().key(ParameterKey.LOGIN_POLICY).name("登录策略").value(ParameterKey.LoginPolicy.MANY.name()).describe("ONLY_ONE:一个用户只能登录一次; MANY:用户可以任意登录; ONLY_ONE_CLIENT:一个用户在一个应用只能登录一次").status(true).readonly(true).build());
        return parameterService.saveBatch(list);
    }

    private boolean initRoleAuthority(List<Menu> menuList, List<Resource> resourceList, Long roleId) {
        List<RoleAuthority> roleAuthorityList = new ArrayList<>();
        menuList.forEach(item -> {
            roleAuthorityList.add(RoleAuthority.builder().authorityType(AuthorizeType.MENU).authorityId(item.getId()).roleId(roleId).build());
        });
        resourceList.forEach(item -> {
            roleAuthorityList.add(RoleAuthority.builder().authorityType(AuthorizeType.RESOURCE).authorityId(item.getId()).roleId(roleId).build());
        });
        return roleAuthorityService.saveBatch(roleAuthorityList);
    }

    private boolean initRole(Long roleId) {
        Role role = Role.builder().id(roleId).name("平台管理员").code("PT_ADMIN").describe("平台内置管理员").dsType(DataScopeType.ALL).readonly(true).build();
        return roleService.save(role);
    }

    private boolean initResource(List<Resource> resourceList, Map<String, Long> menuMap) {
        Long orgId = menuMap.get(ORG);
        resourceList.add(Resource.builder().code("org:add").name("新增").menuId(orgId).build());
        resourceList.add(Resource.builder().code("org:delete").name("删除").menuId(orgId).build());
        resourceList.add(Resource.builder().code("org:export").name("导出").menuId(orgId).build());
        resourceList.add(Resource.builder().code("org:import").name("导入").menuId(orgId).build());
        resourceList.add(Resource.builder().code("org:update").name("修改").menuId(orgId).build());
        resourceList.add(Resource.builder().code("org:view").name("查看").menuId(orgId).build());

        Long stationId = menuMap.get(STATION);
        resourceList.add(Resource.builder().code("station:add").name("新增").menuId(stationId).build());
        resourceList.add(Resource.builder().code("station:delete").name("删除").menuId(stationId).build());
        resourceList.add(Resource.builder().code("station:export").name("导出").menuId(stationId).build());
        resourceList.add(Resource.builder().code("station:import").name("导入").menuId(stationId).build());
        resourceList.add(Resource.builder().code("station:update").name("修改").menuId(stationId).build());
        resourceList.add(Resource.builder().code("station:view").name("查看").menuId(stationId).build());

        Long userId = menuMap.get(USER);
        resourceList.add(Resource.builder().code("user:add").name("新增").menuId(userId).build());
        resourceList.add(Resource.builder().code("user:delete").name("删除").menuId(userId).build());
        resourceList.add(Resource.builder().code("user:export").name("导出").menuId(userId).build());
        resourceList.add(Resource.builder().code("user:import").name("导入").menuId(userId).build());
        resourceList.add(Resource.builder().code("user:update").name("修改").menuId(userId).build());
        resourceList.add(Resource.builder().code("user:view").name("查看").menuId(userId).build());

        Long menuId = menuMap.get(MENU);
        resourceList.add(Resource.builder().code("menu:add").name("新增").menuId(menuId).build());
        resourceList.add(Resource.builder().code("menu:delete").name("删除").menuId(menuId).build());
        resourceList.add(Resource.builder().code("menu:export").name("导出").menuId(menuId).build());
        resourceList.add(Resource.builder().code("menu:import").name("导入").menuId(menuId).build());
        resourceList.add(Resource.builder().code("menu:update").name("修改").menuId(menuId).build());
        resourceList.add(Resource.builder().code("menu:view").name("查看").menuId(menuId).build());
        resourceList.add(Resource.builder().code("resource:add").name("添加").menuId(menuId).build());
        resourceList.add(Resource.builder().code("resource:update").name("修改").menuId(menuId).build());
        resourceList.add(Resource.builder().code("resource:delete").name("删除").menuId(menuId).build());
        resourceList.add(Resource.builder().code("resource:view").name("查看").menuId(menuId).build());

        Long roleId = menuMap.get(ROLE);
        resourceList.add(Resource.builder().code("role:add").name("新增").menuId(roleId).build());
        resourceList.add(Resource.builder().code("role:delete").name("删除").menuId(roleId).build());
        resourceList.add(Resource.builder().code("role:export").name("导出").menuId(roleId).build());
        resourceList.add(Resource.builder().code("role:import").name("导入").menuId(roleId).build());
        resourceList.add(Resource.builder().code("role:update").name("修改").menuId(roleId).build());
        resourceList.add(Resource.builder().code("role:view").name("查看").menuId(roleId).build());
        resourceList.add(Resource.builder().code("role:config").name("配置").menuId(roleId).build());
        resourceList.add(Resource.builder().code("role:auth").name("授权").menuId(roleId).build());

        Long parameterId = menuMap.get(PARAMETER);
        resourceList.add(Resource.builder().code("parameter:add").name("新增").menuId(parameterId).build());
        resourceList.add(Resource.builder().code("parameter:delete").name("删除").menuId(parameterId).build());
        resourceList.add(Resource.builder().code("parameter:export").name("导出").menuId(parameterId).build());
        resourceList.add(Resource.builder().code("parameter:import").name("导入").menuId(parameterId).build());
        resourceList.add(Resource.builder().code("parameter:update").name("修改").menuId(parameterId).build());
        resourceList.add(Resource.builder().code("parameter:view").name("查看").menuId(parameterId).build());

        Long areaId = menuMap.get(AREA);
        resourceList.add(Resource.builder().code("area:add").name("新增").menuId(areaId).build());
        resourceList.add(Resource.builder().code("area:delete").name("删除").menuId(areaId).build());
        resourceList.add(Resource.builder().code("area:export").name("导出").menuId(areaId).build());
        resourceList.add(Resource.builder().code("area:import").name("导入").menuId(areaId).build());
        resourceList.add(Resource.builder().code("area:update").name("修改").menuId(areaId).build());
        resourceList.add(Resource.builder().code("area:view").name("查看").menuId(areaId).build());

        Long dictId = menuMap.get(DICT);
        resourceList.add(Resource.builder().code("dict:add").name("新增").menuId(dictId).build());
        resourceList.add(Resource.builder().code("dict:delete").name("删除").menuId(dictId).build());
        resourceList.add(Resource.builder().code("dict:export").name("导出").menuId(dictId).build());
        resourceList.add(Resource.builder().code("dict:import").name("导入").menuId(dictId).build());
        resourceList.add(Resource.builder().code("dict:update").name("修改").menuId(dictId).build());
        resourceList.add(Resource.builder().code("dict:view").name("查看").menuId(dictId).build());


        Long applicationId = menuMap.get(APPLICATION);
        resourceList.add(Resource.builder().code("application:add").name("新增").menuId(applicationId).build());
        resourceList.add(Resource.builder().code("application:delete").name("删除").menuId(applicationId).build());
        resourceList.add(Resource.builder().code("application:export").name("导出").menuId(applicationId).build());
        resourceList.add(Resource.builder().code("application:update").name("修改").menuId(applicationId).build());
        resourceList.add(Resource.builder().code("application:view").name("查看").menuId(applicationId).build());

        Long systemApiId = menuMap.get(SYSTEM_API);
        resourceList.add(Resource.builder().code("systemApi:add").name("新增").menuId(systemApiId).build());
        resourceList.add(Resource.builder().code("systemApi:delete").name("删除").menuId(systemApiId).build());
        resourceList.add(Resource.builder().code("systemApi:export").name("导出").menuId(systemApiId).build());
        resourceList.add(Resource.builder().code("systemApi:update").name("修改").menuId(systemApiId).build());
        resourceList.add(Resource.builder().code("systemApi:view").name("查看").menuId(systemApiId).build());

        Long loginLogId = menuMap.get(LOGIN_LOG);
        resourceList.add(Resource.builder().code("loginLog:delete").name("删除").menuId(loginLogId).build());
        resourceList.add(Resource.builder().code("loginLog:export").name("导出").menuId(loginLogId).build());
        resourceList.add(Resource.builder().code("loginLog:view").name("查看").menuId(loginLogId).build());

        Long optLogId = menuMap.get(OPT_LOG);
        resourceList.add(Resource.builder().code("optLog:delete").name("删除").menuId(optLogId).build());
        resourceList.add(Resource.builder().code("optLog:export").name("导出").menuId(optLogId).build());
        resourceList.add(Resource.builder().code("optLog:view").name("查看").menuId(optLogId).build());

        Long msgsId = menuMap.get(MSGS);
        resourceList.add(Resource.builder().code("msgs:add").name("新增").menuId(msgsId).build());
        resourceList.add(Resource.builder().code("msgs:delete").name("删除").menuId(msgsId).build());
        resourceList.add(Resource.builder().code("msgs:export").name("导出").menuId(msgsId).build());
        resourceList.add(Resource.builder().code("msgs:import").name("导入").menuId(msgsId).build());
        resourceList.add(Resource.builder().code("msgs:update").name("修改").menuId(msgsId).build());
        resourceList.add(Resource.builder().code("msgs:view").name("查看").menuId(msgsId).build());
        resourceList.add(Resource.builder().code("msgs:mark").name("标记已读").menuId(msgsId).build());

        Long smsManageId = menuMap.get(SMS_MANAGE);
        resourceList.add(Resource.builder().code("sms:manage:add").name("新增").menuId(smsManageId).build());
        resourceList.add(Resource.builder().code("sms:manage:delete").name("删除").menuId(smsManageId).build());
        resourceList.add(Resource.builder().code("sms:manage:export").name("导出").menuId(smsManageId).build());
        resourceList.add(Resource.builder().code("sms:manage:import").name("导入").menuId(smsManageId).build());
        resourceList.add(Resource.builder().code("sms:manage:update").name("修改").menuId(smsManageId).build());
        resourceList.add(Resource.builder().code("sms:manage:view").name("查看").menuId(smsManageId).build());

        Long smsTemplateId = menuMap.get(SMS_TEMPLATE);
        resourceList.add(Resource.builder().code("sms:template:add").name("新增").menuId(smsTemplateId).build());
        resourceList.add(Resource.builder().code("sms:template:delete").name("删除").menuId(smsTemplateId).build());
        resourceList.add(Resource.builder().code("sms:template:export").name("导出").menuId(smsTemplateId).build());
        resourceList.add(Resource.builder().code("sms:template:import").name("导入").menuId(smsTemplateId).build());
        resourceList.add(Resource.builder().code("sms:template:update").name("修改").menuId(smsTemplateId).build());
        resourceList.add(Resource.builder().code("sms:template:view").name("查看").menuId(smsTemplateId).build());

        Long fileId = menuMap.get(FILE);
        resourceList.add(Resource.builder().code("file:add").name("新增").menuId(fileId).build());
        resourceList.add(Resource.builder().code("file:delete").name("删除").menuId(fileId).build());
        resourceList.add(Resource.builder().code("file:download").name("下载").menuId(fileId).build());
        resourceList.add(Resource.builder().code("file:update").name("修改").menuId(fileId).build());
        resourceList.add(Resource.builder().code("file:view").name("查看").menuId(fileId).build());

        return resourceService.saveBatch(resourceList);
    }

    private boolean initMenu(Snowflake snowflake, List<Menu> menuList, Map<String, Long> menuMap) {
        Long menuUserCenterId = snowflake.nextId();
        Long authId = snowflake.nextId();
        Long baseId = snowflake.nextId();
        Long developerId = snowflake.nextId();
        Long msgsId = snowflake.nextId();
        Long smsId = snowflake.nextId();
        Long fileId = snowflake.nextId();
        // 1级菜单
        menuList.add(Menu.builder().id(menuUserCenterId).label("用户中心").describe("用户组织机构").path("/user").component("Layout").icon("el-icon-user-solid").sortValue(1).build());
        menuList.add(Menu.builder().id(authId).label("权限管理").describe("管理权限相关").path("/auth").component("Layout").icon("el-icon-lock").sortValue(2).build());
        menuList.add(Menu.builder().id(baseId).label("基础配置").describe("基础的配置").path("/base").component("Layout").icon("el-icon-set-up").sortValue(3).build());
        menuList.add(Menu.builder().id(developerId).label("开发者管理").describe("开发者").path("/developer").component("Layout").icon("el-icon-user-solid").sortValue(4).build());
        menuList.add(Menu.builder().id(msgsId).label("消息中心").describe("站内信").path("/msgs").component("Layout").icon("el-icon-chat-line-square").sortValue(5).build());
        menuList.add(Menu.builder().id(smsId).label("短信中心").describe("短信接口").path("/sms").component("Layout").icon("el-icon-chat-line-round").sortValue(6).build());
        menuList.add(Menu.builder().id(fileId).label("文件中心").describe("附件接口").path("/file").component("Layout").icon("el-icon-folder-add").sortValue(7).build());

        // 2级菜单
        Long orgId = snowflake.nextId();
        menuMap.put(ORG, orgId);
        menuList.add(Menu.builder().id(orgId).parentId(menuUserCenterId).label("组织管理").path("/user/org").component("zuihou/user/org/Index").sortValue(0).build());
        Long stationId = snowflake.nextId();
        menuMap.put(STATION, stationId);
        menuList.add(Menu.builder().id(stationId).parentId(menuUserCenterId).label("岗位管理").path("/user/station").component("zuihou/user/station/Index").sortValue(1).build());
        Long userId = snowflake.nextId();
        menuMap.put(USER, userId);
        menuList.add(Menu.builder().id(userId).parentId(menuUserCenterId).label("用户管理").path("/user/user").component("zuihou/user/user/Index").sortValue(2).build());

        Long roleId = snowflake.nextId();
        menuMap.put(ROLE, roleId);
        menuList.add(Menu.builder().id(roleId).parentId(authId).label("角色管理").path("/auth/role").component("zuihou/auth/role/Index").sortValue(1).build());
        Long menuId = snowflake.nextId();
        menuMap.put(MENU, menuId);
        menuList.add(Menu.builder().id(menuId).parentId(authId).label("菜单配置").path("/auth/user").component("zuihou/auth/menu/Index").sortValue(2).build());

        Long parameterId = snowflake.nextId();
        menuMap.put(PARAMETER, parameterId);
        menuList.add(Menu.builder().id(parameterId).parentId(baseId).label("参数配置").path("/base/parameter").component("zuihou/base/parameter/Index").sortValue(2).build());
        Long dictId = snowflake.nextId();
        menuMap.put(DICT, dictId);
        menuList.add(Menu.builder().id(dictId).parentId(baseId).label("数据字典维护").path("/base/dict").component("zuihou/base/dict/Index").sortValue(0).build());
        Long areaId = snowflake.nextId();
        menuMap.put(AREA, areaId);
        menuList.add(Menu.builder().id(areaId).parentId(baseId).label("地区信息维护").path("/base/area").component("zuihou/base/area/Index").sortValue(1).build());

        Long applicationApi = snowflake.nextId();
        menuMap.put(APPLICATION, applicationApi);
        menuList.add(Menu.builder().id(applicationApi).parentId(developerId).label("应用管理").path("/developer/application").component("zuihou/developer/application/Index").sortValue(1).build());
        Long systemId = snowflake.nextId();
        menuMap.put(SYSTEM_API, systemId);
        menuList.add(Menu.builder().id(systemId).parentId(developerId).label("接口查询").path("/developer/systemApi").component("zuihou/developer/systemApi/Index").sortValue(2).build());
        Long optLogId = snowflake.nextId();
        menuMap.put(OPT_LOG, optLogId);
        menuList.add(Menu.builder().id(optLogId).parentId(developerId).label("操作日志").path("/developer/optLog").component("zuihou/developer/optLog/Index").sortValue(3).build());
        Long loginLogId = snowflake.nextId();
        menuMap.put(LOGIN_LOG, loginLogId);
        menuList.add(Menu.builder().id(loginLogId).parentId(developerId).label("登录日志").path("/developer/loginLog").component("zuihou/developer/loginLog/Index").sortValue(4).build());
        Long dbId = snowflake.nextId();
        menuMap.put(DB, dbId);
        menuList.add(Menu.builder().id(dbId).parentId(developerId).label("数据库监控").path("/developer/db").component("zuihou/developer/db/Index").sortValue(5).build());

        Long interId = snowflake.nextId();
        menuList.add(Menu.builder().id(interId).parentId(developerId).label("接口文档").path("http://127.0.0.1:8760/api/gate/doc.html").component("Layout").sortValue(6).build());
        Long nacosId = snowflake.nextId();
        menuList.add(Menu.builder().id(nacosId).parentId(developerId).label("注册&配置中心").path("http://127.0.0.1:8848/nacos").component("Layout").sortValue(7).build());
        Long redisId = snowflake.nextId();
        menuList.add(Menu.builder().id(redisId).parentId(developerId).label("缓存监控").path("http://www.baidu.com").component("Layout").sortValue(8).build());
        Long serverId = snowflake.nextId();
        menuList.add(Menu.builder().id(serverId).parentId(developerId).label("服务器监控").path("http://127.0.0.1:8762/zuihou-monitor").component("Layout").sortValue(9).build());
        Long jobsId = snowflake.nextId();
        menuList.add(Menu.builder().id(jobsId).parentId(developerId).label("定时调度中心").path("http://127.0.0.1:8767/zuihou-jobs-server").component("Layout").sortValue(10).build());
        Long zipkinId = snowflake.nextId();
        menuList.add(Menu.builder().id(zipkinId).parentId(developerId).label("链路调用监控").path("http://127.0.0.1:8772/zipkin").component("Layout").sortValue(11).build());

        Long msgsPushId = snowflake.nextId();
        menuList.add(Menu.builder().id(msgsPushId).parentId(msgsId).label("消息推送").path("/msgs/sendMsgs").component("zuihou/msgs/sendMsgs/Index").sortValue(1).build());
        Long myMsgsId = snowflake.nextId();
        menuMap.put(MSGS, myMsgsId);
        menuList.add(Menu.builder().id(myMsgsId).parentId(msgsId).label("我的消息").path("/msgs/myMsgs").component("zuihou/msgs/myMsgs/Index").sortValue(2).build());

        Long smsManageId = snowflake.nextId();
        menuMap.put(SMS_MANAGE, smsManageId);
        menuList.add(Menu.builder().id(smsManageId).parentId(smsId).label("短信管理").path("/sms/manage").component("zuihou/sms/manage/Index").sortValue(1).build());
        Long templateId = snowflake.nextId();
        menuMap.put(SMS_TEMPLATE, templateId);
        menuList.add(Menu.builder().id(templateId).parentId(smsId).label("账号配置").path("/sms/template").component("zuihou/sms/template/Index").sortValue(2).build());

        Long attachmentId = snowflake.nextId();
        menuMap.put(FILE, attachmentId);
        menuList.add(Menu.builder().id(attachmentId).parentId(fileId).label("附件列表").path("/file/attachment").component("zuihou/file/attachment/Index").sortValue(1).build());

        return menuService.saveBatch(menuList);
    }


    private boolean initDict() {
        List<Dictionary> dictionaryList = new ArrayList<>();
        dictionaryList.add(Dictionary.builder().type("NATION").name("民族").build());
        dictionaryList.add(Dictionary.builder().type("POSITION_STATUS").name("在职状态").build());
        dictionaryList.add(Dictionary.builder().type("EDUCATION").name("学历").build());
        dictionaryList.add(Dictionary.builder().type("AREA_LEVEL").name("行政区级").build());
        dictionaryService.saveBatch(dictionaryList);

        List<DictionaryItem> dictionaryItemList = new ArrayList<>();

        Dictionary nation = dictionaryList.get(0);
        Integer nationSort = 1;
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_hanz").name("汉族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_zz").name("壮族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_mz").name("满族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_hz").name("回族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_miaoz").name("苗族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_wwez").name("维吾尔族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_tjz").name("土家族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_yz").name("彝族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_mgz").name("蒙古族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_zhangz").name("藏族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_byz").name("布依族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_dz").name("侗族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_yaoz").name("瑶族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_cxz").name("朝鲜族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_bz").name("白族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_hnz").name("哈尼族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_hskz").name("哈萨克族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_lz").name("黎族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_daiz").name("傣族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_sz").name("畲族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_llz").name("傈僳族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_glz").name("仡佬族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_dxz").name("东乡族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_gsz").name("高山族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_lhz").name("拉祜族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_shuiz").name("水族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_wz").name("佤族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_nxz").name("纳西族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_qz").name("羌族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_tz").name("土族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_zlz").name("仫佬族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_xbz").name("锡伯族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_kehzz").name("柯尔克孜族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_dwz").name("达斡尔族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_jpz").name("景颇族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_mlz").name("毛南族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_slz").name("撒拉族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_tjkz").name("塔吉克族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_acz").name("阿昌族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_pmz").name("普米族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_ewkz").name("鄂温克族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_nz").name("怒族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_jz").name("京族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_jnz").name("基诺族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_daz").name("德昂族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_baz").name("保安族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_elsz").name("俄罗斯族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_ygz").name("裕固族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_wzbkz").name("乌兹别克族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_mbz").name("门巴族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_elcz").name("鄂伦春族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_dlz").name("独龙族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_tkez").name("塔塔尔族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_hzz").name("赫哲族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_lbz").name("珞巴族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_blz").name("布朗族").sortValue(nationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(nation.getId()).dictionaryType(nation.getType())
                .code("mz_qt").name("其他").sortValue(nationSort++).build());


        Dictionary positionStatus = dictionaryList.get(1);
        Integer positionStatusSort = 1;
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(positionStatus.getId()).dictionaryType(positionStatus.getType())
                .code("WORKING").name("在职").sortValue(positionStatusSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(positionStatus.getId()).dictionaryType(positionStatus.getType())
                .code("QUIT").name("离职").sortValue(positionStatusSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(positionStatus.getId()).dictionaryType(positionStatus.getType())
                .code("LEAVE").name("请假").sortValue(positionStatusSort++).build());

        Dictionary education = dictionaryList.get(2);
        Integer educationSort = 1;
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(education.getId()).dictionaryType(education.getType())
                .code("XIAOXUE").name("小学").sortValue(educationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder()
                .dictionaryId(education.getId()).dictionaryType(education.getType())
                .code("ZHONGXUE").name("中学").sortValue(educationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(education.getId()).dictionaryType(education.getType())
                .code("GAOZHONG").name("高中").sortValue(educationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(education.getId()).dictionaryType(education.getType())
                .code("ZHUANKE").name("专科").sortValue(educationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(education.getId()).dictionaryType(education.getType())
                .code("COLLEGE").name("本科").sortValue(educationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(education.getId()).dictionaryType(education.getType())
                .code("SUOSHI").name("硕士").sortValue(educationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(education.getId()).dictionaryType(education.getType())
                .code("BOSHI").name("博士").sortValue(educationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(education.getId()).dictionaryType(education.getType())
                .code("BOSHIHOU").name("博士后").sortValue(educationSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(education.getId()).dictionaryType(education.getType())
                .code("QITA").name("其他").sortValue(educationSort++).build());

        Dictionary areaLevel = dictionaryList.get(3);
        Integer areaLevelSort = 1;
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(areaLevel.getId()).dictionaryType(areaLevel.getType())
                .code("COUNTRY").name("国家").sortValue(areaLevelSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(areaLevel.getId()).dictionaryType(areaLevel.getType())
                .code("PROVINCE").name("省份/直辖市").sortValue(areaLevelSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(areaLevel.getId()).dictionaryType(areaLevel.getType())
                .code("CITY").name("地市").sortValue(areaLevelSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(areaLevel.getId()).dictionaryType(areaLevel.getType())
                .code("COUNTY").name("区县").sortValue(areaLevelSort++).build());
        dictionaryItemList.add(DictionaryItem.builder().dictionaryId(areaLevel.getId()).dictionaryType(areaLevel.getType())
                .code("TOWNS").name("乡镇").sortValue(areaLevelSort++).build());

        return dictionaryItemService.saveBatch(dictionaryItemList);
    }

    @Override
    public boolean reset(String tenant) {
        //TODO 待实现
        // 1，清空所有表的数据
        // 2，重新初始化 tenant
        // 3，重新初始化 业务数据
        //        init(tenant);
        return true;
    }

    @Override
    public boolean delete(List<String> tenantCodeList) {
        // 清空所有表中当前租户的数据
        //TODO 待实现
        //1,查询系统中的所有表
        //删除该租户的所有数据
        return true;
    }
}
