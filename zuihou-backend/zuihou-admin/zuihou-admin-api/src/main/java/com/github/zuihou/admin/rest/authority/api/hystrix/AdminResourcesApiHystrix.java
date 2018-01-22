package com.github.zuihou.admin.rest.authority.api.hystrix;

import com.github.pagehelper.PageInfo;
import com.github.zuihou.admin.rest.authority.api.AdminResourcesApi;
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
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zuihou
 * @createTime 2018-01-02 16:16
 */
@Component
public class AdminResourcesApiHystrix implements AdminResourcesApi {
    @Override
    public Result<MenuGroupDto> groupGet(Long id) {
        return Result.timeout();
    }

    @Override
    public Result<List<MenuGroupDto>> listGroup() {
        return Result.timeout();
    }

    @Override
    public Result<MenuGroupDto> groupSave(MenuGroupSaveReqDto menuDto) {
        return Result.timeout();
    }

    @Override
    public Result<Boolean> groupUpdate(MenuGroupUpdateReqDto menuDto) {
        return Result.timeout();
    }

    @Override
    public Result<Boolean> groupRemove(Long id) {
        return Result.timeout();
    }

    @Override
    public Result<MenuDto> get(Long id) {
        return Result.timeout();
    }

    @Override
    public Result<List<MenuTreeDto>> findTree(String menuGroupCode) {
        return Result.timeout();
    }

    @Override
    public Result<List<MenuDto>> list(String menuGroupCode) {
        return Result.timeout();
    }

    @Override
    public Result<List<MenuDto>> findByParentId(Long parentId) {
        return Result.timeout();
    }

    @Override
    public Result<MenuDto> save(MenuSaveDto menuDto) {
        return Result.timeout();
    }

    @Override
    public Result<Boolean> update(MenuUpdateDto menuDto) {
        return Result.timeout();
    }

    @Override
    public Result<Boolean> remove(Long id) {
        return Result.timeout();
    }

    @Override
    public Result<PageInfo<ResourceDto>> page(OpenApiReq openApiReq, ResourcePageReqDto resourcePageReqDto) {
        return Result.timeout();
    }

    @Override
    public Result<ResourceDto> resourceGet(Long id) {
        return Result.timeout();
    }

    @Override
    public Result<ResourceDto> resourceSave(ResourceSaveReqDto resourceDto) {
        return Result.timeout();
    }

    @Override
    public Result<Boolean> resourceUpdate(ResourceUpdateReqDto resourceDto) {
        return Result.timeout();
    }

    @Override
    public Result<Boolean> resourceRemove(Long id) {
        return Result.timeout();
    }

    @Override
    public Result<List<MenuDto>> listMenu() {
        return Result.timeout();
    }

    @Override
    public Result<List<MenuTreeDto>> treeMenu() {
        return Result.timeout();
    }

    @Override
    public Result<List<ResourceDto>> listResource() {
        return Result.timeout();
    }
}
