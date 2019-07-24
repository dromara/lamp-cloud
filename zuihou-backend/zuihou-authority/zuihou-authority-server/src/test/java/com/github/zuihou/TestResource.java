package com.github.zuihou;

import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.zuihou.authority.dao.auth.MenuMapper;
import com.github.zuihou.authority.dao.auth.ResourceMapper;
import com.github.zuihou.authority.dao.auth.UserMapper;
import com.github.zuihou.authority.entity.auth.Resource;
import com.github.zuihou.authority.entity.common.OptLog;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.enumeration.auth.ResourceType;
import com.github.zuihou.authority.service.auth.ResourceService;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.authority.service.core.OrgService;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.dozer.DozerUtils;
import com.github.zuihou.log.entity.OptLogDTO;
import com.github.zuihou.utils.NumberHelper;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/06/26
 */


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TestResource {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private DozerUtils dozer;

    @Test
    public void testWrapper() {
        Resource build = Resource.builder().code("123%456").name("nide %z").build();
        LbqWrapper<Resource> ignore = Wraps.lbQ(build).ignore(Resource::setCode).eq(Resource::getCode, build.getCode());
        resourceService.list(ignore);
    }

    @Test
    public void testfindChildren() {
        List<Org> children = orgService.findChildren(Arrays.asList(101L));
        System.out.println(children.size());
    }

    @Test
    public void testObjlist() {
        for (int i = 0; i < 20; i++) {
            List<Long> list = orgService.listObjs(NumberHelper::longValueOf0);
            System.out.println(list.size());
        }
        System.out.println("endendendendend");
    }

    @Test
    public void testDelete() {
//        boolean flag = userService.removeById(2221L);
//        System.out.println(flag);
//        boolean flag2 = resourceService.removeById(32L);
//        System.out.println(flag2);
        resourceService.update(Wraps.<Resource>lbU().set(Resource::getMenuId, null).eq(Resource::getId, 1L));
//        resourceService.updateById(Resource.builder().menuId(null).describe("1").id(1L).build());
    }

    @Test
    public void dozerTest() {
        OptLogDTO dot = new OptLogDTO();
        dot.setHttpMethod("POST");
        dot.setType("EX");

        OptLog opt = dozer.map(dot, OptLog.class);
        System.out.println(opt.getHttpMethod());
        System.out.println(opt.getType());

    }


    @Test
    public void test3() {


        LbqWrapper<Resource> query2 = Wraps.lbQ(Resource.builder().name("新增").build());
        List<Resource> resources2 = resourceMapper.selectList(query2);
        System.out.println(resources2.size());


        LbqWrapper<Resource> query = Wraps.lbQ(Resource.builder().name("%").resourceType(ResourceType.BUTTON).build());
        List<Resource> resources = resourceMapper.selectList(query);
        System.out.println(resources.size());


    }


    @Test
    public void test2() {
        List<Resource> list = resourceService.list(
                Wrappers.lambdaQuery(Resource.builder()
                        .code("aaa")
                        .name("vvv")
                        .build()));
//        List<Resource> list = resourceService.list(
//                Wraps.lbQ(Resource.builder()
//                        .code("aaa")
//                        .name("vvv")
//                        .build()));

        System.out.println(list.size());
    }
}
