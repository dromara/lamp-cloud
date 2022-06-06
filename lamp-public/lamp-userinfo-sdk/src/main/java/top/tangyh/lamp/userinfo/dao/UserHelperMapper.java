package top.tangyh.lamp.userinfo.dao;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.springframework.stereotype.Repository;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.model.entity.base.SysUser;

/**
 * @author tangyh
 * @version v1.0
 * @date 2022/4/24 11:25 AM
 * @create [2022/4/24 11:25 AM ] [tangyh] [初始创建]
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface UserHelperMapper extends SuperMapper<SysUser> {

}
