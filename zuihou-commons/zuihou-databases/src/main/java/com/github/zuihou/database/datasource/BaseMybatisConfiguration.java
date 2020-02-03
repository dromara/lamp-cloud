package com.github.zuihou.database.datasource;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.github.zuihou.database.mybatis.WriteInterceptor;
import com.github.zuihou.database.mybatis.typehandler.FullLikeTypeHandler;
import com.github.zuihou.database.mybatis.typehandler.LeftLikeTypeHandler;
import com.github.zuihou.database.mybatis.typehandler.RightLikeTypeHandler;
import com.github.zuihou.database.parsers.DynamicTableNameParser;
import com.github.zuihou.database.parsers.TenantWebMvcConfigurer;
import com.github.zuihou.database.properties.DatabaseProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis 常用重用拦截器
 *
 * @author zuihou
 * @date 2018/10/24
 */
public abstract class BaseMybatisConfiguration {
    private final DatabaseProperties databaseProperties;

    public BaseMybatisConfiguration(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    /**
     * 演示环境权限拦截器
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "zuihou.database.isNotWrite", havingValue = "true")
    public WriteInterceptor getWriteInterceptor() {
        return new WriteInterceptor();
    }


    /**
     * 分页插件，自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();

        if (this.databaseProperties.getIsBlockAttack()) {
            // 攻击 SQL 阻断解析器 加入解析链
            sqlParserList.add(new BlockAttackSqlParser());
        }

        //动态"表名" 插件 来实现 租户schema切换 加入解析链
        if (this.databaseProperties.getIsMultiTenant()) {
            DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
            sqlParserList.add(dynamicTableNameParser);
        }

        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }


    /**
     * Mybatis Plus 注入器
     *
     * @return
     */
    @Bean("myMetaObjectHandler")
    public MetaObjectHandler getMyMetaObjectHandler() {
        DatabaseProperties.Id id = databaseProperties.getId();
        return new MyMetaObjectHandler(id.getWorkerId(), id.getDataCenterId());
    }

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

    /**
     * 租户信息拦截器
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "zuihou.database.isMultiTenant", havingValue = "true", matchIfMissing = true)
    public TenantWebMvcConfigurer getTenantWebMvcConfigurer() {
        return new TenantWebMvcConfigurer();
    }

    /**
     * Mybatis 自定义的类型处理器： 处理XML中  #{name,typeHandler=leftLike} 类型的参数
     * 用于左模糊查询时使用
     * <p>
     * eg：
     * and name like #{name,typeHandler=leftLike}
     *
     * @return
     */
    @Bean
    public LeftLikeTypeHandler getLeftLikeTypeHandler() {
        return new LeftLikeTypeHandler();
    }

    /**
     * Mybatis 自定义的类型处理器： 处理XML中  #{name,typeHandler=rightLike} 类型的参数
     * 用于右模糊查询时使用
     * <p>
     * eg：
     * and name like #{name,typeHandler=rightLike}
     *
     * @return
     */
    @Bean
    public RightLikeTypeHandler getRightLikeTypeHandler() {
        return new RightLikeTypeHandler();
    }

    /**
     * Mybatis 自定义的类型处理器： 处理XML中  #{name,typeHandler=fullLike} 类型的参数
     * 用于全模糊查询时使用
     * <p>
     * eg：
     * and name like #{name,typeHandler=fullLike}
     *
     * @return
     */
    @Bean
    public FullLikeTypeHandler getFullLikeTypeHandler() {
        return new FullLikeTypeHandler();
    }

//    /**
//     * Mybatis 类型处理器： 处理 RemoteData 类型的字段
//     *
//     * @return
//     */
//    @Bean
//    public RemoteDataTypeHandler getRemoteDataTypeHandler() {
//        return new RemoteDataTypeHandler();
//    }

}
