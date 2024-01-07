package top.tangyh.lamp.system.mapper.application;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.system.entity.application.DefResource;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 资源
 * </p>
 *
 * @author zuihou
 * @date 2021-09-13
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefResourceMapper extends SuperMapper<DefResource> {
    /**
     * 删除 角色-资源关系表
     *
     * @param resourceIds 资源id
     * @return int
     * @author tangyh
     * @date 2021/9/17 10:51 下午
     * @create [2021/9/17 10:51 下午 ] [tangyh] [初始创建]
     */
    int deleteRoleResourceRelByResourceId(@Param("resourceIds") List<Long> resourceIds);

}
