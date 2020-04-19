package com.github.zuihou.tenant.dao;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.github.zuihou.base.mapper.SuperMapper;
import com.github.zuihou.tenant.entity.GlobalUser;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 全局账号
 * </p>
 *
 * @author zuihou
 * @date 2019-10-25
 */
@Repository
@SqlParser(filter = true)
public interface GlobalUserMapper extends SuperMapper<GlobalUser> {

}
