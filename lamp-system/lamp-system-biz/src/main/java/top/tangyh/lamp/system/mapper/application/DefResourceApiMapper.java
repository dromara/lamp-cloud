package top.tangyh.lamp.system.mapper.application;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.system.entity.application.DefResourceApi;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 资源接口
 * </p>
 *
 * @author zuihou
 * @date 2021-09-17
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefResourceApiMapper extends SuperMapper<DefResourceApi> {
    /**
     * 查询指定租户下指定应用和指定资源类型的 接口
     *
     * @param applicationIdList 应用ID
     * @param resourceTypes     资源类型
     * @return java.util.List<top.tangyh.lamp.system.entity.application.DefResourceApi>
     * @author tangyh
     * @date 2023/5/19 3:26 PM
     * @create [2023/5/19 3:26 PM ] [tangyh] [初始创建]
     */
    List<DefResourceApi> findResourceApi(@Param("applicationIdList") List<Long> applicationIdList,
                                         @Param("resourceTypes") Collection<String> resourceTypes);
}
