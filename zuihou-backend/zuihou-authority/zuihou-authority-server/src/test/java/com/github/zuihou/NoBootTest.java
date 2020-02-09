package com.github.zuihou;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.StaticLog;
import com.github.zuihou.auth.utils.JwtHelper;
import com.github.zuihou.auth.utils.JwtUserInfo;
import com.github.zuihou.auth.utils.Token;
import com.github.zuihou.authority.dto.auth.VueRouter;
import com.github.zuihou.authority.dto.core.StationPageDTO;
import com.github.zuihou.authority.entity.auth.Menu;
import com.github.zuihou.authority.entity.common.Area;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.entity.core.Station;
import com.github.zuihou.database.parsers.TableNameParser;
import com.github.zuihou.injection.annonation.InjectionField;
import com.github.zuihou.injection.core.InjectionFieldPo;
import com.github.zuihou.model.RemoteData;
import com.github.zuihou.utils.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.hibernate.validator.HibernateValidator;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 没有Spring 环境的测试工具类
 *
 * @author zuihou
 * @date 2019/07/05
 */
@Slf4j
public class NoBootTest {

    @Test
    public void test() {
        TestModel obj = new TestModel();
        obj.setStation(new RemoteData<>(101L));
        obj.setOrg2(new RemoteData<>(101L));

        Field[] fields = ReflectUtil.getFields(obj.getClass());
        for (Field field : fields) {
            InjectionField anno = field.getDeclaredAnnotation(InjectionField.class);
            if (anno == null) {
                continue;
            }
            field.setAccessible(true);

            String api = anno.api();
            Class<?> feign = anno.feign();

            if (StrUtil.isEmpty(api) && Object.class.equals(feign)) {
                log.warn("忽略注入字段: {}.{}", field.getType(), field.getName());
                continue;
            }


        }
    }


    @Test

    public void testFan() {
        StationPageDTO data = StationPageDTO.builder()
//                .orgId(new RemoteData<>(123L, Org.builder().id(123L).build()))
//                .orgId(123L)
                .name("123").describe("ad")
                .build();
        Station station = BeanUtil.toBean(data, Station.class);
        System.out.println(station);
    }

    @Test
    public void testBeanUtil() {

        //10000 - 511
        //50000 - 719
        //100000 - 812
        //1000000 - 2303

        TimeInterval timer = DateUtil.timer();
        for (int i = 0; i <= 1000000; i++) {
            Org org = Org.builder()
                    .name("string")
                    .id(123L + i)
                    .createTime(LocalDateTime.now())
                    .build();
            Station station = Station.builder().id(1L + i).name("nihaoa").createTime(LocalDateTime.now()).orgId(new RemoteData(12L, org)).build();

            StationPageDTO stationPageDTO = new StationPageDTO();

            BeanUtil.copyProperties(station, stationPageDTO);
        }

        long interval = timer.interval();// 花费毫秒数
        long intervalMinute = timer.intervalMinute();// 花费分钟数
        StaticLog.info("本次程序执行 花费毫秒数: {} ,   花费分钟数:{} . ", interval, intervalMinute);
    }

    @Test
    public void testBeanUtilToBean() {
        Menu menu = Menu.builder()
                .id(123L)
                .name("menu")
                .group("group")
                .createTime(LocalDateTime.MAX)
                .icon("aicon")
                .build();

//        VueRouter vueRouter = BeanUtil.toBean(menu, VueRouter.class);

        Map<String, String> map = new HashedMap();
        map.put("name", "path");
//        VueRouter vueRouter = BeanUtil.toBean(VueRouter.class, new ValueProvider<String>(){
//            @Override
//            public Object value(String key, Type valueType) {
//                return "1";
//            }
//
//            @Override
//            public boolean containsKey(String key) {
//                return true;
//            }
//        }, CopyOptions.create().setFieldMapping(map));

        VueRouter vueRouter = new VueRouter();
        BeanUtil.copyProperties(menu, vueRouter, CopyOptions.create().setFieldMapping(map));


        System.out.println(vueRouter);
    }


    @Test
    public void testFanxin() {
        Org org = Org.builder().name("ahaha").build();
        Station station = Station.builder().id(1L).orgId(new RemoteData(12L, org)).build();

        RemoteData orgId = station.getOrg();

        System.out.println(((Org) orgId.getData()).getName());
    }

