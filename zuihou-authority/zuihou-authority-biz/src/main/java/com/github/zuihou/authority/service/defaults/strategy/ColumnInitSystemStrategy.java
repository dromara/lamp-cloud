package com.github.zuihou.authority.service.defaults.strategy;

import com.github.zuihou.authority.entity.auth.Menu;
import com.github.zuihou.authority.service.auth.MenuService;
import com.github.zuihou.authority.service.auth.ResourceService;
import com.github.zuihou.authority.service.auth.RoleAuthorityService;
import com.github.zuihou.authority.service.auth.RoleService;
import com.github.zuihou.authority.service.common.DictionaryItemService;
import com.github.zuihou.authority.service.common.DictionaryService;
import com.github.zuihou.authority.service.defaults.InitSystemStrategy;
import com.github.zuihou.context.BaseContextHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("COLUMN")
@Slf4j
public class ColumnInitSystemStrategy implements InitSystemStrategy {
    @Autowired
    private MenuService menuService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleAuthorityService roleAuthorityService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private DictionaryItemService itemService;


    @Override
    public boolean init(String tenant) {
        // 初始化数据
        //1, 生成并关联 ID TENANT

        BaseContextHandler.setTenant(tenant);
        // 菜单 资源 角色 角色_资源 字典 参数
        List<Menu> menuList = new ArrayList<>();
        menuList.add(Menu.builder().label("").describe("").isPublic(true).path("").component("").icon("").build());
        menuService.saveBatch(menuList);

        return true;
    }

    @Override
    public boolean reset(String tenant) {
        return true;
    }
}
