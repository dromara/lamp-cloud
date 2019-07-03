package com.github.zuihou.database.datasource;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.github.zuihou.base.entity.Entity;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.base.id.IdGenerate;
import com.github.zuihou.context.BaseContextHandler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

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
    /**
     * 实体类型判断符
     */
    private final static String ET = "et";
    private final IdGenerate<Long> idGenerator;

    public MyMetaObjectHandler(IdGenerate<Long> idGenerator) {
        super();
        this.idGenerator = idGenerator;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");

        boolean flag = true;
        if (metaObject.getOriginalObject() instanceof SuperEntity) {
            Object oldId = ((SuperEntity) metaObject.getOriginalObject()).getId();
            if (oldId != null) {
                flag = false;
            }

            SuperEntity entity = (SuperEntity) metaObject.getOriginalObject();
            if (entity.getCreateTime() == null) {
                this.setFieldValByName(Entity.CREATE_TIME, LocalDateTime.now(), metaObject);
            }
            if (entity.getCreateUser() == null) {
                if (ID_TYPE.equals(metaObject.getGetterType(SuperEntity.CREATE_USER).getName())) {
                    this.setFieldValByName(Entity.CREATE_USER, String.valueOf(BaseContextHandler.getUserId()), metaObject);
                } else {
                    this.setFieldValByName(Entity.CREATE_USER, BaseContextHandler.getUserId(), metaObject);
                }
            }
        }

        if (flag) {
            Long id = idGenerator.generate();
            if (ID_TYPE.equals(metaObject.getGetterType(SuperEntity.ID).getName())) {
                this.setFieldValByName(SuperEntity.ID, String.valueOf(id), metaObject);
            } else {
                this.setFieldValByName(SuperEntity.ID, id, metaObject);
            }
        }

        if (metaObject.getOriginalObject() instanceof Entity) {
            Entity entity = (Entity) metaObject.getOriginalObject();
            update(metaObject, entity);
        }
    }

    private void update(MetaObject metaObject, Entity entity, String et) {
        if (entity.getUpdateUser() == null) {
            if (ID_TYPE.equals(metaObject.getGetterType(et + Entity.UPDATE_USER).getName())) {
                this.setFieldValByName(Entity.UPDATE_USER, String.valueOf(BaseContextHandler.getUserId()), metaObject);
            } else {
                this.setFieldValByName(Entity.UPDATE_USER, BaseContextHandler.getUserId(), metaObject);
            }
        }
        if (entity.getUpdateTime() == null) {
            this.setFieldValByName(Entity.UPDATE_TIME, LocalDateTime.now(), metaObject);
        }
    }

    private void update(MetaObject metaObject, Entity entity) {
        update(metaObject, entity, "");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        if (metaObject.getOriginalObject() instanceof Entity) {
            Entity entity = (Entity) metaObject.getOriginalObject();
            update(metaObject, entity);
        } else {
            //updateById updateBatchById update(T entity, Wrapper<T> updateWrapper);
            Object et = metaObject.getValue(ET);
            if (et != null && et instanceof Entity) {
                Entity entity = (Entity) et;
                update(metaObject, entity, ET + ".");
            }
        }
    }
}
