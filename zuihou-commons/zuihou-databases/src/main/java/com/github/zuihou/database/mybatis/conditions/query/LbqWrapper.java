package com.github.zuihou.database.mybatis.conditions.query;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.AbstractLambdaWrapper;
import com.baomidou.mybatisplus.core.conditions.SharedString;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.zuihou.database.mybatis.typehandler.BaseLikeTypeHandler;
import com.github.zuihou.model.RemoteData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static com.baomidou.mybatisplus.core.enums.WrapperKeyword.BRACKET;
import static com.github.zuihou.database.mybatis.conditions.Wraps.replace;


/**
 * 类似 LambdaQueryWrapper 的增强 Wrapper
 * <p>
 * 相比 LambdaQueryWrapper 的增强如下：
 * 1，new QueryWrapper(T entity)时， 对entity 中的string字段 %和_ 符号进行转义，便于模糊查询
 * 2，对nested、eq、ne、gt、ge、lt、le、in、*like*、 等方法 进行条件判断，null 或 "" 字段不加入查询
 * 3，对*like*相关方法的参数 %和_ 符号进行转义，便于模糊查询
 * 4，增加 leFooter 方法， 将日期参数值，强制转换成当天 23：59：59
 * 5，增加 geHeader 方法， 将日期参数值，强制转换成当天 00：00：00
 *
 * @author zuihou
 * @date Created on 2019/5/27 17:11
 * @description 查询构造器
 */
