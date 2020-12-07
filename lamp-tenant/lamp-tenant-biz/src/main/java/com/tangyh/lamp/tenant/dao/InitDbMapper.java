package com.tangyh.lamp.tenant.dao;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 初始化数据库DAO
 *
 * @author zuihou
 * @date 2019/09/02
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface InitDbMapper {
    /**
     * 创建数据库
     *
     * @param database 数据库
     * @return 创建数量
     */
    int createDatabase(@Param("database") String database);


    /**
     * 删除数据库
     *
     * @param database 数据库
     * @return 删除数量
     */
    int dropDatabase(@Param("database") String database);

}
