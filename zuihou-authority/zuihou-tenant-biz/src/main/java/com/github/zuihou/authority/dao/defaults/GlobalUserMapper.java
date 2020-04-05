package com.github.zuihou.authority.dao.defaults;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.github.zuihou.authority.entity.defaults.GlobalUser;
import com.github.zuihou.base.mapper.SuperMapper;
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
