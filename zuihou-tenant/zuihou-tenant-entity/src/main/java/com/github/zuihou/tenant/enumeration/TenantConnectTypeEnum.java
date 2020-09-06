package com.github.zuihou.tenant.enumeration;

/**
 * @author zuihou
 * @date 2020/8/26 下午5:28
 */
public enum TenantConnectTypeEnum {
    /**
     * 同一个数据库(物理)，链接不同的数据库实例. 从mysql.yml中读取master数据源来自动新增其他数据库
     */
    LOCAL,
    /**
     * 不同的数据库(物理)，需要先在DatasourceConfig表配置链接源信息，然后指定以下字段（xxxDatasource）
     */
    REMOTE,
    ;

    public boolean eq(TenantConnectTypeEnum val) {
        return val == null ? false : eq(val.name());
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }
}
