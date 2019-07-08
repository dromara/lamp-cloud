package com.github.zuihou;

import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.zuihou.authority.dao.auth.MenuMapper;
import com.github.zuihou.authority.dao.auth.ResourceMapper;
import com.github.zuihou.authority.entity.auth.Resource;
import com.github.zuihou.authority.entity.common.OptLog;
import com.github.zuihou.authority.enumeration.auth.ResourceType;
import com.github.zuihou.authority.service.auth.ResourceService;
import com.github.zuihou.common.utils.context.DozerUtils;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.log.entity.OptLogDTO;

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
    private DozerUtils dozer;

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
