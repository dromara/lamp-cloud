package com.github.zuihou.admin.rest.account.dto;

import lombok.Data;

/**
 * @author zuihou
 * @createTime 2018-01-02 15:44
 */
@Data
public abstract class BaseApplicationsDto {
    /**
     * 应用名称
     *
     * @mbggenerated
     */
    private String appName;

    /**
     * 应用url
     *
     * @mbggenerated
     */
    private String url;

    /**
     * 应用logo
     *
     * @mbggenerated
     */
    private String logoUrl;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String comment;

    /**
     * 排序字段
     *
     * @mbggenerated
     */
    private Integer orderNum;
    /**
     * 是否启用
     *
     * @mbggenerated
     */
    private Boolean isEnable;

}
