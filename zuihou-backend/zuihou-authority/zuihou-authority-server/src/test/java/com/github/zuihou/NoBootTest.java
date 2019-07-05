package com.github.zuihou;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import com.github.zuihou.authority.entity.auth.User;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/07/05
 */
public class NoBootTest {


    public static void main(String[] args) {

        Test<User> t = new Test<>(User.builder().name("aaaa").build());

        User target = User.builder().name("aa%aa").build();
        User source = new User();


    }


    static class Test<E> {
        protected Class<E> entityClass;
        E entity;

        public Test(E entity) {
            this.entity = entity;
            if (this.entityClass == null && this.entity != null) {
                this.entityClass = (Class<E>) entity.getClass();
            }
            E entity2 = entity;
            getObjectByCompareWith(entity, entity2);

        }

        /**
         * 比较用目标对象替换源对象的值
         *
         * @param source 源对象
         * @param target 目标对象
         * @return 最新源对象
         * @see
         */
        public static Object getObjectByCompareWith(Object source, Object target) {
            Class<?> srcClass = source.getClass();
            Field[] fields = srcClass.getDeclaredFields();
            for (Field field : fields) {
                String nameKey = field.getName();
                //获取源对象中的属性值
                String srcValue = getClassValue(source, nameKey) == null ? "" : getClassValue(source, nameKey).toString();
                //获取目标对象中的属性值
                String tarValue = getClassValue(target, nameKey) == null ? "" : getClassValue(target, nameKey).toString();
                //比较两个属性值，不相等的时候进行赋值
                if (!srcValue.equals(tarValue)) {
                    source = setClassValue(source, nameKey, tarValue);
                }
            }
            return source;
        }

        /**
         * 给对象的字段赋指定的值
         *
         * @param obj       指定对象
         * @param fieldName 属性
         * @param value     值
         * @return
         * @see
         */
        public static Object setClassValue(Object obj, String fieldName, Object value) {
            if (obj == null) {
                return null;
            }
            if ("null".equals(value)) {
                value = null;
            }
            Class beanClass = obj.getClass();
            Method[] ms = beanClass.getMethods();
            for (int i = 0; i < ms.length; i++) {
                try {
                    if (ms[i].getName().startsWith("set")) {
                        if (ms[i].getName().toUpperCase().equals(fieldName.toUpperCase())
                                || ms[i].getName().substring(3).toUpperCase().equals(fieldName.toUpperCase())) {
                            String pt = ms[i].getParameterTypes()[0].toString();
                            if (value != null) {
                                ms[i].invoke(obj, transVal(value.toString(), pt.substring(pt.lastIndexOf(".") + 1)));
                            } else {
                                ms[i].invoke(obj, new Object[]{null});
                            }
                            break;
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            return obj;
        }


        /**
         * 根据属性类型赋值
         *
         * @param value      值
         * @param paramsType 属性类型
         * @return
         * @see
         */
        public static Object transVal(String value, String paramsType) {
            if (ColumnType.String.toString().equals(paramsType)) {
                return new String(value);
            }
            if (ColumnType.Double.toString().equals(paramsType)) {
                return Double.parseDouble(value);
            }
            if (ColumnType.Integer.toString().equals(paramsType)) {
                return Integer.parseInt(value);
            }
            if (ColumnType.Long.toString().equals(paramsType)) {
                return Long.parseLong(value);
            }
            if (ColumnType.BigDecimal.toString().equals(paramsType)) {
                return new BigDecimal(value);
            }
            return value;
        }

        /**
         * 根据字段名称取值
         *
         * @param obj       指定对象
         * @param fieldName 字段名称
         * @return 指定对象
         */
        public static Object getClassValue(Object obj, String fieldName) {
            if (obj == null) {
                return null;
            }
            Class beanClass = obj.getClass();
            Method[] ms = beanClass.getMethods();
            for (int i = 0; i < ms.length; i++) {
                // 非get方法不取
                if (!ms[i].getName().startsWith("get")) {
                    continue;
                }
                Object objValue = null;
                try {
                    objValue = ms[i].invoke(obj, new Object[]{});
                } catch (Exception e) {
                    continue;
                }
                if (objValue == null) {
                    continue;
                }
                if (ms[i].getName().toUpperCase().equals(fieldName.toUpperCase())
                        || ms[i].getName().substring(3).toUpperCase().equals(fieldName.toUpperCase())) {
                    return objValue;
                }
            }
            return null;
        }

        static enum ColumnType {
            String, Double, Integer, Long, BigDecimal;
        }


    }


}
