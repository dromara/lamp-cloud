package com.github.zuihou.authority.dao.defaults;

import com.baomidou.mybatisplus.annotation.SqlParser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 初始化数据库DAO
 *
 * @author zuihou
 * @date 2019/09/02
 */
@Repository
@SqlParser(filter = true)
public interface InitDbMapper {
    /**
     * 创建数据库
     *
     * @param database
     * @return
     */
    int createDatabase(@Param("database") String database);


    /**
     * 删除数据库
     *
     * @param database
     * @return
     */
    int dropDatabase(@Param("database") String database);

}
