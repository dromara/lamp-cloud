package top.tangyh.lamp.system.mapper.system;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.springframework.stereotype.Repository;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.system.entity.system.DefDict;

/**
 * <p>
 * Mapper 接口
 * 字典
 * </p>
 *
 * @author zuihou
 * @date 2021-10-04
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefDictMapper extends SuperMapper<DefDict> {

}
