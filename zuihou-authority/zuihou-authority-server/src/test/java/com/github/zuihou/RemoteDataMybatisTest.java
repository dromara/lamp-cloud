package com.github.zuihou;

import com.github.zuihou.authority.api.OrgApi;
import com.github.zuihou.authority.dao.auth.MenuMapper;
import com.github.zuihou.authority.dao.auth.ResourceMapper;
import com.github.zuihou.authority.dao.auth.RoleMapper;
import com.github.zuihou.authority.dao.auth.UserMapper;
import com.github.zuihou.authority.dao.common.AreaMapper;
import com.github.zuihou.authority.dao.core.StationMapper;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.entity.core.Station;
import com.github.zuihou.authority.service.auth.ResourceService;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.authority.service.core.OrgService;
import com.github.zuihou.authority.service.core.impl.StationServiceImpl;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.injection.core.InjectionCore;
import com.github.zuihou.model.RemoteData;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest(classes = AuthorityApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
@WebAppConfiguration
public class RemoteDataMybatisTest {
    @Autowired
    ResourceService resourceService;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired

    private OrgService orgService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private StationMapper stationMapper;
    @Autowired
    private StationServiceImpl stationServiceImpl;
    @Autowired
    private InjectionCore injectionCore;
    @Autowired
    private OrgApi orgApi;

    @Before
    public void setTenant() {
        BaseContextHandler.setTenant("0000");
    }

    @Test
    public void testGet() {
        User user = new User();
//        user.setSex(Sex.W);
        LbqWrapper<User> wrapper = Wraps.lbQ(user);
        wrapper
//                .geHeader(User::getCreateTime, LocalDateTime.MIN)
//                .leFooter(User::getCreateTime, LocalDateTime.MAX)
                .like(User::getAccount, "zuihou")
                .nested(i -> i.like(User::getName, "")
                        .or().like(User::getMobile, "")
                ).orderByDesc(User::getCreateTime);

        List<User> list = userService.list(wrapper);

        System.out.println(list);

    }

    @Test
    public void testFeign() {
        Set<Serializable> ids = new HashSet<>();
        Map<Serializable, Object> orgByIds = orgApi.findOrgByIds(ids);
        System.out.println(orgByIds.size());
    }

    @Test
    public void testSave3() {
        Station station = Station.builder()
                .name("test4")
                .orgId(new RemoteData<>(4L))
                .build();
        stationMapper.insert(station);
    }


    @Test
    public void test345() {
        RemoteData<Long, Station> stationData = new RemoteData<>(101L);

        Station station = stationMapper.selectById(stationData);
        System.out.println(station);



//        injectionCore.injection(station2);
//        System.out.println(station2);
    }


    @Test
    public void test343() {
        TestModel obj = new TestModel();

        obj.setEducation(new RemoteData<>("COLLEGE"));
        obj.setEducation2("BOSHI");

        obj.setStation(new RemoteData<>(101L));
        obj.setError(new RemoteData<>(101L));

        injectionCore.injection(obj);
        System.out.println(obj);
    }


}
