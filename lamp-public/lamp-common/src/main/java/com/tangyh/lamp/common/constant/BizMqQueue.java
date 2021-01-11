package com.tangyh.lamp.common.constant;

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
    String TENANT_DS_FANOUT_EXCHANGE_OAUTH = "tenant_ds_fe_oauth";
    /**
     * 租户数据源 广播
     */
    String TENANT_DS_FANOUT_EXCHANGE_AUTHORITY = "tenant_ds_fe_authority";
    /**
     * 租户数据源 广播
     */
    String TENANT_DS_FANOUT_EXCHANGE_MSG = "tenant_ds_fe_msg";
    /**
     * 租户数据源 广播
     */
    String TENANT_DS_FANOUT_EXCHANGE_DEMO = "tenant_ds_fe_demo";
    /**
     * 租户数据源 广播
     */
    String TENANT_DS_FANOUT_EXCHANGE_ORDER = "tenant_ds_fe_order";
    /**
     * 租户数据源 广播
     */
    String TENANT_DS_FANOUT_EXCHANGE_FILE = "tenant_ds_fe_file";
    /**
     * 租户数据源 广播
     */
    String TENANT_DS_FANOUT_EXCHANGE_BASE_EXECUTOR = "tenant_ds_fe_base_executor";
    String TENANT_DS_FANOUT_EXCHANGE_EXTEND_EXECUTOR = "tenant_ds_fe_extend_executor";
    /**
     * 租户数据源 广播
     */
    String TENANT_DS_FANOUT_EXCHANGE_GATEWAY = "tenant_ds_fe_geteway";

    /**
     * 租户数据源 队列 消费者
     */
    String TENANT_DS_QUEUE_BY_OAUTH = "tenant_ds_oauth";
    /**
     * 租户数据源 队列 消费者
     */
    String TENANT_DS_QUEUE_BY_BASE_EXECUTOR = "tenant_ds_base_executor";
    String TENANT_DS_QUEUE_BY_EXTEND_EXECUTOR = "tenant_ds_extend_executor";
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
    String TENANT_DS_QUEUE_BY_MSG = "tenant_ds_msg";
    /**
     * 租户数据源 队列 消费者
     */
    String TENANT_DS_QUEUE_BY_GATEWAY = "tenant_ds_gateway";
}
