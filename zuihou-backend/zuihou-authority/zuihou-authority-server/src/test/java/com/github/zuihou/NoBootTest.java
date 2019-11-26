package com.github.zuihou;

import java.lang.reflect.Field;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.github.zuihou.auth.utils.JwtHelper;
import com.github.zuihou.auth.utils.JwtUserInfo;
import com.github.zuihou.auth.utils.Token;
import com.github.zuihou.database.parsers.TableNameParser;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;
import org.junit.Test;

/**
 * 没有Spring 环境的测试工具类
 *
 * @author zuihou
 * @date 2019/07/05
 */
@Slf4j
public class NoBootTest {

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

    public static void main(String[] args) {
//        String field = "name";
//        System.out.println(getField(MenuTreeDTO.class, field));
//
//        System.out.println(ReflectUtil.getField(MenuTreeDTO.class, field));


        String sql ="       SELECT u.id, account, name, mobile, sex\n" +
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

