package com.github.zuihou.database.mybatis.conditions.query;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.SharedString;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
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
 * Entity 对象封装操作类
 * <p>
 * 相比 QueryWrapper 的增强如下：
 * 1，new QueryWrapper(T entity)时， 对entity 中的string字段 %和_ 符号进行转义，便于模糊查询
 * 2，对nested、eq、ne、gt、ge、lt、le、in、*like*、 等方法 进行条件判断，null 或 "" 字段不加入查询
 * 3，对*like*相关方法的参数 %和_ 符号进行转义，便于模糊查询
 * 4，增加 leFooter 方法， 将日期参数值，强制转换成当天 23：59：59
 * 5，增加 geHeader 方法， 将日期参数值，强制转换成当天 00：00：00
 *
 * @author hubin miemie HCL
 * @since 2018-05-25
 */
@SuppressWarnings("serial")
public class QueryWrap<T> extends AbstractWrapper<T, String, QueryWrap<T>>
        implements Query<QueryWrap<T>, T, String> {
    private boolean skipEmpty = true;
    /**
     * 查询字段
     */
    private SharedString sqlSelect = new SharedString();

    public QueryWrap() {
        this(null);
    }

    public QueryWrap(T entity) {
        super.setEntity(entity);
        super.initNeed();
        //覆盖之前的entity
        if (entity != null) {
            super.setEntity(replace(BeanUtil.toBean(entity, getEntityClass())));
        }
    }

    public QueryWrap(T entity, String... columns) {
        super.setEntity(entity);
        super.initNeed();
        this.select(columns);
        //覆盖之前的entity
        if (entity != null) {
            super.setEntity(replace(BeanUtil.toBean(entity, getEntityClass())));
        }
    }

    /**
     * 非对外公开的构造方法,只用于生产嵌套 sql
     *
     * @param entityClass 本不应该需要的
     */
    private QueryWrap(T entity, Class<T> entityClass, AtomicInteger paramNameSeq,
                      Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments,
                      SharedString lastSql, SharedString sqlComment, SharedString sqlFirst) {
        super.setEntity(entity);
        super.setEntityClass(entityClass);
        this.paramNameSeq = paramNameSeq;
        this.paramNameValuePairs = paramNameValuePairs;
        this.expression = mergeSegments;
        this.lastSql = lastSql;
        this.sqlComment = sqlComment;
        this.sqlFirst = sqlFirst;
    }

    @Override
    public QueryWrap<T> select(String... columns) {
        if (ArrayUtils.isNotEmpty(columns)) {
            this.sqlSelect.setStringValue(String.join(StringPool.COMMA, columns));
        }
        return typedThis;
    }

    @Override
    public QueryWrap<T> select(Class<T> entityClass, Predicate<TableFieldInfo> predicate) {
        super.setEntityClass(entityClass);
        this.sqlSelect.setStringValue(TableInfoHelper.getTableInfo(getEntityClass()).chooseSelect(predicate));
        return typedThis;
    }

    @Override
    public String getSqlSelect() {
        return sqlSelect.getStringValue();
    }

    /**
     * 返回一个支持 lambda 函数写法的 wrapper
     */
    public LbqWrapper<T> lambda() {
        return new LbqWrapper<>(getEntity(), getEntityClass(), sqlSelect, paramNameSeq, paramNameValuePairs,
                expression, lastSql, sqlComment, sqlFirst);
    }

    /**
     * 用于生成嵌套 sql
     * <p>
     * 故 sqlSelect 不向下传递
     * </p>
     */
    @Override
    protected QueryWrap<T> instance() {
        return new QueryWrap<>(getEntity(), getEntityClass(), paramNameSeq, paramNameValuePairs, new MergeSegments(),
                SharedString.emptyString(), SharedString.emptyString(), SharedString.emptyString());
    }

    @Override
    public void clear() {
        super.clear();
        sqlSelect.toNull();
    }

    @Override
    public QueryWrap<T> nested(Consumer<QueryWrap<T>> consumer) {
        final QueryWrap<T> instance = instance();
        consumer.accept(instance);
        if (!instance.isEmptyOfWhere()) {
            return doIt(true, BRACKET, instance);
        }
        return this;
    }

    @Override
    public QueryWrap<T> eq(String column, Object val) {
        return super.eq(this.checkCondition(val), column, val);
    }

    @Override
    public QueryWrap<T> ne(String column, Object val) {
        return super.ne(this.checkCondition(val), column, val);
    }

    @Override
    public QueryWrap<T> gt(String column, Object val) {
        return super.gt(this.checkCondition(val), column, val);
    }

    @Override
    public QueryWrap<T> ge(String column, Object val) {
        return super.ge(this.checkCondition(val), column, val);
    }

    public QueryWrap<T> geHeader(String column, LocalDateTime val) {
        if (val != null) {
            val = LocalDateTime.of(val.toLocalDate(), LocalTime.MIN);
        }
        return super.ge(this.checkCondition(val), column, val);
    }

    public QueryWrap<T> geHeader(String column, LocalDate val) {
        LocalDateTime dateTime = null;
        if (val != null) {
            dateTime = LocalDateTime.of(val, LocalTime.MIN);
        }
        return super.ge(this.checkCondition(val), column, val);
    }


    @Override
    public QueryWrap<T> lt(String column, Object val) {
        return super.lt(this.checkCondition(val), column, val);
    }

    @Override
    public QueryWrap<T> le(String column, Object val) {
        return super.le(this.checkCondition(val), column, val);
    }

    public QueryWrap<T> leFooter(String column, LocalDateTime val) {
        if (val != null) {
            val = LocalDateTime.of(val.toLocalDate(), LocalTime.MAX);
        }
        return super.le(this.checkCondition(val), column, val);
    }

    public QueryWrap<T> leFooter(String column, LocalDate val) {
        LocalDateTime dateTime = null;
        if (val != null) {
            dateTime = LocalDateTime.of(val, LocalTime.MAX);
        }
        return super.le(this.checkCondition(val), column, dateTime);
    }

    @Override
    public QueryWrap<T> between(String column, Object val1, Object val2) {
        return super.between(val1 != null && val2 != null, column, val1, val2);
    }

    @Override
    public QueryWrap<T> notBetween(String column, Object val1, Object val2) {
        return super.notBetween(val1 != null && val2 != null, column, val1, val2);
    }

    @Override
    public QueryWrap<T> like(String column, Object val) {
        return super.like(this.checkCondition(val), column, val);
    }

    @Override
    public QueryWrap<T> notLike(String column, Object val) {
        return super.notLike(this.checkCondition(val), column, val);
    }

    @Override
    public QueryWrap<T> likeLeft(String column, Object val) {
        return super.likeLeft(this.checkCondition(val), column, val);
    }

    @Override
    public QueryWrap<T> likeRight(String column, Object val) {
        return super.likeRight(this.checkCondition(val), column, val);
    }

    @Override
    public QueryWrap<T> in(String column, Collection<?> coll) {
        return super.in(coll != null && !coll.isEmpty(), column, coll);
    }

    @Override
    public QueryWrap<T> in(String column, Object... values) {
        return super.in(values != null && values.length > 0, column, values);
    }


    //----------------以下为自定义方法---------

    /**
     * 取消跳过空的字符串  不允许跳过空的字符串
     *
     * @return
     */
    public QueryWrap<T> cancelSkipEmpty() {
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
    public <A extends Object> QueryWrap<T> ignore(BiFunction<T, A, ?> setColumn) {
        setColumn.apply(this.getEntity(), null);
        return this;
    }
}
