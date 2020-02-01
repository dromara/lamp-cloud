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
     * 数据字典项 api查询类
     */
    public static final String DICTIONARY_ITEM_CLASS = "dictionaryItemServiceImpl";

    /**
     * 数据字典项 api查询方法
     */
    public static final String DICTIONARY_ITEM_METHOD = "findDictionaryItem";

    /**
     * 组织 api查询类
     */
    public static final String ORG_ID_CLASS = "orgServiceImpl";

    /**
     * 组织 api查询方法
     */
    public static final String ORG_ID_METHOD = "findOrgByIds";
    /**
     * 组织 api查询类
     * 注意，如果是想要使用 Feign
     */
    public static final String STATION_ID_CLASS = "stationController";

    /**
     * 组织 api查询方法
     */
    public static final String STATION_ID_METHOD = "findStationByIds";
}
