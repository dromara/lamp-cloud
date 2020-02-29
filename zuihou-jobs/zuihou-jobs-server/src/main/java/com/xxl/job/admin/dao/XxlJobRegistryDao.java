package com.xxl.job.admin.dao;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.xxl.job.admin.core.model.XxlJobRegistry;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xuxueli on 16/9/30.
 */
@SqlParser(filter = true)
@Repository
public interface XxlJobRegistryDao {


    int removeDead(@Param("timeout") Integer timeout);

    List<XxlJobRegistry> findAll(@Param("timeout") Integer timeout);

    int registryUpdate(@Param("registryGroup") String registryGroup,
                       @Param("registryKey") String registryKey,
                       @Param("registryValue") String registryValue);

    int registrySave(@Param("registryGroup") String registryGroup,
                     @Param("registryKey") String registryKey,
                     @Param("registryValue") String registryValue);

    int registryDelete(@Param("registryGroup") String registGroup,
                       @Param("registryKey") String registryKey,
                       @Param("registryValue") String registryValue);

}
