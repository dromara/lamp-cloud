//package com.github.zuihou.database.parsers;
//
//import java.util.List;
//import java.util.Properties;
//
//import com.alibaba.druid.sql.ast.SQLExpr;
//import com.alibaba.druid.sql.ast.SQLObject;
//import com.alibaba.druid.sql.ast.SQLStatement;
//import com.alibaba.druid.sql.ast.expr.SQLAggregateExpr;
//import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
//import com.alibaba.druid.sql.ast.expr.SQLCaseExpr;
//import com.alibaba.druid.sql.ast.expr.SQLExistsExpr;
//import com.alibaba.druid.sql.ast.expr.SQLInSubQueryExpr;
//import com.alibaba.druid.sql.ast.expr.SQLQueryExpr;
//import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
//import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
//import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
//import com.alibaba.druid.sql.ast.statement.SQLJoinTableSource;
//import com.alibaba.druid.sql.ast.statement.SQLSelect;
//import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
//import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
//import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
//import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
//import com.alibaba.druid.sql.ast.statement.SQLSubqueryTableSource;
//import com.alibaba.druid.sql.ast.statement.SQLTableSource;
//import com.alibaba.druid.sql.ast.statement.SQLUnionQuery;
//import com.alibaba.druid.sql.ast.statement.SQLUnionQueryTableSource;
//import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
//import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlDeleteStatement;
//import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
//import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
//
//import org.apache.ibatis.cache.CacheKey;
//import org.apache.ibatis.executor.Executor;
//import org.apache.ibatis.mapping.BoundSql;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.mapping.ParameterMapping;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.plugin.Intercepts;
//import org.apache.ibatis.plugin.Invocation;
//import org.apache.ibatis.plugin.Plugin;
//import org.apache.ibatis.plugin.Signature;
//import org.apache.ibatis.session.ResultHandler;
//import org.apache.ibatis.session.RowBounds;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
////import com.quanbu.erpCommon.tenant.TenantInfoBizService;
////import com.quanbu.erpCommon.tenant.dto.TenantInfoDTO;
////import com.quanbu.framework.cache.JedisClient;
////import com.quanbu.framework.result.SingleEntryResult;
////import com.quanbu.framework.util.QBStringUtil;
////import com.quanbu.uac.org.OPOrganization;
//
///**
// * *********************************************
// * Copyright QUANBU.
// * All rights reserved.
// * Description:
// * HISTORY:
// * ****************************************************
// * Version       Date         Author            Desc
// * v1.0        2019/2/18    chenYongBin     多账套拦截器
// * <p>
// * ****************************************************
// */
//@Intercepts(value = {
//        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
//        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
//        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})}
//)
//public class MultiTenantInterceptor implements Interceptor {
//
//    private static final String SCHEMA_NAME_PREFIX = "erp_";
//
//    //    private static JedisClient jedisClient;
////    private static TenantInfoBizService tenantInfoBizService;
//    private static final String DEFAULT_SCHEMA_NAME = "common";
//    private static final String ERP_SCHEMA_NAME_BY_TENANT_ID = "ERP_SCHEMA_NAME_BY_TENANT_ID_";
//    private static final String EXCLUDE_MAPPER_ID = "com.quanbu.erpCommon.tenant.mapper.TenantInfoMapper.selectByPrimaryKey";
//    private static Logger logger = LoggerFactory.getLogger(MultiTenantInterceptor.class);
//    private String schemaName;
//
//    public static void main(String[] args) {
//        MultiTenantInterceptor multiTenantInterceptor = new MultiTenantInterceptor();
//        multiTenantInterceptor.schemaName = "erp_szzj";
//        String sql = "select\n" +
//                "t1.*,\n" +
//                "t2.customer_name,t2.dye_factory_name,t2.fabric_ch,t2.create_time as header_create_time,t2.create_user as header_create_user,\n" +
//                "t3.ld_detail_record_id,t3.dye_color_code,t3.dye_color_name,t3.dye_deliver_date,t3.customer_choice,t3.comment_date,t3.comment_remark,t3.deliver_num as record_deliver_num\n" +
//                "from ld_order_detail t1\n" +
//                "left join ld_order_header t2 on t1.ld_order_header_id = t2.ld_order_header_id\n" +
//                "left join ld_detail_record t3 on t3.ld_order_detail_id = t1.ld_order_detail_id and t3.deliver_num = (select MAX(deliver_num) FROM ld_detail_record t3)";
////         sql = "";
//        String newSql = multiTenantInterceptor.processSqlByInterceptor(sql);
//        System.out.println(newSql);
//    }
//
//    @Override
//    public Object plugin(Object target) {
//        return Plugin.wrap(target, this);
//    }
//
//    @Override
//    public void setProperties(Properties properties) {
//    }
//
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        Object[] args = invocation.getArgs();
//        MappedStatement mappedStatement = (MappedStatement) args[0];
//        Object parameter = args[1];
//        schemaName = SCHEMA_NAME_PREFIX + this.getCurrentSchemaName(mappedStatement);
//        args[0] = this.getNewMappedStatement(parameter, mappedStatement);
//        return invocation.proceed();
//    }
//
//    private String getCurrentSchemaName(MappedStatement mappedStatement) {
////        if (EXCLUDE_MAPPER_ID.equals(mappedStatement.getId())) return DEFAULT_SCHEMA_NAME;
////
////        String tenantId = QBStringUtil.isEmptyString(OPOrganization.getTargetOrgCode()) ? OPOrganization.getOrgCode() : OPOrganization.getTargetOrgCode();
////
////        SingleEntryResult<String> schemaNameByTenantIdResult = jedisClient.get(ERP_SCHEMA_NAME_BY_TENANT_ID + tenantId);
////        if (schemaNameByTenantIdResult.getSuccessful() && QBStringUtil.isNotEmptyString(schemaNameByTenantIdResult.getResult())) {
////            return schemaNameByTenantIdResult.getResult();
////        }
////
////        SingleEntryResult<TenantInfoDTO> tenantInfoResult = tenantInfoBizService.selectByTenantId(tenantId);
////        if (tenantInfoResult.getSuccessful() && null != tenantInfoResult.getResult()) {
////            jedisClient.set(ERP_SCHEMA_NAME_BY_TENANT_ID + tenantId, tenantInfoResult.getResult().getSchemeName(), 60 * 60 * 24 * 7);
////            return tenantInfoResult.getResult().getSchemeName();
////        }
//
//        return DEFAULT_SCHEMA_NAME;
//    }
//
//    private MappedStatement getNewMappedStatement(Object parameter, MappedStatement mappedStatement) {
//        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
//        logger.debug("原SQL：{}", boundSql.getSql());
//        String resultSql = this.processSqlByInterceptor(boundSql.getSql());
//        logger.debug("结果SQL：{}", resultSql);
//        BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), resultSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
//        MappedStatement.Builder builder = new MappedStatement.Builder(mappedStatement.getConfiguration(), mappedStatement.getId(), parameterObject -> newBoundSql, mappedStatement.getSqlCommandType());
//        builder.resource(mappedStatement.getResource());
//        builder.fetchSize(mappedStatement.getFetchSize());
//        builder.statementType(mappedStatement.getStatementType());
//        builder.keyGenerator(mappedStatement.getKeyGenerator());
//        if (mappedStatement.getKeyProperties() != null && mappedStatement.getKeyProperties().length > 0)
//            builder.keyProperty(mappedStatement.getKeyProperties()[0]);
//        builder.timeout(mappedStatement.getTimeout());
//        builder.parameterMap(mappedStatement.getParameterMap());
//        builder.resultMaps(mappedStatement.getResultMaps());
//        builder.resultSetType(mappedStatement.getResultSetType());
//        builder.cache(mappedStatement.getCache());
//        builder.flushCacheRequired(mappedStatement.isFlushCacheRequired());
//        builder.useCache(mappedStatement.isUseCache());
//        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
//            String prop = mapping.getProperty();
//            if (boundSql.hasAdditionalParameter(prop))
//                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
//        }
//        return builder.build();
//    }
//
//    private void setSQLSchema(SQLSelectQuery sqlSelectQuery) {
//        if (sqlSelectQuery instanceof SQLUnionQuery) {
//            SQLUnionQuery sqlUnionQuery = (SQLUnionQuery) sqlSelectQuery;
//            SQLSelectQuery sqlSelectQueryLeft = sqlUnionQuery.getLeft();
//            setSQLSchema(sqlSelectQueryLeft);
//            SQLSelectQuery sqlSelectQueryRight = sqlUnionQuery.getRight();
//            setSQLSchema(sqlSelectQueryRight);
//        }
//        if (sqlSelectQuery instanceof SQLSelectQueryBlock) {
//            SQLSelectQueryBlock sqlSelectQueryBlock = (SQLSelectQueryBlock) sqlSelectQuery;
//            SQLTableSource sqlTableSource = sqlSelectQueryBlock.getFrom();
//            setSQLSchema(sqlTableSource);
//            SQLExpr whereSqlExpr = sqlSelectQueryBlock.getWhere();
//            if (whereSqlExpr instanceof SQLInSubQueryExpr) {
//                SQLInSubQueryExpr sqlInSubQueryExpr = (SQLInSubQueryExpr) whereSqlExpr;
//                SQLSelectQuery sqlSelectQueryIn = sqlInSubQueryExpr.getSubQuery().getQuery();
//                setSQLSchema(sqlSelectQueryIn);
//            }
//            if (whereSqlExpr instanceof SQLBinaryOpExpr) {
//                SQLBinaryOpExpr sqlBinaryOpExpr = (SQLBinaryOpExpr) whereSqlExpr;
//                setSQLSchema(sqlBinaryOpExpr);
//            }
//            List<SQLSelectItem> sqlSelectItemList = sqlSelectQueryBlock.getSelectList();
//            for (SQLSelectItem sqlSelectItem : sqlSelectItemList) {
//                SQLExpr sqlExpr = sqlSelectItem.getExpr();
//                setSQLSchema(sqlExpr);
//            }
//        }
//    }
//
//    private String processSqlByInterceptor(String sql) {
//        MySqlStatementParser parser = new MySqlStatementParser(sql);
//        SQLStatement sqlStatement = parser.parseStatement();
//        if (sqlStatement instanceof SQLSelectStatement) {
//            SQLSelectStatement sqlSelectStatement = (SQLSelectStatement) sqlStatement;
//            SQLSelectQuery sqlSelectQuery = sqlSelectStatement.getSelect().getQuery();
//            setSQLSchema(sqlSelectQuery);
//        }
//        if (sqlStatement instanceof SQLUpdateStatement) {
//            SQLUpdateStatement sqlUpdateStatement = (SQLUpdateStatement) sqlStatement;
//            SQLTableSource sqlTableSource = sqlUpdateStatement.getTableSource();
//            setSQLSchema(sqlTableSource);
//            SQLExpr where = sqlUpdateStatement.getWhere();
//            setSQLSchema(where);
//        }
//        if (sqlStatement instanceof SQLInsertStatement) {
//            SQLInsertStatement sqlInsertStatement = (SQLInsertStatement) sqlStatement;
//            SQLExprTableSource tableSource = sqlInsertStatement.getTableSource();
//            setSQLSchema(tableSource);
//        }
//        if (sqlStatement instanceof SQLDeleteStatement) {
//            SQLDeleteStatement sqlDeleteStatement = (SQLDeleteStatement) sqlStatement;
//            SQLTableSource tableSource = sqlDeleteStatement.getTableSource();
//            setSQLSchema(tableSource);
//            SQLExpr where = sqlDeleteStatement.getWhere();
//            setSQLSchema(where);
//        }
//        return sqlStatement.toString();
//    }
//
//    private void setSQLSchema(SQLTableSource sqlTableSource) {
//        if (sqlTableSource instanceof SQLJoinTableSource) {
//            SQLJoinTableSource sqlJoinTableSource = (SQLJoinTableSource) sqlTableSource;
//            SQLTableSource sqlTableSourceLeft = sqlJoinTableSource.getLeft();
//            setSQLSchema(sqlTableSourceLeft);
//            SQLTableSource sqlTableSourceRight = sqlJoinTableSource.getRight();
//            setSQLSchema(sqlTableSourceRight);
//            SQLExpr condition = sqlJoinTableSource.getCondition();
//            setSQLSchema(condition);
//        }
//        if (sqlTableSource instanceof SQLSubqueryTableSource) {
//            SQLSubqueryTableSource sqlSubqueryTableSource = (SQLSubqueryTableSource) sqlTableSource;
//            SQLSelectQuery sqlSelectQuery = sqlSubqueryTableSource.getSelect().getQuery();
//            setSQLSchema(sqlSelectQuery);
//        }
//        if (sqlTableSource instanceof SQLUnionQueryTableSource) {
//            SQLUnionQueryTableSource sqlUnionQueryTableSource = (SQLUnionQueryTableSource) sqlTableSource;
//            SQLSelectQuery sqlSelectQueryLeft = sqlUnionQueryTableSource.getUnion().getLeft();
//            setSQLSchema(sqlSelectQueryLeft);
//            SQLSelectQuery sqlSelectQueryRight = sqlUnionQueryTableSource.getUnion().getRight();
//            setSQLSchema(sqlSelectQueryRight);
//        }
//        if (sqlTableSource instanceof SQLExprTableSource) {
//            SQLExprTableSource sqlExprTableSource = (SQLExprTableSource) sqlTableSource;
//            SQLObject sqlObject = sqlExprTableSource.getParent();
//            if (sqlObject instanceof MySqlDeleteStatement) {
//                MySqlDeleteStatement mySqlDeleteStatement = (MySqlDeleteStatement) sqlObject;
//                SQLExpr sqlExpr = mySqlDeleteStatement.getWhere();
//                setSQLSchema(sqlExpr);
//            }
//            if (sqlObject instanceof MySqlInsertStatement) {
//                MySqlInsertStatement mySqlInsertStatement = (MySqlInsertStatement) sqlObject;
//                SQLSelect sqlSelect = mySqlInsertStatement.getQuery();
//                if (sqlSelect != null) {
//                    SQLSelectQuery sqlSelectQuery = sqlSelect.getQuery();
//                    setSQLSchema(sqlSelectQuery);
//                }
//            }
//            sqlExprTableSource.setSchema(schemaName);
//        }
//    }
//
//    private void setSQLSchema(SQLBinaryOpExpr sqlBinaryOpExpr) {
//        SQLExpr sqlExprLeft = sqlBinaryOpExpr.getLeft();
//        setSQLSchema(sqlExprLeft);
//        SQLExpr sqlExprRight = sqlBinaryOpExpr.getRight();
//        setSQLSchema(sqlExprRight);
//    }
//
//    private void setSQLSchema(SQLExpr sqlExpr) {
//        if (sqlExpr instanceof SQLInSubQueryExpr) {
//            SQLInSubQueryExpr sqlInSubQueryExpr = (SQLInSubQueryExpr) sqlExpr;
//            SQLSelectQuery sqlSelectQuery = sqlInSubQueryExpr.getSubQuery().getQuery();
//            setSQLSchema(sqlSelectQuery);
//        }
//        if (sqlExpr instanceof SQLExistsExpr) {
//            SQLExistsExpr sqlExistsExpr = (SQLExistsExpr) sqlExpr;
//            SQLSelectQuery sqlSelectQuery = sqlExistsExpr.getSubQuery().getQuery();
//            setSQLSchema(sqlSelectQuery);
//        }
//        if (sqlExpr instanceof SQLCaseExpr) {
//            SQLCaseExpr sqlCaseExpr = (SQLCaseExpr) sqlExpr;
//            List<SQLCaseExpr.Item> sqlCaseExprItemList = sqlCaseExpr.getItems();
//            for (SQLCaseExpr.Item item : sqlCaseExprItemList) {
//                SQLExpr sqlExprItem = item.getValueExpr();
//                setSQLSchema(sqlExprItem);
//            }
//        }
//        if (sqlExpr instanceof SQLQueryExpr) {
//            SQLQueryExpr sqlQueryExpr = (SQLQueryExpr) sqlExpr;
//            SQLSelectQuery sqlSelectQuery = sqlQueryExpr.getSubQuery().getQuery();
//            setSQLSchema(sqlSelectQuery);
//        }
//        if (sqlExpr instanceof SQLBinaryOpExpr) {
//            SQLBinaryOpExpr sqlBinaryOpExpr = (SQLBinaryOpExpr) sqlExpr;
//            setSQLSchema(sqlBinaryOpExpr);
//        }
//        if (sqlExpr instanceof SQLAggregateExpr) {
//            SQLAggregateExpr sqlAggregateExpr = (SQLAggregateExpr) sqlExpr;
//            List<SQLExpr> arguments = sqlAggregateExpr.getArguments();
//            for (SQLExpr argument : arguments) {
//                setSQLSchema(argument);
//            }
//        }
//    }
//
////    public static JedisClient getJedisClient() {
////        return jedisClient;
////    }
////
////    public static void setJedisClient(JedisClient jedisClient) {
////        MultiTenantInterceptor.jedisClient = jedisClient;
////    }
////
////    public static TenantInfoBizService getTenantInfoBizService() {
////        return tenantInfoBizService;
////    }
////
////    public static void setTenantInfoBizService(TenantInfoBizService tenantInfoBizService) {
////        MultiTenantInterceptor.tenantInfoBizService = tenantInfoBizService;
////    }
//}
