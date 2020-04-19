package com.github.zuihou.tenant.strategy;

import java.util.List;

/**
 * 初始化系统
 * <p>
 *
 * @author zuihou
 * @date 2019/10/25
 */
public interface InitSystemStrategy {
    /**
     * 初始化系统
     *
     * @param tenant
     */
    boolean init(String tenant);

    /**
     * 重置系统
     *
     * @param tenant
     */
    boolean reset(String tenant);

    /**
     * 删除租户数据
     *
     * @param tenantCodeList
     * @return
     */
    boolean delete(List<String> tenantCodeList);
}
