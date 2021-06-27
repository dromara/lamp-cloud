package com.tangyh.lamp.common.constant;

/**
 * 仅仅用于记录 RemoteField 注解相关的 接口和方法名称
 * <p>
 * 切记，该类下的接口和方法，一定要自己手动创建，否则会注入失败
 *
 * @author zuihou
 * @date 2020年01月20日11:16:37
 */
public class EchoConstants {
    private EchoConstants() {
    }

    public static final String FIND_NAME_BY_IDS = "findNameByIds";
    public static final String FIND_BY_IDS = "findByIds";

    /**
     * 数据字典项 feign查询类 全类名
     */
    public static final String DICTIONARY_ITEM_FEIGN_CLASS = "com.tangyh.lamp.oauth.api.DictionaryApi";
    /**
     * 数据字典项 service查询类
     */
    public static final String DICTIONARY_ITEM_CLASS = "dictionaryServiceImpl";

    /**
     * 组织 service查询类
     */
    public static final String ORG_ID_CLASS = "orgServiceImpl";
    /**
     * 组织 feign查询类
     */
    public static final String ORG_ID_FEIGN_CLASS = "com.tangyh.lamp.oauth.api.OrgApi";

    /**
     * 用户 service查询类
     */
    public static final String USER_ID_CLASS = "userServiceImpl";
    /**
     * 用户 feign查询类
     */
    public static final String USER_ID_FEIGN_CLASS = "com.tangyh.lamp.oauth.api.UserApi";

    /**
     * 组织 service查询类
     */
    public static final String STATION_ID_CLASS = "stationServiceImpl";
    /**
     * 组织 feign查询类
     */
    public static final String STATION_ID_FEIGN_CLASS = "com.tangyh.lamp.oauth.api.StationApi";


    /**
     * 短信模板 service查询类
     */
    public static final String SMS_TEMPLATE_ID_CLASS = "smsTemplateServiceImpl";

}
