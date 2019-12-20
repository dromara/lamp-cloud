package com.github.zuihou;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.zuihou.authority.entity.defaults.GlobalUser;
import com.github.zuihou.authority.service.defaults.GlobalUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

// import com.github.zuihou.database.mybatis.conditions.Wraps;
// import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class DemoTest {
    private static final String SUPER_TENANT = "admin";
    @Autowired
    private GlobalUserService globalUserService;

    // @Test
    public void test() {
        String account = "admin";
        GlobalUser user =
                globalUserService.getOne(
                        Wrappers.<GlobalUser>lambdaQuery()
                                .eq(GlobalUser::getAccount, account)
                                .eq(GlobalUser::getTenantCode, SUPER_TENANT));
        System.out.println(user.toString());
    }

    @Test
    public void test2() {
        String account = "admin";
        List<GlobalUser> list = globalUserService.list(Wrappers.lambdaQuery(GlobalUser.builder().account(account).tenantCode(SUPER_TENANT).build()));
        List<GlobalUser> list1 =
                globalUserService.list(
                        Wrappers.<GlobalUser>lambdaQuery().eq(GlobalUser::getAccount, account));

        System.out.println(list.size());
    }
}
