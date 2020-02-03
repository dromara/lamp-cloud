package com.github.zuihou.database.datasource;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.zuihou.base.entity.Entity;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.context.BaseContextHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * MyBatis Plus 元数据处理类
 * 用于自动 注入 id, createTime, updateTime, createUser, updateUser 等字段
 *
 * @author zuihou
 * @date 2019/04/29
 */
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * id类型判断符
     */
    private final static String ID_TYPE = "java.lang.String";

    private long workerId;
    private long dataCenterId;

    public MyMetaObjectHandler(long workerId, long dataCenterId) {
        super();
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 注意：不支持 复合主键 自动注入！！
     * <p>
     * 所有的继承了Entity、SuperEntity的实体，在insert时，
     * id： id为空时， 通过IdGenerate生成唯一ID， 不为空则使用传递进来的id
     * createUser, updateUser: 自动赋予 当前线程上的登录人id
     * createTime, updateTime: 自动赋予 服务器的当前时间
     * <p>
     * 未继承任何父类的实体，且主键标注了 @TableId(value = "xxx", type = IdType.INPUT) 自动注入 主键
     * 主键的字段名称任意
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        boolean flag = true;
        // 设置创建时间和创建人
        if (metaObject.getOriginalObject() instanceof SuperEntity) {
            Object oldId = ((SuperEntity) metaObject.getOriginalObject()).getId();
            if (oldId != null) {
                flag = false;
            }

            SuperEntity entity = (SuperEntity) metaObject.getOriginalObject();
            if (entity.getCreateTime() == null) {
                this.setFieldValByName(Entity.CREATE_TIME, LocalDateTime.now(), metaObject);
            }
            if (entity.getCreateUser() == null || entity.getCreateUser().equals(0)) {
                Object userIdVal = ID_TYPE.equals(metaObject.getGetterType(SuperEntity.CREATE_USER).getName()) ? String.valueOf(BaseContextHandler.getUserId()) : BaseContextHandler.getUserId();
                this.setFieldValByName(Entity.CREATE_USER, userIdVal, metaObject);
            }
        }

        // 修改人 修改时间
        if (metaObject.getOriginalObject() instanceof Entity) {
            Entity entity = (Entity) metaObject.getOriginalObject();
            update(metaObject, entity);
        }

        // 若 ID 中有值，就不设置
        if (!flag) {
            return;
        }
        Snowflake snowflake = IdUtil.getSnowflake(workerId, dataCenterId);
        Long id = snowflake.nextId();
        if (metaObject.hasGetter(SuperEntity.FIELD_ID)) {
            Object idVal = ID_TYPE.equals(metaObject.getGetterType(SuperEntity.FIELD_ID).getName()) ? String.valueOf(id) : id;
            this.setFieldValByName(SuperEntity.FIELD_ID, idVal, metaObject);
            return;
        }

        // 实体没有继承 Entity 和 SuperEntity
        TableInfo tableInfo = metaObject.hasGetter(Constants.MP_OPTLOCK_ET_ORIGINAL) ?
                TableInfoHelper.getTableInfo(metaObject.getValue(Constants.MP_OPTLOCK_ET_ORIGINAL).getClass())
                : TableInfoHelper.getTableInfo(metaObject.getOriginalObject().getClass());
        if (tableInfo == null) {
            return;
        }
        // 主键类型
        Class<?> keyType = tableInfo.getKeyType();
        if (keyType == null) {
            return;
        }
        // id 字段名
        String keyProperty = tableInfo.getKeyProperty();

        // 反射得到 主键的值
        Field idField = ReflectUtil.getField(metaObject.getOriginalObject().getClass(), keyProperty);
        Object fieldValue = ReflectUtil.getFieldValue(metaObject.getOriginalObject(), idField);
        // 判断ID 是否有值，有值就不
        if (ObjectUtil.isNotEmpty(fieldValue)) {
            return;
        }
        Object idVal = keyType.getName().equalsIgnoreCase(ID_TYPE) ? String.valueOf(id) : id;
        this.setFieldValByName(keyProperty, idVal, metaObject);
    }

    private void update(MetaObject metaObject, Entity entity, String et) {
        if (entity.getUpdateUser() == null || entity.getUpdateUser().equals(0)) {
            Object userIdVal = ID_TYPE.equals(metaObject.getGetterType(et + Entity.UPDATE_USER).getName()) ? String.valueOf(BaseContextHandler.getUserId()) : BaseContextHandler.getUserId();
            this.setFieldValByName(Entity.UPDATE_USER, BaseContextHandler.getUserId(), metaObject);
        }
        if (entity.getUpdateTime() == null) {
            this.setFieldValByName(Entity.UPDATE_TIME, LocalDateTime.now(), metaObject);
        }
    }

    private void update(MetaObject metaObject, Entity entity) {
        update(metaObject, entity, "");
    }

    /**
     * 所有的继承了Entity、SuperEntity的实体，在update时，
     * updateUser: 自动赋予 当前线程上的登录人id
     * updateTime: 自动赋予 服务器的当前时间
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("start update fill ....");
        if (metaObject.getOriginalObject() instanceof Entity) {
            Entity entity = (Entity) metaObject.getOriginalObject();
            update(metaObject, entity);
        } else {
            //updateById updateBatchById update(T entity, Wrapper<T> updateWrapper);
            Object et = metaObject.getValue(Constants.ENTITY);
            if (et != null && et instanceof Entity) {
                Entity entity = (Entity) et;
                update(metaObject, entity, Constants.ENTITY + ".");
            }
        }
    }
}
