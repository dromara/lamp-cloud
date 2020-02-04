package com.github.zuihou.database.parsers;

import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.*;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlDeleteStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.github.zuihou.context.BaseContextHandler;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;


/**
 * 多租户拦截器
 *
 * @author zuihou
 * @date 2019-11-26 09:27
 */
@Intercepts(value = {
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})}
)
public class MultiTenantInterceptor implements Interceptor {

    private static Logger logger = LoggerFactory.getLogger(MultiTenantInterceptor.class);
    private String schemaName;

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        Object parameter = args[1];

        String tenantCode = BaseContextHandler.getTenant();
        if (StrUtil.isEmpty(tenantCode)) {
            return invocation.proceed();
        }
        this.setSchemaName(BaseContextHandler.getDatabase(tenantCode));
        args[0] = getNewMappedStatement(parameter, mappedStatement);
        return invocation.proceed();
    }

    private MappedStatement getNewMappedStatement(Object parameter, MappedStatement mappedStatement) {
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        logger.debug("原SQL：{}", boundSql.getSql());
        String resultSql = processSqlByInterceptor(boundSql.getSql());
        logger.debug("结果SQL：{}", resultSql);
        BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), resultSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
        MappedStatement.Builder builder = new MappedStatement.Builder(mappedStatement.getConfiguration(), mappedStatement.getId(), parameterObject -> newBoundSql, mappedStatement.getSqlCommandType());
        builder.resource(mappedStatement.getResource());
        builder.fetchSize(mappedStatement.getFetchSize());
        builder.statementType(mappedStatement.getStatementType());
        builder.keyGenerator(mappedStatement.getKeyGenerator());
        if (mappedStatement.getKeyProperties() != null && mappedStatement.getKeyProperties().length > 0) {
            builder.keyProperty(mappedStatement.getKeyProperties()[0]);
        }
        builder.timeout(mappedStatement.getTimeout());
        builder.parameterMap(mappedStatement.getParameterMap());
        builder.resultMaps(mappedStatement.getResultMaps());
        builder.resultSetType(mappedStatement.getResultSetType());
        builder.cache(mappedStatement.getCache());
        builder.flushCacheRequired(mappedStatement.isFlushCacheRequired());
        builder.useCache(mappedStatement.isUseCache());

        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        return builder.build();
    }

    private void setSQLSchema(SQLSelectQuery sqlSelectQuery) {
        if (sqlSelectQuery instanceof SQLUnionQuery) {
            SQLUnionQuery sqlUnionQuery = (SQLUnionQuery) sqlSelectQuery;
            SQLSelectQuery sqlSelectQueryLeft = sqlUnionQuery.getLeft();
            setSQLSchema(sqlSelectQueryLeft);
            SQLSelectQuery sqlSelectQueryRight = sqlUnionQuery.getRight();
            setSQLSchema(sqlSelectQueryRight);
        }
        if (sqlSelectQuery instanceof SQLSelectQueryBlock) {
            SQLSelectQueryBlock sqlSelectQueryBlock = (SQLSelectQueryBlock) sqlSelectQuery;
            SQLTableSource sqlTableSource = sqlSelectQueryBlock.getFrom();
            setSQLSchema(sqlTableSource);
            SQLExpr whereSqlExpr = sqlSelectQueryBlock.getWhere();
            if (whereSqlExpr instanceof SQLInSubQueryExpr) {
                SQLInSubQueryExpr sqlInSubQueryExpr = (SQLInSubQueryExpr) whereSqlExpr;
                SQLSelectQuery sqlSelectQueryIn = sqlInSubQueryExpr.getSubQuery().getQuery();
                setSQLSchema(sqlSelectQueryIn);
            }
            if (whereSqlExpr instanceof SQLBinaryOpExpr) {
                SQLBinaryOpExpr sqlBinaryOpExpr = (SQLBinaryOpExpr) whereSqlExpr;
                setSQLSchema(sqlBinaryOpExpr);
            }
            List<SQLSelectItem> sqlSelectItemList = sqlSelectQueryBlock.getSelectList();
            for (SQLSelectItem sqlSelectItem : sqlSelectItemList) {
                SQLExpr sqlExpr = sqlSelectItem.getExpr();
                setSQLSchema(sqlExpr);
            }
        }
    }

    String processSqlByInterceptor(String sql) {
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement sqlStatement = parser.parseStatement();
        if (sqlStatement instanceof SQLSelectStatement) {
            SQLSelectStatement sqlSelectStatement = (SQLSelectStatement) sqlStatement;
            SQLSelectQuery sqlSelectQuery = sqlSelectStatement.getSelect().getQuery();
            setSQLSchema(sqlSelectQuery);
        }
        if (sqlStatement instanceof SQLUpdateStatement) {
            SQLUpdateStatement sqlUpdateStatement = (SQLUpdateStatement) sqlStatement;
            SQLTableSource sqlTableSource = sqlUpdateStatement.getTableSource();
            setSQLSchema(sqlTableSource);
            SQLExpr where = sqlUpdateStatement.getWhere();
            setSQLSchema(where);
        }
        if (sqlStatement instanceof SQLInsertStatement) {
            SQLInsertStatement sqlInsertStatement = (SQLInsertStatement) sqlStatement;
            SQLExprTableSource tableSource = sqlInsertStatement.getTableSource();
            setSQLSchema(tableSource);
        }
        if (sqlStatement instanceof SQLDeleteStatement) {
            SQLDeleteStatement sqlDeleteStatement = (SQLDeleteStatement) sqlStatement;
            SQLTableSource tableSource = sqlDeleteStatement.getTableSource();
            setSQLSchema(tableSource);
            SQLExpr where = sqlDeleteStatement.getWhere();
            setSQLSchema(where);
        }
        if (sqlStatement instanceof SQLCreateStatement) {
            SQLCreateTableStatement sqlCreateStatement = (SQLCreateTableStatement) sqlStatement;
            SQLExprTableSource tableSource = sqlCreateStatement.getTableSource();
            setSQLSchema(tableSource);
        }
        return sqlStatement.toString();
    }

    private void setSQLSchema(SQLTableSource sqlTableSource) {
        if (sqlTableSource instanceof SQLJoinTableSource) {
            SQLJoinTableSource sqlJoinTableSource = (SQLJoinTableSource) sqlTableSource;
            SQLTableSource sqlTableSourceLeft = sqlJoinTableSource.getLeft();
            setSQLSchema(sqlTableSourceLeft);
            SQLTableSource sqlTableSourceRight = sqlJoinTableSource.getRight();
            setSQLSchema(sqlTableSourceRight);
            SQLExpr condition = sqlJoinTableSource.getCondition();
            setSQLSchema(condition);
        }
        if (sqlTableSource instanceof SQLSubqueryTableSource) {
            SQLSubqueryTableSource sqlSubqueryTableSource = (SQLSubqueryTableSource) sqlTableSource;
            SQLSelectQuery sqlSelectQuery = sqlSubqueryTableSource.getSelect().getQuery();
            setSQLSchema(sqlSelectQuery);
        }
        if (sqlTableSource instanceof SQLUnionQueryTableSource) {
            SQLUnionQueryTableSource sqlUnionQueryTableSource = (SQLUnionQueryTableSource) sqlTableSource;
            SQLSelectQuery sqlSelectQueryLeft = sqlUnionQueryTableSource.getUnion().getLeft();
            setSQLSchema(sqlSelectQueryLeft);
            SQLSelectQuery sqlSelectQueryRight = sqlUnionQueryTableSource.getUnion().getRight();
            setSQLSchema(sqlSelectQueryRight);
        }
        if (sqlTableSource instanceof SQLExprTableSource) {
            SQLExprTableSource sqlExprTableSource = (SQLExprTableSource) sqlTableSource;
            SQLObject sqlObject = sqlExprTableSource.getParent();
            if (sqlObject instanceof MySqlDeleteStatement) {
                MySqlDeleteStatement mySqlDeleteStatement = (MySqlDeleteStatement) sqlObject;
                SQLExpr sqlExpr = mySqlDeleteStatement.getWhere();
                setSQLSchema(sqlExpr);
            }
            if (sqlObject instanceof MySqlInsertStatement) {
                MySqlInsertStatement mySqlInsertStatement = (MySqlInsertStatement) sqlObject;
                SQLSelect sqlSelect = mySqlInsertStatement.getQuery();
                if (sqlSelect != null) {
                    SQLSelectQuery sqlSelectQuery = sqlSelect.getQuery();
                    setSQLSchema(sqlSelectQuery);
                }
            }
            sqlExprTableSource.setSchema(schemaName);
        }
    }

    private void setSQLSchema(SQLBinaryOpExpr sqlBinaryOpExpr) {
        SQLExpr sqlExprLeft = sqlBinaryOpExpr.getLeft();
        setSQLSchema(sqlExprLeft);
        SQLExpr sqlExprRight = sqlBinaryOpExpr.getRight();
        setSQLSchema(sqlExprRight);
    }

    private void setSQLSchema(SQLExpr sqlExpr) {
        if (sqlExpr instanceof SQLInSubQueryExpr) {
            SQLInSubQueryExpr sqlInSubQueryExpr = (SQLInSubQueryExpr) sqlExpr;
            SQLSelectQuery sqlSelectQuery = sqlInSubQueryExpr.getSubQuery().getQuery();
            setSQLSchema(sqlSelectQuery);
        }
        if (sqlExpr instanceof SQLExistsExpr) {
            SQLExistsExpr sqlExistsExpr = (SQLExistsExpr) sqlExpr;
            SQLSelectQuery sqlSelectQuery = sqlExistsExpr.getSubQuery().getQuery();
            setSQLSchema(sqlSelectQuery);
        }
        if (sqlExpr instanceof SQLCaseExpr) {
            SQLCaseExpr sqlCaseExpr = (SQLCaseExpr) sqlExpr;
            List<SQLCaseExpr.Item> sqlCaseExprItemList = sqlCaseExpr.getItems();
            for (SQLCaseExpr.Item item : sqlCaseExprItemList) {
                SQLExpr sqlExprItem = item.getValueExpr();
                setSQLSchema(sqlExprItem);
            }
        }
        if (sqlExpr instanceof SQLQueryExpr) {
            SQLQueryExpr sqlQueryExpr = (SQLQueryExpr) sqlExpr;
            SQLSelectQuery sqlSelectQuery = sqlQueryExpr.getSubQuery().getQuery();
            setSQLSchema(sqlSelectQuery);
        }
        if (sqlExpr instanceof SQLBinaryOpExpr) {
            SQLBinaryOpExpr sqlBinaryOpExpr = (SQLBinaryOpExpr) sqlExpr;
            setSQLSchema(sqlBinaryOpExpr);
        }
        if (sqlExpr instanceof SQLAggregateExpr) {
            SQLAggregateExpr sqlAggregateExpr = (SQLAggregateExpr) sqlExpr;
            List<SQLExpr> arguments = sqlAggregateExpr.getArguments();
            for (SQLExpr argument : arguments) {
                setSQLSchema(argument);
            }
        }
    }

}
