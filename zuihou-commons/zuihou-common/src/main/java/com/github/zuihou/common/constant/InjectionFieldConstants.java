package com.github.zuihou.common.constant;

/**
 * 仅仅用于记录 RemoteField 注解相关的 接口和方法名称
 * <p>
 * 切记，该类下的接口和方法，一定要自己手动创建，否则会注入失败
 *
 * @author zuihou
 * @date 2020年01月20日11:16:37
 */
public abstract class InjectionFieldConstants {

    /**
     * 数据字典项 feign查询类 切记，一定要在 DictionaryItemApi 上面添加属性：
     * qualifier="dictionaryItemApi"
     * <p>
     * 如： @FeignClient(name = "${zuihou.feign.authority-server:zuihou-authority-server}", path = "dictionaryItem",
     * qualifier = "dictionaryItemApi", fallback = DictionaryItemApiFallback.class)
     */
    public static final String DICTIONARY_ITEM_FEIGN_CLASS = "dictionaryItemApi";
    /**
     * 数据字典项 service查询类
     */
    public static final String DICTIONARY_ITEM_CLASS = "dictionaryItemServiceImpl";

    /**
     * 数据字典项 api查询方法
     */
    public static final String DICTIONARY_ITEM_METHOD = "findDictionaryItem";

    /**
     * 组织 service查询类
     */
    public static final String ORG_ID_CLASS = "orgServiceImpl";
    /**
     * 组织 feign查询类
     */
    public static final String ORG_ID_FEIGN_CLASS = "orgApi";

    /**
     * 组织 查询方法
     */
    public static final String ORG_ID_METHOD = "findOrgByIds";


    /**
     * 用户 service查询类
     */
    public static final String USER_ID_CLASS = "userServiceImpl";
    /**
     * 用户 feign查询类
     */
    public static final String USER_ID_FEIGN_CLASS = "userApi";

    /**
     * 用户 查询方法
     */
    public static final String USER_ID_METHOD = "findUserByIds";


    /**
     * 组织 service查询类
     */
    public static final String STATION_ID_CLASS = "stationServiceImpl";
    /**
     * 组织 feign查询类
     */
    public static final String STATION_ID_FEIGN_CLASS = "stationApi";

    /**
     * 组织 查询方法
     */
    public static final String STATION_ID_METHOD = "findStationByIds";
}
