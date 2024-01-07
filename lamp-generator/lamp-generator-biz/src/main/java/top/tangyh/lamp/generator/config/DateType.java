package top.tangyh.lamp.generator.config;

/**
 * 数据库时间类型 到 实体类时间类型 对应策略
 *
 * @author zuihou
 * @date 2022/3/13 23:11
 */
public enum DateType {
    /**
     * 只使用 java.util 代替
     */
    ONLY_DATE,
    /**
     * 使用 java.sql 包下的
     */
    SQL_PACK,
    /**
     * 使用 java.time 包下的
     * <p>java8 新的时间类型</p>
     */
    TIME_PACK
}
