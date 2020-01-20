//package com.github.zuihou;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.github.zuihou.authority.dao.auth.MenuMapper;
//import com.github.zuihou.authority.dao.auth.ResourceMapper;
//import com.github.zuihou.authority.dao.auth.RoleMapper;
//import com.github.zuihou.authority.dao.auth.UserMapper;
//import com.github.zuihou.authority.dao.common.AreaMapper;
//import com.github.zuihou.authority.entity.auth.Resource;
//import com.github.zuihou.authority.entity.auth.User;
//import com.github.zuihou.authority.entity.common.Area;
//import com.github.zuihou.authority.entity.common.DictionaryItem;
//import com.github.zuihou.authority.entity.common.OptLog;
//import com.github.zuihou.authority.entity.core.Org;
//import com.github.zuihou.authority.service.auth.ResourceService;
//import com.github.zuihou.authority.service.auth.UserService;
//import com.github.zuihou.authority.service.common.impl.AreaServiceImpl;
//import com.github.zuihou.authority.service.core.OrgService;
//import com.github.zuihou.context.BaseContextHandler;
//import com.github.zuihou.database.mybatis.conditions.Wraps;
//import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
//import com.github.zuihou.database.mybatis.conditions.update.LbuWrapper;
//import com.github.zuihou.dozer.DozerUtils;
//import com.github.zuihou.log.entity.OptLogDTO;
//import com.github.zuihou.model.RemoteData;
//import com.github.zuihou.remotedata.core.InjectionCore;
//import com.github.zuihou.remotedata.core.RemoteCore;
//import com.github.zuihou.utils.NumberHelper;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
///**
// * 测试类
// *
// * @author zuihou
// * @date 2019/06/26
// */
//
//
//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//@Slf4j
//public class TestRemoteData {
//
//    @Autowired
//    private DozerUtils dozer;
//    @Autowired
//    private AreaMapper areaMapper;
//    @Before
//    public void setTenant() {
//        BaseContextHandler.setTenant("0000");
//        BaseContextHandler.setDatabase("zuihou_base");
//    }
//
//    @Test
//    public void testAreaGet() {
//        Area area = areaMapper.selectById(1L);
//        System.out.println(area);
//    }
//
//    @Test
//    public void testAreaList() {
//        List<Area> areas = areaMapper.selectList(null);
//        System.out.println(areas);
//    }
//
//    @Test
//    public void testAreaPage() {
//        IPage<Area> page = new Page(1, 20);
//        areaMapper.selectPage(page, null);
//        System.out.println(page.getRecords());
//    }
//
//    @Test
//    public void testAreaSave() {
//        Area area = Area.builder()
//                .code("aaaa")
//                .name("vvvvv")
//                .build();
//        area.setRemoteCreateUser(new RemoteData<>(3L));
//        area.setRemoteUpdateUser(new RemoteData<>(2L));
//        area.setType(new RemoteData<>("HAHA"));
//
//        int insert = areaMapper.insert(area);
//        System.out.println(area);
//    }
//
//    @Test
//    public void testAreaUpdate() {
//        Area area = Area.builder()
//                .id(668136559643459617L)
//                .code("bb")
//                .name("ddd")
//                .build();
//        area.setRemoteCreateUser(new RemoteData<>(666L));
//        area.setRemoteUpdateUser(new RemoteData<>(4L));
////        area.setType(new RemoteData<>("null"));
//
//        int insert = areaMapper.updateById(area);
//        System.out.println(area);
//    }
//
//    @Test
//    public void testAreaUpdateWrp() {
//        LbuWrapper<Area> up = Wraps.lbU();
//        up.set(Area::getType, new RemoteData<>(""));
//        up.set(Area::getRemoteCreateUser, new RemoteData<>(null));
//        up.eq(Area::getId, 668136559643459617L);
//        int insert = areaMapper.update(null, up);
//        System.out.println(insert);
//    }
//
//
//    @Autowired
//    private RemoteCore remoteCore;
//    @Autowired
//    private InjectionCore injectionComponent;
//
//    @Test
//    public void getOne() throws Exception {
//        Area area = Area.builder()
//                .id(668136559643459617L)
//                .code("bb")
//                .name("ddd")
//                .build();
//        area.setRemoteCreateUser(new RemoteData<>(3L));
//        area.setRemoteUpdateUser(new RemoteData<>(null));
//        area.setType(new RemoteData<>("COLLEGE"));
//        area.setType2(new RemoteData<>(""));
////        area.setType3("mz_zz");
//
//        DictionaryItem di = DictionaryItem.builder()
//        .code("haha")
//                .build();
//        di.setType(new RemoteData<>("mz_hz"));
//        area.setDictionaryItem(di);
//        area.setDictionaryItem2(di);
//        area.setDictionaryItem3(di);
//
////        Area area4 = Area.builder()
////                .id(4L)
////                .code("444")
////                .name("4444")
////                .build();
////        area4.setRemoteCreateUser(new RemoteData<>(5L));
////
////        Area area3 = Area.builder()
////                .id(3L)
////                .code("333")
////                .name("333")
////                .build();
////        area3.setRemoteCreateUser(new RemoteData<>(5L));
////        area3.setArea(area4);
//
////        Area area2 = Area.builder()
////                .id(2L)
////                .code("222")
////                .name("222")
////                .build();
////        area2.setRemoteCreateUser(new RemoteData<>(5L));
////        area2.setArea(area);
////        area.setArea(area2);
//
////        remoteCore.injectionOne(Area.class, area);
//        injectionComponent.injection(area);
//
//        log.info("area={}", area);
//    }
//
//    @Autowired
//    private AreaServiceImpl areaService;
//    @Test
//    public void remoteResultByGet(){
//        Area area = areaService.get();
//        System.out.println(area);
//    }
//    @Test
//    public void remoteResultByList(){
//        List<Area> list = areaService.list2();
//        System.out.println(list);
//    }
//    @Test
//    public void remoteResultByPage(){
//        IPage<Area> page = areaService.page();
//        System.out.println(page);
//    }
//
//}
