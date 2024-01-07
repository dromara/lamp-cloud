package top.tangyh.lamp.common.constant;

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
    String TENANT_DS_FANOUT_EXCHANGE_BASE = "tenant_ds_fe_base";
    String TENANT_DS_FANOUT_EXCHANGE_SYSTEM = "tenant_ds_fe_system";
    /**
     * 租户数据源 广播
     */
    String TENANT_DS_FANOUT_EXCHANGE_BASE_EXECUTOR = "tenant_ds_fe_base_executor";
    String TENANT_DS_FANOUT_EXCHANGE_EXTEND_EXECUTOR = "tenant_ds_fe_extend_executor";
    /**
     * 租户数据源 广播
     */
    String TENANT_DS_FANOUT_EXCHANGE_GATEWAY = "tenant_ds_fe_gateway";

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
    String TENANT_DS_QUEUE_BY_BASE = "tenant_ds_base";
    /**
     * 租户数据源 队列 消费者
     */
    String TENANT_DS_QUEUE_BY_SYSTEM = "tenant_ds_system";
    /**
     * 租户数据源 队列 消费者
     */
    String TENANT_DS_QUEUE_BY_GATEWAY = "tenant_ds_gateway";
}
