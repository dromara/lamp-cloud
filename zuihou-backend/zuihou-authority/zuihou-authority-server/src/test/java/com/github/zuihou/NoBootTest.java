package com.github.zuihou;

import java.lang.reflect.Field;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.github.zuihou.auth.utils.JwtHelper;
import com.github.zuihou.auth.utils.JwtUserInfo;
import com.github.zuihou.auth.utils.Token;
import com.github.zuihou.authority.controller.test.model.ValidatorDTO;
import com.github.zuihou.authority.dto.auth.MenuTreeDTO;

import cn.hutool.core.util.ReflectUtil;
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
        String field = "name";
        System.out.println(getField(MenuTreeDTO.class, field));

        System.out.println(ReflectUtil.getField(MenuTreeDTO.class, field));

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
    public void test() {

        ValidatorDTO dto = new ValidatorDTO();
        dto.setAge(1L).setCode("1").setNotnull(1L).setEmail("q@q").setUrl("http:");
        Set<ConstraintViolation<ValidatorDTO>> validate = getValidator().validate(dto);
        for (ConstraintViolation<ValidatorDTO> c : validate) {
            log.info(c.getMessage());
        }
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