    public static void main(String[] args) {
//        String field = "name";
//        System.out.println(getField(MenuTreeDTO.class, field));
//
//        System.out.println(ReflectUtil.getField(MenuTreeDTO.class, field));


        String sql = "       SELECT u.id, account, name, mobile, sex\n" +
                "FROM c_auth_user\n" +
                "u\n" +
                "WHERE 1=1\n" +
                "and EXISTS (\n" +
                "select 1 from c_auth_user_role\n" +
                "ur where  u.id = ur.user_id\n" +
                "and ur.role_id = 100\n" +
                ")";


        sql = "insert into c_auth_resource ( id, create_user, create_time, update_user, update_time, code, name, menu_id, describe_)\n" +
                "    values (1, 2, SYSDATE(), 2,SYSDATE(), 'code', 'name', 1, ''\t\t)\n" +
                "    ON DUPLICATE KEY UPDATE " +
                "      name = 'name2',\n" +
                "      describe_ = 'ddd',\n" +
                "      update_user = 3,\n" +
                "      update_time = SYSDATE()";
        sql = "CREATE TABLE `aaa_ba`  (\n" +
                "  `id` bigint(20) NOT NULL COMMENT 'ID',\n" +
                "  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '资源编码\\n规则：\\n链接：\\n数据列：\\n按钮：',\n" +
                "  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '接口名称',\n" +
                "  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID\\n#c_auth_menu',\n" +
                "  `describe_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '接口描述',\n" +
                "  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人id',\n" +
                "  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',\n" +
                "  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人id',\n" +
                "  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',\n" +
                "  PRIMARY KEY (`id`) USING BTREE,\n" +
                "  UNIQUE INDEX `UN_CODE`(`code`) USING BTREE COMMENT '编码唯一'\n" +
                ") ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资源' ROW_FORMAT = Dynamic;";
        TableNameParser tableNameParser = new TableNameParser(sql);
        tableNameParser.tables().forEach(System.out::println);
    }

    @Test
    public void testBuildTree() {
        List<Area> list = new ArrayList<>();

        Area a1 = Area.builder().id(1L).parentId(0L).build();
        Area a2 = Area.builder().id(2L).parentId(1L).build();
        Area a3 = Area.builder().id(3L).parentId(2L).build();
        Area a4 = Area.builder().id(4L).parentId(3L).build();
        Area a5 = Area.builder().id(5L).parentId(2L).build();
        Area a6 = Area.builder().id(6L).parentId(0L).build();
        Area a7 = Area.builder().id(7L).parentId(6L).build();
        Area a8 = Area.builder().id(8L).parentId(7L).build();

        list.add(a1);
        list.add(a2);
        list.add(a3);
        list.add(a4);
        list.add(a5);
        list.add(a6);
        list.add(a7);
        list.add(a8);

        List<Area> tree = TreeUtil.buildTree(list);
        System.out.println(tree);
    }


    private static Field getField(Class<?> clazz, String fieldName) {
        if (clazz == null) {
            return null;
        }
        try {
            Field declaredField = clazz.getDeclaredField(fieldName);
            if (declaredField != null) {
                return declaredField;
            }

        } catch (NoSuchFieldException e) {
            Class<?> superclass = clazz.getSuperclass();
            if (superclass != null) {
                return getField(superclass, fieldName);
            }
        }

        return null;
    }

    @Test
    public void test2222() {
        Map<InjectionFieldPo, String> map = new HashedMap();

        InjectionFieldPo a = new InjectionFieldPo();
        a.setApi("aa");
        a.setMethod("bb");
        a.setKey("vv");

        InjectionFieldPo b = new InjectionFieldPo();
        b.setApi("aa");
        b.setMethod("bb");
        b.setKey("aaaa");

        map.put(a, "1");
        map.put(b, "2");

        System.out.println(map);
    }

    @Test
    public void testLog() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            Log log = LogFactory.get();
            log.error("我是错误消息", e);
        }
    }

    @Test
    public void testSlfLog() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            log.error("我是slf错误消息", e);
        }
    }


    public Validator getValidator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                //快速失败返回模式
                .addProperty("hibernate.validator.fail_fast", "false")
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

    @Test
    public void test2() {
        JwtUserInfo jwtInfo = new JwtUserInfo();
        jwtInfo.setAccount("111");
        jwtInfo.setName("123");
        jwtInfo.setOrgId(1L);
        jwtInfo.setStationId(1L);
        jwtInfo.setUserId(1L);
        Token token = JwtHelper.generateUserToken(jwtInfo, "client/pub.key", 2700);
        log.info("{}", token);
    }
}

