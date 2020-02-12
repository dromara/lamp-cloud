package com.github.zuihou.database.mybatis.auth;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import com.github.zuihou.context.BaseContextHandler;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;


/**
 * mybatis 数据权限拦截器
 * <p>
 *
 * @author zuihou
 * @date 2019/2/1
 */
@Slf4j
@AllArgsConstructor
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class DataScopeInterceptor extends AbstractSqlParserHandler implements Interceptor {

    final private Function<Long, Map<String, Object>> function;

    @Override
    @SneakyThrows
    public Object intercept(Invocation invocation) {
        StatementHandler statementHandler = (StatementHandler) PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        this.sqlParser(metaObject);
        // 先判断是不是SELECT操作
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
            return invocation.proceed();
        }

        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        String originalSql = boundSql.getSql();
        Object parameterObject = boundSql.getParameterObject();

        //查找参数中包含DataScope类型的参数
        DataScope dataScope = findDataScopeObject(parameterObject);

        if (dataScope == null) {
            return invocation.proceed();
        }
        String scopeName = dataScope.getScopeName();
        String selfScopeName = dataScope.getSelfScopeName();
        Long userId = dataScope.getUserId() == null ? BaseContextHandler.getUserId() : dataScope.getUserId();
        List<Long> orgIds = dataScope.getOrgIds();
        DataScopeType dsType = DataScopeType.SELF;
        if (CollectionUtil.isEmpty(orgIds)) {
            //查询当前用户的 角色 最小权限
            //userId

            //dsType orgIds
            Map<String, Object> result = function.apply(userId);
            if (result == null) {
                return invocation.proceed();
            }

            Integer type = (Integer) result.get("dsType");
            dsType = DataScopeType.get(type);
            orgIds = (List<Long>) result.get("orgIds");
        }

        //查全部
        if (DataScopeType.ALL.eq(dsType)) {
            return invocation.proceed();
        }

        //查个人
        if (DataScopeType.SELF.eq(dsType)) {
            originalSql = "select * from (" + originalSql + ") temp_data_scope where temp_data_scope." + selfScopeName + " = " + userId;
        }
        //查其他
        else if (StrUtil.isNotBlank(scopeName) && CollectionUtil.isNotEmpty(orgIds)) {

            String join = CollectionUtil.join(orgIds, ",");
            originalSql = "select * from (" + originalSql + ") temp_data_scope where temp_data_scope." + scopeName + " in (" + join + ")";
        }

        metaObject.setValue("delegate.boundSql.sql", originalSql);
        return invocation.proceed();

    }

    /**
     * 生成拦截对象的代理
     *
     * @param target 目标对象
     * @return 代理对象
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    /**
     * mybatis配置的属性
     *
     * @param properties mybatis配置的属性
     */
    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 查找参数是否包括DataScope对象
     *
     * @param parameterObj 参数列表
     * @return DataScope
     */
    private DataScope findDataScopeObject(Object parameterObj) {
        if (parameterObj instanceof DataScope) {
            return (DataScope) parameterObj;
        } else if (parameterObj instanceof Map) {
            for (Object val : ((Map<?, ?>) parameterObj).values()) {
                if (val instanceof DataScope) {
                    return (DataScope) val;
                }
            }
        }
        return null;
    }

}
