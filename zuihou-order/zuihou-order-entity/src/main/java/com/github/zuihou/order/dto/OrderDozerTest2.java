package com.github.zuihou.order.dto;

import com.github.zuihou.model.RemoteData;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * 订单(用于测试)
 * </p>
 *
 * @author zuihou
 * @since 2020-06-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "OrderDozerTest2", description = "OrderDozerTest2")
public class OrderDozerTest2 implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private RemoteData<String, String> nation;
    private RemoteData<Long, String> orgId;
    private String code2;

    private String date;
    private LocalDateTime ldt2;
    private String ldtStr;


}
