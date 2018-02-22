package com.github.zuihou.admin.rest.authority.api;

import com.github.pagehelper.PageInfo;
import com.github.zuihou.admin.rest.authority.dto.MenuDTO;
import com.github.zuihou.admin.rest.authority.dto.MenuGroupDTO;
import com.github.zuihou.admin.rest.authority.dto.MenuGroupSaveReqDTO;
import com.github.zuihou.admin.rest.authority.dto.MenuGroupUpdateReqDTO;
import com.github.zuihou.admin.rest.authority.dto.MenuSaveDTO;
import com.github.zuihou.admin.rest.authority.dto.MenuTreeDTO;
import com.github.zuihou.admin.rest.authority.dto.MenuUpdateDTO;
import com.github.zuihou.admin.rest.authority.dto.ResourceDTO;
import com.github.zuihou.admin.rest.authority.dto.ResourcePageReqDTO;
import com.github.zuihou.admin.rest.authority.dto.ResourceSaveReqDTO;
import com.github.zuihou.admin.rest.authority.dto.ResourceUpdateReqDTO;
import com.github.zuihou.base.Result;
import com.github.zuihou.page.plugins.openapi.OpenApiReq;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zuihou
 * @createTime 2017-12-15 11:17
 */
@FeignClient(name = "${zuihou.feign-server.gateway:zuihou-gateway-server}", fallback = AdminResourcesApi.class)
public interface AdminResourcesApi {
    /**
     * 根据id查询菜单组
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/admin/menu/group/{id}", method = RequestMethod.GET)
    Result<MenuGroupDTO> groupGet(@PathVariable(value = "id") Long id);

    /**
     * 查询所有的菜单组
     * @return
     */
    @RequestMapping(value = "/api/admin/menu/group", method = RequestMethod.GET)
    Result<List<MenuGroupDTO>> listGroup();

    /**
     * 添加菜单组
     * @param menuDto
     * @return
     */
    @RequestMapping(value = "/api/admin/menu/group", method = RequestMethod.POST)
    Result<MenuGroupDTO> groupSave(@RequestBody MenuGroupSaveReqDTO menuDto);

    /**
     * 修改菜单组
     * @param menuDto
     * @return
     */
    @RequestMapping(value = "/api/admin/menu/group", method = RequestMethod.PUT)
    Result<Boolean> groupUpdate(@RequestBody MenuGroupUpdateReqDTO menuDto);

    /**
     * 删除指定菜单组
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/admin/menu/group/{id}", method = RequestMethod.DELETE)
    Result<Boolean> groupRemove(@PathVariable(value = "id")  Long id);



    /**
     * 根据id查询菜单
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/admin/menu/{id}", method = RequestMethod.GET)
    Result<MenuDTO> get(@PathVariable(value = "id")  Long id);



    /**
     * 根据父id 查询菜单列表
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/api/admin/menu/{parentId}/children", method = RequestMethod.GET)
    Result<List<MenuDTO>> findByParentId(@PathVariable(value = "parentId")  Long parentId);

    /**
     * 新增菜单
     * @param menuDto
     * @return
     */
    @RequestMapping(value = "/api/admin/menu", method = RequestMethod.POST)
    Result<MenuDTO> save(@RequestBody MenuSaveDTO menuDto);

    /**
     * 修改菜单
     * @param menuDto
     * @return
     */
    @RequestMapping(value = "/api/admin/menu", method = RequestMethod.PUT)
    Result<Boolean> update(@RequestBody MenuUpdateDTO menuDto);

    /**
     * 删除指定菜单
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/admin/menu/{id}", method = RequestMethod.DELETE)
    Result<Boolean> remove(@PathVariable(value = "id")  Long id);
    /**
     * 查询所有的菜单树列表
     * @param menuGroupCode
     * @return
     */
    @RequestMapping(value = "/api/admin/menu/tree", method = RequestMethod.GET)
    Result<List<MenuTreeDTO>> findTree(@RequestParam(value = "menuGroupCode") String menuGroupCode);

    /**
     * 查询所有菜单列表
     * @param menuGroupCode
     * @return
     */
    @RequestMapping(value = "/api/admin/menu/all", method = RequestMethod.GET)
    Result<List<MenuDTO>> list(@RequestParam(value = "menuGroupCode") String menuGroupCode);
    /**
     * 查询指定applicationId的菜单列表
     * @return
     */
    @RequestMapping(value = "/api/admin/menu/self", method = RequestMethod.GET)
    Result<List<MenuDTO>> listMenu();
    /**
     * 查询指定applicationId的菜单列表
     * @return
     */
    @RequestMapping(value = "/api/admin/menu/self/tree", method = RequestMethod.GET)
    Result<List<MenuTreeDTO>> treeMenu();

    /**
     * 查询资源分页信息
     * @param openApiReq
     * @param resourcePageReqDTO
     * @return
     */
    @RequestMapping(value = "/api/admin/menu/resource/page", method = RequestMethod.GET)
    Result<PageInfo<ResourceDTO>> page(OpenApiReq openApiReq, ResourcePageReqDTO resourcePageReqDTO);

    /**
     * 根据id查询资源
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/admin/menu/resource/{id}", method = RequestMethod.GET)
    Result<ResourceDTO> resourceGet(@PathVariable(value = "id") Long id);

    /**
     * 新增资源
     * @param resourceDto
     * @return
     */
    @RequestMapping(value = "/api/admin/menu/resource", method = RequestMethod.POST)
    Result<ResourceDTO> resourceSave(@RequestBody ResourceSaveReqDTO resourceDto);

    /**
     * 修改资源
     * @param resourceDto
     * @return
     */
    @RequestMapping(value = "/api/admin/menu/resource", method = RequestMethod.PUT)
    Result<Boolean> resourceUpdate(@RequestBody ResourceUpdateReqDTO resourceDto);

    /**
     * 删除指定资源
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/admin/menu/resource/{id}", method = RequestMethod.DELETE)
    Result<Boolean> resourceRemove(@PathVariable(value = "id") Long id);
    /**
     * 查询指定applicationId的资源列表
     * @return
     */
    @RequestMapping(value = "/api/admin/menu/resource/self", method = RequestMethod.GET)
    Result<List<ResourceDTO>> listResource();

}
