package com.github.zuihou.admin.rest.authority.api;

import com.github.pagehelper.PageInfo;
import com.github.zuihou.admin.rest.authority.dto.MenuDto;
import com.github.zuihou.admin.rest.authority.dto.MenuGroupDto;
import com.github.zuihou.admin.rest.authority.dto.MenuGroupSaveReqDto;
import com.github.zuihou.admin.rest.authority.dto.MenuGroupUpdateReqDto;
import com.github.zuihou.admin.rest.authority.dto.MenuSaveDto;
import com.github.zuihou.admin.rest.authority.dto.MenuTreeDto;
import com.github.zuihou.admin.rest.authority.dto.MenuUpdateDto;
import com.github.zuihou.admin.rest.authority.dto.ResourceDto;
import com.github.zuihou.admin.rest.authority.dto.ResourcePageReqDto;
import com.github.zuihou.admin.rest.authority.dto.ResourceSaveReqDto;
import com.github.zuihou.admin.rest.authority.dto.ResourceUpdateReqDto;
import com.github.zuihou.base.Result;
import com.github.zuihou.page.plugins.openapi.OpenApiReq;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zuihou
 * @createTime 2017-12-15 11:17
 */
@FeignClient(name = "${zuihou.admin.feign.server:zuihou-admin-server}", fallback = AdminResourcesApi.class)
public interface AdminResourcesApi {
    /**
     * 根据id查询菜单组
     * @param id
     * @return
     */
    @RequestMapping(value = "/menu/group/get", method = RequestMethod.GET)
    Result<MenuGroupDto> groupGet(@RequestParam("id") Long id);

    /**
     * 查询所有的菜单组
     * @return
     */
    @RequestMapping(value = "/menu/group/list", method = RequestMethod.GET)
    Result<List<MenuGroupDto>> listGroup();

    /**
     * 添加菜单组
     * @param menuDto
     * @return
     */
    @RequestMapping(value = "/menu/group/save", method = RequestMethod.POST)
    Result<MenuGroupDto> groupSave(@RequestBody MenuGroupSaveReqDto menuDto);

    /**
     * 修改菜单组
     * @param menuDto
     * @return
     */
    @RequestMapping(value = "/menu/group/update", method = RequestMethod.POST)
    Result<Boolean> groupUpdate(@RequestBody MenuGroupUpdateReqDto menuDto);

    /**
     * 删除指定菜单组
     * @param id
     * @return
     */
    @RequestMapping(value = "/menu/group/remove", method = RequestMethod.POST)
    Result<Boolean> groupRemove(@RequestParam("id") Long id);

    /**
     * 根据id查询菜单
     * @param id
     * @return
     */
    @RequestMapping(value = "/menu/get", method = RequestMethod.GET)
    Result<MenuDto> get(@RequestParam("id") Long id);

    /**
     * 查询所有的菜单树列表
     * @param menuGroupCode
     * @return
     */
    @RequestMapping(value = "/menu/findTree", method = RequestMethod.GET)
    Result<List<MenuTreeDto>> findTree(@RequestParam("menuGroupCode") String menuGroupCode);

    /**
     * 查询所有菜单列表
     * @param menuGroupCode
     * @return
     */
    @RequestMapping(value = "/menu/list", method = RequestMethod.GET)
    Result<List<MenuDto>> list(@RequestParam("menuGroupCode") String menuGroupCode);

    /**
     * 根据父id 查询菜单列表
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/menu/findByParentId", method = RequestMethod.GET)
    Result<List<MenuDto>> findByParentId(@RequestParam("parentId") Long parentId);

    /**
     * 新增菜单
     * @param menuDto
     * @return
     */
    @RequestMapping(value = "/menu/save", method = RequestMethod.POST)
    Result<MenuDto> save(@RequestBody MenuSaveDto menuDto);

    /**
     * 修改菜单
     * @param menuDto
     * @return
     */
    @RequestMapping(value = "/menu/update", method = RequestMethod.POST)
    Result<Boolean> update(@RequestBody MenuUpdateDto menuDto);

    /**
     * 删除指定菜单
     * @param id
     * @return
     */
    @RequestMapping(value = "/menu/remove", method = RequestMethod.POST)
    Result<Boolean> remove(@RequestParam("id") Long id);

    /**
     * 查询资源分页信息
     * @param openApiReq
     * @param resourcePageReqDto
     * @return
     */
    @RequestMapping(value = "/menu/resource/page", method = RequestMethod.GET)
    Result<PageInfo<ResourceDto>> page(OpenApiReq openApiReq, ResourcePageReqDto resourcePageReqDto);

    /**
     * 根据id查询资源
     * @param id
     * @return
     */
    @RequestMapping(value = "/menu/resource/get", method = RequestMethod.GET)
    Result<ResourceDto> resourceGet(@RequestParam("id") Long id);

    /**
     * 新增资源
     * @param resourceDto
     * @return
     */
    @RequestMapping(value = "/menu/resource/save", method = RequestMethod.POST)
    Result<ResourceDto> resourceSave(@RequestBody ResourceSaveReqDto resourceDto);

    /**
     * 修改资源
     * @param resourceDto
     * @return
     */
    @RequestMapping(value = "/menu/resource/update", method = RequestMethod.POST)
    Result<Boolean> resourceUpdate(@RequestBody ResourceUpdateReqDto resourceDto);

    /**
     * 删除指定资源
     * @param id
     * @return
     */
    @RequestMapping(value = "/menu/resource/remove", method = RequestMethod.POST)
    Result<Boolean> resourceRemove(@RequestParam("id") Long id);

    /**
     * 查询指定applicationId的菜单列表
     * @param applicationId
     * @param menuGroupCode
     * @return
     */
    @RequestMapping(value = "/menu/listMenu", method = RequestMethod.GET)
    Result<List<MenuDto>> listMenu();
    /**
     * 查询指定applicationId的菜单列表
     * @param applicationId
     * @param menuGroupCode
     * @return
     */
    @RequestMapping(value = "/menu/tree", method = RequestMethod.GET)
    Result<List<MenuTreeDto>> treeMenu();

    /**
     * 查询指定applicationId的资源列表
     * @param applicationId
     * @param menuGroupCode
     * @return
     */
    @RequestMapping(value = "/menu/resource/list", method = RequestMethod.GET)
    Result<List<ResourceDto>> listResource();

}