public class LbqWrapper<T> extends AbstractLambdaWrapper<T, LbqWrapper<T>>
        implements Query<LbqWrapper<T>, T, SFunction<T, ?>> {
    private static final long serialVersionUID = -6842140106034506889L;
    /**
     * 查询字段
     */
    private SharedString sqlSelect = new SharedString();

    /**
     * 是否跳过空值（zuihou项目扩展）
     */
    private boolean skipEmpty = true;

    /**
     * 不建议直接 new 该实例，使用 Wrappers.lambdaQuery(entity)
     */
    public LbqWrapper() {
        this((T) null);
    }

    /**
     * 不建议直接 new 该实例，使用 Wrappers.lambdaQuery(entity)
     */
    public LbqWrapper(T entity) {
        super.setEntity(entity);
        super.initNeed();
        //覆盖之前的entity
        if (entity != null) {
            super.setEntity(replace(BeanUtil.toBean(entity, getEntityClass())));
        }
//        if (entity instanceof SuperEntity) {
//            T cloneT = (T) ((SuperEntity) entity).clone();
//            super.setEntity(cloneT);
//            super.initNeed();
//            this.setEntity((T) replace(cloneT));
//        } else {
//            super.setEntity(entity);
//            super.initNeed();
//            this.setEntity((T) replace(entity));
//        }
    }

    /**
     * 不建议直接 new 该实例，使用 Wraps.lbQ(entity)
     */
    public LbqWrapper(Class<T> entityClass) {
        super.setEntityClass(entityClass);
        super.initNeed();
    }

    /**
     * 不建议直接 new 该实例，使用 Wrappers.lambdaQuery(...)
     */
    LbqWrapper(T entity, Class<T> entityClass, SharedString sqlSelect, AtomicInteger paramNameSeq,
               Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments,
               SharedString lastSql, SharedString sqlComment, SharedString sqlFirst) {
        super.setEntity(entity);
        super.setEntityClass(entityClass);
        this.paramNameSeq = paramNameSeq;
        this.paramNameValuePairs = paramNameValuePairs;
        this.expression = mergeSegments;
        this.sqlSelect = sqlSelect;
        this.lastSql = lastSql;
        this.sqlComment = sqlComment;
        this.sqlFirst = sqlFirst;
    }


    /**
     * SELECT 部分 SQL 设置
     *
     * @param columns 查询字段
     */
    @SafeVarargs
    @Override
    public final LbqWrapper<T> select(SFunction<T, ?>... columns) {
        if (ArrayUtils.isNotEmpty(columns)) {
            this.sqlSelect.setStringValue(columnsToString(false, columns));
        }
        return this.typedThis;
    }

    /**
     * 过滤查询的字段信息(主键除外!)
     * <p>例1: 只要 java 字段名以 "test" 开头的             -> select(i -&gt; i.getProperty().startsWith("test"))</p>
     * <p>例2: 只要 java 字段属性是 CharSequence 类型的     -> select(TableFieldInfo::isCharSequence)</p>
     * <p>例3: 只要 java 字段没有填充策略的                 -> select(i -&gt; i.getFieldFill() == FieldFill.DEFAULT)</p>
     * <p>例4: 要全部字段                                   -> select(i -&gt; true)</p>
     * <p>例5: 只要主键字段                                 -> select(i -&gt; false)</p>
     *
     * @param predicate 过滤方式
     * @return this
     */
    @Override
    public LbqWrapper<T> select(Class<T> entityClass, Predicate<TableFieldInfo> predicate) {
        super.setEntityClass(entityClass);
        this.sqlSelect.setStringValue(TableInfoHelper.getTableInfo(getEntityClass()).chooseSelect(predicate));
        return this.typedThis;
    }

    @Override
    public String getSqlSelect() {
        return this.sqlSelect.getStringValue();
    }


    /**
     * 用于生成嵌套 sql
     * <p>故 sqlSelect 不向下传递</p>
     */
    @Override
    protected LbqWrapper<T> instance() {
        return new LbqWrapper<>(getEntity(), getEntityClass(), null, paramNameSeq, paramNameValuePairs,
                new MergeSegments(), SharedString.emptyString(), SharedString.emptyString(), SharedString.emptyString());
    }

    @Override
    public void clear() {
        super.clear();
        sqlSelect.toNull();
    }

    @Override
    public LbqWrapper<T> nested(Consumer<LbqWrapper<T>> consumer) {
        final LbqWrapper<T> instance = instance();
        consumer.accept(instance);
        if (!instance.isEmptyOfWhere()) {
            return doIt(true, BRACKET, instance);
        }
        return this;
    }

    @Override
    public LbqWrapper<T> eq(SFunction<T, ?> column, Object val) {
        return super.eq(this.checkCondition(val), column, val);
    }

    @Override
    public LbqWrapper<T> ne(SFunction<T, ?> column, Object val) {
        return super.ne(this.checkCondition(val), column, val);
    }

    @Override
    public LbqWrapper<T> gt(SFunction<T, ?> column, Object val) {
        return super.gt(this.checkCondition(val), column, val);
    }

    @Override
    public LbqWrapper<T> ge(SFunction<T, ?> column, Object val) {
        return super.ge(this.checkCondition(val), column, val);
    }

    public LbqWrapper<T> geHeader(SFunction<T, ?> column, LocalDateTime val) {
        if (val != null) {
            val = LocalDateTime.of(val.toLocalDate(), LocalTime.MIN);
        }
        return super.ge(this.checkCondition(val), column, val);
    }

    public LbqWrapper<T> geHeader(SFunction<T, ?> column, LocalDate val) {
        LocalDateTime dateTime = null;
        if (val != null) {
            dateTime = LocalDateTime.of(val, LocalTime.MIN);
        }
        return super.ge(this.checkCondition(val), column, dateTime);
    }

    @Override
    public LbqWrapper<T> lt(SFunction<T, ?> column, Object val) {
        return super.lt(this.checkCondition(val), column, val);
    }

    @Override
    public LbqWrapper<T> le(SFunction<T, ?> column, Object val) {
        return super.le(this.checkCondition(val), column, val);
    }

    public LbqWrapper<T> leFooter(SFunction<T, ?> column, LocalDateTime val) {
        if (val != null) {
            val = LocalDateTime.of(val.toLocalDate(), LocalTime.MAX);
        }
        return super.le(this.checkCondition(val), column, val);
    }

    public LbqWrapper<T> leFooter(SFunction<T, ?> column, LocalDate val) {
        LocalDateTime dateTime = null;
        if (val != null) {
            dateTime = LocalDateTime.of(val, LocalTime.MAX);
        }
        return super.le(this.checkCondition(val), column, dateTime);
    }

    @Override
    public LbqWrapper<T> in(SFunction<T, ?> column, Collection<?> coll) {
        return super.in(coll != null && !coll.isEmpty(), column, coll);
    }

    @Override
    public LbqWrapper<T> in(SFunction<T, ?> column, Object... values) {
        return super.in(values != null && values.length > 0, column, values);
    }

    @Override
    public LbqWrapper<T> like(SFunction<T, ?> column, Object val) {
        return super.like(this.checkCondition(val), column, BaseLikeTypeHandler.likeConvert(val));
    }

    @Override
    public LbqWrapper<T> likeLeft(SFunction<T, ?> column, Object val) {
        return super.likeLeft(this.checkCondition(val), column, BaseLikeTypeHandler.likeConvert(val));
    }

    @Override
    public LbqWrapper<T> likeRight(SFunction<T, ?> column, Object val) {
        return super.likeRight(this.checkCondition(val), column, BaseLikeTypeHandler.likeConvert(val));
    }

    /**
     * 忽略实体中的某些字段，实体中的字段默认是会除了null以外的全部进行等值匹配
     * 再次可以进行忽略
     *
     * @param <T>    这个是传入的待忽略字段的set方法
     * @param column
     * @return
     */

    @Override
    public LbqWrapper<T> notLike(SFunction<T, ?> column, Object val) {
        return super.notLike(this.checkCondition(val), column, BaseLikeTypeHandler.likeConvert(val));
    }


    //----------------以下为自定义方法---------

    /**
     * 取消跳过空的字符串  不允许跳过空的字符串
     *
     * @return
     */
    public LbqWrapper<T> cancelSkipEmpty() {
        this.skipEmpty = false;
        return this;
    }

    /**
     * 空值校验
     * 传入空字符串("")时， 视为： 字段名 = ""
     *
     * @param val 参数值
     */
    private boolean checkCondition(Object val) {
        if (val instanceof String && this.skipEmpty) {
            return StringUtils.isNotBlank((String) val);
        }
        if (val instanceof Collection && this.skipEmpty) {
            return !((Collection) val).isEmpty();
        }
        if (val instanceof RemoteData && this.skipEmpty) {
            RemoteData value = (RemoteData) val;
            return ObjectUtil.isNotEmpty(value.getKey());
        }
        return val != null;
    }

    /**
     * 忽略实体中的某些字段，实体中的字段默认是会除了null以外的全部进行等值匹配
     * 再次可以进行忽略
     *
     * @param <A>       这个是传入的待忽略字段的set方法
     * @param setColumn
     * @return
     */
    public <A extends Object> LbqWrapper<T> ignore(BiFunction<T, A, ?> setColumn) {
        setColumn.apply(this.getEntity(), null);
        return this;
    }

//    /**
//     * 字段类型
//     */
//    enum ColumnType {
//        /**
//         * 类型
//         */
//        String,
//        /**
//         * 类型
//         */
//        Double,
//        /**
//         * 类型
//         */
//        Integer,
//        /**
//         * 类型
//         */
//        Long,
//        /**
//         * 类型
//         */
//        BigDecimal
//    }
//    /**
//     * 根据属性类型赋值
//     *
//     * @param value      值
//     * @param paramsType 属性类型
//     * @return
//     * @see
//     */
//    private static Object transVal(String value, String paramsType) {
//        if (ColumnType.String.toString().equals(paramsType)) {
//            return value;
//        }
//        if (ColumnType.Double.toString().equals(paramsType)) {
//            return Double.parseDouble(value);
//        }
//        if (ColumnType.Integer.toString().equals(paramsType)) {
//            return Integer.parseInt(value);
//        }
//        if (ColumnType.Long.toString().equals(paramsType)) {
//            return Long.parseLong(value);
//        }
//        if (ColumnType.BigDecimal.toString().equals(paramsType)) {
//            return new BigDecimal(value);
//        }
//        return value;
//    }
//    /**
//     * 比较用目标对象替换源对象的值
//     *
//     * @param source 源对象
//     * @return 最新源对象
//     * @see
//     */
//    public static <T> T replace(Object source) {
//        if (source == null) {
//            return null;
//        }
//        Object target = source;
//
//        Class<?> srcClass = source.getClass();
//        Field[] fields = srcClass.getDeclaredFields();
//        for (Field field : fields) {
//            String nameKey = field.getName();
//            //获取源对象中的属性值
//            Object classValue = getClassValue(source, nameKey);
//            if (classValue == null) {
//                continue;
//            }
//            String srcValue = classValue.toString();
//            //比较两个属性值，不相等的时候进行赋值
//            if (srcValue.contains("%") || srcValue.contains("_")) {
//                String tarValue = srcValue.replaceAll("\\%", "\\\\%");
//                tarValue = tarValue.replaceAll("\\_", "\\\\_");
//                setClassValue(target, nameKey, tarValue);
//            }
//        }
//        return (T) target;
//    }
//
//
//    /**
//     * 根据字段名称取值
//     *
//     * @param obj       指定对象
//     * @param fieldName 字段名称
//     * @return 指定对象
//     */
//    private static Object getClassValue(Object obj, String fieldName) {
//        if (obj == null) {
//            return null;
//        }
//        Class beanClass = obj.getClass();
//        Method[] ms = beanClass.getMethods();
//        for (int i = 0; i < ms.length; i++) {
//            // 非get方法不取
//            if (!ms[i].getName().startsWith("get")) {
//                continue;
//            }
//            if (!ms[i].getName().equalsIgnoreCase("get" + fieldName)) {
//                continue;
//            }
//            Object objValue = null;
//            try {
//                objValue = ms[i].invoke(obj, new Object[]{});
//            } catch (Exception e) {
//                continue;
//            }
//            if (objValue == null) {
//                continue;
//            }
//            if (ms[i].getName().toUpperCase().equals(fieldName.toUpperCase())
//                    || ms[i].getName().substring(3).toUpperCase().equals(fieldName.toUpperCase())) {
//                return objValue;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 给对象的字段赋指定的值
//     *
//     * @param obj       指定对象
//     * @param fieldName 属性
//     * @param value     值
//     * @return
//     * @see
//     */
//    private static Object setClassValue(Object obj, String fieldName, Object value) {
//        if (obj == null) {
//            return null;
//        }
//        if (StrPool.NULL.equals(value)) {
//            value = null;
//        }
//        Class beanClass = obj.getClass();
//        Method[] ms = beanClass.getMethods();
//        for (int i = 0; i < ms.length; i++) {
//            try {
//                if (ms[i].getName().startsWith("set")) {
//                    if (ms[i].getName().toUpperCase().equals(fieldName.toUpperCase())
//                            || ms[i].getName().substring(3).toUpperCase().equals(fieldName.toUpperCase())) {
//                        String pt = ms[i].getParameterTypes()[0].toString();
//                        if (value != null) {
//                            ms[i].invoke(obj, transVal(value.toString(), pt.substring(pt.lastIndexOf(".") + 1)));
//                        } else {
//                            ms[i].invoke(obj, new Object[]{null});
//                        }
//                        break;
//                    }
//                }
//            } catch (Exception e) {
//                continue;
//            }
//        }
//        return obj;
//    }

}
