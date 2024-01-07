package top.tangyh.lamp.model.constant;

/**
 * Echo 注解中api的常量
 * <p>
 * 切记，该类下的接口和方法，一定要自己手动创建，否则会注入失败
 * <p>
 * 本类中的 @lamp.generator auto insert 请勿删除
 *
 * @author zuihou
 * @date 2020年01月20日11:16:37
 */
public interface EchoApi {
    // @lamp.generator auto insert EchoApi

    /**
     * lamp-cloud 数据字典项 feign查询类 全类名
     */
    String DICTIONARY_ITEM_FEIGN_CLASS = "top.tangyh.lamp.common.api.DictApi";
    /**
     * lamp-cloud 数据字典项 feign查询类 全类名
     */
//    String DICTIONARY_ITEM_FEIGN_CLASS = "dictServiceImpl";
    /**
     * 组织 service查询类
     */
    String ORG_ID_CLASS = "baseOrgManagerImpl";
    /**
     * 岗位 service查询类
     */
    String POSITION_ID_CLASS = "basePositionManagerImpl";
    String DEF_TENANT_SERVICE_IMPL_CLASS = "defTenantManagerImpl";
    String DEF_APPLICATION_SERVICE_IMPL_CLASS = "defApplicationManagerImpl";

}
