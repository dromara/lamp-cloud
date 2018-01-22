package com.github.zuihou.admin.rest.account.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zuihou
 * @createTime 2018-01-02 15:44
 */
@Data
public class ApplicationsUpdateDto extends BaseApplicationsDto implements Serializable {
    private Long id;

}
