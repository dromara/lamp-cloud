package top.tangyh.lamp.userinfo.dao;

import org.springframework.stereotype.Repository;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.model.entity.base.SysResource;
import top.tangyh.lamp.model.vo.query.ResourceQueryDTO;

import java.util.List;

/**
 * 资源操作类
 *
 * @author tangyh
 * @version v1.0
 * @date 2022/4/24 11:25 AM
 * @create [2022/4/24 11:25 AM ] [tangyh] [初始创建]
 */
@Repository
public interface ResourceHelperMapper extends SuperMapper<SysResource> {

    /**
     * 查询 拥有的资源
     *
     * @param resource 查询参数
     * @return 可用资源
     */
    List<SysResource> findVisibleResource(ResourceQueryDTO resource);
}
