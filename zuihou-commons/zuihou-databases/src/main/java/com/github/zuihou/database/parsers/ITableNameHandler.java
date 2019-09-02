package com.github.zuihou.database.parsers;

import org.apache.ibatis.reflection.MetaObject;

/**
 * 表名处理
 *
 * @author zuihou
 * @date 2019/08/20
 */
@FunctionalInterface
public interface ITableNameHandler {

    /**
     * 表名 SQL 处理
     *
     * @param metaObject 元对象
     * @param sql        当前执行 SQL
     * @param tableName  表名
     * @return
     */
    default String process(MetaObject metaObject, String sql, String tableName) {
        String dynamicTableName = dynamicTableName(metaObject, sql, tableName);
        if (null != dynamicTableName && !dynamicTableName.equalsIgnoreCase(tableName)) {
            return sql.replaceAll(tableName, dynamicTableName);
        }
        return sql;
    }

    /**
     * 生成动态表名，无改变返回 NULL
     *
     * @param metaObject 元对象
     * @param sql        当前执行 SQL
     * @param tableName  表名
     * @return String
     */
    String dynamicTableName(MetaObject metaObject, String sql, String tableName);
}
