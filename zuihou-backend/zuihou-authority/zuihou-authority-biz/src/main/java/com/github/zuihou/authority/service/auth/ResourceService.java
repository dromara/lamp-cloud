package com.github.zuihou.authority.service.auth;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuihou.authority.dto.auth.ResourceQueryDTO;
import com.github.zuihou.authority.entity.auth.Resource;

/**
 * <p>
 * 业务接口
 * 资源
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
public interface ResourceService extends IService<Resource> {

    /**
     * 查询 拥有的资源
     *
     * @param resource
     * @return
     */
    List<Resource> findVisibleResource(ResourceQueryDTO resource);

    /**
     * 根据ID查
     *
     * @param id 主键
     * @return
     */
    Resource getByIdWithCache(Long id);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    boolean removeByIdWithCache(Long id);

    /**
     * 修改
     *
     * @param resource
     * @return
     */
    boolean updateWithCache(Resource resource);

    /**
     * 保存
     *
     * @param resource
     * @return
     */
    boolean saveWithCache(Resource resource);


    /**
     * 修改资源中冗余的菜单名称，并清除缓存
     *
     * @param menuId   菜单id
     * @param menuName 菜单名称
     * @return
     */
    boolean updateMenuWithCache(Long menuId, String menuName);
}
