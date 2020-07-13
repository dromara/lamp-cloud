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
import java.util.Date;

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
@ApiModel(value = "OrderDozerTest", description = "OrderDozerTest")
public class OrderDozerTest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private RemoteData<String, String> nation;
    private RemoteData<Long, String> orgId;
    private String code;

    private Date date;
    private LocalDateTime ldt;

    private OrderSaveDTO save;

}
