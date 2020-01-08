//package com.github.zuihou.database.datasource;
//
//
//import java.lang.annotation.Annotation;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.baomidou.mybatisplus.annotation.FieldStrategy;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.core.MybatisConfiguration;
//import com.baomidou.mybatisplus.core.config.GlobalConfig;
//import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
//import com.baomidou.mybatisplus.core.parser.ISqlParser;
//import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import com.github.zuihou.base.id.IdGenerate;
//import com.github.zuihou.base.id.SnowflakeIdGenerate;
//import com.github.zuihou.database.mybatis.WriteInterceptor;
//import com.github.zuihou.database.mybatis.auth.DataScopeInterceptor;
//import com.github.zuihou.database.mybatis.typehandler.FullLikeTypeHandler;
//import com.github.zuihou.database.mybatis.typehandler.LeftLikeTypeHandler;
//import com.github.zuihou.database.mybatis.typehandler.RightLikeTypeHandler;
//import com.github.zuihou.database.parsers.DynamicTableNameParser;
//import com.github.zuihou.database.parsers.TenantWebMvcConfigurer;
//
//import cn.hutool.core.util.ArrayUtil;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.type.JdbcType;
//import org.apache.ibatis.type.TypeHandler;
//import org.slf4j.Logger;
//import org.springframework.aop.Advisor;
//import org.springframework.aop.ClassFilter;
//import org.springframework.aop.MethodMatcher;
//import org.springframework.aop.Pointcut;
//import org.springframework.aop.support.DefaultPointcutAdvisor;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.TransactionDefinition;
//import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
//import org.springframework.transaction.interceptor.RollbackRuleAttribute;
//import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
//import org.springframework.transaction.interceptor.TransactionAttribute;
//import org.springframework.transaction.interceptor.TransactionAttributeSource;
//import org.springframework.transaction.interceptor.TransactionInterceptor;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 数据库配置基础类
// *
// * @author zuihou
// * @date 2018/10/24
// */
//public abstract class BaseDbConfiguration {
//
//    /**
//     * 事务超时时间 单位秒
//     */
//    protected static final int TX_METHOD_TIMEOUT = 60 * 60;
//    /**
//     * 测试环境
//     */
//    protected static final String[] DEV_PROFILES = new String[]{"dev"};
//    private static final List<Class<? extends Annotation>> AOP_POINTCUT_ANNOTATIONS = new ArrayList<>(2);
//    private static final String TX_BASE_PACKAGE = "com.github.zuihou";
//
//    static {
//        //事务在controller层开启。
//        AOP_POINTCUT_ANNOTATIONS.add(RestController.class);
//        AOP_POINTCUT_ANNOTATIONS.add(Controller.class);
//    }
//
//    @Value("${spring.profiles.active:dev}")
//    protected String profiles;
//
//    /**
//     * 将like类型处理器加入到mybatis中
//     *
//     * @param sessionFactory
//     */
//    private static void addTypeHandler(final MybatisSqlSessionFactoryBean sessionFactory) {
//        TypeHandler<?>[] typeHandlers = new TypeHandler[3];
//        typeHandlers[0] = new LeftLikeTypeHandler();
//        typeHandlers[1] = new RightLikeTypeHandler();
//        typeHandlers[2] = new FullLikeTypeHandler();
//        sessionFactory.setTypeHandlers(typeHandlers);
//
//
//        Class<?>[] typeAliases = new Class[3];
//        typeAliases[0] = LeftLikeTypeHandler.class;
//        typeAliases[1] = RightLikeTypeHandler.class;
//        typeAliases[2] = FullLikeTypeHandler.class;
//        sessionFactory.setTypeAliases(typeAliases);
//    }
//
//    public int getTxTimeOut() {
//        return TX_METHOD_TIMEOUT;
//    }
//
//    protected TransactionInterceptor txAdvice(PlatformTransactionManager transactionManager) {
//        return new TransactionInterceptor(transactionManager, transactionAttributeSource());
//    }
//
//    private TransactionAttributeSource transactionAttributeSource() {
//        /*当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务*/
//        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
//        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Throwable.class)));
//        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        requiredTx.setTimeout(getTxTimeOut());
//        Map<String, TransactionAttribute> txMap = new HashMap<>(32);
//        txMap.put("add*", requiredTx);
//        txMap.put("save*", requiredTx);
//        txMap.put("insert*", requiredTx);
//        txMap.put("create*", requiredTx);
//        txMap.put("update*", requiredTx);
//        txMap.put("edit*", requiredTx);
//        txMap.put("upload*", requiredTx);
//        txMap.put("delete*", requiredTx);
//        txMap.put("remove*", requiredTx);
//        txMap.put("clean*", requiredTx);
//        txMap.put("recycle*", requiredTx);
//        txMap.put("batch*", requiredTx);
//        txMap.put("mark*", requiredTx);
//        txMap.put("disable*", requiredTx);
//        txMap.put("enable*", requiredTx);
//        txMap.put("handle*", requiredTx);
//        txMap.put("syn*", requiredTx);
//        txMap.put("reg*", requiredTx);
//        txMap.put("gen*", requiredTx);
//        txMap.put("*Tx", requiredTx);
//
//        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
//        readOnlyTx.setReadOnly(true);
//        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);
//        //除了上面的事物以外，都走只读事物
//        txMap.put("*", readOnlyTx);
//        NameMatchTransactionAttributeSource txTransactionAttributeSource = new NameMatchTransactionAttributeSource();
//        txTransactionAttributeSource.setNameMap(txMap);
//        return txTransactionAttributeSource;
//    }
//
//    private ClassFilter builderClassFilter() {
//        Logger log = getLog();
//        return (clazz) -> {
//            if (!clazz.getName().startsWith(TX_BASE_PACKAGE)) {
//                return false;
//            }
//            for (Class<? extends Annotation> aop : AOP_POINTCUT_ANNOTATIONS) {
//                if (clazz.getAnnotation(aop) == null) {
//                    continue;
//                }
//                if (log != null) {
//                    log.debug("允许带事务的类为：{}", clazz);
//                }
//                return true;
//            }
//            return false;
//        };
//    }
//
//    public Advisor txAdviceAdvisor(PlatformTransactionManager transactionManager) {
//        return new DefaultPointcutAdvisor(new Pointcut() {
//            private final ClassFilter filter = builderClassFilter();
//
//            @Override
//            public MethodMatcher getMethodMatcher() {
//                return MethodMatcher.TRUE;
//            }
//
//            @Override
//            public ClassFilter getClassFilter() {
//                return filter;
//            }
//        }, txAdvice(transactionManager));
//    }
//
//    protected Logger getLog() {
//        return null;
//    }
//
//    protected SqlSessionFactory setMybatisSqlSessionFactoryBean(MybatisSqlSessionFactoryBean sqlSessionFactory,
//                                                                String[] resourceLocationPatterns,
//                                                                GlobalConfig globalConfig,
//                                                                MetaObjectHandler myMetaObjectHandler) throws Exception {
//        Resource[] all = new Resource[]{};
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        for (String locationPattern : resourceLocationPatterns) {
//            all = ArrayUtil.addAll(all, resolver.getResources(locationPattern));
//        }
//        sqlSessionFactory.setMapperLocations(all);
//
//        MybatisConfiguration configuration = new MybatisConfiguration();
//        configuration.setJdbcTypeForNull(JdbcType.NULL);
//        configuration.setMapUnderscoreToCamelCase(true);
//        configuration.setCacheEnabled(false);
//        sqlSessionFactory.setConfiguration(configuration);
//        List<Interceptor> list = new ArrayList<>();
//        list.add(paginationInterceptor());
//
//        DataScopeInterceptor dataScopeInterceptor = dataScopeInterceptor();
//        if (dataScopeInterceptor != null) {
//            list.add(dataScopeInterceptor);
//        }
//
//        //开发环境
//        if (ArrayUtil.contains(DEV_PROFILES, profiles)) {
//        } else {
//            //演示环境
//            list.add(getWriteInterceptor());
//        }
//        sqlSessionFactory.setPlugins(list.toArray(new Interceptor[list.size()]));
//        globalConfig.setMetaObjectHandler(myMetaObjectHandler);
//        sqlSessionFactory.setGlobalConfig(globalConfig);
//
//        addTypeHandler(sqlSessionFactory);
//        return sqlSessionFactory.getObject();
//    }
//
//    /**
//     * 演示环境权限拦截器
//     *
//     * @return
//     */
//    @Bean
//    public WriteInterceptor getWriteInterceptor() {
//        return new WriteInterceptor();
//    }
//
//    /**
//     * 分页插件，自动识别数据库类型
//     * 多租户，请参考官网【插件扩展】
//     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        List<ISqlParser> sqlParserList = new ArrayList<>();
//
//        // sqlParserList.add(new BlockAttackSqlParser()); 攻击 SQL 阻断解析器、加入解析链
//
//        //动态"表名" 插件 来实现 租户schema切换
//        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
//        sqlParserList.add(dynamicTableNameParser);
//        paginationInterceptor.setSqlParserList(sqlParserList);
//
//        return paginationInterceptor;
//    }
//
//    /**
//     * 数据权限插件
//     *
//     * @return DataScopeInterceptor
//     */
//    @Bean
//    public DataScopeInterceptor dataScopeInterceptor() {
//        return getDataScopeInterceptor();
//    }
//
//    /**
//     * 待子类实现
//     *
//     * @return
//     */
//    public DataScopeInterceptor getDataScopeInterceptor() {
//        return null;
//    }
//
//    /**
//     * Mybatis Plus 注入器
//     *
//     * @param idGenerate
//     * @return
//     */
//    @Bean("myMetaObjectHandler")
//    public MetaObjectHandler getMyMetaObjectHandler(@Qualifier("snowflakeIdGenerate") IdGenerate<Long> idGenerate) {
//        return new MyMetaObjectHandler(idGenerate);
//    }
//
//    /**
//     * id生成 机器码， 单机配置1即可。 集群部署，每个实例自增1即可。
//     *
//     * @param machineCode
//     * @return
//     */
//    @Bean("snowflakeIdGenerate")
//    public IdGenerate getIdGenerate(@Value("${id-generator.machine-code:1}") Long machineCode) {
//        return new SnowflakeIdGenerate(machineCode);
//    }
//
//    protected GlobalConfig defGlobalConfig() {
//        GlobalConfig conf = new GlobalConfig();
//        GlobalConfig.DbConfig config = new GlobalConfig.DbConfig();
//        config.setIdType(IdType.INPUT);
//        config.setInsertStrategy(FieldStrategy.NOT_NULL);
//        config.setUpdateStrategy(FieldStrategy.NOT_NULL);
//        config.setSelectStrategy(FieldStrategy.NOT_EMPTY);
////        config.setColumnFormat(LIKE);
////        config.setColumnLike(true);
////        conf.setSqlParserCache(true);
//        conf.setDbConfig(config);
//        return conf;
//    }
//
//    @Bean
//    public TenantWebMvcConfigurer getTenantWebMvcConfigurer() {
//        return new TenantWebMvcConfigurer();
//    }
//}
