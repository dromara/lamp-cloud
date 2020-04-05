package com.github.zuihou.common.constant;

/**
 * 队列常量
 *
 * @author zuihou
 * @date 2020年04月05日15:44:03
 */
public interface BizMqQueue {

    /**
     * 租户数据源 广播
     */
    String TENANT_DS_FANOUT_EXCHANGE = "tenant_ds_fe";
    /**
     * 租户数据源 队列 消费者
     */
    String TENANT_DS_QUEUE_BY_OAUTH = "tenant_ds_oauth";
    /**
     * 租户数据源 队列 消费者
     */
    String TENANT_DS_QUEUE_BY_AUTHORITY = "tenant_ds_authority";
    /**
     * 租户数据源 队列 消费者
     */
    String TENANT_DS_QUEUE_BY_DEMO = "tenant_ds_demo";
    /**
     * 租户数据源 队列 消费者
     */
    String TENANT_DS_QUEUE_BY_ORDER = "tenant_ds_order";
    /**
     * 租户数据源 队列 消费者
     */
    String TENANT_DS_QUEUE_BY_FILE = "tenant_ds_file";
    /**
     * 租户数据源 队列 消费者
     */
    String TENANT_DS_QUEUE_BY_MSGS = "tenant_ds_msgs";
}
