package com.github.zuihou.database.mybatis;

import java.sql.Connection;
import java.util.Properties;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.exception.BizException;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;


/**
 * 演示环境写权限控制 拦截器
 * 该拦截器只用于演示环境， 开发和生产都不需要
 * <p>
 *
 * @author zuihou
 * @date 2019/2/1
 */
@Slf4j
@AllArgsConstructor
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class WriteInterceptor extends AbstractSqlParserHandler implements Interceptor {

    @Override
    @SneakyThrows
    public Object intercept(Invocation invocation) {
        StatementHandler statementHandler = (StatementHandler) PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        this.sqlParser(metaObject);
        // 读操作 放行
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        if (SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
            return invocation.proceed();
        }
        // 记录日志相关的 放行
        if (StrUtil.containsAnyIgnoreCase(mappedStatement.getId(), "OptLog", "File", "xxl")) {
            return invocation.proceed();
        }
        // userId=1 的超级管理员 放行
        Long userId = BaseContextHandler.getUserId();
        log.info("mapperid={}, userId={}", mappedStatement.getId(), userId);
        if (userId == 1) {
            return invocation.proceed();
        }

        //上传可以
        // 你还可以自定义其他放行规则， 比如：IP 等

        throw new BizException(-1, "演示环境，无写入权限");
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

}
