package com.github.zuihou.database.parsers;

import cn.hutool.core.util.StrUtil;
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
            // 加空格是预防 sql 语句后面无空格的情况
            sql += StrUtil.SPACE;
            // 加替换表名时，加一个空格为了预防 2个表名是包含关系， 如： select * from user join user_role
            return sql.replaceAll(tableName + StrUtil.SPACE, dynamicTableName + StrUtil.SPACE);
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
