package com.github.zuihou;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.github.zuihou.auth.utils.JwtHelper;
import com.github.zuihou.auth.utils.JwtUserInfo;
import com.github.zuihou.auth.utils.Token;
import com.github.zuihou.authority.entity.common.Area;
import com.github.zuihou.database.parsers.TableNameParser;
import com.github.zuihou.injection.core.InjectionFieldPo;
import com.github.zuihou.utils.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.hibernate.validator.HibernateValidator;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Field;
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

