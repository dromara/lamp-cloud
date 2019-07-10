package com.github.zuihou.database.mybatis.conditions.query;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import com.baomidou.mybatisplus.core.conditions.AbstractLambdaWrapper;
import com.baomidou.mybatisplus.core.conditions.SharedString;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.zuihou.database.mybatis.typehandler.BaseLikeTypeHandler;

/**
 * @author luosh
 * @date Created on 2019/5/27 17:11
 * @description 查询构造器
 */
public class LbqWrapper<T> extends AbstractLambdaWrapper<T, LbqWrapper<T>>
        implements Query<LbqWrapper<T>, T, SFunction<T, ?>> {

    private SharedString sqlSelect = new SharedString();

    public LbqWrapper() {
        this(null);
    }

    public LbqWrapper(T entity) {
        super.setEntity(entity);
        super.initNeed();
        this.entity = (T) replace(entity);
    }


    LbqWrapper(T entity, Class<T> entityClass, SharedString sqlSelect, AtomicInteger paramNameSeq,
               Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments) {
        super.setEntity(entity);
        this.paramNameSeq = paramNameSeq;
        this.paramNameValuePairs = paramNameValuePairs;
        this.expression = mergeSegments;
        this.sqlSelect = sqlSelect;
        this.entityClass = entityClass;
    }

    public static <T> LbqWrapper<T> lambdaQuery() {
        return new LbqWrapper<>();
    }

    /**
     * 空值校验
     * 传入空字符串("")时， 视为： 字段名 = ""
     *
     * @param val 参数值
     */
    private static boolean checkCondition(Object val) {
        return val != null;
    }

    @SafeVarargs
    @Override
    public final LbqWrapper<T> select(SFunction<T, ?>... columns) {
        if (ArrayUtils.isNotEmpty(columns)) {
            this.sqlSelect.setStringValue(columnsToString(false, columns));
        }
        return typedThis;
    }

    @Override
    public LbqWrapper<T> select(Predicate<TableFieldInfo> predicate) {
        return select(entityClass, predicate);
    }

    @Override
    public LbqWrapper<T> select(Class<T> entityClass, Predicate<TableFieldInfo> predicate) {
        this.entityClass = entityClass;
        this.sqlSelect.setStringValue(TableInfoHelper.getTableInfo(getCheckEntityClass()).chooseSelect(predicate));
        return typedThis;
    }

    @Override
    public String getSqlSelect() {
        return sqlSelect.getStringValue();
    }

    @Override
    protected LbqWrapper<T> instance() {
        return new LbqWrapper<>(entity, entityClass, null, paramNameSeq, paramNameValuePairs, new MergeSegments());
    }

    @Override
    public LbqWrapper<T> eq(SFunction<T, ?> column, Object val) {
        return super.eq(checkCondition(val), column, val);
    }

    /**
     * 当val为字符串时，val != null && val != "" 时，才拼接该条件
     * 当val为集合时，val != null && !val.isEmpty() 时，才拼接该条件
     *
     * @param column
     * @param val
     * @return
     */
    public LbqWrapper<T> eqNe(SFunction<T, ?> column, Object val) {
        boolean flag = checkCondition(val);
        if (flag && val instanceof String) {
            flag = !"".equals((String) val);
        }

        if (flag && val instanceof Collection) {
            flag = !((Collection) val).isEmpty();
        }
        return super.eq(flag, column, val);
    }

    @Override
    public LbqWrapper<T> ne(SFunction<T, ?> column, Object val) {
        return super.ne(checkCondition(val), column, val);
    }

    @Override
    public LbqWrapper<T> gt(SFunction<T, ?> column, Object val) {
        return super.gt(checkCondition(val), column, val);
    }

    @Override
    public LbqWrapper<T> ge(SFunction<T, ?> column, Object val) {
        return super.ge(checkCondition(val), column, val);
    }

    public LbqWrapper<T> geHeader(SFunction<T, ?> column, LocalDateTime val) {
        if (val != null) {
            val = LocalDateTime.of(val.toLocalDate(), LocalTime.MIN);
        }
        return super.ge(checkCondition(val), column, val);
    }

    public LbqWrapper<T> geHeader(SFunction<T, ?> column, LocalDate val) {
        LocalDateTime dateTime = null;
        if (val != null) {
            dateTime = LocalDateTime.of(val, LocalTime.MIN);
        }
        return super.ge(checkCondition(val), column, val);
    }

    @Override
    public LbqWrapper<T> lt(SFunction<T, ?> column, Object val) {
        return super.lt(checkCondition(val), column, val);
    }

    @Override
    public LbqWrapper<T> le(SFunction<T, ?> column, Object val) {
        return super.le(checkCondition(val), column, val);
    }

    public LbqWrapper<T> leFooter(SFunction<T, ?> column, LocalDateTime val) {
        if (val != null) {
            val = LocalDateTime.of(val.toLocalDate(), LocalTime.MAX);
        }
        return super.le(checkCondition(val), column, val);
    }

    public LbqWrapper<T> leFooter(SFunction<T, ?> column, LocalDate val) {
        LocalDateTime dateTime = null;
        if (val != null) {
            dateTime = LocalDateTime.of(val, LocalTime.MAX);
        }
        return super.le(checkCondition(val), column, dateTime);
    }

    @Override
    public LbqWrapper<T> like(SFunction<T, ?> column, Object val) {
        return super.like(checkCondition(val), column, BaseLikeTypeHandler.likeConvert(val));
    }

    @Override
    public LbqWrapper<T> notLike(SFunction<T, ?> column, Object val) {
        return super.notLike(checkCondition(val), column, BaseLikeTypeHandler.likeConvert(val));
    }

    @Override
    public LbqWrapper<T> likeLeft(SFunction<T, ?> column, Object val) {
        return super.likeLeft(checkCondition(val), column, BaseLikeTypeHandler.likeConvert(val));
    }

    @Override
    public LbqWrapper<T> likeRight(SFunction<T, ?> column, Object val) {
        return super.likeRight(checkCondition(val), column, BaseLikeTypeHandler.likeConvert(val));
    }

    @Override
    public LbqWrapper<T> in(SFunction<T, ?> column, Collection<?> coll) {
        return super.in(coll != null && !coll.isEmpty(), column, coll);
    }

    @Override
    public LbqWrapper<T> in(SFunction<T, ?> column, Object... values) {
        return super.in(values != null && values.length > 0, column, values);
    }

    //----------------以下为自定义方法---------


    /**
     * 比较用目标对象替换源对象的值
     *
     * @param source 源对象
     * @return 最新源对象
     * @see
     */
    public static Object replace(Object source) {
        if (source == null) {
            return null;
        }
        Object target = source;

        Class<?> srcClass = source.getClass();
        Field[] fields = srcClass.getDeclaredFields();
        for (Field field : fields) {
            String nameKey = field.getName();
            //获取源对象中的属性值
            String srcValue = getClassValue(source, nameKey) == null ? "" : getClassValue(source, nameKey).toString();
            //比较两个属性值，不相等的时候进行赋值
            if (srcValue.contains("%") || srcValue.contains("_")) {
                String tarValue = srcValue.replaceAll("\\%", "\\\\%");
                tarValue = tarValue.replaceAll("\\_", "\\\\_");
                setClassValue(target, nameKey, tarValue);
            }
        }
        return target;
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
            return value;
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

    enum ColumnType {
        String, Double, Integer, Long, BigDecimal;
    }


}
