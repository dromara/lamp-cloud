package com.github.zuihou.base.service.impl;


import com.github.zuihou.base.dao.BaseDao;
import com.github.zuihou.base.entity.BaseEntity;
import com.github.zuihou.base.entity.CommonBaseEntity;
import com.github.zuihou.base.id.IdGenerate;
import com.github.zuihou.base.service.BaseService;
import com.github.zuihou.example.BaseExample;
import com.github.zuihou.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author zuihou
 * @createTime 2017-12-08 17:33
 */
public abstract class BaseServiceImpl<I extends Serializable, T extends BaseEntity<I>, TE extends BaseExample>
        implements BaseService<I, T, TE> {

    @Autowired
    private IdGenerate<I> idGenerator;

    protected abstract BaseDao<I, T, TE> getDao();

    @Override
    public final I getId() {
        return idGenerator.generate();
    }


    @Override
    public T save(T entity) {
        if (entity == null) {
            throw new RuntimeException("不允许保存空对象");
        }
        setSaveTimes(entity);
        entity.setId(getId());
        getDao().insert(entity);
        return entity;
    }

    @Override
    public Collection<T> save(Collection<T> list) {
        if (list == null) {
            throw new RuntimeException("list is null");
        }
        for (T t : list) {
            save(t);
        }
        return list;
    }

    @Override
    public T saveSelective(T entity) {
        if (entity == null) {
            throw new RuntimeException("不允许保存空对象");
        }
        setSaveTimes(entity);
        entity.setId(getId());
        getDao().insertSelective(entity);
        return entity;
    }

    private void setSaveTimes(T entity) {
        if (entity instanceof CommonBaseEntity) {
            CommonBaseEntity<I> t = (CommonBaseEntity<I>) entity;
            Date nowDate = new Date();
            t.setCreateTime(nowDate);
            t.setUpdateTime(nowDate);
        }
    }

    private void setUpdateTimes(T entity) {
        if (entity instanceof CommonBaseEntity) {
            CommonBaseEntity<I> t = (CommonBaseEntity<I>) entity;
            Date nowDate = new Date();
            t.setUpdateTime(nowDate);
        }
    }

    @Override
    public T getByAppIdAndId(String appId, I id) {
        if (appId == null || appId.isEmpty() || id == null) {
            return null;
        }
        return getDao().selectByAppIdAndId(appId, id);
    }

    @Override
    public T getUnique(TE example) {
        if (example == null) {
            return null;
        }
        return getDao().selectEntity(example);
    }

    @Override
    public List<T> find(TE example) {
        return getDao().selectByExample(example);
    }



    @Override
    public int count(TE example) {
        return getDao().countByExample(example);
    }

    @Override
    public int updateByAppIdAndId(String appId, T entity) {
        if (appId == null || appId.isEmpty() || entity == null) {
            return 0;
        }
        setUpdateTimes(entity);
        if (entity instanceof CommonBaseEntity) {
            CommonBaseEntity<I> baseEntity = (CommonBaseEntity<I>) entity;
            baseEntity.setAppId(appId);
        }
        return getDao().updateByAppIdAndId(entity);
    }

    @Override
    public int updateByAppIdAndId(String appId, Collection<T> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        int rows = 0;
        for (T t : list) {
            rows += updateByAppIdAndId(appId, t);
        }
        return rows;
    }

    @Override
    public int updateByAppIdAndIdSelective(String appId, T entity) {
        if (entity == null) {
            return 0;
        }
        setUpdateTimes(entity);
        if (entity instanceof CommonBaseEntity) {
            CommonBaseEntity<I> baseEntity = (CommonBaseEntity<I>) entity;
            baseEntity.setAppId(appId);
        }
        return getDao().updateByAppIdAndIdSelective(entity);
    }

    @Override
    public int updateByAppIdAndIdSelective(String appId, Collection<T> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        int rows = 0;
        for (T t : list) {
            rows += updateByAppIdAndIdSelective(appId, t);
        }
        return rows;
    }

    @Override
    public int deleteByAppIdAndId(String appId, I id) {
        return getDao().deleteByAppIdAndId(appId, id);
    }

    @Override
    public int deleteByAppIdAndIds(String appId, Collection<I> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return getDao().deleteByAppIdAndIds(appId, list);
    }

    @Override
    public int removeByAppIdAndId(String appId, I id) {
        try {
            return getDao().removeByAppIdAndId(appId, id);
        } catch (Exception e) {
            throw new BizException(10000, "无法软删除!");
        }
    }

    @Override
    public int removeByAppIdAndIds(String appId, Collection<I> ids) {
        try {
            return getDao().removeByAppIdAndIds(appId, ids);
        } catch (Exception e) {
            throw new BizException(10000, "无法软删除!");
        }
    }


}
