package com.github.zuihou.base.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zuihou
 * @createTime 2017-12-08 22:12
 */
public abstract class CommonBaseEntity<I extends Serializable> extends BaseEntity<I>{

    public abstract void setCreateTime(Date nowDate);
    public abstract void setUpdateTime(Date nowDate);
    public abstract String getAppId();
    public abstract void setAppId(String appId);
}