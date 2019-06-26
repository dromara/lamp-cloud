package com.github.zuihou;

import java.util.List;

import com.github.zuihou.authority.dao.auth.ResourceMapper;
import com.github.zuihou.authority.dto.auth.ResourceQueryDTO;
import com.github.zuihou.authority.entity.auth.Resource;
import com.github.zuihou.authority.enumeration.auth.ResourceType;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2019/06/26
 */


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TestResource {
    @Autowired
    private ResourceMapper resourceMapper;

    @Test
    public void test() {
        ResourceQueryDTO resource = ResourceQueryDTO.builder()
                .userId(1L).menuId(4L).type(ResourceType.BUTTON).build();
        List<Resource> list = resourceMapper.findVisibleResource(resource);
        System.out.println(list.size());
    }
}
